package me.enzol.minecraft.roguelike.api.character;

import me.enzol.minecraft.roguelike.api.item.Item;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CharacterEquipment {

    Map<ArmorEquipmentSlot, ? extends Item> getArmor();

    Item getWeapon();

    List<Item> getContent();

    public enum ArmorEquipmentSlot {
        HEAD,
        BODY,
        LEGS,
        FEET
    }

}
