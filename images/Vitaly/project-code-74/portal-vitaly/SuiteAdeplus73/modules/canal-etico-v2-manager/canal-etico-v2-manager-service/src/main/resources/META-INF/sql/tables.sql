create table canal_plus_Accion (
	accionId LONG not null primary key,
	compId LONG,
	nombre STRING null,
	nombre_cat VARCHAR(75) null,
	nombre_eng VARCHAR(75) null,
	estado LONG,
	activo BOOLEAN,
	createBy VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	endDate DATE null
);

create table canal_plus_Categoria (
	categoriaId LONG not null primary key,
	compId LONG,
	nombre VARCHAR(75) null,
	nombre_cat VARCHAR(75) null,
	nombre_eng VARCHAR(75) null,
	tipo VARCHAR(75) null,
	codigo VARCHAR(75) null,
	activo BOOLEAN,
	createBy VARCHAR(75) null,
	createDate DATE null,
	deleteData DATE null,
	modifiedDate DATE null
);

create table canal_plus_Comp (
	compId LONG not null primary key,
	observations STRING null,
	active_ BOOLEAN,
	urlId VARCHAR(75) null,
	startDate DATE null,
	deleteDate DATE null,
	createDate DATE null,
	modifiedDate DATE null,
	politicaFileId VARCHAR(75) null,
	procedimientoFileId VARCHAR(75) null
);

create table canal_plus_Comunicado (
	comunicadoId LONG not null primary key,
	codigo VARCHAR(75) null,
	cif VARCHAR(75) null,
	compId LONG,
	vinculacion VARCHAR(75) null,
	email VARCHAR(75) null,
	nombre VARCHAR(75) null,
	apellidos VARCHAR(75) null,
	nif VARCHAR(75) null,
	telefono VARCHAR(75) null,
	direccionPostal VARCHAR(75) null,
	otros VARCHAR(75) null,
	gestorId VARCHAR(75) null,
	gestorLabel VARCHAR(75) null,
	idGrupoEmpresa LONG,
	nombreEmpresa VARCHAR(75) null,
	anonimo BOOLEAN,
	tipo LONG,
	categoria LONG,
	categoriaDesc VARCHAR(75) null,
	asunto VARCHAR(300) null,
	descripcion TEXT null,
	estado LONG,
	motivoId LONG,
	observaciones VARCHAR(3000) null,
	prorrogado BOOLEAN,
	notificacion VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	endDate DATE null,
	caducidadDate DATE null,
	adjuntosId VARCHAR(75) null
);

create table canal_plus_ComunicadoAccion (
	comunicadoAccionId LONG not null primary key,
	comunicadoId LONG,
	accionId LONG,
	userId LONG,
	observaciones TEXT null,
	idEstado LONG,
	notificado BOOLEAN,
	aplicateDate DATE null,
	createBy VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	fileId VARCHAR(75) null
);

create table canal_plus_ComunicadoAdicional (
	comunicadoAdicionalId LONG not null primary key,
	codigoComunicado VARCHAR(75) null,
	asunto VARCHAR(75) null,
	descripcion TEXT null,
	fileId VARCHAR(75) null,
	createDate DATE null,
	idComunicado LONG
);

create table canal_plus_GrupoEmpresa (
	grupoEmpresasId LONG not null primary key,
	compId LONG,
	entidad STRING null,
	cif STRING null,
	numTrabajadores LONG
);