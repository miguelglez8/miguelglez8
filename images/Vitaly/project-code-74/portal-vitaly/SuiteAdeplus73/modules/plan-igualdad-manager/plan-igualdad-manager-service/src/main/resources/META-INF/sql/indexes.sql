create index IX_2163295D on planigualdad_Area (versionId, compId);

create index IX_4DDD5EE0 on planigualdad_Cuestionario (compId);

create index IX_E116C4F3 on planigualdad_Estado (activa);

create index IX_E7EA9701 on planigualdad_Evaluacion (compId, versionId);

create index IX_230746D3 on planigualdad_FechaHito (versionId, compId);

create index IX_10CE5518 on planigualdad_Hito (peso[$COLUMN_LENGTH:75$]);

create index IX_B1EC1FC1 on planigualdad_Informes (compId, versionId, tipoInforme[$COLUMN_LENGTH:75$]);

create index IX_3812281 on planigualdad_Medida (compId, medidaPredefinidaId[$COLUMN_LENGTH:75$]);
create index IX_BF296C34 on planigualdad_Medida (compId, versionId);
create index IX_882CA62B on planigualdad_Medida (userId);
create index IX_FF10515D on planigualdad_Medida (versionMedida, versionOriginalMedidaId);
create index IX_AD6803DD on planigualdad_Medida (versionOriginalMedidaId);

create unique index IX_7E9E3466 on planigualdad_Organizacion (versionId, compId);

create index IX_82A30E1 on planigualdad_ParametricasFDD (compId, versionId, tipo[$COLUMN_LENGTH:75$], materia);

create index IX_8E8DBCE0 on planigualdad_Pregunta (seccionId);

create index IX_156F68CF on planigualdad_PuestoTrabajo (versionId, compId, areaId);
create index IX_6A968E63 on planigualdad_PuestoTrabajo (versionId, compId, responsabilidad);

create unique index IX_AE6A9A75 on planigualdad_Respuesta (versionId, compId, seccionId);

create index IX_3048A9F7 on planigualdad_Valoracion (compId);

create index IX_D39BC4EB on planigualdad_Version (compId);