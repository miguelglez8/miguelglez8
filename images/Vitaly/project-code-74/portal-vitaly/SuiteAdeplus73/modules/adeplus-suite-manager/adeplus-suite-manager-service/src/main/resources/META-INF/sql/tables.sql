create table adeplus_Application (
	applicationId LONG not null primary key,
	name VARCHAR(75) null,
	description STRING null,
	url VARCHAR(200) null,
	configuration VARCHAR(200) null,
	roles VARCHAR(75) null,
	alias_ VARCHAR(75) null,
	keycloak_client VARCHAR(200) null,
	keycloak_secret VARCHAR(200) null,
	logo LONG,
	order_ INTEGER,
	limitAdmins BOOLEAN,
	limitUsers BOOLEAN,
	limitNumAdmins LONG,
	limitNumUsers LONG,
	createDate DATE null,
	modifiedDate DATE null
);

create table adeplus_ApplicationLicense (
	licenseId LONG not null primary key,
	applicationId LONG,
	name VARCHAR(75) null,
	permissionRoleKey VARCHAR(75) null,
	createDate DATE null,
	deleteDate DATE null,
	modifiedDate DATE null
);

create table adeplus_Audit (
	auditId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	action VARCHAR(75) null,
	description VARCHAR(3300) null,
	date_ DATE null
);

create table adeplus_AuditadoDataApi (
	idDataTemporal LONG not null primary key,
	mensaje VARCHAR(500) null,
	procesadoDate DATE null,
	procesado INTEGER,
	evento VARCHAR(75) null,
	cif VARCHAR(75) null,
	idClient LONG,
	idContract LONG,
	app VARCHAR(3300) null,
	changeStateDate DATE null,
	state_ BOOLEAN,
	userIdChangeState LONG
);

create table adeplus_Comp (
	compId LONG not null primary key,
	cif VARCHAR(75) null,
	name VARCHAR(200) null,
	description STRING null,
	observations STRING null,
	agreement STRING null,
	idCrm VARCHAR(75) null,
	idContrato VARCHAR(75) null,
	idEstado VARCHAR(75) null,
	logo LONG,
	startDate DATE null,
	deleteDate DATE null,
	createDate DATE null,
	modifiedDate DATE null
);

create table adeplus_CompApplication (
	compId LONG not null,
	applicationId LONG not null,
	licenseId LONG,
	createDate DATE null,
	deleteDate DATE null,
	modifiedDate DATE null,
	primary key (compId, applicationId)
);

create table adeplus_CompClientApplication (
	empresaId LONG not null primary key,
	cif VARCHAR(75) null,
	compId LONG,
	clientId LONG,
	contractId LONG,
	applicationId VARCHAR(3300) null,
	name VARCHAR(200) null,
	description VARCHAR(200) null,
	observations STRING null,
	idEstado VARCHAR(75) null,
	logo LONG,
	startDate DATE null,
	deleteDate DATE null,
	createDate DATE null,
	modifiedDate DATE null
);

create table adeplus_Role (
	roleId LONG not null primary key,
	applicationId LONG,
	name VARCHAR(75) null,
	alias_ VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null
);

create table adeplus_TemporalDataApi (
	idDataTemporal LONG not null primary key,
	typeEvent VARCHAR(75) null,
	dataApiEmpresa VARCHAR(3300) null,
	dataApiApplicaciones TEXT null,
	dataApiUsuarios VARCHAR(3300) null,
	procesado INTEGER,
	createDate DATE null,
	procesadoDate DATE null
);

create table adeplus_UserApplication (
	userId LONG not null,
	compId LONG not null,
	applicationId LONG not null,
	admin_ BOOLEAN,
	createDate DATE null,
	deleteDate DATE null,
	modifiedDate DATE null,
	primary key (userId, compId, applicationId)
);

create table adeplus_UserApplicationClient (
	empresaId LONG not null,
	userId LONG not null,
	compId LONG,
	clientId LONG,
	contractId LONG,
	applicationId VARCHAR(3300) null,
	admin_ BOOLEAN,
	createDate DATE null,
	deleteDate DATE null,
	modifiedDate DATE null,
	primary key (empresaId, userId)
);

create table adeplus_UserCompany (
	userId LONG not null,
	compId LONG not null,
	name VARCHAR(200) null,
	lastName VARCHAR(200) null,
	nif VARCHAR(75) null,
	email VARCHAR(75) null,
	phone VARCHAR(75) null,
	admin_ BOOLEAN,
	defaultUserComp BOOLEAN,
	defaultClientId LONG,
	defaultContractId LONG,
	defaultEmpresaId LONG,
	userEndDate DATE null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (userId, compId)
);

create table adeplus_UserCompanyClient (
	userId LONG not null,
	empresaId LONG not null,
	compId LONG,
	name VARCHAR(75) null,
	lastName VARCHAR(75) null,
	nif VARCHAR(75) null,
	email VARCHAR(75) null,
	phone VARCHAR(75) null,
	admin_ BOOLEAN,
	defaultUserComp BOOLEAN,
	defaultClientId LONG,
	defaultContractId LONG,
	defaultEmpresaId LONG,
	userEndDate DATE null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (userId, empresaId)
);

create table adeplus_UserRole (
	userId LONG not null,
	compId LONG not null,
	roleId LONG not null,
	createDate DATE null,
	modifiedDate DATE null,
	primary key (userId, compId, roleId)
);