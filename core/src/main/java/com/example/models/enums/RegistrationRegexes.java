package com.example.models.enums;

import org.intellij.lang.annotations.Language;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegistrationRegexes {
    CAPITAL_LETTER("[A-Z]"),
    LOWERCASE_LETTER("[a-z]"),
    NUMBER("[0-9]"),
    SPECIAL_CHARACTERS("[@%$#&*()]");

    final private String regex;

    RegistrationRegexes(@Language("Regexp") String regex) {
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
