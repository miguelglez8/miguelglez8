create table legalplus_Ayuntamiento (
	ccaaId LONG not null,
	provinciaId LONG not null,
	ayuntamientoId LONG not null,
	nombre VARCHAR(300) null,
	primary key (ccaaId, provinciaId, ayuntamientoId)
);

create table legalplus_CCAA (
	ccaaId LONG not null primary key,
	nombre VARCHAR(75) null
);



create table legalplus_CNAES (
	cnae VARCHAR(75) not null primary key,
	nombre VARCHAR(300) null
);

create table legalplus_ConsultorCompany (
	appId LONG not null,
	userId LONG not null,
	compId LONG not null,
	name VARCHAR(75) null,
	lastName VARCHAR(75) null,
	primary key (appId, userId, compId)
);

create table legalplus_ContratoCompany (
	contractId LONG not null,
	compId LONG not null,
	familia VARCHAR(75) null,
	ccaa VARCHAR(75) null,
	ayuntamiento VARCHAR(200) null,
	cnaes TEXT null,
	primary key (contractId, compId)
);

create table legalplus_EvalLegislacion (
	version LONG not null,
	compId LONG not null,
	legislacionId VARCHAR(75) not null,
	fecha DATE null,
	proxima DATE null,
	cumplimiento INTEGER,
	usuario LONG,
	adjunto LONG,
	observaciones VARCHAR(3000) null,
	primary key (version, compId, legislacionId)
);

create table legalplus_EvalRequisito (
	version LONG not null,
	compId LONG not null,
	legislacionId VARCHAR(75) not null,
	requisitoId VARCHAR(75) not null,
	fecha DATE null,
	proxima DATE null,
	cumplimiento INTEGER,
	usuario LONG,
	adjunto LONG,
	observaciones VARCHAR(3000) null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (version, compId, legislacionId, requisitoId)
);

create table legalplus_HiddenLegis (
	legislacionId VARCHAR(75) not null,
	empresaId LONG not null,
	primary key (legislacionId, empresaId)
);

create table legalplus_Legislacion (
	legislacionId VARCHAR(75) not null primary key,
	nombre VARCHAR(3000) null,
	familia VARCHAR(75) null,
	tipo LONG,
	ambito LONG,
	publicacion DATE null,
	derogacion DATE null,
	descripcion TEXT null,
	etiquetas VARCHAR(75) null,
	enlace VARCHAR(1000) null,
	ccaa LONG,
	ayuntamiento LONG,
	urgente DATE null,
	empresas VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table legalplus_LegislacionCNAES (
	legislacionId VARCHAR(75) not null,
	cnae VARCHAR(75) not null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (legislacionId, cnae)
);

create table legalplus_Provincia (
	ccaaId LONG not null,
	provinciaId LONG not null,
	nombre VARCHAR(75) null,
	capitalId LONG,
	primary key (ccaaId, provinciaId)
);

create table legalplus_Requisito (
	legislacionId VARCHAR(75) not null,
	requisitoId VARCHAR(75) not null,
	descripcion TEXT null,
	baja DATE null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (legislacionId, requisitoId)
);

create table legalplus_RequisitoCNAES (
	legislacionId VARCHAR(75) not null,
	requisitoId VARCHAR(75) not null,
	cnae VARCHAR(75) not null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (legislacionId, requisitoId, cnae)
);