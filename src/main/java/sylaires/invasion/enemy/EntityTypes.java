package sylaires.invasion.enemy;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import net.minecraft.server.v1_8_R3.Entity;
import sylaires.invasion.util.EnemyUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public enum EntityTypes
{
    DRUID("Druid", 51, EnemyDruid.class), //You can add as many as you want.
	SPIDER("Forest Spider", 52, EnemyForestSpider.class),
	SILVERFISH("Silverfish", 60, EnemySilverfish.class),
	PALADIN("Paladin", 54, EnemyPaladin.class),
	LOSTMINER("Lost Miner", 54, EnemyLostMiner.class),
	GHOSTMINER("Ghostly Miner", 51, EnemyGhostlyMiner.class),
	RAM("Crag Ram", 91, EnemyCragRam.class),
	WALKER("White Walker", 51, EnemyWhiteWalker.class),
	LICH("Lich", 51, EnemyLich.class);
	
    private EntityTypes(String name, int id, Class<? extends Entity> custom)
    {
        addToMaps(custom, name, id);
    }
    
    
  public static void spawnEntity(Entity entity, Location loc)
   {
     entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
     ((CraftWorld)loc.getWorld()).getHandle().addEntity(entity);
   }
  
  

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private static void addToMaps(Class clazz, String name, int id)
    {
        //getPrivateField is the method from above.
        //Remove the lines with // in front of them if you want to override default entities (You'd have to remove the default entity from the map first though).
        ((Map)EnemyUtil.getPrivateField("c", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(name, clazz);
        ((Map)EnemyUtil.getPrivateField("d", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, name);
        //((Map)getPrivateField("e", net.minecraft.server.v1_7_R4.EntityTypes.class, null)).put(Integer.valueOf(id), clazz);
        ((Map)EnemyUtil.getPrivateField("f", net.minecraft.server.v1_8_R3.EntityTypes.class, null)).put(clazz, Integer.valueOf(id));
        //((Map)getPrivateField("g", net.minecraft.server.v1_7_R4.EntityTypes.class, null)).put(name, Integer.valueOf(id));
        
    }
}
