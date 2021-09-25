package com.exam.nisum.app.utils;

import java.util.regex.Pattern;

public class Util {

    public static boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (email == null){
            return false;
        }
        return pattern.matcher(email).matches();
    }

    public static boolean isPasswordCorrect(String password) {

        boolean onlyTwoNumbers = false;

        String numberOnly = password.replaceAll("[^0-9]", "");
        if(!numberOnly.isEmpty()){
            if(numberOnly.length() == 2){
                onlyTwoNumbers = true;
            }
        }

        String numRegex   = ".*[0-9].*";
        String alphaRegexUpper = ".*[A-Z].*";
        String alphaRegexLower = ".*[a-z].*";

        if (password == null){
            return false;
        }

        return onlyTwoNumbers && password.matches(numRegex) && password.matches(alphaRegexUpper);
    }
}
