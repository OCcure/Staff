package me.occure.staffplugin.effect;

import org.bukkit.Location;
import org.bukkit.entity.Entity;


public interface Effect {

    void applyEffect(Location location);

    void removeEffect(Entity entity);

}
