package com.adeplus.liferay.portlet.audit.nav.utils;

import com.adeplus.liferay.portlet.audit.nav.constants.AuditNavPortletKeys;

import java.io.File;
import java.io.IOException;

public class AuditUtils {

    public static File createNewFileVersion(String nombreFichero){
        boolean existFile=true;
        File file =null;
        int currentFileNumber=0;
        do {
            currentFileNumber++;
            String filename = nombreFichero +"_"+ currentFileNumber + AuditNavPortletKeys.FILENAME_SUFFIX;
            file = new File(filename);
            if (!file.exists() || file.length()<AuditNavPortletKeys.MAX_FILE_SIZE) {
                // Se crea el nuevo archivo
                existFile=false;
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        } while (true);

        return file;
    }
}
