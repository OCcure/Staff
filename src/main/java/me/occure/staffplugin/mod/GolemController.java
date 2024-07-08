package me.occure.staffplugin.mod;

import me.occure.staffplugin.StaffPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GolemController implements ModController<IronGolem>{

    private Map<IronGolem,Integer> taskMap = new HashMap<>();

    @Override
    public void moveToLoc(Location location, List <IronGolem> golems) {
        for(IronGolem golem : golems){
            if(golem != null) {
                golem.setAI(true);
                golem.setTarget(null);
                golem.getPathfinder().moveTo(location);
                int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(StaffPlugin.instance, new Runnable() {
                    @Override
                    public void run() {
                        if(golem.getLocation().distance(location) < 1.5 ){
                            golem.setAI(false);
                            Integer id = taskMap.get(golem);
                            if(id != null){
                                Bukkit.getScheduler().cancelTask(taskMap.get(golem));
                                taskMap.remove(golem);
                            }
                        }
                    }
                }, 0L, 20L);
                taskMap.put(golem,taskId);
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
    public void surroundEntity(LivingEntity target, List <IronGolem> golems) {
        Location targetLoc = target.getLocation();
        int numGolems = golems.size();
        double radius = (5 * numGolems - 1) / (2 * Math.PI);
        double angleValue = 2 * Math.PI / numGolems;

        for(int i = 0; i < numGolems; i++){
            double angle = i * angleValue;
            double x = targetLoc.getX() + radius * Math.cos(angle);
            double z = targetLoc.getZ() + radius * Math.sin(angle);
            Location surroundLoc = new Location(targetLoc.getWorld(), x ,targetLoc.getY(),z);
            moveToLoc(surroundLoc, List.of(golems.get(i)));
        }
    }

}
