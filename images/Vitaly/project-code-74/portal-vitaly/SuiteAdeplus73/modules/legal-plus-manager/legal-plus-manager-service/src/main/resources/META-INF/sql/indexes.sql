create index IX_AAB5B53D on legalplus_Ayuntamiento (ayuntamientoId);
create unique index IX_C5CA31AC on legalplus_Ayuntamiento (ccaaId, ayuntamientoId);
create index IX_E7EB83BC on legalplus_Ayuntamiento (ccaaId, nombre[$COLUMN_LENGTH:300$]);
create index IX_C6F8284D on legalplus_Ayuntamiento (nombre[$COLUMN_LENGTH:300$]);

create unique index IX_F880DD2F on legalplus_CCAA (nombre[$COLUMN_LENGTH:75$]);

create unique index IX_B3355A41 on legalplus_CNAES (cnae[$COLUMN_LENGTH:75$]);
create unique index IX_7B4CA9BB on legalplus_CNAES (nombre[$COLUMN_LENGTH:300$]);

create index IX_5A24648E on legalplus_ConsultorCompany (compId, appId);
create index IX_CE541A52 on legalplus_ConsultorCompany (userId, appId);

create index IX_E389B639 on legalplus_ContratoCompany (compId);

create index IX_3367706F on legalplus_EvalLegislacion (compId, legislacionId[$COLUMN_LENGTH:75$]);

create index IX_74F5C514 on legalplus_EvalRequisito (compId, fecha, cumplimiento);
create index IX_A98060F0 on legalplus_EvalRequisito (compId, legislacionId[$COLUMN_LENGTH:75$], requisitoId[$COLUMN_LENGTH:75$]);
create index IX_66454CCA on legalplus_EvalRequisito (compId, legislacionId[$COLUMN_LENGTH:75$], version);

create index IX_8894404C on legalplus_HiddenLegis (empresaId);

create index IX_EF02BB9E on legalplus_Legislacion (ayuntamiento);
create index IX_D58768A0 on legalplus_Legislacion (ccaa);
create index IX_63CA47C8 on legalplus_Legislacion (urgente);

create unique index IX_1CA463A4 on legalplus_Provincia (nombre[$COLUMN_LENGTH:75$]);