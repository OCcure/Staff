package me.occure.staffplugin;

import me.occure.staffplugin.command.StaffCommand;
import me.occure.staffplugin.item.StaffHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getCommandMap().register("", new StaffCommand());
        getServer().getPluginManager().registerEvents(new StaffHandler(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
