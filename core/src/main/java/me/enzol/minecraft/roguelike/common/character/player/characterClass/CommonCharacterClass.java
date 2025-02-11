package me.enzol.minecraft.roguelike.common.character.player.characterClass;

import lombok.Getter;
import me.enzol.minecraft.roguelike.api.character.CharacterClass;

@Getter
public abstract class CommonCharacterClass implements CharacterClass {

    private final String name;

    public CommonCharacterClass(String name) {
        this.name = name;
    }

}
