package me.occure.staffplugin.effect;

import com.google.common.util.concurrent.AtomicDouble;
import dev.lone.itemsadder.api.CustomStack;
import me.occure.staffplugin.StaffPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;



public class CreateMagicCircle implements Effect{

    private final CustomStack magicCircle = CustomStack.getInstance("my_items:magic_circle");
    private ItemDisplay displayEntity;

    @Override
    public void applyEffect(Location location) {

        World world = location.getWorld();
        Vector direction = location.getDirection().normalize();

        location.setDirection(direction);
        location.setPitch(0);

        AtomicDouble offset = new AtomicDouble();
        displayEntity = world.spawn(location , ItemDisplay.class, display ->{
            display.setItemStack(magicCircle.getItemStack());
            Transformation transformation = display.getTransformation();
            transformation.getScale().set(0f,0f,0.55f);

            Bukkit.getScheduler().scheduleSyncRepeatingTask(StaffPlugin.instance, () -> {

                double angle =offset.getAndAdd(15);
                float radians = (float)Math.toRadians(angle);

                transformation.getLeftRotation().setAngleAxis(radians, 0,0,1);
                transformation.getScale().add(0.1f,0.1f,0f);
                display.setTransformation(transformation);

            },0,1);
        });
    }

    @Override
    public void removeEffect() {
        if(displayEntity != null){
            displayEntity.remove();
        }
    }

}
