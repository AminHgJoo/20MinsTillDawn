package com.example.controllers;

import com.example.models.AppData;
import com.example.models.User;
import com.example.models.enums.Languages;
import com.example.models.enums.RegistrationRegexes;

import java.io.File;
import java.sql.*;

public class RegistrationController {
    public static Languages registerUser(final String username, final String password
        , final String securityQuestion, final String securityAnswer) {

        Languages message = validateUser(username, password, securityQuestion, securityAnswer);

        if (message == Languages.SUCCESS) {
            createUserAndLoadToDB(username, password, securityQuestion, securityAnswer);
            AppData.setCurrentUser(User.users.get(User.users.size() - 1));
        }

        return message;
    }

    private static Languages validateUser(final String username, final String password
        , final String securityQuestion, final String securityAnswer) {
        User user = User.getUserByName(username);

        if (user != null) {
            return Languages.USERNAME_ALREADY_EXISTS;
        }

        if (username.contains(" ") || username.isBlank()) {
            return Languages.INVALID_USERNAME;
        }

        if (!validatePasswordStrength(password)) {
            return Languages.PASSWORD_IS_WEAK;
        }

        return Languages.SUCCESS;
    }

    private static boolean validatePasswordStrength(final String password) {
        if (password.length() < 8) {
            return false;
        }

        if (!RegistrationRegexes.NUMBER.isValid(password)) {
            return false;
        }

        if (!RegistrationRegexes.LOWERCASE_LETTER.isValid(password)) {
            return false;
        }

        if (!RegistrationRegexes.CAPITAL_LETTER.isValid(password)) {
            return false;
        }

        if (!RegistrationRegexes.SPECIAL_CHARACTER.isValid(password)) {
            return false;
        }

        return true;
    }

    private static void createUserAndLoadToDB(String username, String password, String securityQuestion, String securityAnswer) {
        File dataDir = new File("./user_data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        String url = "jdbc:h2:file:./user_data/mydatabase;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE";
        String dbUser = "sa";
        String dbPassword = "";

        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "username VARCHAR(255) NOT NULL, " +
            "password VARCHAR(255) NOT NULL, " +
            "securityQuestion VARCHAR(255) NOT NULL, " +
            "securityAnswer VARCHAR(255) NOT NULL" +
            ")";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);

            User newUser = new User(username, securityQuestion, securityAnswer, password, User.lastUserId + 1);
            newUser.initializeOtherObjects();

            User.users.add(newUser);
            User.lastUserId++;

            System.out.println("User created: " + newUser);

            String insertSQL = "INSERT INTO users (username, password, securityQuestion, securityAnswer) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, newUser.getUsername());
                pstmt.setString(2, newUser.getPassword());
                pstmt.setString(3, newUser.getSecurityQuestion());
                pstmt.setString(4, newUser.getSecurityAnswer());
                pstmt.executeUpdate();
                System.out.println("User loaded to the database: " + newUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
