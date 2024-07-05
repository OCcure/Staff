package me.occure.staffplugin.mod;

import me.occure.staffplugin.effect.CreateMagicCircle;
import me.occure.staffplugin.effect.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;

public class TestIronGoLem {

    public static void spawnIronGoLem(Location location) {
        World world = location.getWorld();
        IronGolem golem = (IronGolem) world.spawnEntity(location, EntityType.IRON_GOLEM);
    }
}
