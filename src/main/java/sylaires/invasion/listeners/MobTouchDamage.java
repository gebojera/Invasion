package sylaires.invasion.listeners;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import sylaires.invasion.enemy.EnemyForestSpider;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class MobTouchDamage implements Listener {
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			net.minecraft.server.v1_8_R3.Entity el = ((CraftEntity)e.getDamager()).getHandle(); //Convert attacker into nms entity
			
			if(el instanceof EnemyForestSpider) { //Level 3+ spiders poison
				EnemyForestSpider ibe = (EnemyForestSpider) el;
				if(ibe.getStage() > 2) {
					p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 1));
				}
			}
		}
	}

}
