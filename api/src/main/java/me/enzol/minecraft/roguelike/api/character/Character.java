package me.enzol.minecraft.roguelike.api.character;

import me.enzol.minecraft.roguelike.api.character.skill.Skill;
import me.enzol.minecraft.roguelike.api.character.statistics.CharacterStatistic;
import me.enzol.minecraft.roguelike.api.effect.Effect;
import me.enzol.minecraft.roguelike.api.item.Item;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Character {

    String getName();

    int getLevel();

    long getExperience();

    Set<Skill> getSkills();

    void addExperience(long experience);

    void addSkill(Skill skill);

    void removeSkill(Skill skill);

    Map<String, CharacterStatistic> getStatistics();

    void addStatistic(CharacterStatistic statistic);

    void removeStatistic(CharacterStatistic statistic);

    CharacterStatistic getStatistic(String name);

    CharacterStatistic getStatistic(Class<? extends CharacterStatistic> clazz);

    CharacterClass getCharacterClass();

    void setCharacterClass(CharacterClass characterClass);

    CharacterEquipment getEquipment();

    Set<Effect> getActiveEffects();
}
