package me.enzol.minecraft.roguelike.common.character.statistics.impl;

import lombok.Getter;
import lombok.Setter;
import me.enzol.minecraft.roguelike.api.character.statistics.ScalableStatistic;

@Getter @Setter
public class HealthStatistic extends ScalableStatistic {

    public static final int DEFAULT_HEALTH = 10;

    public HealthStatistic() {
        super("Health", DEFAULT_HEALTH);
    }

    public HealthStatistic(int baseValue) {
        super("Health", baseValue);
    }
}
