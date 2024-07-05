package me.occure.staffplugin.command;

import me.occure.staffplugin.item.StaffManager;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StaffCommand extends BukkitCommand {

    public StaffCommand() {
        super("staff");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if(!(sender instanceof Player player)) {
        return false;
        }
        StaffManager.staff(player);
        return true;
    }
}
