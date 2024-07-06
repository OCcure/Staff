package me.occure.staffplugin.mod;

import me.occure.staffplugin.StaffPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;


import java.util.List;

public class GolemController implements ModController<IronGolem>{

    @Override
    public void moveToLoc(Location location, List <IronGolem> golems) {
        for(IronGolem golem : golems){
            if(golem != null) {
                golem.setAI(true);
                golem.setTarget(null);
                golem.getPathfinder().moveTo(location);
                Bukkit.getScheduler().scheduleSyncRepeatingTask(StaffPlugin.instance, new Runnable() {
                    @Override
                    public void run() {
                        if(golem.getLocation().distance(location) < 1 ){
                            golem.setAI(false);
                            this.cancel();
                        }
                    }
                    private void cancel() {
                        Bukkit.getScheduler().cancelTask(this.hashCode());
                    }
                }, 0L, 20L);
            }
        }
    }

    @Override
    public void attackEntity(LivingEntity target, List <IronGolem> golems) {
        for (IronGolem golem : golems) {
            if (golem != null) {
                golem.setAI(true);
                golem.setTarget(target);
            }
        }
    }

    @Override
    public void surroundEntity(LivingEntity target, double distance, List <IronGolem> golems) {
        Location targetLoc = target.getLocation();
        int numGolems = golems.size();
        double angleIncrement = 2 * Math.PI / numGolems;

        for(int i = 0; i < numGolems; i++){
            double angle = i * angleIncrement;
            double x = targetLoc.getX() + distance * Math.cos(angle);
            double z = targetLoc.getZ() + distance * Math.sin(angle);
            Location surroundLoc = new Location(targetLoc.getWorld(), x ,targetLoc.getY(),z);
            moveToLoc(surroundLoc, golems);
        }
    }

}
