package com.example.models;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.example.models.enums.Languages;

public class UIHelper {

    private Stage stage;
    private Skin skin;

    public UIHelper(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
    }

    public void showErrorDialog(String message) {

        Dialog errorDialog = new Dialog(Languages.ERROR.translate(), skin) {
            @Override
            protected void result(Object object) {

            }
        };

        errorDialog.text(message);
        errorDialog.button(Languages.CONFIRM.translate());

        errorDialog.show(stage);
    }
}
