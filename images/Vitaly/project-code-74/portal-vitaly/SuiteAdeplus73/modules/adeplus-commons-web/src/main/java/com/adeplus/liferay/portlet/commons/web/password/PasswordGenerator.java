package com.adeplus.liferay.portlet.commons.web.password;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {

    private static final int PASSWORD_LENGTH = 8;

    private static SecureRandom random = new SecureRandom();

    public static String generateStrongPassword() {

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();

        String password = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(PASSWORD_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return password;
    }



}