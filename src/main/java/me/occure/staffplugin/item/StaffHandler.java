package me.occure.staffplugin.item;

import dev.lone.itemsadder.api.CustomStack;
import me.occure.staffplugin.mod.GolemController;
import me.occure.staffplugin.mod.ModController;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class StaffHandler implements Listener {

    @EventHandler
    public void onStaffInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        ModController<IronGolem> controller = new GolemController();

        if(item != null){
            CustomStack customItem = CustomStack.byItemStack(player.getInventory().getItemInMainHand());
            if(customItem != null && customItem.getId().equals("custom_staff")){
                if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                    if(player.isSneaking()){
                        Location spawnLoc = StaffManager.getStaffInteractLoc(player);
                        safeSpawnLoc(player, spawnLoc);
                    } else {
                        Location targetLoc = StaffManager.getStaffInteractLoc(player);
                        List<IronGolem> golems = StaffManager.getGolems(player);
                        if(golems != null && !golems.isEmpty()){
                            controller.moveToLoc(targetLoc, golems);
                        }
                    }
                } else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    LivingEntity target = controller.getTargetEntity(player);
                    if(target != null) {
                        List<IronGolem> golems = StaffManager.getGolems(player);
                        if (player.isSneaking()){
                            controller.surroundEntity(target,golems);
                        }else {
                            controller.attackEntity(target, golems);
                        }
                    }

                }
            }
        }
    }
    @EventHandler
    public void onGolemDeath(EntityDeathEvent event){
        if(event.getEntity() instanceof IronGolem){
            IronGolem golem = (IronGolem) event.getEntity();
            UUID playerUUID = null;
            for(UUID uuid : StaffManager.getPlayerGolems().keySet()) {
                List<IronGolem> golems = StaffManager.getPlayerGolems().get(uuid);
                if(golems != null && golems.contains(golem)){
                    playerUUID = uuid;
                    break;
                }
            }
            if(playerUUID != null){
                StaffManager.removeGolem(Bukkit.getPlayer(playerUUID), golem);
            }
        }
    }

    private void safeSpawnLoc(Player player , Location spawnLoc) {
        if (spawnLoc.getBlock().getType() == Material.AIR) {
            StaffManager.spwnGolem(player, spawnLoc, 60);
        } else {
            for(int i = 0 ; i <= 10 ; i++){
                Location safeLoc = spawnLoc.clone().set(spawnLoc.getX(), spawnLoc.getY() + i, spawnLoc.getZ());
                if(safeLoc.getBlock().getType() == Material.AIR){
                    StaffManager.spwnGolem(player, safeLoc, 60);
                    player.sendMessage("반복 횟수 :" + i);
                    break;
                }
            }
        }

    }

}
