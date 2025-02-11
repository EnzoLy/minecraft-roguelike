package me.enzol.minecraft.roguelike.common.effect;

import lombok.Getter;
import me.enzol.minecraft.roguelike.api.effect.Effect;

import java.util.List;

@Getter
public abstract class CommonEffect implements Effect {

    private final String name;
    private final List<String> description;

    public CommonEffect(String name, String... description) {
        this.name = name;
        this.description = List.of(description);
    }

}
