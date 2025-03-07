create table planigualdad_Area (
	areaId LONG not null,
	versionId LONG not null,
	compId LONG not null,
	nombre VARCHAR(75) null,
	baja DATE null,
	primary key (areaId, versionId, compId)
);

create table planigualdad_Cuestionario (
	cuestionarioId LONG not null primary key,
	compId LONG,
	versionId LONG,
	userId LONG,
	respuestasCuestionario TEXT null,
	observaciones TEXT null,
	versionCuestionario INTEGER,
	createDate DATE null
);

create table planigualdad_Estadistica (
	compId LONG not null,
	versionId LONG not null,
	seccionId LONG not null,
	estadistica TEXT null,
	primary key (compId, versionId, seccionId)
);

create table planigualdad_Estado (
	estadoId LONG not null primary key,
	nombre VARCHAR(75) null,
	activa BOOLEAN,
	gestionParametrizaciones BOOLEAN
);

create table planigualdad_Evaluacion (
	evaluacionId LONG not null primary key,
	compId LONG,
	versionId LONG,
	userId LONG,
	datosGenerales TEXT null,
	informacionResultados TEXT null,
	informacionImplantacion TEXT null,
	informacionImpacto TEXT null,
	conclusion VARCHAR(1000) null,
	observaciones VARCHAR(1000) null,
	versionEvaluacion INTEGER,
	createDate DATE null
);

create table planigualdad_FechaHito (
	hitoId LONG not null,
	versionId LONG not null,
	compId LONG not null,
	fecha DATE null,
	primary key (hitoId, versionId, compId)
);

create table planigualdad_Hito (
	hitoId LONG not null primary key,
	nombre VARCHAR(75) null,
	peso VARCHAR(75) null
);

create table planigualdad_ImagenCanvasEst (
	compId LONG not null,
	versionId LONG not null,
	seccionId LONG not null,
	imgCanvasId VARCHAR(75) not null,
	parteImg LONG not null,
	canvasImage TEXT null,
	primary key (compId, versionId, seccionId, imgCanvasId, parteImg)
);

create table planigualdad_Informes (
	informeId LONG not null primary key,
	compId LONG,
	userId LONG,
	versionId LONG,
	tipoInforme VARCHAR(75) null,
	versionInforme INTEGER,
	parametrosInforme TEXT null,
	beansInforme TEXT null,
	formato VARCHAR(75) null,
	createDate DATE null
);

create table planigualdad_Medida (
	medidaId LONG not null primary key,
	compId LONG,
	versionId LONG,
	userId LONG,
	datosGenerales TEXT null,
	cumplimentacion TEXT null,
	isMedidaPredefinida BOOLEAN,
	medidaPredefinidaId VARCHAR(75) null,
	versionMedida INTEGER,
	versionOriginalMedidaId LONG,
	createDate DATE null
);

create table planigualdad_Organizacion (
	organizacionId LONG not null,
	versionId LONG not null,
	compId LONG not null,
	annoConstitucion VARCHAR(75) null,
	paginaWeb TEXT null,
	domicilioSocial VARCHAR(200) null,
	facturacionAnual VARCHAR(75) null,
	formaJuridica VARCHAR(75) null,
	nombreResponsableEntidad VARCHAR(75) null,
	telefonoResponsableEntidad VARCHAR(75) null,
	cargoResponsableEntidad VARCHAR(75) null,
	emailResponsableEntidad VARCHAR(75) null,
	nombreResponsableIgualdad VARCHAR(75) null,
	telefonoResponsableIgualdad VARCHAR(75) null,
	cargoResponsableIgualdad VARCHAR(75) null,
	emailResponsableIgualdad VARCHAR(75) null,
	cnaes VARCHAR(75) null,
	nCentros LONG,
	descripcionActividad TEXT null,
	convenio TEXT null,
	ambito VARCHAR(75) null,
	comentarioAmbito TEXT null,
	nHombresPlantilla LONG,
	nMujeresPlantilla LONG,
	representacionLegal VARCHAR(75) null,
	nRepresentacionLegalHombres LONG,
	nRepresentacionLegalMujeres LONG,
	representaTotalidad VARCHAR(75) null,
	nNoRepresentados LONG,
	comentarioRepresentacion TEXT null,
	departamentoPersonal VARCHAR(75) null,
	sindicatos TEXT null,
	reprComisionOrganizacion TEXT null,
	reprComisionSocial TEXT null,
	reprPlanOrganizacion TEXT null,
	reprPlanSociales TEXT null,
	primary key (organizacionId, versionId, compId)
);

create table planigualdad_ParametricasFDD (
	compId LONG not null,
	versionId LONG not null,
	idParametrica VARCHAR(75) not null,
	tipo VARCHAR(75) null,
	materia INTEGER,
	contenido TEXT null,
	seccionId LONG,
	primary key (compId, versionId, idParametrica)
);

create table planigualdad_Pregunta (
	preguntaId LONG not null,
	seccionId LONG not null,
	tipo VARCHAR(75) null,
	pregunta TEXT null,
	primary key (preguntaId, seccionId)
);

create table planigualdad_PuestoTrabajo (
	puestoId LONG not null,
	versionId LONG not null,
	compId LONG not null,
	nombre VARCHAR(75) null,
	areaId LONG,
	responsabilidad LONG,
	nHombres LONG,
	nMujeres LONG,
	baja DATE null,
	primary key (puestoId, versionId, compId)
);

create table planigualdad_Respuesta (
	compId LONG not null,
	versionId LONG not null,
	seccionId LONG not null,
	respuestas TEXT null,
	estado LONG,
	primary key (compId, versionId, seccionId)
);

create table planigualdad_Valoracion (
	valoracionId LONG not null primary key,
	compId LONG,
	versionId LONG,
	userId LONG,
	respuestasValoracion TEXT null,
	observaciones TEXT null,
	versionValoracion INTEGER,
	createDate DATE null
);

create table planigualdad_Version (
	versionId LONG not null,
	compId LONG not null,
	nombre VARCHAR(75) null,
	inicioPeriodoDatos DATE null,
	finPeriodoDatos DATE null,
	inicioPeriodoPlan DATE null,
	finPeriodoPlan DATE null,
	baja DATE null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (versionId, compId)
);