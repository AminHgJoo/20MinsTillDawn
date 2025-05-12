package com.example.models;

import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.HashMap;

public class UserSettings {
    private String avatarKeyString;

    private int score;
    private int kills;
    private int longestSurvivalTimeSeconds;

    private String lang;
    private boolean playSFX;
    private String musicPath;
    private float soundVolume;

    private boolean autoReload;

    public HashMap<String, Integer> keyBinds;

    public ArrayList<String> savedProfileAssetPaths = new ArrayList<>();

    public void initializeKeyBinds() {
        keyBinds.put("upKey", Input.Keys.W);
        keyBinds.put("downKey", Input.Keys.S);
        keyBinds.put("leftKey", Input.Keys.A);
        keyBinds.put("rightKey", Input.Keys.D);
        keyBinds.put("reloadKey", Input.Keys.R);
        keyBinds.put("reduceTimeCheat", Input.Keys.Q);
        keyBinds.put("pauseKey", Input.Keys.ESCAPE);
        keyBinds.put("levelUpCheat", Input.Keys.L);
        keyBinds.put("invincibilityCheat", Input.Keys.I);
        keyBinds.put("addHpCheat", Input.Keys.H);
        keyBinds.put("aimbotKey", Input.Keys.SPACE);
        keyBinds.put("goToBossCheat", Input.Keys.G);
    }

    public boolean detectKeybindConflict(int keycode) {
        for (String key : keyBinds.keySet()) {
            int value = keyBinds.get(key);
            if (value == keycode) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author AminHg
     * @apiNote Only for JSON Deserialization.
     */
    public UserSettings() {
    }

    public UserSettings(boolean useDefaultKeymap) {
        if (useDefaultKeymap) {
            keyBinds = new HashMap<>();
            initializeKeyBinds();
        }
    }

    public UserSettings(String avatarKeyString, String lang) {
        keyBinds = new HashMap<>();
        initializeKeyBinds();
        this.soundVolume = 0.75f;
        this.lang = lang;
        this.score = 0;
        this.playSFX = true;
        this.musicPath = "sfx/music/Pretty Dungeon.wav";
        this.longestSurvivalTimeSeconds = 0;
        this.kills = 0;
        this.autoReload = true;
        this.avatarKeyString = avatarKeyString;
    }

    public boolean isAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }

    public String getAvatarKeyString() {
        return avatarKeyString;
    }

    public void setAvatarKeyString(String avatarKeyString) {
        this.avatarKeyString = avatarKeyString;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getLongestSurvivalTimeSeconds() {
        return longestSurvivalTimeSeconds;
    }

    public void setLongestSurvivalTimeSeconds(int longestSurvivalTimeSeconds) {
        this.longestSurvivalTimeSeconds = longestSurvivalTimeSeconds;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public boolean isPlaySFX() {
        return playSFX;
    }

    public void setPlaySFX(boolean playSFX) {
        this.playSFX = playSFX;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
