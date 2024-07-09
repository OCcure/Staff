package me.occure.staffplugin.mod;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;


import java.util.List;


public interface ModController <T extends Mob>{

    void moveToLoc(Location location , List<T> Mod );

    void attackEntity(LivingEntity target, List<T> Mod);

    void surroundEntity(LivingEntity target, List<T> Mod);

    LivingEntity getTargetEntity (Player player) ;

}
