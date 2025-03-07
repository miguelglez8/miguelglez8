create index IX_BB822FF6 on adeplus_Application (alias_[$COLUMN_LENGTH:75$]);
create index IX_AE6BE0B2 on adeplus_Application (name[$COLUMN_LENGTH:75$]);

create index IX_6DF17DA1 on adeplus_ApplicationLicense (applicationId);
create unique index IX_5C6F0E12 on adeplus_ApplicationLicense (licenseId);

create index IX_FDE3F6F8 on adeplus_Audit (userId);

create unique index IX_BA1E9A58 on adeplus_Comp (cif[$COLUMN_LENGTH:75$]);
create index IX_27571BF9 on adeplus_Comp (name[$COLUMN_LENGTH:200$], cif[$COLUMN_LENGTH:75$]);

create index IX_23B5A17 on adeplus_CompApplication (applicationId);

create index IX_E596B602 on adeplus_CompClientApplication (applicationId[$COLUMN_LENGTH:3300$]);
create index IX_257CC5D3 on adeplus_CompClientApplication (clientId);
create index IX_4EECF74B on adeplus_CompClientApplication (compId, clientId, clientId);
create index IX_5B691052 on adeplus_CompClientApplication (compId, clientId, contractId);
create index IX_2A236C14 on adeplus_CompClientApplication (contractId, clientId);
create index IX_CC3907E0 on adeplus_CompClientApplication (empresaId, applicationId[$COLUMN_LENGTH:3300$]);

create index IX_293F4F5C on adeplus_Role (applicationId);
create index IX_E0F1E857 on adeplus_Role (roleId, applicationId);

create index IX_81DD0FD1 on adeplus_SuiteConfig (key_[$COLUMN_LENGTH:75$]);

create index IX_41F35447 on adeplus_TemporalDataApi (procesado);

create index IX_9BB7E8B5 on adeplus_UserApplication (applicationId, compId, admin_);
create index IX_77EAE9D7 on adeplus_UserApplication (userId, applicationId, compId);

create index IX_1904EB46 on adeplus_UserApplicationClient (applicationId[$COLUMN_LENGTH:3300$], compId);
create index IX_6A1CE3CE on adeplus_UserApplicationClient (clientId, contractId);
create index IX_AB39A7CC on adeplus_UserApplicationClient (compId, clientId, contractId);
create index IX_66B9A94E on adeplus_UserApplicationClient (userId, applicationId[$COLUMN_LENGTH:3300$]);
create index IX_703FD6CB on adeplus_UserApplicationClient (userId, compId, applicationId[$COLUMN_LENGTH:3300$], clientId, contractId);
create index IX_DB42EA05 on adeplus_UserApplicationClient (userId, compId, clientId);
create index IX_9FCF7087 on adeplus_UserApplicationClient (userId, empresaId);

create index IX_DBF7E013 on adeplus_UserCompany (compId);
create index IX_F9399095 on adeplus_UserCompany (email[$COLUMN_LENGTH:75$], compId);
create index IX_2BD12E84 on adeplus_UserCompany (nif[$COLUMN_LENGTH:75$], compId);
create index IX_49515104 on adeplus_UserCompany (nif[$COLUMN_LENGTH:75$], email[$COLUMN_LENGTH:75$], compId);
create index IX_FB14CB9E on adeplus_UserCompany (userId, compId, defaultUserComp);

create index IX_5A80FEFE on adeplus_UserCompanyClient (compId);
create index IX_7D86E34A on adeplus_UserCompanyClient (email[$COLUMN_LENGTH:75$], compId);
create index IX_BD140CB4 on adeplus_UserCompanyClient (empresaId);
create index IX_1D4DAA79 on adeplus_UserCompanyClient (nif[$COLUMN_LENGTH:75$], compId);
create index IX_8F64676F on adeplus_UserCompanyClient (nif[$COLUMN_LENGTH:75$], email[$COLUMN_LENGTH:75$], compId);
create index IX_429FD38 on adeplus_UserCompanyClient (userId, compId);

create index IX_EEE390E8 on adeplus_UserRole (compId);
create index IX_2C556CF on adeplus_UserRole (roleId);

create index IX_63B818D2 on adeplus_application (name[$COLUMN_LENGTH:75$]);

create index IX_9241FD37 on adeplus_comp (name[$COLUMN_LENGTH:75$]);