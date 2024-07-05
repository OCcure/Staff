package me.occure.staffplugin.effect;

import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;


public class CreateMagicCircle implements Effect{

    private final CustomStack magicCircle = CustomStack.getInstance("my_items:magic_circle");

    @Override
    public void applyEffect(Location location) {

        World world = location.getWorld();
        Vector direction = location.getDirection().normalize();

        location.setDirection(direction);
        location.setPitch(0);
        location.add(0,2,0);

        world.spawn(location , ItemDisplay.class, display ->{

            display.setItemStack(magicCircle.getItemStack());
            Transformation transformation = display.getTransformation();
            transformation.getScale().add(3f,3f,-0.75f);
            display.setTransformation(transformation);

        });


    }

    @Override
    public void removeEffect(Entity entity) {

    }

}
