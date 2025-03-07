package com.canal.etico.liferay.portlet.commons.web.denuncia;

import com.canal.etico.liferay.portlet.canal.manager.model.Denuncia;
import com.canal.etico.liferay.portlet.canal.manager.service.ComunicacionLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaAccionLocalServiceUtil;
import com.canal.etico.liferay.portlet.canal.manager.service.DenunciaLocalServiceUtil;
import com.canal.etico.liferay.portlet.commons.web.file.CanalEticoDocumentLibraryUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comunicado;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.ComunicadoAdicional;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoAdicionalLocalServiceUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.ComunicadoLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.List;


public class CanalEticoDenunciaUtil {

    public static boolean isDenunciaFinalizada(long denunciaId){

        Denuncia denuncia = DenunciaLocalServiceUtil.fetchDenuncia(denunciaId);

        if(Validator.isNotNull(denuncia)){
            if ((Validator.isNotNull(denuncia.getEndDate()) && denuncia.getEndDate().before(new Date()))
                && (denuncia.getEstado() == 2 || denuncia.getEstado() == 3)){

                return true;
            }
        }
        return false;
    }

    public static boolean isComunicadoFinalizada(long comunicadoId){

        Comunicado comunicado = ComunicadoLocalServiceUtil.fetchComunicado(comunicadoId);

        if(Validator.isNotNull(comunicado)){
            if (comunicado.getCaducidadDate().before(new Date()) || (Validator.isNotNull(comunicado.getEndDate()) && comunicado.getEndDate().before(new Date()))){

                return true;
            }
        }
        return false;
    }

    public static void borrarDatosNoEsencialesDenuncia(long groupId, long denunciaId, String parentFolderName, String folderName){

        Denuncia denuncia = DenunciaLocalServiceUtil.fetchDenuncia(denunciaId);

        if(Validator.isNotNull(denuncia) && isDenunciaFinalizada(denunciaId)){

            //Borrar categoria
            denuncia.setCategoria(0);

            //Borrar descripción
            denuncia.setDescripcion("");

            //Actualizar datos denuncia.
            DenunciaLocalServiceUtil.updateDenuncia(denuncia);

            //Borrar datos adjuntos
            CanalEticoDocumentLibraryUtil.deleteDLFolderContent(groupId, parentFolderName, folderName);

            //Borrar acciones
            DenunciaAccionLocalServiceUtil.deleteDenunciaAccionFromDenuncia(denunciaId);

            //Borrar comunicaciones
            ComunicacionLocalServiceUtil.deleteComunicacionesFromDenuncia(denunciaId);

            //Borrar consultas realizadas

        }

    }

    public static void borrarDatosNoEsencialesComunicado(long comunicadoId, String codigoCom) throws PortalException {

        Comunicado comunicado = ComunicadoLocalServiceUtil.fetchComunicado(comunicadoId);

        //if(Validator.isNotNull(comunicado) && isComunicadoFinalizada(comunicadoId)){

        if(Validator.isNotNull(comunicado)){
            List<ComunicadoAdicional> comunicadoAdicionalList =ComunicadoAdicionalLocalServiceUtil.getAllComunicadosByCodigo(codigoCom);

            for(ComunicadoAdicional comunicadoAdicional:comunicadoAdicionalList){
                if (!comunicadoAdicional.getFileId().equals("")) {
                    if (comunicadoAdicional.getFileId().contains(",")) {
                        for (String idFileAdicional : comunicadoAdicional.getFileId().split(",")) {
                            DLFileEntryLocalServiceUtil.deleteDLFileEntry(Long.valueOf(idFileAdicional));
                        }
                    } else {
                        DLFileEntryLocalServiceUtil.deleteDLFileEntry(Long.valueOf(comunicadoAdicional.getFileId()));
                    }
                }
                ComunicadoAdicionalLocalServiceUtil.deleteComunicadoAdicional(comunicadoAdicional);
            }

            //Borrar Nombre
            comunicado.setNombre("");

            //Borrar apellidos
            comunicado.setApellidos("");

            //Borrar nif
            comunicado.setNif("");

            //Borrar email
            comunicado.setEmail("");

            //Borrar tlfono
            comunicado.setTelefono("");

            //Borrar Vinculacion
            comunicado.setVinculacion("");

            //Borrar direccion postal
            comunicado.setDireccionPostal("");

            //Borrar otro
            comunicado.setOtros("");

            //Borrar asunto
            comunicado.setAsunto("");

            //Borrar descripcion
            comunicado.setDescripcion("");
            if(!comunicado.getAdjuntosId().equals("")) {
                if (comunicado.getAdjuntosId().contains(",")) {
                    for (String idFile : comunicado.getAdjuntosId().split(",")) {
                        DLFileEntryLocalServiceUtil.deleteDLFileEntry(Long.valueOf(idFile));
                    }
                } else {
                    DLFileEntryLocalServiceUtil.deleteDLFileEntry(Long.valueOf(comunicado.getAdjuntosId()));
                }
            }
            comunicado.setAdjuntosId("");
            //Actualizar datos denuncia.
            ComunicadoLocalServiceUtil.updateComunicado(comunicado);

        }

    }

}
