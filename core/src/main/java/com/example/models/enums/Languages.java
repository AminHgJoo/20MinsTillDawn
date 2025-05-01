package com.example.models.enums;

import com.example.models.AppData;
import scala.App;

public enum Languages {

    LAUNCHER_GAME_DEV("Developed By @AminHgJoo : May 2025", "Ontwikkeld door @AminHgJoo: Mei 2025"),
    LOGIN("Login", "Inloggen"),
    REGISTER("Register", "Registeren"),
    LANGUAGE_BUTTON_TEXT("DE", "EN"),
    CHOOSE_A_USERNAME("Choose a username", "Kies een gebruikersnaam"),
    CONFIRM("Confirm", "Bevestigen"),
    PLAY_AS_GUEST("Play as Guest", "Speel als gast"),
    USERNAME("Username", "Gebruikersnaam"),
    PASSWORD("Password", "Wachtwoord"),
    SECURITY_QUESTION("Security Question", "Beveiligingsvraag"),
    SECURITY_ANSWER("Security Answer", "Beveiligingsantwoord"),
    USER_SUCCESSFULLY_REGISTERED("User successfully registered", "Gebruiker succesvol geregistreerd"),
    ERROR("Error", "Foutmelding"),
    SUCCESS("Success", "Success"),
    ;

    final public String english;
    final public String dutch;

    Languages(String english, String dutch) {
        this.english = english;
        this.dutch = dutch;
    }

    public String translate() {
        String language = AppData.getLang();

        if (language.compareToIgnoreCase("english") == 0) {
            return english;
        } else if (language.compareToIgnoreCase("dutch") == 0) {
            return dutch;
        }
        return null;
    }
}
