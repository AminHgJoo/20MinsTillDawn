package com.example.models;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class User {
    public static int lastUserId;
    //TODO: Update when a user is created.
    public final static ArrayList<User> users = new ArrayList<>();

    private int id;
    private String username;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private UserSettings userSettings;
    //TODO: private Game savedGame;
    //TODO: private ProfileAvatar profileAvatar;

    public static void loadUsersFromDB() {
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

            String querySQL = "SELECT username, password, securityQuestion, securityAnswer, ID FROM users";
            try (ResultSet rs = stmt.executeQuery(querySQL)) {
                while (rs.next()) {
                    String username1 = rs.getString("username");
                    String password1 = rs.getString("password");
                    String securityQuestion1 = rs.getString("securityQuestion");
                    String securityAnswer1 = rs.getString("securityAnswer");
                    int id = rs.getInt("id");

                    User userFromDb = new User(username1, securityQuestion1, securityAnswer1, password1, id);
                    userFromDb.loadUserObjects();

                    users.add(userFromDb);

                    System.out.println("Retrieved: " + userFromDb);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (users.isEmpty()) {
            lastUserId = 0;
        } else {
            lastUserId = users.get(users.size() - 1).getId();
        }
        System.out.println("lastUserId: " + lastUserId);
    }

    public static User getUserByName(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User(String username, String securityQuestion, String securityAnswer, String password, int id) {
        this.username = username;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.password = password;
        this.id = id;
    }

    public void initializeOtherObjects() {
        //TODO : implement
    }

    public void loadUserObjects() {
        //TODO: Implement
        this.userSettings = new UserSettings();
    }

    @Override
    public String toString() {
        return username + " " + password + " " + securityQuestion + " " + securityAnswer;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(UserSettings userSettings) {
        this.userSettings = userSettings;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
