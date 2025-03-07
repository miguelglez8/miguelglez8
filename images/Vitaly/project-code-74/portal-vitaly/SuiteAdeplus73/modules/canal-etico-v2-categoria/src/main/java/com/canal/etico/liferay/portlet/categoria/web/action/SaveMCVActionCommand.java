package com.canal.etico.liferay.portlet.categoria.web.action;

//import com.canal.etico.liferay.portlet.canal.manager.model.Categoria;
//import com.canal.etico.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
import com.canal.etico.liferay.portlet.categoria.web.constants.CategoriasPortletKeys;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Categoria;
import com.canal.etico.v2.liferay.portlet.canal.manager.model.Comp;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.CategoriaLocalServiceUtil;
import com.canal.etico.v2.liferay.portlet.canal.manager.service.CompLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.service.component.annotations.Component;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "javax.portlet.name=" + CategoriasPortletKeys.CATEGORIAS,
                "mvc.command.name=/categoria/save"
        },
        service = MVCActionCommand.class
)
public class SaveMCVActionCommand implements MVCActionCommand {

        private static Log _log = LogFactoryUtil.getLog(SaveMCVActionCommand.class);

        @Override
        public boolean processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws PortletException {

                long categoriaEditId = ParamUtil.getLong(actionRequest, CategoriasPortletKeys.CATEGORIA_ID_EDIT, 0);
                String name = ParamUtil.getString(actionRequest, CategoriasPortletKeys.NAME, "");
                long companyId = ParamUtil.getLong(actionRequest, CategoriasPortletKeys.COMPANY_ID, 0);
                String global = ParamUtil.getString(actionRequest, CategoriasPortletKeys.GLOBAL, "");
                String afecta = ParamUtil.getString(actionRequest, CategoriasPortletKeys.AFECTA, "");
                String codigo = ParamUtil.getString(actionRequest, CategoriasPortletKeys.CODIGO, "");
                String activeStr = ParamUtil.getString(actionRequest, CategoriasPortletKeys.ACTIVE, "");


                /*System.out.println("categoriaEditId " + categoriaEditId);
                System.out.println("afecta " + afecta);

                boolean active = false;
                if(CategoriasPortletKeys.ACTIVE_YES.equals(activeStr)){
                        active = true;
                }

                Categoria categoria = null;
                // New.
                if(categoriaEditId == 0){
                        categoria = CategoriaLocalServiceUtil.createCategoria(CounterLocalServiceUtil.increment(Categoria.class.getName()));
                //Update.
                }else{
                        categoria = CategoriaLocalServiceUtil.fetchCategoria(categoriaEditId);
                }

                if(Validator.isNotNull(categoria)){
                        categoria.setCompId(companyId);
                        categoria.setNombre(name);
                        categoria.setTipo(afecta);
                        categoria.setCodigo(codigo);
                        categoria.setActivo(active);

                        CategoriaLocalServiceUtil.updateCategoria(categoria);
                }
                */
                return true;
        }
}
