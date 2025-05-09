package com.example.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.example.MainApp;
import com.example.models.enums.Languages;

import java.util.ArrayList;
import java.util.HashMap;

public class AppData {
    private final static HashMap<String, Texture> profileAssets = new HashMap<>();
    private static String lang = "english";
    private static Screen currentScreen;
    private static User currentUser = null;
    public static boolean isProgramWaitingForFileDrop = false;
    private static MainApp mainApp;

    public static void initializeProfileAssets() {
        profileAssets.put("AbbyPic", new Texture("avatar_pictures/T_Abby_Portrait.png"));
        profileAssets.put("DasherPic", new Texture("avatar_pictures/T_Dasher_Portrait.png"));
        profileAssets.put("DiamondPic", new Texture("avatar_pictures/T_Diamond_Portrait.png"));
        profileAssets.put("HasturPic", new Texture("avatar_pictures/T_Hastur_Portrait.png"));
        profileAssets.put("HinaPic", new Texture("avatar_pictures/T_Hina_Portrait.png"));
        profileAssets.put("LilithPic", new Texture("avatar_pictures/T_Lilith_Portrait.png"));
        profileAssets.put("LunaPic", new Texture("avatar_pictures/T_Luna_Portrait.png"));
        profileAssets.put("RavenPic", new Texture("avatar_pictures/T_Raven_Portrait.png"));
        profileAssets.put("ScarlettPic", new Texture("avatar_pictures/T_Scarlett_Portrait.png"));
        profileAssets.put("ShanaPic", new Texture("avatar_pictures/T_Shana_Portrait.png"));
        profileAssets.put("SparkPic", new Texture("avatar_pictures/T_Spark_Portrait.png"));
        profileAssets.put("YukiPic", new Texture("avatar_pictures/T_Yuki_Portrait.png"));
    }

    public static void loadCustomProfileAssets(ArrayList<String> assetPaths) {
        for (String assetPath : assetPaths) {
            profileAssets.put(assetPath, new Texture(Gdx.files.absolute(assetPath)));
        }
    }

    public static String getCurrentUsername() {
        if (currentUser == null) {
            return Languages.GUEST.translate();
        } else {
            return currentUser.getUsername();
        }
    }

    public static HashMap<String, Texture> getProfileAssets() {
        return profileAssets;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        AppData.currentUser = currentUser;
    }

    public static String getLang() {
        return lang;
    }

    public static void setLang(String lang) {
        AppData.lang = lang;
    }

    public static Screen getCurrentScreen() {
        return currentScreen;
    }

    public static void setCurrentScreen(Screen currentScreen) {
        AppData.currentScreen = currentScreen;
    }

    public static MainApp getMainApp() {
        return mainApp;
    }

    public static void setMainApp(MainApp mainApp) {
        AppData.mainApp = mainApp;
    }
}
