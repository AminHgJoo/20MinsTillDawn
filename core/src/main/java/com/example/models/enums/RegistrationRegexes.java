package com.example.models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegistrationRegexes {
    ;

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
