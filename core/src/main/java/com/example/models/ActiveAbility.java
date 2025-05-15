package com.example.models;

import com.example.models.enums.AbilityTypes;

public class ActiveAbility {
    private final AbilityTypes type;

    public ActiveAbility(AbilityTypes type) {
        this.type = type;
    }

    public AbilityTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.name;
    }
}
