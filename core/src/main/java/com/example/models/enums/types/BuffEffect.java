package com.example.models.enums.types;

import com.example.models.GameData;

@FunctionalInterface
public interface BuffEffect {
    void execute(GameData gameData);
}
