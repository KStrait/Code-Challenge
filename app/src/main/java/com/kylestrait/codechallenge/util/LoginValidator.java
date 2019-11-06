package com.kylestrait.codechallenge.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Util to validate email and password. If each match the correct pattern ? true : false
public class LoginValidator {

    private Pattern pattern;
    private Matcher matcher;

    private final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";


    public Boolean validateEmail(String email) {
        if (email != null && !email.isEmpty()) {
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Boolean validatePassword(String password) {
        if (password != null && !password.isEmpty()) {

            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(password);

            return matcher.matches();
        } else {
            return false;
        }
    }
}
