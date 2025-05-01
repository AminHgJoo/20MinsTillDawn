package com.example.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegistrationRegexes {
    CAPITAL_LETTER("[A-Z]"),
    LOWERCASE_LETTER("[a-z]"),
    NUMBER("[0-9]"),
    SPECIAL_CHARACTER("[@%$#&*()]");

    final private String regex;

    RegistrationRegexes(String regex) {
        this.regex = regex;
    }

    private Matcher getMatcher(String input) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
    }

    public boolean isValid(String input) {
        return getMatcher(input).find();
    }
}
