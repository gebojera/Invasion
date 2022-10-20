package sylaires.invasion.enemy;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import sylaires.invasion.main.Main;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class SpawnRenderer implements Listener {
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		net.minecraft.server.v1_8_R3.Entity en = ((CraftEntity) e.getEntity()).getHandle();
		if(en instanceof EntityInsentient) {
			EntityInsentient living = (EntityInsentient) en;
			new BukkitRunnable() {
	        	
				@Override
				public void run() {
					if(living.dead) {
						this.cancel();
					}else {
						//living.activatedTick = MinecraftServer.currentTick;
					}
				}
				
			}.runTaskTimer(Main.getPlugin(), 0, 3);
		}
	}

}
