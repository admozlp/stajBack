package com.ahievran.staj.util.mail;

public class EmailUtil {
    public static String getVerificationUrl(String host, String token) {
        return host + "/verify-account/" + token;
    }

    public static String getResetUrl(String host, String token) {
        return host + "/reset-password?token=" + token;
    }
}
