package com.canal.etico.liferay.portlet.admin.denuncia.web.constants;

/**
 * @author agarciap
 */
public class AdminDenunciaPortletKeys {

	public static final String ADMINDENUNCIA =
		"com_canal_etico_liferay_portlet_admin_denuncia_web_AdminDenunciaPortlet";

	/* Params */
	public static final String USER_COMP_ID = "userCompId";
	public static final String DENUNCIAS = "denunciaList";
	public static final String CATEGORIAS = "categoriasList";
	public static final String DENUNCIA_ID = "denunciaId";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";

	public static final String CODIGO = "codigo";
	public static final String TIPO = "tipo";
	public static final String CATEGORIA = "categoria";
	public static final String ESTADO = "estado";

	public static final String ASUNTO = "asunto";
	public static final String DESCRIPTION = "description";
	public static final String OBSERVACIONES_ACCION = "observacionesAccion";
	public static final String OBSERVACIONES_COMUNICACION = "observacionesComunicacion";
	public static final String OBSERVACIONES_FINALIZACION = "observacionesFinalizacion";

    public static final String ACCION = "accion";
    public static final String COMUNICACION = "comunicacion";
    public static final String OBSERVACION = "observacion";
    public static final String ADJUNTO = "adjunto";
    public static final String MOTIVO = "motivoId";

    public static final String HAS_PERMISSION_IN_COMP = "hasPermissionComp";

	public static final String PARENT_FOLDER_NAME = "Documentos";
	public static final String COMUNICACION_ADJUNTO_DESCRIPTION = "Archivo adjunto para la comunicación ";

	/* Tipo consulta/denuncia */
	public static final long TIPO_CONSULTA = 0;
	public static final long TIPO_DENUNCIA = 1;

	/* Estados */
	public static final long ESTADO_REGISTRADO = 0;
	public static final long ESTADO_EN_PROCESO = 1;
	public static final long ESTADO_FINALIZADO = 2;
	public static final long ESTADO_SIN_FINALIZAR = 3;
}