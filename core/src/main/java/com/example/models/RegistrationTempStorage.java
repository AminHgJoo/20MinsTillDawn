package com.example.models;

public class RegistrationTempStorage {
    private static String username;
    private static String password;
    private static String securityQuestion;
    private static String securityAnswer;

    static {
        clear();
    }

    public static void clear() {
        username = "";
        password = "";
        securityQuestion = "";
        securityAnswer = "";
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getSecurityQuestion() {
        return securityQuestion;
    }

    public static String getSecurityAnswer() {
        return securityAnswer;
    }

    public static void setUsername(String string) {
        username = string;
    }

    public static void setPassword(String string) {
        password = string;
    }

    public static void setSecurityQuestion(String string) {
        securityQuestion = string;
    }

    public static void setSecurityAnswer(String string) {
        securityAnswer = string;
    }
}
