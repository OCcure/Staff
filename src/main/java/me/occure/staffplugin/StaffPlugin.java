package me.occure.staffplugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.occure.staffplugin.item.StaffHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class StaffPlugin extends JavaPlugin {

    public static StaffPlugin instance;
    private static ProtocolManager protocolManager;

    public static @NotNull Optional<ProtocolManager> getProtocolManger(){
        return Optional.ofNullable(protocolManager);
    }

    @Override
    public void onEnable() {
        instance = this;
        protocolManager = ProtocolLibrary.getProtocolManager();

        getServer().getPluginManager().registerEvents(new StaffHandler(),this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
