package com.example.models;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.example.models.enums.Languages;

import java.util.HashMap;

public class AppData {
    private final static HashMap<String, Texture> assets = new HashMap<>();
    private static String lang = "english";
    private static Screen currentScreen;
    private static User currentUser = null;
    //TODO: handle being guest, handle current user getting set, handle current screen.
    //TODO: load language from user settings.

    public static void initializeAssets() {
        //TODO: implement
        assets.put("AbbyPic", new Texture("avatar_pictures/T_Abby_Portrait.png"));
        assets.put("DasherPic", new Texture("avatar_pictures/T_Dasher_Portrait.png"));
        assets.put("DiamondPic", new Texture("avatar_pictures/T_Diamond_Portrait.png"));
        assets.put("HasturPic", new Texture("avatar_pictures/T_Hastur_Portrait.png"));
        assets.put("HinaPic", new Texture("avatar_pictures/T_Hina_Portrait.png"));
        assets.put("LilithPic", new Texture("avatar_pictures/T_Lilith_Portrait.png"));
        assets.put("LunaPic", new Texture("avatar_pictures/T_Luna_Portrait.png"));
        assets.put("RavenPic", new Texture("avatar_pictures/T_Raven_Portrait.png"));
        assets.put("ScarlettPic", new Texture("avatar_pictures/T_Scarlett_Portrait.png"));
        assets.put("ShanaPic", new Texture("avatar_pictures/T_Shana_Portrait.png"));
        assets.put("SparkPic", new Texture("avatar_pictures/T_Spark_Portrait.png"));
        assets.put("YukiPic", new Texture("avatar_pictures/T_Yuki_Portrait.png"));
    }

    public static String getCurrentUsername() {
        if (currentUser == null) {
            return Languages.GUEST.translate();
        } else {
            return currentUser.getUsername();
        }
    }

    public static HashMap<String, Texture> getAssets() {
        return assets;
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
}
