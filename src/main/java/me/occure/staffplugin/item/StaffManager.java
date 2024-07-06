package me.occure.staffplugin.item;

import dev.lone.itemsadder.api.CustomStack;

import me.occure.staffplugin.StaffPlugin;
import me.occure.staffplugin.effect.CreateMagicCircle;
import me.occure.staffplugin.effect.Effect;
import me.occure.staffplugin.mod.TestIronGolem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class StaffManager {

    private static final Map<UUID, List<IronGolem>> playerGolems = new HashMap<>();

    public static void staff(Player player){
        CustomStack stack = CustomStack.getInstance("my_items:custom_staff");
        if(stack != null){
            ItemStack item = stack.getItemStack();
            player.getInventory().addItem(item);
        }else {
            player.sendMessage("custom item null");
        }

    }

    public static Location getStaffInteractLoc(Player player){
        return player.getEyeLocation().add(player.getLocation().getDirection().multiply(10));
    }

    public static IronGolem spwnGolem(Player player, Location location, int delay) {
        AtomicReference<IronGolem> golemRef = new AtomicReference<>();

        Effect effect = new CreateMagicCircle();
        effect.applyEffect(location);

        Bukkit.getScheduler().scheduleSyncDelayedTask(StaffPlugin.instance, ()->{
            IronGolem golem = TestIronGolem.spawnIronGolem(location);
            golemRef.set(golem);

            playerGolems.computeIfAbsent(player.getUniqueId(), k -> new ArrayList<>()).add(golem);

            Bukkit.getLogger().warning("" + playerGolems);

            Bukkit.getScheduler().scheduleSyncDelayedTask(StaffPlugin.instance, effect::removeEffect,10L);
        }, delay);

        return golemRef.get();
    }

    public static List<IronGolem> getPlayerGolems(Player player){
        return playerGolems.getOrDefault(player.getUniqueId(), Collections.emptyList());
    }
}
