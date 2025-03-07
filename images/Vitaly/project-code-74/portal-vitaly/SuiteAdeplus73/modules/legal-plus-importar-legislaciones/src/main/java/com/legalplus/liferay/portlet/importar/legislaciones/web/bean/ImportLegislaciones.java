package com.legalplus.liferay.portlet.importar.legislaciones.web.bean;

import java.util.List;

public class ImportLegislaciones {

    private List<CSVLegislacion> csvDataList;
    private List<String> csvDataErrors;

    public List<CSVLegislacion> getCsvDataList() {
        return csvDataList;
    }

    public void setCsvDataList(List<CSVLegislacion> csvDataList) {
        this.csvDataList = csvDataList;
    }

    public List<String> getCsvDataErrors() {
        return csvDataErrors;
    }

    public void setCsvDataErrors(List<String> csvDataErrors) {
        this.csvDataErrors = csvDataErrors;
    }
}
