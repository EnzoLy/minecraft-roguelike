package me.enzol.minecraft.roguelike.common.character.combat;

import lombok.extern.slf4j.Slf4j;
import me.enzol.minecraft.roguelike.api.character.Character;
import me.enzol.minecraft.roguelike.api.item.Item;
import me.enzol.minecraft.roguelike.common.item.impl.damage.DamageItem;
import me.enzol.minecraft.roguelike.common.item.impl.defense.DefenseItem;
import me.enzol.minecraft.roguelike.common.character.statistics.impl.AttackStatistic;
import me.enzol.minecraft.roguelike.common.character.statistics.impl.DefenseStatistic;
import me.enzol.minecraft.roguelike.common.character.statistics.impl.HealthStatistic;

import java.util.Collection;

@Slf4j
public class CombatTracker {

    private final Character character;

    public CombatTracker(Character character) {
        this.character = character;
    }

    public void attack(Character target) {
        AttackStatistic attackStat = (AttackStatistic) character.getStatistic(AttackStatistic.class);
        int baseDamage = (attackStat != null) ? attackStat.getCurrentValue() : 0;

        int damageBonusFromItems = 0;
        DamageItem attackerWeapon = (DamageItem) character.getEquipment().getWeapon();

        if (attackerWeapon instanceof DamageItem) {
            damageBonusFromItems += attackerWeapon.getDamageBonus();
        }

        int totalDamage = baseDamage + damageBonusFromItems;

        DefenseStatistic defenseStat = (DefenseStatistic) target.getStatistic(DefenseStatistic.class);
        int baseDefense = defenseStat.getCurrentValue();

        int damageReductionFromItems = 0;
        Collection<DefenseItem> defenderEquipment = (Collection<DefenseItem>) target.getEquipment().getArmor().values();

        for (Item item : defenderEquipment) {
            if (item instanceof DefenseItem) {
                damageReductionFromItems += ((DefenseItem) item).getDamageReduction(attackerWeapon.getDamageType());
            }
        }

        int totalDamageReduction = baseDefense + damageReductionFromItems;
        if (totalDamageReduction < 0) totalDamageReduction = 0;

        int finalDamage = totalDamage - totalDamageReduction;
        if (finalDamage < 0) finalDamage = 0;

        HealthStatistic targetHealth = (HealthStatistic) target.getStatistic(HealthStatistic.class);
        targetHealth.setCurrentValue(targetHealth.getCurrentValue() - finalDamage);
    }
}