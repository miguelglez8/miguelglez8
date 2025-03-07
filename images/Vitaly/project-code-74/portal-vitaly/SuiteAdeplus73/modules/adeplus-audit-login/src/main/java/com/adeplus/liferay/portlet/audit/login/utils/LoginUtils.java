package com.adeplus.liferay.portlet.audit.login.utils;

import com.adeplus.liferay.portlet.audit.login.constants.AdeplusAuditLoginPortletKeys;

import java.io.File;
import java.io.IOException;

public class LoginUtils {

    public static File createNewFileVersion(String nombreFichero){
        boolean existFile=true;
        File file =null;
        int currentFileNumber=0;
        do {
            currentFileNumber++;
            String filename = nombreFichero +"_"+ currentFileNumber + AdeplusAuditLoginPortletKeys.FILENAME_SUFFIX;
            file = new File(filename);
            if (!file.exists() || file.length()<AdeplusAuditLoginPortletKeys.MAX_FILE_SIZE) {
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
