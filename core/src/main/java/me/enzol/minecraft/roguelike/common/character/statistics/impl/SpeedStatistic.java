package me.enzol.minecraft.roguelike.common.character.statistics.impl;

import me.enzol.minecraft.roguelike.api.character.statistics.ScalableStatistic;

public class SpeedStatistic extends ScalableStatistic {
    public static final int DEFAULT_SPEED = 10;

    public SpeedStatistic() {
        super("Speed", DEFAULT_SPEED);
    }
}
