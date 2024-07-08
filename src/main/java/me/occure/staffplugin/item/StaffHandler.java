package me.occure.staffplugin.item;

import dev.lone.itemsadder.api.CustomStack;
import me.occure.staffplugin.mod.GolemController;
import me.occure.staffplugin.mod.ModController;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.List;

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
                        StaffManager.spwnGolem(player, spawnLoc,60);
                    } else {
                        Location targetLoc = StaffManager.getStaffInteractLoc(player);
                        List<IronGolem> golems = StaffManager.getPlayerGolems(player);
                        if(golems != null && !golems.isEmpty()){
                            controller.moveToLoc(targetLoc, golems);
                        }
                    }
                } else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                    LivingEntity target = getTargetEntity(player);
                    if(target != null) {
                        List<IronGolem> golems = StaffManager.getPlayerGolems(player);
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
    private LivingEntity getTargetEntity (Player player) {
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();
        World world = player.getWorld();

        RayTraceResult result = world.rayTraceEntities(eyeLocation, direction, 50, entity -> entity instanceof LivingEntity && !entity.equals(player));

        if (result != null) {
            if (result.getHitEntity() instanceof LivingEntity) {
                return (LivingEntity) result.getHitEntity();
            }
        }

        return null;
    }
}
