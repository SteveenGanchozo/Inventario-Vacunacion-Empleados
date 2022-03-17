package com.example.inventarioVacunas.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilies {
    public static String generatePassword() {
        String characters = "0123456789";
        String password = "";
        for (int i = 0; i < 4; i++) {
            password += (characters.charAt((int) (Math.random() * characters.length())));
        }
        return password;
    }

    public static boolean isValidEmail(String email) {
        String r = "^(.+)@(.+)$";
        Pattern p = Pattern.compile(r);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isNumber(String num) {
        return num.matches("[0-9]+");
    }

    public static boolean isText(String text) {
        return text.matches("[a-zA-Z]+[\\s[a-zA-Z]*]*");
    }

    public static boolean isValidDNI(String dni) {
        if (dni.length() == 10 && isNumber(dni))
            return true;
        else
            return false;

    }


}