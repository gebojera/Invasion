package sylaires.invasion.attack;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.enemy.EnemyLich;
import sylaires.invasion.enemy.IBasicEnemy;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class LichAttack implements IMagicAttack {
	
	private EntityInsentient shooter;
	private EntityLiving target;
	
	public LichAttack(EntityInsentient shooter, EntityLiving target) {
		this.shooter = shooter;
		this.target = target;
	}
	
	public void shoot() {
		Bukkit.broadcastMessage("shoot");
		if(target == null) {
			return;
		}
		if(!(shooter instanceof EnemyLich)) {
			return;
		}
		IBasicEnemy i = (IBasicEnemy) shooter;
		LivingEntity en = (LivingEntity) shooter.getBukkitEntity();
		Location loc = en.getEyeLocation();
		loc.setY(loc.getY()-1);
		new BukkitRunnable() {
			int counter = 0;
			Vector dir = target.getBukkitEntity().getLocation().subtract(loc).toVector();
			@SuppressWarnings("deprecation")
			public void run() {
				   ParticleUtil.playParticles(EnumParticle.SPELL_WITCH, loc, 0, 0, 0, 0, 20);
				   ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, loc, 0.1, 0.1, 0.1, 0, 10);
				   loc.add(dir);
				   
			       if(loc.getBlock().getType() != Material.AIR) {
			    	   this.cancel();
			    	   ParticleUtil.playParticles(EnumParticle.BLOCK_CRACK, loc, 0.1, 0.1, 0.1, 0, 100, loc.getBlock().getTypeId());
			       }
			       if(counter > 800) {
			    	   this.cancel();
			       }else {
			    	   counter++;
			       }
			       for(Entity e1 : Main.getWorld().getNearbyEntities(loc, 0.3, 0.3, 0.3)) {
			    	   if(e1 instanceof Player) {
				    		  Player p = (Player) e1;
				    		  if(p.getGameMode() != GameMode.SPECTATOR) {
				    			  if(i.getStage() > 1) {
					    			  p.damage(10.0);
					    			  p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 0));
					    			  p.playSound(p.getLocation(), Sound.BLAZE_DEATH, 1.5f, 1.5f);
					    		  }else {
					    			  p.damage(7.0);
					    			  p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 400, 0));
					    			  p.playSound(p.getLocation(), Sound.BLAZE_DEATH, 1.5f, 1.5f);
					    		  }
				    		  }
			    	   }
			       }
			}
		    
		}.runTaskTimer(Main.getPlugin(), 0, 1);
	}
	
	

}
