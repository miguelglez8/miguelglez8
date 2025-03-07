create index IX_22B38EE8 on canal_etico_Accion (compId);
create index IX_98ADDA67 on canal_etico_Accion (nombre[$COLUMN_LENGTH:75$]);

create index IX_B5CE3F1A on canal_etico_Categoria (compId);
create index IX_2BC88A99 on canal_etico_Categoria (nombre[$COLUMN_LENGTH:75$]);

create index IX_6E8E065E on canal_etico_Comp (cif[$COLUMN_LENGTH:75$]);
create index IX_5EFC8562 on canal_etico_Comp (friendlyURL[$COLUMN_LENGTH:75$]);
create index IX_758B6611 on canal_etico_Comp (name[$COLUMN_LENGTH:75$]);
create unique index IX_D44902C0 on canal_etico_Comp (suiteCompId);

create index IX_6D70B439 on canal_etico_Comunicacion (denunciaId);

create index IX_2E482425 on canal_etico_Denuncia (codigo[$COLUMN_LENGTH:75$]);
create index IX_3DF899D6 on canal_etico_Denuncia (compId);
create index IX_A9C73FF4 on canal_etico_Denuncia (email[$COLUMN_LENGTH:75$]);
create index IX_EC7EDBB2 on canal_etico_Denuncia (estado);

create index IX_E06542B9 on canal_etico_DenunciaAccion (denunciaId, accionId);

create index IX_B64D8C51 on canal_etico_Motivo (compId);
create index IX_2C47D7D0 on canal_etico_Motivo (nombre[$COLUMN_LENGTH:75$]);

create index IX_13F2180A on canal_etico_Observacion (denunciaId);

create index IX_A6C7C99 on canal_etico_UserCompany (compId);

create index IX_4505F7EE on canal_etico_Vinculacion (compId);
create index IX_BB00436D on canal_etico_Vinculacion (nombre[$COLUMN_LENGTH:75$]);

create index IX_CAE99647 on canal_etico_accion (nombre[$COLUMN_LENGTH:75$]);

create index IX_E114C2B9 on canal_etico_categoria (nombre[$COLUMN_LENGTH:75$]);

create index IX_5E8393B0 on canal_etico_motivo (nombre[$COLUMN_LENGTH:75$]);

create index IX_4E1EF38D on canal_etico_vinculacion (nombre[$COLUMN_LENGTH:75$]);