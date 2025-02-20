package me.occure.staffplugin.item;

import dev.lone.itemsadder.api.CustomStack;

import me.occure.staffplugin.StaffPlugin;
import me.occure.staffplugin.effect.CreateMagicCircle;
import me.occure.staffplugin.effect.Effect;
import me.occure.staffplugin.effect.sound.EffectSoundPlayer;
import me.occure.staffplugin.mod.SpawnIronGolem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class StaffManager {

    private static final Map<UUID, List<IronGolem>> playerGolems = new HashMap<>();

    public static IronGolem spwnGolem(Player player, Location location, int delay) {
        AtomicReference<IronGolem> golemRef = new AtomicReference<>();
        EffectSoundPlayer soundPlayer = new EffectSoundPlayer();

        Effect effect = new CreateMagicCircle();
        effect.applyEffect(location);

        Bukkit.getScheduler().scheduleSyncDelayedTask(StaffPlugin.instance, ()->{
            IronGolem golem = SpawnIronGolem.spawnIronGolem(location);
            golemRef.set(golem);

            playerGolems.computeIfAbsent(player.getUniqueId(), k -> new ArrayList<>()).add(golem);

            soundPlayer.playSound(player, location, 1.0f, 1.0f);

            Bukkit.getScheduler().scheduleSyncDelayedTask(StaffPlugin.instance, effect::removeEffect,10L);
        }, delay);

        return golemRef.get();
    }

    public static void removeGolem(Player player, IronGolem golem) {
        List<IronGolem> golems = playerGolems.get(player.getUniqueId());
        if (golems != null) {
            golems.remove(golem);
        }
    }

    public static List<IronGolem> getGolems(Player player){
        return playerGolems.getOrDefault(player.getUniqueId(), Collections.emptyList());
    }
    public static Map<UUID, List<IronGolem>> getPlayerGolems(){
        return playerGolems;
    }
    public static Location getStaffInteractLoc(Player player){
        return player.getEyeLocation().add(player.getLocation().getDirection().multiply(10));
    }
}
