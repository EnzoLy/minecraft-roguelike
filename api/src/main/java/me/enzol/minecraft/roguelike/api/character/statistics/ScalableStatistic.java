package me.enzol.minecraft.roguelike.api.character.statistics;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class ScalableStatistic implements CharacterStatistic {
    protected final String name;
    protected final int baseValue;
    protected int currentValue;

    public ScalableStatistic(String name, int baseValue) {
        this.name = name;
        this.baseValue = baseValue;
        this.currentValue = baseValue;
    }

    @Override
    public void resetToBaseValue() {
        this.currentValue = this.baseValue;
    }

    public void applyBuff(int amount) {
        this.currentValue += amount;
    }

    public void applyDebuff(int amount) {
        this.currentValue -= amount;
        if (this.currentValue < 0) this.currentValue = 0;
    }
}
