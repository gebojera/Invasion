package sylaires.invasion.enemy;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyGhostlyMinerInvis implements Listener {
	
	@EventHandler
	public void onTarget(EntityTargetEvent e) {
		net.minecraft.server.v1_8_R3.Entity en = ((CraftEntity)e.getEntity()).getHandle();
		if(en instanceof EnemyGhostlyMiner) {
			LivingEntity le = (LivingEntity) e.getEntity();
			le.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 0));
			ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, le.getLocation(), 0.5, 0.5, 0.5, 0, 60);
		}
		
	}

}
