package me.enzol.minecraft.roguelike.api.character;

import me.enzol.minecraft.roguelike.api.character.statistics.CharacterStatistic;

import java.util.Set;

public interface CharacterClass {

    String getName();

    Set<CharacterStatistic> getStatistics();
}
