package com.example.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.example.models.GameData;

import java.io.File;

public class GameSaveUtil {
    public static GameData loadGame(int userId) {
        FileHandle fileHandle = Gdx.files.absolute("../saved_data/games/user_" + userId + "_save.json");

        if (!fileHandle.exists()) {
            return null;
        }

        Json json = new Json();
        GameData gameData = null;

        try {
            gameData = json.fromJson(GameData.class, fileHandle.readString());
            gameData.loadTransientObjects();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading user ID " + userId + "'s saved game.");
        }

        return gameData;
    }

    /**
     * @author AminHg
     * @apiNote Saves the passed game object. Overrides the last save if existent.
     * */
    public static void saveGame(GameData gameData, int userId) {

        Json json = new Json();

        String jsonString = json.prettyPrint(gameData);

        File dataDir = new File("../saved_data/games");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        FileHandle fileHandle = Gdx.files.absolute("../saved_data/games/user_" + userId + "_save.json");
        fileHandle.writeString(jsonString, false);
    }
}
