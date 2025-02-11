package me.enzol.minecraft.roguelike.common.character.player.characterClass.impl;

import com.google.common.collect.Sets;
import me.enzol.minecraft.roguelike.api.character.statistics.CharacterStatistic;
import me.enzol.minecraft.roguelike.common.character.player.characterClass.CommonCharacterClass;
import me.enzol.minecraft.roguelike.common.character.statistics.impl.DefenseStatistic;
import me.enzol.minecraft.roguelike.common.character.statistics.impl.HealthStatistic;

import java.util.Set;

public class TankClass extends CommonCharacterClass {

    public TankClass() {
        super("Tank");
    }

    @Override
    public Set<CharacterStatistic> getStatistics() {
        return Sets.newHashSet(
                new HealthStatistic(10),
                new DefenseStatistic(5)
        );
    }
}
