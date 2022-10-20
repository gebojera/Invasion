package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.main.Main;
import sylaires.invasion.main.Game.GameState;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentRepulsion implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Repulsor I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Hold block to push all enemies within " + ChatColor.RED + "5" + ChatColor.GRAY + " blocks away from you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Repulsor II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Hold block to push all enemies within " + ChatColor.RED + "7" + ChatColor.GRAY + " blocks away from you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Repulsor III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Hold block to push all enemies within " + ChatColor.RED + "9" + ChatColor.GRAY + " blocks away from you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Repulsor IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Hold block to push all enemies within " + ChatColor.RED + "11" + ChatColor.GRAY + " blocks away from you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Repulsor V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Hold block to push all enemies within " + ChatColor.RED + "15" + ChatColor.GRAY + " blocks away from you");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					ItemStack i = p.getItemInHand();
					if(i.getItemMeta().getLore() != null) {
						if(Main.getGame().getState() == GameState.INGAME) {
							if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Repulsor I")) {
								new BukkitRunnable() {

									@Override
									public void run() {
										if(p.isBlocking()) {
											ParticleUtil.playParticles(EnumParticle.REDSTONE, p.getLocation(), 1, 0, 1, 0, 8);
											p.playSound(p.getLocation(), Sound.ZOMBIE_PIG_IDLE, 20, 0.3f);
											for(Entity e : p.getNearbyEntities(5, 5, 5)) {
												if(!(e instanceof Player)) {
													ParticleUtil.playParticles(EnumParticle.REDSTONE, e.getLocation(), 0.5, 0, 0.5, 0, 10);
													e.setVelocity(e.getLocation().getDirection().setY(0).multiply(-1));
												}
											}
										}else {
											this.cancel();
										}
									}
									
								}.runTaskTimer(Main.getPlugin(), 0, 5);
							}else if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Repulsor II")) {
								new BukkitRunnable() {

									@Override
									public void run() {
										if(p.isBlocking()) {
											ParticleUtil.playParticles(EnumParticle.REDSTONE, p.getLocation(), 1, 0, 1, 0, 8);
											p.playSound(p.getLocation(), Sound.ZOMBIE_PIG_IDLE, 20, 0.3f);
											for(Entity e : p.getNearbyEntities(7, 7, 7)) {
												if(!(e instanceof Player)) {
													ParticleUtil.playParticles(EnumParticle.REDSTONE, e.getLocation(), 0.5, 0, 0.5, 0, 10);
													e.setVelocity(e.getLocation().getDirection().setY(0).multiply(-1));
												}
											}
										}else {
											this.cancel();
										}
									}
									
								}.runTaskTimer(Main.getPlugin(), 0, 5);
							}else if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Repulsor III")) {
								new BukkitRunnable() {

									@Override
									public void run() {
										if(p.isBlocking()) {
											ParticleUtil.playParticles(EnumParticle.REDSTONE, p.getLocation(), 1, 0, 1, 0, 8);
											p.playSound(p.getLocation(), Sound.ZOMBIE_PIG_IDLE, 20, 0.3f);
											for(Entity e : p.getNearbyEntities(9, 9, 9)) {
												if(!(e instanceof Player)) {
													ParticleUtil.playParticles(EnumParticle.REDSTONE, e.getLocation(), 0.5, 0, 0.5, 0, 10);
													e.setVelocity(e.getLocation().getDirection().setY(0).multiply(-1));
												}
											}
										}else {
											this.cancel();
										}
									}
									
								}.runTaskTimer(Main.getPlugin(), 0, 5);
							}else if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Repulsor IV")) {
								new BukkitRunnable() {

									@Override
									public void run() {
										if(p.isBlocking()) {
											ParticleUtil.playParticles(EnumParticle.REDSTONE, p.getLocation(), 1, 0, 1, 0, 8);
											p.playSound(p.getLocation(), Sound.ZOMBIE_PIG_IDLE, 20, 0.3f);
											for(Entity e : p.getNearbyEntities(11, 11, 11)) {
												if(!(e instanceof Player)) {
													ParticleUtil.playParticles(EnumParticle.REDSTONE, e.getLocation(), 0.5, 0, 0.5, 0, 10);
													e.setVelocity(e.getLocation().getDirection().setY(0).multiply(-1));
												}
											}
										}else {
											this.cancel();
										}
									}
									
								}.runTaskTimer(Main.getPlugin(), 0, 5);
							}else if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Repulsor V")) {
								new BukkitRunnable() {

									@Override
									public void run() {
										if(p.isBlocking()) {
											ParticleUtil.playParticles(EnumParticle.REDSTONE, p.getLocation(), 1, 0, 1, 0, 8);
											p.playSound(p.getLocation(), Sound.ZOMBIE_PIG_IDLE, 20, 0.3f);
											for(Entity e : p.getNearbyEntities(15, 15, 15)) {
												if(!(e instanceof Player)) {
													ParticleUtil.playParticles(EnumParticle.REDSTONE, e.getLocation(), 0.5, 0, 0.5, 0, 10);
													e.setVelocity(e.getLocation().getDirection().setY(0).multiply(-1));
												}
											}
										}else {
											this.cancel();
										}
									}
									
								}.runTaskTimer(Main.getPlugin(), 0, 5);
							}
						}
					}
				}
 			}
		}
	}

}
