package me.occure.staffplugin.mod;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;

public class TestIronGolem {

    public static IronGolem spawnIronGolem(Location location) {
        World world = location.getWorld();
        IronGolem golem = (IronGolem) world.spawnEntity(location , EntityType.IRON_GOLEM);
        return golem;
    }
}
