package sylaires.invasion.enemy;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
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

public class EnemyDruidCurse implements Listener {
	
	static HashMap<Player, Double> eligible = new HashMap<Player, Double>();
	private ArrayList<Player> cursed = new ArrayList<Player>();
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		net.minecraft.server.v1_8_R3.Entity el = ((CraftEntity)e.getEntity()).getHandle();
		
		
		if(el instanceof EnemyDruid) {
			
			new BukkitRunnable() {

				@Override
				public void run() {
					if(el.dead) {
						this.cancel();
					}
					for(Entity en : Main.getWorld().getNearbyEntities(new Location(Main.getWorld(), el.locX, el.locY, el.locZ), 4, 4, 4)) {
						if(en instanceof Player) {
							Player p = (Player) en;
							if(!cursed.contains(p)) {
								eligible.put(p, en.getLocation().distance(p.getLocation()));
							}
						}
					}
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(!Main.getWorld().getNearbyEntities(new Location(Main.getWorld(), el.locX, el.locY, el.locZ), 4, 4, 4).contains(p)) {
							try {
								eligible.remove(p);
							} catch(Exception e) {
								
							}
						}
					}
					
				}
				
			}.runTaskTimer(Main.getPlugin(), 0, 20);
			new BukkitRunnable() {

				@Override
				public void run() {
					if(el.dead) {
						this.cancel();
					}
					double smallest = 4;
					Player affected = null;
					for(Player p : eligible.keySet()) {
						if(eligible.get(p) < smallest) {
							smallest = eligible.get(p);
							affected = p;
						}
					}
					final Player af = affected;
					if(af != null) {
						IBasicEnemy druid = (IBasicEnemy) el;
						if(druid.getStage() > 1) {
							af.damage(4.0);
							eligible.remove(affected);
							af.sendMessage(ChatColor.RED + "You were cursed by a druid!");
							ParticleUtil.playParticles(EnumParticle.VILLAGER_HAPPY, af.getLocation(), 0.3, 1, 0.3, 0, 70);
							af.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 240, 1));
							af.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1));
							af.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
							af.playSound(af.getLocation(), Sound.WITHER_IDLE, 0.5f, 1.2f);
							cursed.add(af);
							new BukkitRunnable() {
								@Override
								public void run() {
									cursed.remove(af);
									af.sendMessage(ChatColor.GREEN + "The Druid's curse was worn off!");
								}
								
							}.runTaskLater(Main.getPlugin(), 400);
						}else {
							af.damage(1.0);
							eligible.remove(affected);
							af.sendMessage(ChatColor.RED + "You were cursed by a druid!");
							ParticleUtil.playParticles(EnumParticle.VILLAGER_HAPPY, af.getLocation(), 0.3, 1, 0.3, 0, 70);
							af.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
							af.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 500, 0));
							af.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
							af.playSound(af.getLocation(), Sound.WITHER_IDLE, 0.5f, 1.2f);
							cursed.add(af);
							new BukkitRunnable() {
								@Override
								public void run() {
									cursed.remove(af);
									af.sendMessage(ChatColor.GREEN + "The Druid's curse was worn off!");
								}
								
							}.runTaskLater(Main.getPlugin(), 500);
						}
					}
					
				}
				
			}.runTaskTimer(Main.getPlugin(), 0, 100);
		}
	}
	

}
