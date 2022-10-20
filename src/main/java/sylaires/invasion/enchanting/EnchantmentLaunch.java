package sylaires.invasion.enchanting;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

public class EnchantmentLaunch implements Listener {
	
	HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Launch I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Sends the enemy soaring into the sky!");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "(15s cooldown)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Launch II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Sends the enemy soaring into the sky!");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "(12s cooldown)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Launch III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Sends the enemy soaring into the sky!");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "(10s cooldown)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Launch IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Sends the enemy soaring into the sky!");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "(8s cooldown)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Launch V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Sends the enemy soaring into the sky!");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "(5s cooldown)");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getLore() != null) {
							if(!(e.getEntity() instanceof Player)) {
								if(e.getEntity() instanceof LivingEntity) {
									LivingEntity le = (LivingEntity) e.getEntity();
									if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Launch I")) {
										if(!cooldown.containsKey(p)) {
											e.setCancelled(true);
											le.damage(e.getDamage());
											le.setVelocity(new Vector(0, 3, 0));
											ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, e.getEntity().getLocation(), 0.5, 0, 0.5, 0, 60);
											p.playSound(p.getLocation(), Sound.EXPLODE, 20, 1.5f);
											cooldown.put(p, 15);
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
									}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Launch II")) {
										if(!cooldown.containsKey(p)) {
											e.setCancelled(true);
											cooldown.put(p, 12);
											le.damage(e.getDamage());
											le.setVelocity(new Vector(0, 3, 0));
											ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, e.getEntity().getLocation(), 0.5, 0, 0.5, 0, 60);
											p.playSound(p.getLocation(), Sound.EXPLODE, 20, 1.5f);
											
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
									}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Launch III")) {
										if(!cooldown.containsKey(p)) {
											e.setCancelled(true);
											le.damage(e.getDamage());
											le.setVelocity(new Vector(0, 3, 0));
											ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, e.getEntity().getLocation(), 0.5, 0, 0.5, 0, 60);
											p.playSound(p.getLocation(), Sound.EXPLODE, 20, 1.5f);
											cooldown.put(p, 10);
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
									}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Launch IV")) {
										if(!cooldown.containsKey(p)) {
											e.setCancelled(true);
											le.damage(e.getDamage());
											le.setVelocity(new Vector(0, 3, 0));
											ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, e.getEntity().getLocation(), 0.5, 0, 0.5, 0, 60);
											p.playSound(p.getLocation(), Sound.EXPLODE, 20, 1.5f);
											cooldown.put(p, 8);
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
									}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Launch V")) {
										if(!cooldown.containsKey(p)) {
											e.setCancelled(true);
											le.damage(e.getDamage());
											le.setVelocity(new Vector(0, 3, 0));
											ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, e.getEntity().getLocation(), 0.5, 0, 0.5, 0, 60);
											p.playSound(p.getLocation(), Sound.EXPLODE, 20, 1.5f);
											cooldown.put(p, 5);
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
		}
	}

}
