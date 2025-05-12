package com.example.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import org.intellij.lang.annotations.Language;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class User {

    public final static ArrayList<User> users = new ArrayList<>();
    public static int lastUserId;

    private int id;
    private String username;
    private String password;
    private String securityQuestion;
    private String securityAnswer;
    private UserSettings userSettings;
    private Texture profileAvatar;
    //TODO: private Game savedGame;

    public static void loadUsersFromDB() {
        File dataDir = new File("./user_data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        String url = "jdbc:h2:file:./user_data/mydatabase;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE";
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

            @Language("H2")
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
            FileHandle file = new FileHandle("./saved_data/users/num.txt");
            try {
                lastUserId = Integer.parseInt(file.readString());
            } catch (GdxRuntimeException e) {
                e.printStackTrace();
                lastUserId = 0;
                file.writeString("0", false);
            }
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

    public void saveSettingsToJson() {
        Json json = new Json();

        String jsonString = json.prettyPrint(this.userSettings);

        File dataDir = new File("./saved_data/users");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        FileHandle fileHandle = Gdx.files.absolute("./saved_data/users/" + id + ".json");
        fileHandle.writeString(jsonString, false);
    }

    public void changeUsernameInDB(final String newUsername) {
        File dataDir = new File("./user_data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        String url = "jdbc:h2:file:./user_data/mydatabase;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE";
        String dbUser = "sa";
        String dbPassword = "";

        @Language("H2")
        String sql = "UPDATE users SET USERNAME = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newUsername);

            pstmt.setInt(2, this.id);

            pstmt.executeUpdate();

            System.out.println("Username changed: " + newUsername);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changePasswordInDB(final String newPassword) {
        File dataDir = new File("./user_data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        String url = "jdbc:h2:file:./user_data/mydatabase;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE";
        String dbUser = "sa";
        String dbPassword = "";

        @Language("H2")
        String sql = "UPDATE users SET password = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword);

            pstmt.setInt(2, this.id);

            pstmt.executeUpdate();

            System.out.println("Password changed: " + newPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User(String username, String securityQuestion, String securityAnswer, String password, int id) {
        this.username = username;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.password = password;
        this.id = id;
    }

    public void initializeOtherObjects() {
        //TODO : implement game creation

        int randomIndex = (int) (Math.random() * 12);
        String key = (String) AppData.getProfileAssets().keySet().toArray()[randomIndex];
        this.profileAvatar = AppData.getProfileAssets().get(key);

        this.userSettings = new UserSettings(key, AppData.getLang());
    }

    public void loadUserObjects() {
        //TODO: Implement loading game.

        File dataDir = new File("./saved_data/users");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        FileHandle file = Gdx.files.internal("saved_data/users/" + this.id + ".json");

        Json json = new Json();

        try {
            this.userSettings = json.fromJson(UserSettings.class, file.readString());
            AppData.loadCustomProfileAssets(this.userSettings.savedProfileAssetPaths);
        } catch (GdxRuntimeException e) {
            e.printStackTrace();
            this.userSettings = null;
        }

        if (this.userSettings == null) {
            int randomIndex = (int) (Math.random() * 12);
            String key = (String) AppData.getProfileAssets().keySet().toArray()[randomIndex];

            this.userSettings = new UserSettings(key, AppData.getLang());

            System.out.println("Error loading user settings. User settings reset.");
        } else {
            System.out.println("Loaded user settings.");
        }

        this.profileAvatar = AppData.getProfileAssets().get(userSettings.getAvatarKeyString());
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

    public Texture getProfileAvatar() {
        return profileAvatar;
    }

    public void setProfileAvatar(Texture profileAvatar) {
        this.profileAvatar = profileAvatar;
    }
}
