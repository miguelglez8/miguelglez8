package com.legalplus.liferay.portlet.importar.legislaciones.web.bean;

import java.util.List;

public class ImportRequisitos {

    private List<CSVRequisito> csvDataList;
    private List<String> csvDataErrors;

    public List<CSVRequisito> getCsvDataList() {
        return csvDataList;
    }

    public void setCsvDataList(List<CSVRequisito> csvDataList) {
        this.csvDataList = csvDataList;
    }

    public List<String> getCsvDataErrors() {
        return csvDataErrors;
    }

    public void setCsvDataErrors(List<String> csvDataErrors) {
        this.csvDataErrors = csvDataErrors;
    }
}
