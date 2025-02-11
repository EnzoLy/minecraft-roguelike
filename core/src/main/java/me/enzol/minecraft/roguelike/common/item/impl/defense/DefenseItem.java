package me.enzol.minecraft.roguelike.common.item.impl.defense;

import com.google.gson.JsonObject;
import me.enzol.minecraft.roguelike.api.character.statistics.CharacterStatistic;
import me.enzol.minecraft.roguelike.common.character.statistics.impl.DefenseStatistic;
import me.enzol.minecraft.roguelike.common.damage.DamageType;
import me.enzol.minecraft.roguelike.common.defense.DefenseType;
import me.enzol.minecraft.roguelike.common.item.CommonItem;

import java.util.List;

public abstract class DefenseItem extends CommonItem {

    public DefenseItem(String name, List<String> description) {
        super(name, description);
    }

    public DefenseItem(JsonObject json) {
        super(json);
    }

    public abstract DefenseType getDefenseType();

    public int getDamageReduction(DamageType damageType) {
        int damageReduction = 0;
        for (CharacterStatistic statistic : getStatistics()) {
            if (statistic instanceof DefenseStatistic && damageType.isApplicable(getDefenseType())) {
                damageReduction += statistic.getBaseValue();
            }
        }
        return damageReduction;
    }
}