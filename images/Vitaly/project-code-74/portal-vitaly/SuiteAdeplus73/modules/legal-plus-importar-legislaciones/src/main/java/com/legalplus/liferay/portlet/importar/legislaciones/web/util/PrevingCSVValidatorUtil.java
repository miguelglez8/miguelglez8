package com.legalplus.liferay.portlet.importar.legislaciones.web.util;

import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.enums.AmbitoAplicacion;
import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.enums.FamiliaNormativa;
import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.enums.TipoNormativa;
import com.legalplus.liferay.portlet.importar.legislaciones.web.constants.ImportPortletKeys;
import com.legalplus.liferay.portlet.legalplus.manager.model.CCAA;
import com.legalplus.liferay.portlet.legalplus.manager.model.CNAES;
import com.legalplus.liferay.portlet.legalplus.manager.model.LegislacionCNAES;
import com.legalplus.liferay.portlet.legalplus.manager.service.*;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PrevingCSVValidatorUtil {
    private static Log _log = LogFactoryUtil.getLog(PrevingCSVValidatorUtil.class);

    public static List<String> isCorrectLegislacion(int pos, List<AssetCategory> categories, String legislacionId, String nombre, String tipo, String familia, String ambito, String ccaa, String ayuntamiento, String publicacion, String derogacion, String etiquetas, String cnae, String enlace, String descripcion) {

        List<String> errors = new ArrayList<>();

        //ID
        if(Validator.isBlank(legislacionId)){
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.id.empty", pos);
            errors.add(errorMessage);
        }

        //Nombre
        if (Validator.isBlank(nombre)) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.nombre.empty", pos);
            errors.add(errorMessage);
        }

        //Tipo
        if (Validator.isBlank(tipo) || Validator.isNull(TipoNormativa.getNormativa(StringUtil.trim(tipo)))) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.tipo.empty", pos);
            errors.add(errorMessage);
        }

        //Familia
        if (Validator.isBlank(familia) || !isFamiliaCorrect(familia)) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.familia.empty", pos);
            errors.add(errorMessage);
        }

        //Ambito
        if (Validator.isBlank(ambito) || Validator.isNull(AmbitoAplicacion.getAmbito(StringUtil.trim(ambito)))) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.ambito.empty", pos);
            errors.add(errorMessage);
        }

        //CCAA
        if ( AmbitoAplicacion.CCAA.getDescripcion().equals(ambito) || AmbitoAplicacion.AYUNTAMIENTO.getDescripcion().equals(ambito) ) {
            if (Validator.isBlank(ccaa) || Validator.isNull(CCAALocalServiceUtil.fetchByNombre(ccaa))) {
                String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.ccaa.empty", pos);
                errors.add(errorMessage);
            }
        }

        //Ayuntamiento
        if (AmbitoAplicacion.AYUNTAMIENTO.getDescripcion().equals(ambito)) {
            CCAA ca = CCAALocalServiceUtil.fetchByNombre(ccaa);
            if (Validator.isBlank(ayuntamiento) || Validator.isNull(AyuntamientoLocalServiceUtil.findByCcaaNombre(ca != null ? ca.getCcaaId() : 0, ayuntamiento))){
                String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.ayto.empty", pos);
                errors.add(errorMessage);
            }
        }

        //Fecha publicacion
        if (Validator.isBlank(publicacion) || !DateUtil.isValid(publicacion)) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.publicacion.empty", pos);
            errors.add(errorMessage);
        }

        //Fecha derogación
        if (!Validator.isBlank(derogacion) && !DateUtil.isValid(derogacion)) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.derogacion.empty", pos);
            errors.add(errorMessage);
        }

        //CNAE
        if (Validator.isBlank(cnae) || !isCNAECorrect(cnae)) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.cnae.empty", pos);
            errors.add(errorMessage);
        }

        //Enlace
        if (Validator.isBlank(enlace)) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.enlace.empty", pos);
            errors.add(errorMessage);
        }

        //Etiquetas
        if (Validator.isBlank(etiquetas) || !isEtiquetasLegislacion(categories, etiquetas)) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.etiqueta.empty", pos);
            errors.add(errorMessage);
        }

        return errors;
    }

    public static List<String> isCorrectRequisito(int pos, String legislacionId, String requisitoId, String descripcion, String baja, String cnae) {

        List<String> errors = new ArrayList<>();

        //ID legislacion
        if(Validator.isBlank(legislacionId)){
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.id.empty", pos);
            errors.add(errorMessage);
        }

        //ID requisito
        if(Validator.isBlank(requisitoId)){
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.id.empty", pos);
            errors.add(errorMessage);
        }

        //Descripcion
        if(Validator.isBlank(descripcion)){
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.descripcion.empty", pos);
            errors.add(errorMessage);
        }

        //CNAE
        if (Validator.isBlank(cnae)  || !isCNAELegislacion(cnae, legislacionId)) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.cnae.empty", pos);
            errors.add(errorMessage);
        }

        //Fecha baja
        if (!Validator.isBlank(baja) && !DateUtil.isValid(baja)) {
            String errorMessage = LanguageUtil.format(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "import.csv.error.baja.empty", pos);
            errors.add(errorMessage);
        }

        return errors;
    }

    private static final boolean isFamiliaCorrect(String value) {
        String[] familias = StringUtil.split(value, StringPool.POUND);
        for (String familia : familias) {
            FamiliaNormativa curFamilia = FamiliaNormativa.getFamilia(familia);
            if (Validator.isNull(curFamilia)) {
                return false;
            }
        }
        return true;
    }

    private static final boolean isEtiquetasLegislacion(List<AssetCategory> categories, String value) {
        String[] categoryArray = StringUtil.split(value, StringPool.POUND);
        for (String category : categoryArray) {
            AssetCategory curCategory = categories.stream()
                    .filter(cat -> StringUtil.lowerCase(cat.getTitle(new Locale("es", "ES")))
                            .equals(StringUtil.lowerCase(category)))
                    .findFirst()
                    .orElse(null);

            if (Validator.isNull(curCategory)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isCNAECorrect(String cnaes) {
        String[] cnaeArray =  StringUtil.split(cnaes, StringPool.POUND);
        for (String cnae : cnaeArray) {
            CNAES curCANE = CNAESLocalServiceUtil.fetchCNAES(cnae);
            if (Validator.isNull(curCANE)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isCNAELegislacion(String cnaes, String legislacionId) {
        String[] cnaeArray =  StringUtil.split(cnaes, StringPool.POUND);
        List<LegislacionCNAES> cnaesLegislacion = LegislacionCNAESLocalServiceUtil.findByLegislacion(legislacionId);
        for (String cnae : cnaeArray) {
            LegislacionCNAES cnaeLegislacon = cnaesLegislacion.stream()
                                                                .filter(cn -> cn.getCnae().equals(cnae))
                                                                .findFirst()
                                                                .orElse(null);
            if (Validator.isNull(cnaeLegislacon)) {
                return false;
            }
        }
        return true;
    }
}
