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
    private static Player currentPlayer = null;
    private static UserSettings currentUserSettings = null;

    public static void initializeAssets() {
        initializeProfileAssets();
        initializeHeroAssets();
        initializeMapAssets();
        initializeWeaponAssets();
        initializeEnemyAssets();
    }

    public static void initializeMapAssets() {
        mapAssets.put("Light", new Texture("game_menu_assets/light.png"));
    }

    public static void initializeEnemyAssets() {
        //TODO: Implement
    }

    public static void initializeWeaponAssets() {
        //TODO: Implement
    }

    public static void initializeHeroAssets() {
        for (int i = 0; i <= 5; i++) {
            String shanaIdle = "ShanaIdle" + i;
            String pathString = "game_menu_assets/shana_assets/shana_idle/Idle_" + i + ".png";
            heroAssets.put(shanaIdle, new Texture(pathString));
        }

        for (int i = 0; i <= 7; i++) {
            if (i == 4) {
                continue;
            }
            String shanaWalking =  "ShanaWalking" + i;
            String pathString = "game_menu_assets/shana_assets/shana_walking/Walk_" + i + ".png";
            heroAssets.put(shanaWalking, new Texture(pathString));
        }

        for (int i = 0; i <= 5; i++) {
            String diamondIdle = "DiamondIdle" + i;
            String pathString = "game_menu_assets/diamond_assets/diamond_idle/Idle_" + i + ".png";
            heroAssets.put(diamondIdle, new Texture(pathString));
        }

        for (int i = 0; i <= 7; i++) {
            String diamondWalking = "DiamondWalking" + i;
            String pathString = "game_menu_assets/diamond_assets/diamond_walking/Walk_" + i + ".png";
            heroAssets.put(diamondWalking, new Texture(pathString));
        }

        for (int i = 0; i <= 5; i++) {
            String scarletIdle = "ScarletIdle" + i;
            String pathString = "game_menu_assets/scarlet_assets/scarlet_idle/Idle_" + i + ".png";
            heroAssets.put(scarletIdle, new Texture(pathString));
        }

        for (int i = 0; i <= 3; i++) {
            String scarletWalking = "ScarletWalking" + i;
            String pathString = "game_menu_assets/scarlet_assets/scarlet_walking/Run_" + i + ".png";
            heroAssets.put(scarletWalking, new Texture(pathString));
        }

        for (int i = 0; i <= 5; i++) {
            String lilithIdle = "LilithIdle" + i;
            String pathString =  "game_menu_assets/lilith_assets/lilith_idle/Idle_" + i + ".png";
            heroAssets.put(lilithIdle, new Texture(pathString));
        }

        for (int i = 0; i <= 7; i++) {
            String lilithWalking = "LilithWalking" + i;
            String pathString = "game_menu_assets/lilith_assets/lilith_walking/Walk_" + i + ".png";
            heroAssets.put(lilithWalking, new Texture(pathString));
        }

        for (int i = 0; i <= 5; i++) {
            String dasherIdle = "DasherIdle" + i;
            String pathString = "game_menu_assets/dasher_assets/dasher_idle/Idle_" + i + ".png";
            heroAssets.put(dasherIdle, new Texture(pathString));
        }

        for (int i = 0; i <= 3; i++) {
            String dasherWalking = "DasherWalking" + i;
            String pathString = "game_menu_assets/dasher_assets/dasher_walking/Run_" + i + ".png";
            heroAssets.put(dasherWalking, new Texture(pathString));
        }
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

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        AppData.currentPlayer = currentPlayer;
    }
}
