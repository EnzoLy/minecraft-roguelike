package me.enzol.minecraft.roguelike.common.item;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import me.enzol.minecraft.roguelike.api.item.Item;

import java.util.Iterator;
import java.util.List;

@Getter @Setter
public abstract class CommonItem implements Item {

    private final String name;
    private final List<String> description;

    public CommonItem(String name, List<String> description) {
        this.name = name;
        this.description = description;
    }

    public CommonItem(JsonObject json) {
        this.name = json.get("name").getAsString();
        this.description = Lists.newArrayList();
        for (JsonElement description : json.get("description").getAsJsonArray()) {
            this.description.add(description.getAsString());
        }
    }

    public JsonObject serialize() {
        JsonObject json = new JsonObject();
        json.addProperty("name", this.name);
        JsonArray description = new JsonArray();

        for (String descriptionLine : this.description) {
            description.add(descriptionLine);
        }

        json.add("description", description);

        json.addProperty("class", this.getClass().getName());
        return json;
    }
}