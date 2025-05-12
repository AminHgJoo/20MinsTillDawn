package com.example.models;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.example.models.enums.Translation;

public class UIHelper {

    private final Stage stage;
    private final Skin skin;

    public UIHelper(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
    }

    public void showDialog(String message, Translation promptType) {

        Dialog dialog = new Dialog(promptType.translate(), skin) {
            @Override
            protected void result(Object object) {
            }
        };

        dialog.text(message);
        dialog.button(Translation.CONFIRM.translate());

        dialog.show(stage);
    }
}
