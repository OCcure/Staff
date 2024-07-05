package me.occure.staffplugin.item;

import dev.lone.itemsadder.api.CustomStack;
import me.occure.staffplugin.effect.CreateMagicCircle;
import me.occure.staffplugin.effect.Effect;
import me.occure.staffplugin.mod.TestIronGoLem;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class StaffHandler implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
            && item != null && player.isSneaking()){

            CustomStack customStack = CustomStack.byItemStack(item);
            if(customStack != null) {
                if (customStack.getId().equals("custom_staff")) {
                    Effect effect = new CreateMagicCircle();
                    Location StaffInteractLoc = StaffManager.getStaffInteractLoc(player);
                    effect.applyEffect(StaffInteractLoc);
                    TestIronGoLem.spawnIronGoLem(StaffInteractLoc);
                }
            }
        }
    }
}
