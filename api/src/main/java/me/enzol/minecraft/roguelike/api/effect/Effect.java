package me.enzol.minecraft.roguelike.api.effect;

import java.util.List;

public interface Effect {

    String getName();

    List<String> getDescription();

    boolean isActive();

}
