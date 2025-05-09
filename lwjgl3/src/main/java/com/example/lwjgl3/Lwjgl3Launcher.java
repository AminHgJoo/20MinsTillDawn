package com.example.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3WindowAdapter;
import com.example.MainApp;
import com.example.models.AppData;
import com.example.views.AvatarMenu;

/**
 * Launches the desktop (LWJGL3) application.
 */
public class Lwjgl3Launcher {
    public static void main(String[] args) {
        if (StartupHelper.startNewJvmIfRequired()) return;
        createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new MainApp(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();

        configuration.setTitle("20 Minutes Till Dawn");

        configuration.useVsync(true);

        configuration.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1);

        configuration.setWindowListener(new Lwjgl3WindowAdapter() {
            @Override
            public void filesDropped(String[] files) {
                for (String file : files) {
                    System.out.println("File dropped to window: " + file);

                    if (AppData.isProgramWaitingForFileDrop) {
                        AppData.isProgramWaitingForFileDrop = false;

                        if (AppData.getCurrentScreen() instanceof AvatarMenu) {
                            AvatarMenu avatarMenu = (AvatarMenu) AppData.getCurrentScreen();

                            Gdx.app.postRunnable(() -> {
                                avatarMenu.processDraggedImage(file);
                            });
                        }
                    }
                }
            }
        });

        configuration.setWindowedMode(1920, 975);

        configuration.setWindowIcon("WingedMonster_4.png");
        return configuration;
    }
}
