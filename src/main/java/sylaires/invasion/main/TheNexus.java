package sylaires.invasion.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.main.Game.GameState;
import sylaires.invasion.util.ParticleUtil;
import sylaires.invasion.util.TitleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class TheNexus {
	
	private static boolean dangered;
	private static Location nexLoc;
	private static boolean dead;
	
	public static void register() {
		nexLoc = Locations.getNexus();
		new BukkitRunnable() { //Buffs
			
			@Override
			public void run() {
				if(!isDead()) {
					if(Main.getGame().getState() == GameState.INGAME) {
						for(Entity e : Main.getWorld().getNearbyEntities(nexLoc, 1, 1, 1)) {
							if(e instanceof Player) {
								Player p = (Player) e;
								p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0));
								p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 0));
							}
							net.minecraft.server.v1_8_R3.Entity en = ((CraftEntity)e).getHandle();
							if(en instanceof EntityInsentient) {
								ParticleUtil.playParticles(EnumParticle.FLAME, nexLoc, 1, 1, 1, 0, 15);
								ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, nexLoc, 1, 1, 1, 0, 15);
								dangered = true;
							}else {
								if(!dangered) {
									ParticleUtil.playParticles(EnumParticle.VILLAGER_HAPPY, nexLoc, 1, 1, 1, 0, 15);
								}
								dangered = false;
							}
						}
					}
				}else {
					this.cancel();
				}
			}
			
		}.runTaskTimer(Main.getPlugin(), 0, 3);
		new BukkitRunnable() {
			public void run() {
				if(!isDead()) {
					if(dangered) {
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(ChatColor.RED + "The Nexus is under attack!");
							p.playSound(p.getLocation(), Sound.GHAST_SCREAM2, 0.9f, 1.2f);
						}
					}
				}else {
					this.cancel();
				}
			}
		}.runTaskTimer(Main.getPlugin(), 0, 20);
		new BukkitRunnable() {
			public void run() {
				if(dangered) {
					Main.getGame().getWavee().subStocked(20);
					if(Main.getGame().getWavee().getStocked() <= 0) {
						for(Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(ChatColor.RED + "The Nexus was besieged! You can no longer respawn!");
							TitleUtil.sendTitleToPlayer(p, 0, 200, 20, "" + ChatColor.DARK_RED + ChatColor.BOLD + "NEXUS DESTROYED!", ChatColor.RED + "You can no longer respawn!");
							p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1.5f, 1.2f);
							ParticleUtil.playParticles(EnumParticle.EXPLOSION_HUGE, nexLoc, 5, 5, 5, 0, 60);
							dangered = false;
							dead = true;
							Scoreboards.updateForAll();
							Main.getGame().getWavee().setTimed();
							this.cancel();
						}
					}
					Scoreboards.updateForAll();
				}
			}
		}.runTaskTimer(Main.getPlugin(), 0, 20);
		
	}
	
	public static boolean isDead() {
		return dead;
	}
	
	public static void reset() {
		Bukkit.broadcastMessage("sup");
		dead = true;
		dangered = false;
	}

}
