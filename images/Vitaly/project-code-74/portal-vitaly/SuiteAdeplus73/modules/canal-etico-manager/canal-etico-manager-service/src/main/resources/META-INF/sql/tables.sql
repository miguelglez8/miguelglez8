create table canal_etico_Accion (
	accionId LONG not null primary key,
	compId LONG,
	nombre STRING null,
	estado LONG,
	activo BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null
);

create table canal_etico_Categoria (
	categoriaId LONG not null primary key,
	compId LONG,
	nombre STRING null,
	tipo VARCHAR(75) null,
	codigo VARCHAR(75) null,
	activo BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null
);

create table canal_etico_Comp (
	compId LONG not null primary key,
	suiteCompId LONG,
	cif VARCHAR(75) null,
	name VARCHAR(75) null,
	description STRING null,
	observations STRING null,
	agreement STRING null,
	friendlyURL VARCHAR(75) null,
	logo LONG,
	active_ BOOLEAN,
	startDate DATE null,
	deleteDate DATE null,
	createDate DATE null,
	modifiedDate DATE null
);

create table canal_etico_Comunicacion (
	comunicacionId LONG not null primary key,
	denunciaId LONG,
	userId LONG,
	descripcion VARCHAR(3000) null,
	adjunto LONG,
	observaciones VARCHAR(3000) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table canal_etico_Denuncia (
	denunciaId LONG not null primary key,
	codigo VARCHAR(75) null,
	cif VARCHAR(75) null,
	compId LONG,
	vinculacion VARCHAR(75) null,
	email VARCHAR(75) null,
	nombre VARCHAR(75) null,
	apellidos VARCHAR(75) null,
	nif VARCHAR(75) null,
	telefono VARCHAR(75) null,
	anonimo BOOLEAN,
	tipo LONG,
	categoria LONG,
	asunto VARCHAR(300) null,
	descripcion VARCHAR(3000) null,
	estado LONG,
	motivoId LONG,
	observaciones VARCHAR(3000) null,
	createDate DATE null,
	modifiedDate DATE null,
	endDate DATE null
);

create table canal_etico_DenunciaAccion (
	denunciaAccionId LONG not null primary key,
	denunciaId LONG,
	accionId LONG,
	userId LONG,
	observaciones VARCHAR(3000) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table canal_etico_Motivo (
	motivoId LONG not null primary key,
	compId LONG,
	nombre STRING null,
	observaciones STRING null,
	activo BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null
);

create table canal_etico_Observacion (
	observacionId LONG not null primary key,
	denunciaId LONG,
	userId LONG,
	descripcion VARCHAR(3000) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table canal_etico_UserCompany (
	userId LONG not null,
	compId LONG not null,
	createDate DATE null,
	modifiedDate DATE null,
	deleteDate DATE null,
	primary key (userId, compId)
);

create table canal_etico_Vinculacion (
	vinculacionId LONG not null primary key,
	compId LONG,
	nombre STRING null,
	activo BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null
);

create table canal_etico_accion (
	accionId LONG not null primary key,
	nombre VARCHAR(75) null,
	estado VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	deleteDate DATE null
);

create table canal_etico_categoria (
	categoriaId LONG not null primary key,
	nombre VARCHAR(75) null,
	tipo VARCHAR(75) null,
	activo BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null,
	deleteDate DATE null
);

create table canal_etico_motivo (
	motivoId LONG not null primary key,
	nombre VARCHAR(75) null,
	observaciones VARCHAR(75) null,
	activo BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null,
	deleteDate DATE null
);

create table canal_etico_vinculacion (
	vinculacionId LONG not null primary key,
	nombre VARCHAR(75) null,
	activo BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null,
	deleteDate DATE null
);