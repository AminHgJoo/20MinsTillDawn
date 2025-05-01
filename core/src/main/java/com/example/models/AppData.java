package com.example.models;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class AppData {
    private final static HashMap<String, Texture> assets = new HashMap<>();
    private static String lang = "english";
    private static boolean isPlayerGuest = false;
    private static Screen currentScreen;
    private static User currentUser = null;
    //TODO: handle being guest, handle current user getting set, handle current screen.
    //TODO: load language from user settings.

    public static void initializeAssets() {
        //TODO: implement
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

    public static boolean isPlayerGuest() {
        return isPlayerGuest;
    }

    public static void setIsPlayerGuest(boolean isPlayerGuest) {
        AppData.isPlayerGuest = isPlayerGuest;
    }
}
