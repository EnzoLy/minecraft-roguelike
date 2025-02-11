package me.enzol.minecraft.roguelike.common.character.equipment;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.enzol.minecraft.roguelike.api.character.CharacterEquipment;
import me.enzol.minecraft.roguelike.api.item.Item;
import me.enzol.minecraft.roguelike.common.item.impl.damage.DamageItem;
import me.enzol.minecraft.roguelike.common.item.impl.defense.DefenseItem;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
public abstract class CommonCharacterEquipment implements CharacterEquipment {

    private final Map<ArmorEquipmentSlot, DefenseItem> armor = Maps.newHashMap();
    private DamageItem weapon;
    private final List<Item> content = Lists.newArrayList();

    public CommonCharacterEquipment(JsonObject json) {
        if (json.has("armor")) {
            JsonArray armorJson = json.getAsJsonArray("armor");

            for (JsonElement armorElement : armorJson) {
                JsonObject armorItemJson = armorElement.getAsJsonObject();
                try {
                    Class<? extends DefenseItem> defenseItemClass = Class.forName(armorItemJson.get("class").getAsString())
                            .asSubclass(DefenseItem.class);
                    DefenseItem defenseItem = defenseItemClass.getDeclaredConstructor(JsonObject.class)
                            .newInstance(armorItemJson);

                    this.armor.put(ArmorEquipmentSlot.valueOf(armorItemJson.get("slot").getAsString()), defenseItem);
                } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                         InstantiationException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (json.has("weapon")) {
            JsonObject weaponJson = json.getAsJsonObject("weapon");
            try {
                Class<? extends DamageItem> damageItemClass = Class.forName(weaponJson.get("class").getAsString())
                        .asSubclass(DamageItem.class);
                DamageItem damageItem = damageItemClass.getDeclaredConstructor(JsonObject.class)
                        .newInstance(weaponJson);

                this.weapon = damageItem;
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }

        if (json.has("content")) {
            JsonArray contentJson = json.getAsJsonArray("content");
            for (JsonElement itemElement : contentJson) {
                JsonObject itemJson = itemElement.getAsJsonObject();
                try {
                    Class<? extends Item> itemClass = Class.forName(itemJson.get("class").getAsString())
                            .asSubclass(Item.class);
                    Item item = itemClass.getDeclaredConstructor(JsonObject.class)
                            .newInstance(itemJson);

                    this.content.add(item);
                } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                         InstantiationException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public JsonObject serialize() {
        JsonObject json = new JsonObject();

        JsonObject armorJson = new JsonObject();
        for (ArmorEquipmentSlot armorEquipmentSlot : this.armor.keySet()) {
            armorJson.add(armorEquipmentSlot.name(), this.armor.get(armorEquipmentSlot).serialize());
        }

        json.add("armor", armorJson);

        if (this.weapon != null) {
            json.add("weapon", this.weapon.serialize());
        }

        JsonArray contentJson = new JsonArray();
        for (Item item : this.content) {
            contentJson.add(item.serialize());
        }

        json.add("content", contentJson);

        return json;
    }

}
