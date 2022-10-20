package sylaires.invasion.enchanting;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentTerrabeam implements Listener {
	
	HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Terrabeam");
		ItemStackUtil.addLoreToItem(item, ChatColor.GREEN + "Fires a straight beam of energy");
		ItemStackUtil.addLoreToItem(item, ChatColor.GREEN + "that damages enemies and heals players");
		ItemStackUtil.addLoreToItem(item, "  ");
	}
	
	@EventHandler
	public void onLeftClick(PlayerInteractEvent e) {
		if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if(e.getItem() != null) {
				ItemStack i = e.getItem();
				if(i.getItemMeta() != null) {
					if(i.getItemMeta().getLore() != null) {
						if(i.getItemMeta().getLore().contains(ChatColor.GOLD + "Terrabeam")) {
							if(!cooldown.containsKey(p)) {
								p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 10, 1.8f);
								cooldown.put(p, 1);
								new BukkitRunnable() {
									int counter = 0;
									Location loc = e.getPlayer().getEyeLocation();
									Vector dir = loc.getDirection().normalize().multiply(1);
									@SuppressWarnings("deprecation")
									@Override
									public void run() {
										   ParticleUtil.playParticles(EnumParticle.VILLAGER_HAPPY, loc, 0, 0, 0, 0, 20);
										   ParticleUtil.playParticles(EnumParticle.FIREWORKS_SPARK, loc, 0, 0, 0, 0, 1);
										   ParticleUtil.playParticles(EnumParticle.BLOCK_CRACK, loc, 0, 0, 0, 0, 7, 133);
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
									    	   if(!(e1 instanceof Player)) {
									    		   if(e1 instanceof LivingEntity) {
										    		   LivingEntity le = (LivingEntity) e1;
									    			   le.damage(20.0);
										    		   ParticleUtil.playParticles(EnumParticle.EXPLOSION_LARGE, loc, 0, 0, 0, 0, 1);
										    	   }
									    	   }else {
									    		   if(e1 instanceof Player) {
									    			   Player hit = (Player) e1;
									    			   if(hit != e.getPlayer()) {
									    				   if(hit.getHealth() + 6.0 > hit.getMaxHealth()) {
									    					   hit.setHealth(hit.getMaxHealth());
									    				   }else {
									    					   hit.setHealth(hit.getHealth() + 6.0);
									    				   }
									    			   }
									    		   }
									    	   }
									       }
									}
								    
								}.runTaskTimer(Main.getPlugin(), 0, 1);
								new BukkitRunnable() {

									@Override
									public void run() {
										if(cooldown.get(p) == 0) {
											cooldown.remove(p);
											this.cancel();
										}else {
											cooldown.put(p, cooldown.get(p)-1);
										}
										
									}
									
								}.runTaskTimer(Main.getPlugin(), 0, 20);
							}
						}
					}
				}
			}
		}
	}

}
