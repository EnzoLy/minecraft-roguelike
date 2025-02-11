package me.enzol.minecraft.roguelike.common.character.statistics.impl;

import me.enzol.minecraft.roguelike.api.character.statistics.ScalableStatistic;

public class AttackStatistic extends ScalableStatistic {
    public static final int DEFAULT_ATTACK = 10;

    public AttackStatistic() {
        super("Attack", DEFAULT_ATTACK);
    }
}
