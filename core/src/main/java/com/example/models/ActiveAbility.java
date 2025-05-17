package com.example.models;

import com.example.models.enums.types.AbilityTypes;

public class ActiveAbility {
    private AbilityTypes type;
    private float duration;

    public ActiveAbility() {
    }

    public ActiveAbility(AbilityTypes type, float duration) {
        this.type = type;
        this.duration = duration;
    }

    public AbilityTypes getType() {
        return type;
    }

    public void setType(AbilityTypes type) {
        this.type = type;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void decreaseDuration(float duration) {
        this.duration -= duration;
    }

    @Override
    public String toString() {
        return type.name;
    }
}
