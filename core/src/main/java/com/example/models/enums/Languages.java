package com.example.models.enums;

import com.example.models.AppData;

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
    NEW_PASSWORD("New Password", "Nieuw Wachtwoord"),
    SECURITY_QUESTION("Security Question", "Beveiligingsvraag"),
    SECURITY_ANSWER("Security Answer", "Beveiligingsantwoord"),
    USER_SUCCESSFULLY_REGISTERED("User successfully registered", "Gebruiker succesvol geregistreerd"),
    ERROR("Error", "Foutmelding"),
    SUCCESS("Success", "Success"),
    USERNAME_ALREADY_EXISTS("Username already exists", "Gebruikersnaam bestaat al"),
    PASSWORD_IS_WEAK("Password is weak", "Wachtwoord is zwak"),
    INVALID_USERNAME("Invalid Username", "Ongeldige gebruikersnaam"),
    WELCOME("Welcome", "Wilkommen"),
    GUEST("Guest", "Gast"),
    SETTINGS("Settings", " Instellingen"),
    PROFILE("Profile", "Profiel"),
    PRE_GAME_MENU("Pre-Game Menu", "Pre-spelmenu"),
    LEADERBOARD("Leaderboard", "Ranglijst"),
    TALENTS("Talents", "Talenten"),
    LOAD_FROM_SAVE("Load From Save", "Laad opgeslagen spel"),
    LOGOUT("Logout", "Uitloggen"),
    ACCOUNT_RECOVERY("Account Recovery", "Accountherstel"),
    GO_TO_REGISTRATION("Go to Registeration", "Ga naar Registratie"),
    USER_DOESNT_EXIST("User does not exist", "Gebruiker bestaat niet"),
    WRONG_PASSWORD("Wrong password", "Verkeerd wachtwoord"),
    WRONG_QUESTION("Wrong question", "Verkeerde beveiligingsvraag"),
    WRONG_ANSWER("Wrong answer", "Verkeerd antwoord"),
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
