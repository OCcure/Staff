package me.occure.staffplugin;

import me.occure.staffplugin.item.StaffHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffPlugin extends JavaPlugin {

    public static StaffPlugin instance;


    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new StaffHandler(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
