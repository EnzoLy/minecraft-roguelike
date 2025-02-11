package me.enzol.minecraft.roguelike.api.item;

import com.google.gson.JsonObject;
import me.enzol.minecraft.roguelike.api.character.statistics.CharacterStatistic;
import me.enzol.minecraft.roguelike.api.effect.Effect;

import java.util.List;
import java.util.Set;

public interface Item {

    String getName();

    List<String> getDescription();

    Set<CharacterStatistic> getStatistics();

    JsonObject serialize();

    Set<Effect> getEffects();
}
