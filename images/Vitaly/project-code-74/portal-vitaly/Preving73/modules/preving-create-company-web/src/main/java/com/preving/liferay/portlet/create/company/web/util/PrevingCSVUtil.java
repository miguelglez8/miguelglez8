package com.preving.liferay.portlet.create.company.web.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.opencsv.CSVReader;
import com.preving.liferay.portlet.create.company.web.bean.CSVData;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PrevingCSVUtil {

    private static Log _log = LogFactoryUtil.getLog(PrevingCSVUtil.class);

    /**
     * Get the CSV in an object list.
     * @param companyId
     * @param user
     * @param sourceFileName
     * @param file
     * @return The list with the correct data. The lines with incorrect data are ignored.
     */
    public static List<CSVData> getCSVDataList(long companyId, User user, String sourceFileName, File file){

        List<CSVData> csvDataList = new ArrayList<>();

        try {

            CSVReader csvReader = new CSVReader(new FileReader(file),';');
            String[] values = null;
            int pos = 0;
            while ((values = csvReader.readNext()) != null) {

                pos++;

                //Validation: Number fields 7 or less
                if(values.length < 7){
                    PrevingMailUtil.sendMailErrorCSVNumberFieldsLess6(user);
                    continue;
                }

                _log.debug("CSV line " + pos +": Company name: " + values[0]+", Company cif: " + values[1]+", Admin name: " + values[2]+", Admin surname: " + values[3]+", Admin NIF: " + values[4] +", Admin mail: " + values[5] +", locale: " + values[6]);


                //Validation: Get errors in the line.
                List<String> companyDataCorrect = PrevingCSVValidatorUtil.isCompanyDataCorrect(companyId, values[0], values[1], values[2], values[3], values[4], values[5], values[6]);

                if(companyDataCorrect.size() > 0){
                    //Send mail.
                    PrevingMailUtil.sendMailErrorCSVCompanyIncorrect(user, pos, companyDataCorrect);

                }else {
                    //Add if all data are correct.
                    //Add if all data are correct.
                    String locale = "es_ES";
                    if(values[6].equals("ca") || values[6].equals("CA")){
                        locale = "ca_ES";
                    }else if(values[6].equals("es") || values[6].equals("ES")){
                        locale = "es_ES";
                    }

                    csvDataList.add(new CSVData(values[0], values[1], values[2], values[3], values[4], values[5], locale));
                }

            }

        } catch (Exception e) {
            PrevingMailUtil.sendMailErrorCSVCompany(user);
            _log.error(e);
        }

        return csvDataList;
    }


    public static String getCSVWithFormat(long companyId, File file){

        StringBuilder sb = new StringBuilder(5);
        sb.append("<p><ul>");
        try {

            String dataMessageCorrect = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.success.message.correct");
            String dataMessageIncorrect = LanguageUtil.get(ResourceBundle.getBundle("content/Language", LocaleUtil.getDefault()), "createcompany.success.message.incorrect");

            CSVReader csvReader = new CSVReader(new FileReader(file),';');
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {

                String row = "";
                String dataMessage = "<span class=\"text-success\">[" + dataMessageCorrect + "]</span>";
                String errors = "";

                List<String> companyDataCorrect = PrevingCSVValidatorUtil.isCompanyDataCorrect(companyId, values[0], values[1], values[2], values[3], values[4], values[5], values[6]);

                if(companyDataCorrect.size() > 0){
                    dataMessage = "<span class=\"text-danger\">[" + dataMessageIncorrect + "]</span>";
                    for(String errorMessage:companyDataCorrect){
                        errors += "<span class=\"d-block pl-5\">"+errorMessage+"</span>";
                    }
                }

                try {
                    row += "<li><p>";
                    row += dataMessage;
                    row += " <strong>Empresa</strong>: " + (values.length > 0 ? values[0]:"");
                    row += ", <strong>CIF:</strong> " + (values.length > 1 ? values[1]:"");
                    row += " <strong>Administrador:</strong> " + (values.length > 2 ? values[2]:"");
                    row += " " + (values.length > 3 ? values[3]:"");
                    row += ", <strong>NIF:</strong> " + (values.length > 4 ? values[4]:"");
                    row += ", <strong>Email:</strong> " + (values.length >5 ? values[5]:"");
                    row += ", <strong>Idioma:</strong> " + (values.length >=6 ? values[6]:"");
                    row += "</p>";
                    if(companyDataCorrect.size() > 0) {
                        row +=  errors;
                    }
                    row += "</li>";

                } catch (Exception e) {
                    row = "";
                    _log.error(e.getMessage());
                }

                sb.append(row);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("</ul></p>");

        return sb.toString();
    }

    public static String getCSVLineWithFormat(String values[], int position){
        String result = "";

        try {
            result = "Fila " + position +
                    ": Empresa: " + (values.length > 0 ? values[0]:"") +
                    ", CIF: " + (values.length > 1 ? values[1]:"")+
                    ", Nombre administrador: " + (values.length > 2 ? values[2]:"")+
                    ", Apellidos administrador: " + (values.length > 3 ? values[3]:"")+
                    ", NIF administrador: " + (values.length > 4 ? values[4]:"")+
                    ", Email administrador: " + (values.length > 5 ? values[5]:"")+
                    ", Idioma: " + (values.length >= 6 ? values[6]:"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String cleanHtml(String text){
        return text.replace("<p>","").replace("</p>","");
    }

}

