package me.enzol.minecraft.roguelike.api.character.statistics;

public interface CharacterStatistic {

    String getName();

    int getBaseValue();
    int getCurrentValue();

    void setCurrentValue(int value);

    void resetToBaseValue();
}
