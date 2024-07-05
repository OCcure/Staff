package me.occure.staffplugin.item;

import dev.lone.itemsadder.api.CustomStack;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StaffManager {

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
}
