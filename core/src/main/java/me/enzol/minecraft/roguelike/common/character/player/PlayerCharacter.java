package me.enzol.minecraft.roguelike.common.character.player;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import me.enzol.minecraft.roguelike.api.character.Character;
import me.enzol.minecraft.roguelike.api.character.CharacterClass;
import me.enzol.minecraft.roguelike.api.character.CharacterEquipment;
import me.enzol.minecraft.roguelike.api.character.skill.Skill;
import me.enzol.minecraft.roguelike.api.character.statistics.CharacterStatistic;
import me.enzol.minecraft.roguelike.api.character.statistics.ScalableStatistic;
import me.enzol.minecraft.roguelike.api.effect.Effect;
import me.enzol.minecraft.roguelike.common.character.player.characterClass.impl.TankClass;
import me.enzol.minecraft.roguelike.common.character.player.equipment.PlayerEquipment;
import me.enzol.minecraft.roguelike.common.character.statistics.impl.*;

import java.util.Map;
import java.util.Set;

@Getter @Setter
public class PlayerCharacter implements Character {

    private final String name;

    private int level;
    private long experience;

    private final Set<Skill> skills = Sets.newHashSet();

    private final Map<String, CharacterStatistic> statistics = Maps.newHashMap();

    private CharacterClass characterClass;

    private final Set<Effect> activeEffects = Sets.newHashSet();

    private PlayerEquipment equipment = new PlayerEquipment();

    public PlayerCharacter(String name) {
        this.name = name;
    }

    public void load(JsonObject json) {
        this.loadDefaultStatistics();
        setCharacterClass(new TankClass());

        setLevel(json.get("level").getAsInt());
        setExperience(json.get("experience").getAsLong());

        if (json.has("statistics")) {
            JsonObject statistics = json.getAsJsonObject("statistics");
            for (String statisticName : statistics.keySet()) {
                CharacterStatistic statistic = getStatistic(statisticName);
                if (statistic != null) {
                    statistic.setCurrentValue(statistics.get(statisticName).getAsInt());
                }
            }
        }

    }

    public JsonObject serialize() {
        JsonObject json = new JsonObject();

        json.addProperty("name", this.name);
        json.addProperty("level", this.level);
        json.addProperty("experience", this.experience);

        removeClassStatistics();

        JsonObject statistics = new JsonObject();
        for (CharacterStatistic statistic : this.statistics.values()) {
            statistics.addProperty(statistic.getName(), statistic.getCurrentValue());
        }

        applyClassStatistics(); // Apply class statistics again in case it save in playing session

        json.add("statistics", statistics);

        return json;
    }

    public void loadDefaultStatistics() {
        this.addStatistic(new HealthStatistic());
        this.addStatistic(new AttackStatistic());
        this.addStatistic(new AttackSpeedStatistic());
        this.addStatistic(new DefenseStatistic());
        this.addStatistic(new ManaStatistic());
        this.addStatistic(new RegenerationStatistic());
        this.addStatistic(new SpeedStatistic());
    }

    public void applyClassStatistics() {
        if (this.characterClass != null) {
            for (CharacterStatistic classStatistic : this.characterClass.getStatistics()) {
                ScalableStatistic playerStatistic = (ScalableStatistic) this.getStatistic(classStatistic.getName());

                playerStatistic.setCurrentValue(playerStatistic.getBaseValue());
            }
        }
    }

    public void removeClassStatistics() {
        if (this.characterClass != null) {
            for (CharacterStatistic classStatistic : this.characterClass.getStatistics()) {
                ScalableStatistic playerStatistic = (ScalableStatistic) this.getStatistic(classStatistic.getName());
                playerStatistic.setCurrentValue(playerStatistic.getCurrentValue() - classStatistic.getCurrentValue());
            }
        }
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
        applyClassStatistics();
    }

    @Override
    public void addExperience(long experience) {
        this.experience += experience;
    }

    @Override
    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    @Override
    public void removeSkill(Skill skill) {
        this.skills.remove(skill);
    }

    @Override
    public void addStatistic(CharacterStatistic statistic) {
        this.statistics.put(statistic.getName(), statistic);
    }

    @Override
    public void removeStatistic(CharacterStatistic statistic) {
        this.statistics.remove(statistic.getName());
    }

    @Override
    public CharacterStatistic getStatistic(String name) {
        return statistics.get(name);
    }

    @Override
    public CharacterStatistic getStatistic(Class<? extends CharacterStatistic> clazz) {
        return statistics.values().stream()
                .filter(statistic -> statistic.getClass().equals(clazz))
                .findFirst()
                .orElse(null);
    }
}
