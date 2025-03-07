package com.legalplus.liferay.portlet.importar.legislaciones.web.util;

import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.enums.AmbitoAplicacion;
import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.enums.FamiliaNormativa;
import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.enums.TipoNormativa;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;

public class LegislacionUtil {

    public static long getTipoLegislacion(String value) {
        TipoNormativa tipo = TipoNormativa.getNormativa(value);
        return Long.valueOf(tipo.getCodigo());
    }

    public static long getAmbitoLegislacion(String value) {
        AmbitoAplicacion ambito = AmbitoAplicacion.getAmbito(value);
        return Long.valueOf(ambito.getCodigo());
    }

    public static String getFamiliaLegislacion(String value) {
        StringBuilder familiaList = new StringBuilder();
        String[] familiaArray = StringUtil.split(value, StringPool.POUND);

        for (String familia : familiaArray) {
            FamiliaNormativa curFamilia = FamiliaNormativa.getFamilia(familia);

            if (FamiliaNormativa.TODAS.getCodigo().equals(curFamilia.getCodigo())) {
                return FamiliaNormativa.TODAS.getCodigo() + StringPool.SEMICOLON;
            }
            familiaList.append(curFamilia.getCodigo()).append(StringPool.SEMICOLON);
        }
        return familiaList.toString();
    }

    public static String getEtiquetasLegislacion(List<AssetCategory> categories, String value) {
        StringBuilder categoryList = new StringBuilder();
        String[] categoryArray = StringUtil.split(value, StringPool.POUND);

        for (String category : categoryArray) {
            AssetCategory curCategory = categories.stream()
                                                    .filter(cat -> StringUtil.lowerCase(cat.getTitle(new Locale("es", "ES")))
                                                    .equals(StringUtil.lowerCase(category)))
                                                    .findFirst()
                                                    .orElse(null);

            if (Validator.isNotNull(curCategory)) {
                categoryList.append(curCategory.getCategoryId() + StringPool.SEMICOLON);
            }
        }
        return categoryList.toString();
    }
}
