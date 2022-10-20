package sylaires.invasion.enemy;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyWhiteWalkerAura implements Listener {
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		net.minecraft.server.v1_8_R3.Entity el = ((CraftEntity)e.getEntity()).getHandle();
		
		
		if(el instanceof EnemyWhiteWalker) {
			
			new BukkitRunnable() {

				@Override
				public void run() {
					if(el.dead) {
						this.cancel();
					}
					for(Entity en : Main.getWorld().getNearbyEntities(new Location(Main.getWorld(), el.locX, el.locY, el.locZ), 3, 3, 3)) {
						if(en instanceof Player) {
							Player p = (Player) en;
							if(((EnemyWhiteWalker) el).getStage() == 1) {
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 1));
							}else {
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 80, 2));
							}
						}
					}
					ParticleUtil.playParticles(EnumParticle.CLOUD, new Location(Main.getWorld(), el.locX, el.locY, el.locZ), 3, 0, 3, 0, 2);
					
				}
				
			}.runTaskTimer(Main.getPlugin(), 0, 4);
		}
	}

}
