create index IX_8FCECDA7 on preving_Activity (companyId, groupId);

create index IX_8EF4708B on preving_Audit (userId);

create index IX_C5B7F819 on preving_CompanyConf (cif[$COLUMN_LENGTH:75$]);
create index IX_B1AD57FC on preving_CompanyConf (clientId, contracId);
create index IX_C3473D39 on preving_CompanyConf (companyId, groupId);

create index IX_3CBEC910 on preving_Holiday (companyId, groupId);

create index IX_5EC4F671 on preving_Sign (companyId, groupId, userId, startDate);

create index IX_A749A42D on preving_UserData (companyId, groupId);
create index IX_69AF578D on preving_UserData (groupId, email[$COLUMN_LENGTH:75$]);
create index IX_8EF6F419 on preving_UserData (groupId, userId);
create index IX_77B60A30 on preving_UserData (nif[$COLUMN_LENGTH:75$]);
create index IX_D3A9C765 on preving_UserData (userId);

create index IX_B4B3D6D9 on preving_UserDataCalendars (userNameCalendarId);

create index IX_281F6B51 on preving_UserNameCalendars (companyId, groupId, userId);
create index IX_DE9E566F on preving_UserNameCalendars (groupId, userId);
create index IX_8D384C4F on preving_UserNameCalendars (userId);

create index IX_881A00E5 on preving_WorkCenters (companyId);