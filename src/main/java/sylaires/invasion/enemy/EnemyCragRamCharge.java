package sylaires.invasion.enemy;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import sylaires.invasion.enchanting.EnchantmentShadowform;
import sylaires.invasion.main.Main;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyCragRamCharge implements Listener {
	
	@EventHandler
	public void onTarget(EntityTargetEvent e) {
		net.minecraft.server.v1_8_R3.Entity en = ((CraftEntity)e.getEntity()).getHandle();
		if(en instanceof EnemyCragRam) {
			LivingEntity le = (LivingEntity) e.getEntity();
			LivingEntity target = (LivingEntity) e.getTarget();
			if(target instanceof Player) {
				Player p = (Player) target;
				if(EnchantmentShadowform.shadowed.contains(p)) {
					return;
				}
			}
			new BukkitRunnable() {
				
				public void run() {
					try {
						Vector attacc = Main.getVectorForPoints(new Location(Main.getWorld(), en.locX, en.locY, en.locZ), e.getTarget().getLocation());
						attacc.setY(0); //No vertical movement
						le.setVelocity(attacc.multiply(1.5));
						((EnemyCragRam) en).setCharging(true);
					} catch(NullPointerException e) {
						
					}
					
				}
				
			}.runTaskLater(Main.getPlugin(), 40);
		}
		
	}

}
