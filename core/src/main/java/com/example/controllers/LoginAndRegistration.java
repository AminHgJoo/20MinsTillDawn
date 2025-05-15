package com.example.controllers;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.example.models.AppData;
import com.example.models.User;
import com.example.models.enums.RegistrationRegexes;
import com.example.models.enums.Translation;
import org.intellij.lang.annotations.Language;

import java.io.File;
import java.sql.*;

public class LoginAndRegistration {
    public static Translation registerUser(final String username, final String password
        , final String securityQuestion, final String securityAnswer) {

        Translation message = validateUser(username, password);

        if (message == Translation.SUCCESS) {
            createUserAndLoadToDB(username, password, securityQuestion, securityAnswer);
            AppData.setCurrentUser(User.users.get(User.users.size() - 1));
            AppData.setCurrentUserSettings(AppData.getCurrentUser().getUserSettings());
        }

        return message;
    }

    private static Translation validateUser(final String username, final String password) {
        User user = User.getUserByName(username);

        if (user != null) {
            return Translation.USERNAME_ALREADY_EXISTS;
        }

        if (username.contains(" ") || username.isBlank()) {
            return Translation.INVALID_USERNAME;
        }

        if (!validatePasswordStrength(password)) {
            return Translation.PASSWORD_IS_WEAK;
        }

        return Translation.SUCCESS;
    }

    public static boolean validatePasswordStrength(final String password) {
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

        if (!RegistrationRegexes.SPECIAL_CHARACTERS.isValid(password)) {
            return false;
        }

        return true;
    }

    private static void createUserAndLoadToDB(String username, String password, String securityQuestion, String securityAnswer) {
        File dataDir = new File("../user_db");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        String url = "jdbc:h2:file:../user_db/mydatabase;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE";
        String dbUser = "sa";
        String dbPassword = "";

        @Language("H2")
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
            newUser.saveSettingsToJson();

            User.users.add(newUser);
            User.lastUserId++;

            FileHandle fileHandle = new FileHandle("../saved_data/users/num.txt");
            fileHandle.writeString(String.valueOf(User.lastUserId), false);

            System.out.println("User created: " + newUser);

            @Language("H2")
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

    public static Translation handleLogin(final String username, final String password) {
        User user = User.getUserByName(username);
        if (user == null) {
            return Translation.USER_DOESNT_EXIST;
        }

        if (!user.getPassword().equals(password)) {
            return Translation.WRONG_PASSWORD;
        }

        AppData.setCurrentUser(user);
        AppData.setLang(user.getUserSettings().getLang());
        AppData.setCurrentUserSettings(user.getUserSettings());
        return Translation.SUCCESS;
    }

    public static Translation handleAccountRecovery(final String securityQuestion, final String securityAnswer
        , final String newPassword, final String username) {

        User user = User.getUserByName(username);

        if (user == null) {
            return Translation.USER_DOESNT_EXIST;
        }

        if (user.getSecurityQuestion().equals(securityQuestion)
            && user.getSecurityAnswer().equals(securityAnswer)) {
            if (validatePasswordStrength(newPassword)) {
                user.setPassword(newPassword);
                user.changePasswordInDB(newPassword);
                AppData.setCurrentUser(user);
                AppData.setLang(user.getUserSettings().getLang());
                return Translation.SUCCESS;
            } else {
                return Translation.PASSWORD_IS_WEAK;
            }
        }

        if (!user.getSecurityQuestion().equals(securityQuestion)) {
            return Translation.WRONG_QUESTION;
        }

        return Translation.WRONG_ANSWER;
    }

    public static void deleteUser(final User user) {
        File dataDir = new File("../user_db");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        String jdbcUrl = "jdbc:h2:file:../user_db/mydatabase;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE";
        String username = "sa";
        String password = "";

        int idToDelete = user.getId();
        User.users.remove(user);

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM USERS WHERE id = ?")) {

            preparedStatement.setInt(1, idToDelete);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Deleted " + rowsAffected + " row(s).");

        } catch (SQLException e) {

            System.out.println("Error while deleting the row: " + e.getMessage());
            e.printStackTrace();
        }

        FileHandle fileHandle = new FileHandle("../saved_data/users/" + user.getId() + ".json");
        try {
            fileHandle.delete();
        } catch (GdxRuntimeException e) {
            e.printStackTrace();
            System.out.println("Error while deleting the file: " + e.getMessage());
        }
    }
}
