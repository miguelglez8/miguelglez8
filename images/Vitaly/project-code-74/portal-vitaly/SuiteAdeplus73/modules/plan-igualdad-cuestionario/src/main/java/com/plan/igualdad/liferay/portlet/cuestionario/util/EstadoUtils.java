package com.plan.igualdad.liferay.portlet.cuestionario.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.plan.igualdad.liferay.portlet.manager.model.Pregunta;
import com.plan.igualdad.liferay.portlet.manager.service.PreguntaLocalServiceUtil;
import com.plan.igualdad.liferay.portlet.manager.service.persistence.PreguntaPK;

import java.util.List;

public class EstadoUtils {

    public static long getEstadoSeccion(Long seccionId, String respuestas) throws PortalException {

        List<Pregunta> preguntas = PreguntaLocalServiceUtil.findBySeccion(seccionId);
        for (Pregunta pregunta : preguntas) {

            JSONObject preguntaJSON = JSONFactoryUtil.createJSONObject(pregunta.getPregunta());
            JSONObject visible = (JSONObject) preguntaJSON.get("visible");

            if (Validator.isNotNull(visible)) {
                PreguntaPK preguntaPK = new PreguntaPK(visible.getLong("id"), seccionId);
                Pregunta preguntaVisible = PreguntaLocalServiceUtil.getPregunta(preguntaPK);

                String respuesta = getRespuesta(preguntaVisible, respuestas);
                if (!StringUtil.equals(respuesta, visible.getString("value")))
                    continue;
            }

            String respuesta = getRespuesta(pregunta, respuestas);
            if ( Validator.isBlank( respuesta ) ) return 0;
        }

        return 1;
    }

    private static String getRespuesta(Pregunta pregunta, String respuestas) throws JSONException {
        if (StringUtil.equals(pregunta.getTipo(), "textarea"))   return getRespuestaField(pregunta, respuestas);
        if (StringUtil.equals(pregunta.getTipo(), "radio"))      return getRespuestaField(pregunta, respuestas);
        if (StringUtil.equals(pregunta.getTipo(), "checkbox"))   return getRespuestaField(pregunta, respuestas);
        if (StringUtil.equals(pregunta.getTipo(), "table"))      return getRespuestaTable(pregunta, respuestas);
        if (StringUtil.equals(pregunta.getTipo(), "matrix"))     return getRespuestaMatrix(pregunta, respuestas);
        return StringPool.BLANK;
    }

    public static String getRespuestaField(Pregunta pregunta, String respuestas) throws JSONException {
        JSONArray arrayJSON = JSONFactoryUtil.createJSONArray(respuestas);
        for(int i=0; i < arrayJSON.length(); i++ ) {

            JSONObject respuestaJSON = arrayJSON.getJSONObject(i);
            Long id = respuestaJSON.getLong("name");

            if (id == pregunta.getPreguntaId()) {
                return (String) respuestaJSON.get("value");
            }

        }
        return StringPool.BLANK;
    }

    public static String getRespuestaTable(Pregunta pregunta, String respuestas) throws JSONException {
        JSONObject object = JSONFactoryUtil.createJSONObject(respuestas);
        JSONArray arrayJSON = object.getJSONArray("data");
        for(int i=0; i < arrayJSON.length(); i++ ) {

            JSONObject respuestaJSON = arrayJSON.getJSONObject(i);
            if ( Validator.isNull( respuestaJSON.getString("puesto")) ) {
                return StringPool.BLANK;
            }

        }
        return String.valueOf(pregunta.getPreguntaId());
    }

    public static String getRespuestaMatrix(Pregunta pregunta, String respuestas) throws JSONException {
        JSONObject matrix = JSONFactoryUtil.createJSONObject(pregunta.getPregunta());
        JSONArray rows = matrix.getJSONArray("rows");

        JSONArray arrayRespuestas = JSONFactoryUtil.createJSONArray(respuestas);
        for(int i=0; i < arrayRespuestas.length(); i++ ) {

            for(int j=0; j < rows.length(); j++ ) {
                for (int k=0; k < rows.getJSONArray(j).length(); k++) {

                    JSONObject preguntaJSON = rows.getJSONArray(j).getJSONObject(k);
                    String preguntaId = "matrix-" + preguntaJSON.getString("id");

                    JSONObject respuestaJSON = arrayRespuestas.getJSONObject(i);
                    String respuestaId = respuestaJSON.getString("name");

                    if (StringUtil.equals(preguntaId, respuestaId)) {
                        return (String) respuestaJSON.get("value");
                    }

                }
            }

        }
        return StringPool.BLANK;
    }

}
