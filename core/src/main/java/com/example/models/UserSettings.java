package com.example.models;

import com.badlogic.gdx.Input;

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
    private int pauseButton;
    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;
    private int shootKey;
    private int aimbotKey;
    private int reloadKey;

    private int reduceTimeCheat;
    private int levelUpCheat;
    private int addHpCheat;
    private int goToBossCheat;
    private int invincibilityCheat;

    public UserSettings() {
    }

    public UserSettings(String avatarKeyString, String lang) {
        this.soundVolume = 1;
        this.lang = lang;
        this.upKey = Input.Keys.W;
        this.shootKey = Input.Buttons.LEFT;
        this.score = 0;
        this.rightKey = Input.Keys.D;
        this.reloadKey = Input.Keys.R;
        this.reduceTimeCheat = Input.Keys.Q;
        this.playSFX = true;
        this.pauseButton = Input.Keys.ESCAPE;
        this.musicPath = "sfx/music/Pretty Dungeon.wav";
        this.longestSurvivalTimeSeconds = 0;
        this.levelUpCheat = Input.Keys.L;
        this.leftKey = Input.Keys.A;
        this.kills = 0;
        this.invincibilityCheat = Input.Keys.I;
        this.addHpCheat = Input.Keys.H;
        this.aimbotKey = Input.Keys.SPACE;
        this.autoReload = true;
        this.avatarKeyString = avatarKeyString;
        this.downKey = Input.Keys.S;
        this.goToBossCheat = Input.Keys.G;
    }

    public int getAddHpCheat() {
        return addHpCheat;
    }

    public void setAddHpCheat(int addHpCheat) {
        this.addHpCheat = addHpCheat;
    }

    public int getAimbotKey() {
        return aimbotKey;
    }

    public void setAimbotKey(int aimbotKey) {
        this.aimbotKey = aimbotKey;
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

    public int getDownKey() {
        return downKey;
    }

    public void setDownKey(int downKey) {
        this.downKey = downKey;
    }

    public int getGoToBossCheat() {
        return goToBossCheat;
    }

    public void setGoToBossCheat(int goToBossCheat) {
        this.goToBossCheat = goToBossCheat;
    }

    public int getInvincibilityCheat() {
        return invincibilityCheat;
    }

    public void setInvincibilityCheat(int invincibilityCheat) {
        this.invincibilityCheat = invincibilityCheat;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(int leftKey) {
        this.leftKey = leftKey;
    }

    public int getLevelUpCheat() {
        return levelUpCheat;
    }

    public void setLevelUpCheat(int levelUpCheat) {
        this.levelUpCheat = levelUpCheat;
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

    public int getPauseButton() {
        return pauseButton;
    }

    public void setPauseButton(int pauseButton) {
        this.pauseButton = pauseButton;
    }

    public boolean isPlaySFX() {
        return playSFX;
    }

    public void setPlaySFX(boolean playSFX) {
        this.playSFX = playSFX;
    }

    public int getReduceTimeCheat() {
        return reduceTimeCheat;
    }

    public void setReduceTimeCheat(int reduceTimeCheat) {
        this.reduceTimeCheat = reduceTimeCheat;
    }

    public int getReloadKey() {
        return reloadKey;
    }

    public void setReloadKey(int reloadKey) {
        this.reloadKey = reloadKey;
    }

    public int getRightKey() {
        return rightKey;
    }

    public void setRightKey(int rightKey) {
        this.rightKey = rightKey;
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

    public int getShootKey() {
        return shootKey;
    }

    public void setShootKey(int shootKey) {
        this.shootKey = shootKey;
    }

    public int getUpKey() {
        return upKey;
    }

    public void setUpKey(int upKey) {
        this.upKey = upKey;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
