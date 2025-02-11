package me.enzol.minecraft.roguelike.common.character.statistics.impl;

import me.enzol.minecraft.roguelike.api.character.statistics.ScalableStatistic;

public class DefenseStatistic extends ScalableStatistic {
    public static final int DEFAULT_DEFENSE = 5;

    public DefenseStatistic() {
        super("Defense", DEFAULT_DEFENSE);
    }

    public DefenseStatistic(int baseValue) {
        super("Defense", baseValue);
    }
}