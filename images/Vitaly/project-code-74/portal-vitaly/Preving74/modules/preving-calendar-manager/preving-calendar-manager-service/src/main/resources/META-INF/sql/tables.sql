create table preving_Activity (
	activityId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	type_ INTEGER,
	name STRING null,
	workTime BOOLEAN,
	color VARCHAR(75) null,
	active_ BOOLEAN,
	observations VARCHAR(500) null,
	usersToInform VARCHAR(500) null,
	infoRespo BOOLEAN,
	infoUser BOOLEAN
);

create table preving_Audit (
	auditId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	date_ DATE null,
	action VARCHAR(75) null,
	description VARCHAR(75) null
);

create table preving_CompanyConf (
	companyConfId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	clientId LONG,
	contracId LONG,
	cif VARCHAR(75) null,
	source VARCHAR(75) null,
	notificationNotWorkable BOOLEAN,
	notificationFinishDateSign BOOLEAN,
	notificationAdminPeriodicity VARCHAR(75) null,
	maxHoursDay BOOLEAN,
	maxHoursWeek BOOLEAN,
	savePastDays BOOLEAN,
	editSigns BOOLEAN,
	editDeleteSigns BOOLEAN,
	addExtraActivity BOOLEAN,
	isWorkCenters BOOLEAN,
	isOwnCalendars BOOLEAN,
	maxHoursDayValue DOUBLE,
	maxHoursWeekValue DOUBLE,
	savePastDaysValue INTEGER,
	editSignsValue INTEGER,
	editDeleteSignsValue INTEGER,
	startDate DATE null,
	description STRING null,
	agreement STRING null,
	observations STRING null,
	soporteEmail VARCHAR(3000) null,
	createDate DATE null,
	implantationDate DATE null,
	modifiedDate DATE null,
	deleteDate DATE null
);

create table preving_Holiday (
	holidayId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	day INTEGER,
	month INTEGER,
	year INTEGER,
	name STRING null,
	typeHoliday VARCHAR(75) null,
	active_ BOOLEAN,
	allowSign BOOLEAN
);

create table preving_Sign (
	signId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	activityId LONG,
	startDate DATE null,
	finishDate DATE null,
	observations STRING null,
	observationsAdmin STRING null,
	obsAdminUserId LONG,
	obsAdminDate DATE null,
	createDate DATE null,
	modifiedDate DATE null
);

create table preving_UserData (
	userDataId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	workCenterId LONG,
	nif VARCHAR(75) null,
	name VARCHAR(75) null,
	lastName VARCHAR(75) null,
	email VARCHAR(75) null,
	jobTitle STRING null,
	workCenter STRING null,
	salary VARCHAR(75) null,
	genre VARCHAR(75) null,
	notificationEndDate DATE null,
	active_ BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null,
	deleteDate DATE null
);

create table preving_UserDataCalendars (
	userDataCalendarId LONG not null primary key,
	userNameCalendarId LONG,
	activityId LONG,
	startDate VARCHAR(75) null,
	finishDate VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	deleteDate DATE null
);

create table preving_UserNameCalendars (
	userNameCalendarId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	name VARCHAR(75) null,
	active_ BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null,
	deleteDate DATE null
);

create table preving_WorkCenters (
	workCenterId LONG not null primary key,
	companyId LONG,
	name VARCHAR(75) null,
	createdBy VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	endDate DATE null
);