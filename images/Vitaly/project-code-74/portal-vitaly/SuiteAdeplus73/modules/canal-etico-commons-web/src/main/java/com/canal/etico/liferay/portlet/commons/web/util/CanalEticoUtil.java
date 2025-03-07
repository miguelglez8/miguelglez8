package com.canal.etico.liferay.portlet.commons.web.util;

import java.util.Random;

public class CanalEticoUtil {

    private static int len = 8; //longitud de la clave
    private static String charsDigits = "0123456789";
    private static String charsLowercase = "abcdefghijklmnopqrstuvwxyz";
    private static String charsUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String chars = charsDigits + charsLowercase + charsUppercase;
    private static boolean hasDigits = false, hasLowercase = false, hasUppercase = false;
    private static Random rnd = new Random();
    private static StringBuilder sb = null;
    private static int[] arrPos = null;

    public static String generatePasswordRandon(){
        sb = new StringBuilder(len);
        positions(3); // 3 pos al azar
        int typeChar = 0; // 0: digit, 1: Upper, 2 : lower
        for (int i = 0; i < len; i++){
            if( arrPos[i] != 0 && typeChar == 0){
                sb.append(charsLowercase.charAt(rnd.nextInt(charsLowercase.length()))); //al menos siempre una minuscula
                typeChar++;
            }else if(arrPos[i] != 0 && typeChar == 1){
                sb.append(charsUppercase.charAt(rnd.nextInt(charsUppercase.length()))); //siempre al menos una mayuscula
                typeChar++;
            }else if( arrPos[i] != 0 && typeChar == 2){
                sb.append(charsDigits.charAt(rnd.nextInt(charsDigits.length()))); //siempre al menos un n�mero
                typeChar++;
            }else{
                sb.append(chars.charAt(rnd.nextInt(chars.length()))); // uno cualquiera
            }
        }
        return sb.toString();
    }
    //Obtener posiciones donde ira un obligatorio; al menos un digito, una minuscula y una mayuscula
    private static void positions(int countPos){
        int pos = -1;
        arrPos = new int[len];
        while(countPos > 0){
            pos = rnd.nextInt(len);
            if(arrPos[pos] == 0){
                arrPos[pos] = 1;
                countPos--;
            }
        }
    }
}
