package me.enzol.minecraft.roguelike.common.character.player.equipment;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import lombok.NoArgsConstructor;
import me.enzol.minecraft.roguelike.api.item.Item;
import me.enzol.minecraft.roguelike.common.character.equipment.CommonCharacterEquipment;

import java.util.List;

@NoArgsConstructor
public class PlayerEquipment extends CommonCharacterEquipment {

    private final List<Item> collectibles = Lists.newArrayList();

    public PlayerEquipment(JsonObject json) {
        super(json);
    }
}
