package sylaires.invasion.enemy;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyWhiteWalkerTrueDmg implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		net.minecraft.server.v1_8_R3.Entity en = ((CraftEntity)e.getDamager()).getHandle();
		if(e.getEntity() instanceof LivingEntity) {
			LivingEntity le = (LivingEntity) e.getEntity();
			if(en instanceof EnemyWhiteWalker) {
				if(((EnemyWhiteWalker) en).getStage() == 1) {
					le.setHealth(le.getHealth()-(e.getDamage()*0.2));
				}else {
					le.setHealth(le.getHealth()-(e.getDamage()*0.5));
				}
			}
		}
	}

}
