create index IX_E8FFE14C on canal_plus_Accion (compId);

create index IX_E1762236 on canal_plus_Categoria (compId);

create index IX_3EBCCF24 on canal_plus_Comp (urlId[$COLUMN_LENGTH:75$]);

create index IX_4B223FAC on canal_plus_Comunicado (codigo[$COLUMN_LENGTH:75$]);
create index IX_5AD2B55D on canal_plus_Comunicado (compId);
create index IX_F508178D on canal_plus_Comunicado (email[$COLUMN_LENGTH:75$]);
create index IX_958F739 on canal_plus_Comunicado (estado);

create index IX_8306276A on canal_plus_ComunicadoAccion (comunicadoAccionId, accionId);
create index IX_BF650A0D on canal_plus_ComunicadoAccion (comunicadoId);

create index IX_8FA7CA28 on canal_plus_ComunicadoAdicional (codigoComunicado[$COLUMN_LENGTH:75$]);

create index IX_849C8145 on canal_plus_GrupoEmpresa (compId);