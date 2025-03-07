package com.canal.etico.liferay.portlet.commons.web.constants;

/**
 * @author agarciap
 */
public class CanalEticoCommonsPortletKeys {

	public static final String CANALETICOCOMMONS =
		"com_canal_etico_liferay_portlet_commons_web_CanalEticoCommonsPortlet";

	public static final String PROPERTY_ROLE_COMPANY_ADMIN 	 = "canal.etico.rol.company.admin";
	public static final String PROPERTY_ROLE_COMPANY_USER	 = "canal.etico.rol.company.user";

	public static final String PROPERTY_ROLE_ADEPLUS_ADMIN   = "canal.etico.rol.adeplus.admin";

	/* Estados */
	public static final long ESTADO_REGISTRADO = 0;
	public static final long ESTADO_EN_PROCESO = 1;
	public static final long ESTADO_FINALIZADO = 2;
	public static final long ESTADO_SIN_FINALIZAR = 3;

	/* Propeties portal-ext.properties */
	public static final String PROPERTY_DAYS_AFTER_CREATE_TO_FINISH = "canal.etico.denuncia.days.after.create";
	public static final String PROPERTY_DAYS_AFTER_CREATE_ADVISE 	= "canal.etico.denuncia.days.after.create.advise";
	public static final String PROPERTY_DAYS_AFTER_FINISH_VIEW	 	= "canal.etico.denuncia.days.after.finish";

	public static final String PROPERTY_APPLICATION_CANAL_ETICO_NAME = "adeplus.application.name.canal.etico";

	//tipos de comunicaciones
	public static final int EMAIL_NUEVO_COMUNICADO = 0;
	public static final int EMAIL_NUEVA_INFORMACION = 1;
	public static final int EMAIL_PRORROGADO = 2;
	public static final int EMAIL_ASIGNAR_CONSULTOR = 3;

	public static final int EMAIL_CAMBIO_ESTADO = 4;
	public static final int EMAIL_FINALIZAR = 5;
	public static final int EMAIL_AVISO_CADUCIDAD = 6;
	public static final int EMAIL_COMUNICACION_CADUCADA = 7;

	public static final int EMAIL_COMUNICACION_PRORROGA = 8;


	public static final String NOMBRE_ROLE = "Acceso completo";

	public static boolean ITS_NEW_VERSION=Boolean.TRUE;


}