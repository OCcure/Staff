package me.occure.staffplugin.effect.sound;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;



public class EffectSoundPlayer {

    private final ProtocolManager protocolManager;

    public EffectSoundPlayer() {
        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    public void playSound(Player player, Location location, float volume, float pitch) {

        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.NAMED_SOUND_EFFECT);

        packet.getSoundCategories()
                .write(0, EnumWrappers.SoundCategory.MASTER);
        packet.getSoundEffects().write(0, Sound.ENTITY_LIGHTNING_BOLT_IMPACT);
        packet.getIntegers()
                .write(0, (int) (location.getX() * 8.0))
                .write(1, (int) (location.getY() * 8.0))
                .write(2, (int) (location.getZ() * 8.0));
        packet.getFloat()
                .write(0, volume)
                .write(1, pitch);
        packet.getLongs()
                .write(0, 0L);

        protocolManager.sendServerPacket(player, packet);
    }
}

