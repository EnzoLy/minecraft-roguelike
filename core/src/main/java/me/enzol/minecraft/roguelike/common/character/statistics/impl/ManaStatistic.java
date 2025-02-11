package me.enzol.minecraft.roguelike.common.character.statistics.impl;

import me.enzol.minecraft.roguelike.api.character.statistics.ScalableStatistic;

public class ManaStatistic extends ScalableStatistic {
    public static final int DEFAULT_MANA = 50;

    public ManaStatistic() {
        super("Mana", DEFAULT_MANA);
    }
}
