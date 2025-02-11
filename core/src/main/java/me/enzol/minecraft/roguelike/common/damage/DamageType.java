package me.enzol.minecraft.roguelike.common.damage;

import me.enzol.minecraft.roguelike.common.defense.DefenseType;

public enum DamageType {

    MAGIC,
    PHYSICAL,
    TRUE;

    public boolean isApplicable(DefenseType damageType) {
        if (this == TRUE) return true;

        return switch (damageType) {
            case MAGIC -> this == MAGIC;
            case PHYSICAL -> this == PHYSICAL;
        };
    }

}
