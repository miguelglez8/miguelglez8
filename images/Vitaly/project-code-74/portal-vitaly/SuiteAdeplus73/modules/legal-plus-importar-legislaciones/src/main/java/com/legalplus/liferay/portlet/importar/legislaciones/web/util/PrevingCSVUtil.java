package com.legalplus.liferay.portlet.importar.legislaciones.web.util;

import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.CSVLegislacion;
import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.CSVRequisito;
import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.ImportLegislaciones;
import com.legalplus.liferay.portlet.importar.legislaciones.web.bean.ImportRequisitos;
import com.legalplus.liferay.portlet.importar.legislaciones.web.constants.ImportPortletKeys;
import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


public class PrevingCSVUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingCSVUtil.class);

    /**
     * Get the CSV in an object list.
     * @param sourceFileName
     * @param file
     * @return The list with the correct data. The lines with incorrect data are ignored.
     */
    public static ImportLegislaciones getLegislacionesCSVDataList(String sourceFileName, File file, List<AssetCategory> categories){

        ImportLegislaciones importLegislaciones = new ImportLegislaciones();
        List<CSVLegislacion> csvDataList = new ArrayList<>();
        List<String> csvDataErrors = new ArrayList<>();

        try {
            _log.info("entro en el try");
            CSVParser separator = new CSVParserBuilder().withSeparator(ImportPortletKeys.CSV_SEPARATOR).build();
            CSVReader csvReader = new CSVReaderBuilder(new FileReader(file)).withCSVParser(separator).withSkipLines(1).build();

            String[] values = null;
            int pos = 1;
            _log.info("estado csvReader "+csvReader);
            while ((values = csvReader.readNext()) != null) {

                pos++;

                //Validation: Number fields 13 or less
                if(values.length < 13){
                    //Add error item
                    continue;
                }

                //Validation: No blank line
                if (Validator.isBlank(values[0])) {
                    continue;
                }
                _log.info("entro en el while");
                //Validation: Get errors in the line.
                List<String> legislacionDataCorrect = PrevingCSVValidatorUtil.isCorrectLegislacion(pos, categories, values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12]);
                _log.info("continuo");
                if (legislacionDataCorrect.size() > 0) {
                    //Add error list
                    csvDataErrors.addAll(legislacionDataCorrect);
                    for(String error: legislacionDataCorrect){
                        _log.info("Error de lista: "+error);

                    }
                } else {
                    //Add if all is correct
                    csvDataList.add(new CSVLegislacion(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9], values[10], values[11], values[12]));
                }
                _log.info("ErrorAlguno "+legislacionDataCorrect.size());

            }

            importLegislaciones.setCsvDataList(csvDataList);
            importLegislaciones.setCsvDataErrors(csvDataErrors);

        } catch (Exception e) {
            _log.error(e);
        }

        return importLegislaciones;
    }

    /**
     * Get the CSV in an object list.
     * @param sourceFileName
     * @param file
     * @return The list with the correct data. The lines with incorrect data are ignored.
     */
    public static ImportRequisitos getRequisitosCSVDataList(String sourceFileName, File file){

        ImportRequisitos importRequisitos = new ImportRequisitos();
        List<CSVRequisito> csvDataList = new ArrayList<>();
        List<String> csvDataErrors = new ArrayList<>();

        try {

            CSVParser separator = new CSVParserBuilder().withSeparator(ImportPortletKeys.CSV_SEPARATOR).build();
            CSVReader csvReader = new CSVReaderBuilder(Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8))
                                        .withCSVParser(separator).withSkipLines(1).build();

            String[] values = null;
            int pos = 1;
            while ((values = csvReader.readNext()) != null) {

                pos++;

                //Validation: Number fields 5 or less
                if(values.length < 5){
                    //Add error item
                    continue;
                }

                //Validation: Get errors in the line.
                List<String> legislacionDataCorrect = PrevingCSVValidatorUtil.isCorrectRequisito(pos, values[0], values[1], values[2], values[3], values[4]);

                if (legislacionDataCorrect.size() > 0) {
                    //Add error list
                    csvDataErrors.addAll(legislacionDataCorrect);

                } else {
                    //Add if all is correct
                    csvDataList.add(new CSVRequisito(values[0], values[1], values[2], values[3], values[4]));
                }

            }

            importRequisitos.setCsvDataList(csvDataList);
            importRequisitos.setCsvDataErrors(csvDataErrors);

        } catch (Exception e) {
            _log.error(e);
        }

        return importRequisitos;
    }
}
