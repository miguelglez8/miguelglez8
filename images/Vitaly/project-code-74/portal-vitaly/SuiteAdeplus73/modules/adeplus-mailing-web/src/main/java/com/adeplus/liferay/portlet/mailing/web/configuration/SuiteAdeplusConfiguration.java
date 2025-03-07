package com.adeplus.liferay.portlet.mailing.web.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;



@ExtendedObjectClassDefinition(category = "adeplus")
@Meta.OCD(
        factory = true,
        id = "com.adeplus.liferay.portlet.mailing.web.configuration.SuiteAdeplusConfiguration",
        localization = "content/Language",
        name = "SuiteAdeplus parametros"
)
public interface SuiteAdeplusConfiguration {
    @Meta.AD(required = true,
            name ="Emails CC para envio de comunicaciones",
            description = "Indicar los emails para mandar la copia de carbon de los correos, separados por un coma (Ej: uno@sitio.es,dos@sitio.es)" )

    public String getEmailCC();
}
