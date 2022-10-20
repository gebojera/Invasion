package sylaires.invasion.enchanting;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentPhoenix implements Listener {
	
	public static HashMap<Player, Integer> activations = new HashMap<Player, Integer>();
	private HashMap<Player, Integer> risen = new HashMap<Player, Integer>();
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Phoenix");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "If you would die, you instead rise from the ashes, your health restored");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "When risen, gain " + ChatColor.RED + "100%" + ChatColor.GRAY + " attack damage, Jump Boost 3");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "and become surrounded in a cloak of flame that damages nearby enemies");
		ItemStackUtil.addLoreToItem(item, ChatColor.BLUE + "Can only activate twice per game");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	@EventHandler
	public void onFakeDeath(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getInventory().getChestplate() != null) {
				ItemStack chest = p.getInventory().getChestplate();
				if(chest.getItemMeta() != null) {
					if(chest.getItemMeta().getLore() != null) {
						if(chest.getItemMeta().getLore().contains(ChatColor.GOLD + "Phoenix")) {
							if(e.getCause() != DamageCause.FALL && e.getCause() != DamageCause.VOID) {
								if(!activations.containsKey(p)) { //It has yet to be activated
									if(p.getHealth()-e.getDamage() < 0.01) {
										e.setCancelled(true);
										p.setHealth(p.getMaxHealth());
										activations.put(p, 1);
										p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 30, 1.4f);
										p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 30, 1f);
										p.setVelocity(new Vector(0, 1, 0));
										ParticleUtil.playParticles(EnumParticle.LAVA, p.getLocation(), 0.5, 0, 0.5, 0.6, 150);
										ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, p.getLocation(), 0.3, 0, 0.3, 0.02, 150);
										for(Entity e1 : p.getNearbyEntities(5, 5, 5)) {
											if(e1 instanceof LivingEntity && !(e1 instanceof Player) && !(e1 instanceof ArmorStand)) {
												LivingEntity le = (LivingEntity) e1;
												le.setVelocity(le.getLocation().getDirection().normalize().multiply(-1.5));
												le.damage(150, p);
												le.setFireTicks(600);
											}
										}
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1, true));
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 2, true));
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 2, true));
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1, true));
										ParticleUtil.createParticleLoop(EnumParticle.SMOKE_NORMAL, p.getLocation(), 0.3, 0, 0.3, 0, 2, 30);
										risen.put(p, 30);
										p.sendMessage(ChatColor.GOLD + "You have risen from the ashes!");
										new BukkitRunnable() {
											int counter = 30;
											@Override
											public void run() {
												if(counter == 0) {
													risen.remove(p);
													p.sendMessage(ChatColor.GRAY + "You are no longer " + ChatColor.GOLD + "Risen");
													this.cancel();
												}else {
													counter--;
												}
											}
											
										}.runTaskTimer(Main.getPlugin(), 0, 20);
										new BukkitRunnable() {
											int counter = 0;
											@Override
											public void run() {
												if(counter > 600) {
													this.cancel();
												}else {
													counter++;
													ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, p.getLocation(), 0.2, 0, 0.2, 0, 4);
													ParticleUtil.playParticles(EnumParticle.FLAME, p.getLocation(), 0.3, 0.7, 0.3, 0.2, 4);
													for(Entity e : p.getNearbyEntities(4, 4, 4)) {
														if(e instanceof LivingEntity) {
															LivingEntity le = (LivingEntity) e;
															le.setFireTicks(100);
															le.damage(2.0, p);
														}
													}
												}
											}
											
										}.runTaskTimer(Main.getPlugin(), 0, 1);
	 								}
								}else if(activations.get(p) == 1) { //Activates for the second time
									if(p.getHealth()-e.getDamage() < 0.01) {
										e.setCancelled(true);
										p.setHealth(p.getMaxHealth());
										activations.put(p, 2);
										p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 30, 1.4f);
										p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 30, 1f);
										p.setVelocity(new Vector(0, 1, 0));
										ParticleUtil.playParticles(EnumParticle.LAVA, p.getLocation(), 0.5, 0, 0.5, 0.6, 150);
										ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, p.getLocation(), 0.3, 0, 0.3, 0.02, 150);
										for(Entity e1 : p.getNearbyEntities(5, 5, 5)) {
											if(e1 instanceof LivingEntity && !(e1 instanceof Player) && !(e1 instanceof ArmorStand)) {
												LivingEntity le = (LivingEntity) e1;
												le.setVelocity(le.getLocation().getDirection().normalize().multiply(-1.5));
												le.damage(150, p);
												le.setFireTicks(600);
											}
										}
										p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, 1, true));
										p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 2, true));
										p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600, 2, true));
										p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 1, true));
										ParticleUtil.createParticleLoop(EnumParticle.SMOKE_NORMAL, p.getLocation(), 0.3, 0, 0.3, 0, 2, 30);
										risen.put(p, 30);
										p.sendMessage(ChatColor.GOLD + "You have risen from the ashes!");
										new BukkitRunnable() {
											int counter = 30;
											@Override
											public void run() {
												if(counter == 0) {
													risen.remove(p);
													p.sendMessage(ChatColor.GRAY + "You are no longer " + ChatColor.GOLD + "Risen");
													this.cancel();
												}else {
													counter--;
												}
											}
											
										}.runTaskTimer(Main.getPlugin(), 0, 20);
										new BukkitRunnable() {
											int counter = 0;
											@Override
											public void run() {
												if(counter > 600) {
													this.cancel();
												}else {
													counter++;
													ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, p.getLocation(), 0.2, 0, 0.2, 0, 4);
													ParticleUtil.playParticles(EnumParticle.FLAME, p.getLocation(), 0.3, 0.7, 0.3, 0.2, 4);
													for(Entity e : p.getNearbyEntities(4, 4, 4)) {
														if(e instanceof LivingEntity) {
															LivingEntity le = (LivingEntity) e;
															le.setFireTicks(100);
															le.damage(2.0, p);
														}
													}
												}
											}
											
										}.runTaskTimer(Main.getPlugin(), 0, 1);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(risen.containsKey(p)) {
				e.setDamage(e.getDamage()*2);
			}
		}
	}

}
