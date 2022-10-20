package sylaires.invasion.enemy;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import sylaires.invasion.main.Main;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class CustomTarget implements Listener {
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		net.minecraft.server.v1_8_R3.Entity en = ((CraftEntity)e.getEntity()).getHandle();
		if(en instanceof EnemyLich) {
			new BukkitRunnable() {

				@Override
				public void run() {
					if(en.dead) {
						this.cancel();
					}else {
						LivingEntity e1 = (LivingEntity) en.getBukkitEntity();
							if(e1 instanceof Player) {
								Player p = (Player) e1;
								if(p.getGameMode() != GameMode.SPECTATOR) {
								//	((EnemyLich) en).setGoalTarget(((CraftPlayer)p).getHandle(), TargetReason.CLOSEST_PLAYER, true);
									LivingEntity e = (LivingEntity) en.getBukkitEntity();
									float yaw = (float) Math.toDegrees(Math.atan2(
					                        p.getEyeLocation().getZ() - e.getEyeLocation().getZ(), p.getEyeLocation().getX() - e.getEyeLocation().getX())) - 90;
									
									float pitch = (float) Math.toDegrees(Math.atan2(
					                        p.getEyeLocation().getX() - e.getEyeLocation().getX(), p.getEyeLocation().getZ() - e.getEyeLocation().getZ())) - 90;
									
									Location loc = e.getLocation();
									loc.setYaw(yaw);
									loc.setPitch(pitch);
									e.teleport(loc);
								}
							}
						
					}
				}
				
			}.runTaskTimer(Main.getPlugin(), 0, 4);
		}
	} 

}
