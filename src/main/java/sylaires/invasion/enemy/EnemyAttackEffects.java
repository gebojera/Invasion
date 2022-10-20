package sylaires.invasion.enemy;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyAttackEffects implements Listener {
	
	@EventHandler(priority = EventPriority.LOW)
	public void onHit(EntityDamageByEntityEvent e) {
		net.minecraft.server.v1_8_R3.Entity en = ((CraftEntity)e.getEntity()).getHandle();
		if(e.getDamager() instanceof LivingEntity) {
			if(en instanceof EnemySilverfish) {
				LivingEntity le = (LivingEntity) e.getDamager();
				if(((EnemySilverfish) en).getStage() == 1) {
					le.damage(1.0);
				}else if(((EnemySilverfish) en).getStage() == 2) {
					le.damage(2.0);
				}else if(((EnemySilverfish) en).getStage() == 3) {
					le.damage(3.0);
				}else if(((EnemySilverfish) en).getStage() == 4) {
					le.damage(5.0);
				}
			}
		}
	}

}
