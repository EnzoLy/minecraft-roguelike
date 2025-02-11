package me.enzol.minecraft.roguelike.common.item.impl.damage;

import me.enzol.minecraft.roguelike.api.character.statistics.CharacterStatistic;
import me.enzol.minecraft.roguelike.common.character.statistics.impl.AttackStatistic;
import me.enzol.minecraft.roguelike.common.damage.DamageType;
import me.enzol.minecraft.roguelike.common.item.CommonItem;

import java.util.List;
import java.util.Set;

public abstract class DamageItem extends CommonItem {

    public DamageItem(String name, List<String> description) {
        super(name, description);
    }

    public abstract DamageType getDamageType();

    public int getDamageBonus() {
        int bonusDamage = 0;
        for (CharacterStatistic statistic : getStatistics()) {
            if (statistic instanceof AttackStatistic) {
                bonusDamage += statistic.getBaseValue();
            }
        }
        return bonusDamage;
    }
}