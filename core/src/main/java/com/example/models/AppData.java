package com.example.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.example.MainApp;
import com.example.models.enums.Translation;

import java.util.ArrayList;
import java.util.HashMap;

public class AppData {
    private final static HashMap<String, Texture> profileAssets = new HashMap<>();

    private final static HashMap<String, Texture> mapAssets = new HashMap<>();

    private final static HashMap<String, Texture> heroAssets = new HashMap<>();

    private static String lang = "english";
    public final static Skin skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

    private static Screen currentScreen;
    private static MainApp mainApp;

    public static boolean isProgramWaitingForFileDrop = false;

    private static User currentUser = null;
    private static UserSettings currentUserSettings = null;

    public static void initializeAssets() {
        initializeProfileAssets();
        initializeHeroAssets();
        initializeMapAssets();
    }

    public static void initializeMapAssets() {
        mapAssets.put("Light", new Texture("game_menu_assets/light.png"));
    }

    public static void initializeHeroAssets() {
        heroAssets.put("ShanaIdle0", new Texture("game_menu_assets/shana_assets/shana_idle/Idle_0.png"));
        heroAssets.put("ShanaIdle1", new Texture("game_menu_assets/shana_assets/shana_idle/Idle_1.png"));
        heroAssets.put("ShanaIdle2", new Texture("game_menu_assets/shana_assets/shana_idle/Idle_2.png"));
        heroAssets.put("ShanaIdle3", new Texture("game_menu_assets/shana_assets/shana_idle/Idle_3.png"));
        heroAssets.put("ShanaIdle4", new Texture("game_menu_assets/shana_assets/shana_idle/Idle_4.png"));
        heroAssets.put("ShanaIdle5", new Texture("game_menu_assets/shana_assets/shana_idle/Idle_5.png"));

        heroAssets.put("ShanaWalking0", new Texture("game_menu_assets/shana_assets/shana_walking/Walk_0.png"));
        heroAssets.put("ShanaWalking1", new Texture("game_menu_assets/shana_assets/shana_walking/Walk_1.png"));
        heroAssets.put("ShanaWalking2", new Texture("game_menu_assets/shana_assets/shana_walking/Walk_2.png"));
        heroAssets.put("ShanaWalking3", new Texture("game_menu_assets/shana_assets/shana_walking/Walk_3.png"));
        heroAssets.put("ShanaWalking5", new Texture("game_menu_assets/shana_assets/shana_walking/Walk_5.png"));
        heroAssets.put("ShanaWalking6", new Texture("game_menu_assets/shana_assets/shana_walking/Walk_6.png"));
        heroAssets.put("ShanaWalking7", new Texture("game_menu_assets/shana_assets/shana_walking/Walk_7.png"));
    }

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
            return Translation.GUEST.translate();
        } else {
            return currentUser.getUsername();
        }
    }

    public static HashMap<String, Texture> getProfileAssets() {
        return profileAssets;
    }

    public static HashMap<String, Texture> getHeroAssets() {
        return heroAssets;
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

    public static UserSettings getCurrentUserSettings() {
        return currentUserSettings;
    }

    public static void setCurrentUserSettings(UserSettings currentUserSettings) {
        AppData.currentUserSettings = currentUserSettings;
    }

    public static HashMap<String, Texture> getMapAssets() {
        return mapAssets;
    }
}
