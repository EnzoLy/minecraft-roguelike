package me.enzol.minecraft.roguelike.common.character.statistics.impl;

import lombok.Getter;
import lombok.Setter;
import me.enzol.minecraft.roguelike.api.character.statistics.CharacterStatistic;

@Getter @Setter
public class AttackSpeedStatistic implements CharacterStatistic {
    public static final int DEFAULT_ATTACK_SPEED = 1; // Attacks per second

    private final String name = "Attack Speed";
    private final int baseValue;
    private int currentValue;

    public AttackSpeedStatistic() {
        this.baseValue = DEFAULT_ATTACK_SPEED;
        this.currentValue = DEFAULT_ATTACK_SPEED;
    }

    public AttackSpeedStatistic(int baseValue) {
        this.baseValue = baseValue;
        this.currentValue = baseValue;
    }

    @Override
    public void resetToBaseValue() {
        this.currentValue = this.baseValue;
    }
}
