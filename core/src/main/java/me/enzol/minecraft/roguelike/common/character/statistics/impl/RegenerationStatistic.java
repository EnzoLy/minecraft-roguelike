package me.enzol.minecraft.roguelike.common.character.statistics.impl;

import me.enzol.minecraft.roguelike.api.character.statistics.ScalableStatistic;

public class RegenerationStatistic extends ScalableStatistic {
    public static final int DEFAULT_REGEN = 1;

    public RegenerationStatistic() {
        super("Regeneration", DEFAULT_REGEN);
    }
}
