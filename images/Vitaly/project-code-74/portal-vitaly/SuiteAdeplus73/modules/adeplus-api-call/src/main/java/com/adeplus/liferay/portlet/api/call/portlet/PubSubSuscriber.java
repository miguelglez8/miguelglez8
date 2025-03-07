package com.adeplus.liferay.portlet.api.call.portlet;


import com.adeplus.liferay.portlet.commons.web.company.AdeplusCompanyUtil;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.liferay.portal.kernel.backgroundtask.*;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplayFactoryUtil;
import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Component;

import java.io.InputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component(
        immediate = true,
        property = {"background.task.executor.class.name=com.adeplus.liferay.portlet.api.call.portlet.PubSubSuscriber"},
        // Without this property osgi will not register this as background executor/handler
        service = BackgroundTaskExecutor.class
)
public class PubSubSuscriber implements BackgroundTaskExecutor {
    public static Log _log = LogFactoryUtil.getLog(PubSubSuscriber.class);
    private final Lock lock = new ReentrantLock();

    @Override
    public BackgroundTaskExecutor clone() {
        return this;
    }

    @Override
    public BackgroundTaskResult execute(BackgroundTask backgroundTask) throws Exception {

        //if(true) return null;

        ClassLoader classLoader = this.getClass().getClassLoader();

        /*datos api PRE
        InputStream inputStream = classLoader.getResourceAsStream("/json_permisos_acceso_adeplus_new.json");

        String projectId = "pdemo-vitaly-erpgke";
        _log.info("projectId: "+projectId);
        String subscriptionId = "suite-adeplus-sub";
        _log.info("subscriptionId: "+subscriptionId);
        */

        //datos api PROD
        InputStream inputStream = classLoader.getResourceAsStream("/json_permisos_accesos_adeplus_prod.json");

        String projectId = "erp-gke-318611";
        _log.info("projectId: "+projectId);
        String subscriptionId = "suite-adeplus-sub";
        _log.info("subscriptionId: "+subscriptionId);


        // Carga las credenciales desde el archivo JSON
        ServiceAccountCredentials credentials = ServiceAccountCredentials.fromStream(inputStream);
        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);

        // Crea un cliente de suscriptor
        Subscriber subscriber = null;
        MessageReceiver receiver = (message, consumer) -> {
            // Procesa el mensaje aquí, necesitariamos algun dato por el cual podramos obtener los 3 tipos distintos de JSON

            lock.lock();
            try {

                String messageData=message.getData().toStringUtf8();
                _log.info("Mensaje recibido: " + message.getData().toStringUtf8());

                JSONObject jsonApi= JSONFactoryUtil.createJSONObject(messageData);
                //Aqui llamariamos a la creación y procesamiento de la api

                JSONObject dataCompanyAll=jsonApi.getJSONObject("company");
                JSONObject dataCompany=JSONFactoryUtil.createJSONObject();
                JSONObject dataUsuarios=JSONFactoryUtil.createJSONObject();
                JSONArray dataApplicaciones=jsonApi.getJSONArray("contracts");
                //Agregamos al JSON los datos de la empresa
                dataCompany.put("cif",dataCompanyAll.get("cif"));
                dataCompany.put("name",dataCompanyAll.get("name"));
                //Agregamos al JSON con los datos del usuario
                dataUsuarios.put("username",dataCompanyAll.get("userName"));
                dataUsuarios.put("lastName",dataCompanyAll.get("userLastName"));
                dataUsuarios.put("email",dataCompanyAll.get("email"));
                dataUsuarios.put("locale",dataCompanyAll.get("locale"));
                String typeEvent=jsonApi.getString("typeEvent");
                long idTemporal=AdeplusCompanyUtil.createTemporalData(typeEvent,dataCompany.toString(),dataApplicaciones.toString(),dataUsuarios.toString());
                AdeplusCompanyUtil.proccesTemporalData(idTemporal);

                //Aqui indicamos que hemos consumido el mensaje para que se elimine de la cola de pubsub
                consumer.ack();
            } catch (JSONException  e) {
                _log.error("error al parsear el Json");
                //con el consumer nack puede volver a intentarse mas tarde
                consumer.nack();
            } finally {
                lock.unlock();
            }


        };
        Thread.currentThread().setContextClassLoader(Subscriber.class.getClass().getClassLoader());
        ExecutorProvider executorProvider =
                InstantiatingExecutorProvider.newBuilder().setExecutorThreadCount(1).build();
        subscriber = Subscriber.newBuilder(subscriptionName, receiver).setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .setExecutorProvider(executorProvider).build();;

        // Inicia la escucha de mensajes
        subscriber.startAsync().awaitRunning();

        // Espera a que el suscriptor siga funcionando
        subscriber.awaitTerminated();

        return BackgroundTaskResult.SUCCESS;
    }

    @Override
    public String generateLockKey(BackgroundTask backgroundTask) {
        return null;
    }

    @Override
    public BackgroundTaskDisplay getBackgroundTaskDisplay(BackgroundTask backgroundTask) {
        return null;
    }

    @Override
    public BackgroundTaskStatusMessageTranslator getBackgroundTaskStatusMessageTranslator() {
        return null;
    }

    @Override
    public int getIsolationLevel() {
        return 0;
    }

    @Override
    public String handleException(BackgroundTask backgroundTask, Exception exception) {
        return null;
    }

    @Override
    public boolean isSerial() {
        return false;
    }


}
