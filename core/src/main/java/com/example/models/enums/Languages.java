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
    NEW_USERNAME("New Username", "Nieuw Gebruikersnaam"),
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
    YOUR_SCORE("Your Score", "Jouw Punten"),
    SCORE("Score", "Punten"),
    RANK("Rank", "Rang"),
    KILLS("Kills", "Kills"),
    SURVIVAL_TIME("Survival Time", "Langste Overleving"),
    TOGGLE_SFX("Toggle SFX", "SFX aan/uit"),
    LOGGED_IN_AS_GUEST("Logged In as Guest", "Ingelogd als gast"),
    MUSIC_VOLUME("Music Volume", "Muziekvolume"),
    TOGGLE_AUTO_RELOAD("Toggle Auto Reload", "Automatisch herladen aan/uit"),
    CHANGE_MUSIC("Change Music", "Muziek veranderen"),
    GO_BACK("Go Back", "Ga terug"),
    CONTROLS("Controls", "Besturing"),
    UP_KEY("upKey", "omhoogToets"),
    DOWN_KEY("downKey", "omlaagToets"),
    LEFT_KEY("leftKey", "linkerToets"),
    RIGHT_KEY("rightKey", "rechterToets"),
    RELOAD_KEY("reloadKey", "herlaadToets"),
    REDUCE_TIME_CHEAT("reduceTimeCheat", "verminderTijdCheat"),
    PAUSE_KEY("pauseKey", "pauzeToets"),
    LEVEL_UP_CHEAT("levelUpCheat", "verhoogNiveauCheat"),
    INVINCIBILITY_CHEAT("invincibilityCheat", "onkwetsbaarCheat"),
    ADD_HP_CHEAT("addHpCheat", "voegHpToeCheat"),
    AIMBOT_KEY("aimbotKey", "aimbotToets"),
    GO_TO_BOSS_CHEAT("goToBossCheat", "gaNaarBaasCheat"),
    PRESS_A_NEW_KEY("Press A New Key", "Druk op een nieuwe toets"),
    DELETE_ACCOUNT("Delete Account", "Account Verwijderen"),
    AVATAR_MENU("Avatar Menu", "Avatar Menu"),
    SORT_BY_SCORE("Sort By Score", "Sorteren op punten"),
    SORT_BY_NAME("Sort By Name", "Sorteren op Gebruikersnaam"),
    SORT_BY_KILLS("Sort By Kills", "Sorteren op kills"),
    SORT_BY_SURVIVAL_TIME("Sort By Survival Time", "Sorteren op Langste Overleving"),
    SORTED_BY("Sorted By", "Gesorteerd op")
    ;

    final public String english;
    final public String dutch;

    Languages(String english, String dutch) {
        this.english = english;
        this.dutch = dutch;
    }

    public static Languages getLangObj(String text) {
        for (Languages lang : Languages.values()) {
            if (lang.english.equals(text)) {
                return lang;
            }
            if (lang.dutch.equals(text)) {
                return lang;
            }
        }
        return null;
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
