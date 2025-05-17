package com.example.models.enums.types;

import com.example.models.ActiveAbility;
import com.example.models.GameData;

public enum AbilityTypes {

    VITALITY("Vitality", (GameData gameData) ->
    {
        gameData.getPlayer().maxHPPlusPlus();
        gameData.getPlayer().hpPlusPlus();
        gameData.getPlayer().getActiveAbilities().add(new ActiveAbility(null, Float.POSITIVE_INFINITY));
    }, (GameData gameData) -> {}),
    DAMAGER("Damager", (GameData gameData) ->
    {
        gameData.getPlayer().getWeapon().applyDmgMultiplier(1.25f);
        gameData.getPlayer().getActiveAbilities().add(new ActiveAbility(null, 10f));
    }, (GameData gameData) ->
    {
        gameData.getPlayer().getWeapon().applyDmgMultiplier(0.8f);
    }),
    PROCREASE("Procrease", (GameData gameData) -> {
        gameData.getPlayer().getWeapon().projectilePlusPlus();
        gameData.getPlayer().getActiveAbilities().add(new ActiveAbility(null, Float.POSITIVE_INFINITY));
    }, (GameData gameData) -> {}),
    AMOCREASE("Amocrease", (GameData gameData) -> {
        gameData.getPlayer().getWeapon().addMagSize(5);
        gameData.getPlayer().getActiveAbilities().add(new ActiveAbility(null, Float.POSITIVE_INFINITY));
    }, (GameData gameData) -> {}),
    SPEEDY("Speedy", (GameData gameData) ->
    {
        gameData.getPlayer().applySpeedMultiplier(2);
        gameData.getPlayer().getActiveAbilities().add(new ActiveAbility(null, 10f));
    }, (GameData gameData) ->
    {
        gameData.getPlayer().applySpeedMultiplier(0.5f);
    });

    public final String name;
    public final BuffEffect effect;
    public final BuffEffect reverseEffect;

    AbilityTypes(String name, BuffEffect effect, BuffEffect reverseEffect) {
        this.name = name;
        this.effect = effect;
        this.reverseEffect = reverseEffect;
    }
}
