package sylaires.invasion.enchanting;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentEnder implements Listener {
	
	HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	ArrayList<Arrow> shot = new ArrayList<Arrow>();
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Ender");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Sneak to shoot a teleportation arrow");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "(2s cooldown)");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	@EventHandler
	public void onShoot(ProjectileHitEvent e) {
		if(e.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) e.getEntity();
			if(a.getShooter() instanceof Player) {
				Player shooter = (Player) a.getShooter();
						if(shot.contains(a)) {
							shooter.teleport(a.getLocation());
							ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, shooter.getLocation(), 0.5, 0.5, 0.5, 0, 70);
							shooter.playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 20, 1.7f);
							for(Entity e1 : shooter.getNearbyEntities(6, 6, 6)) {
								if(e1 instanceof Player) {
									Player p = (Player) e1;
									p.playSound(shooter.getLocation(), Sound.ENDERMAN_TELEPORT, 20, 1.7f);
								}
							}
							a.remove();
				}
			}
		}
	}
	
	@EventHandler
	public void onShoot(ProjectileLaunchEvent e) {
		if(e.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) e.getEntity();
			if(a.getShooter() instanceof Player) {
				Player shooter = (Player) a.getShooter();
				if(shooter.isSneaking()) {
					if(shooter.getItemInHand().getItemMeta() != null) {
						if(shooter.getItemInHand().getItemMeta().getLore() != null) {
							if(shooter.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Ender")) {
								if(!cooldown.containsKey(shooter)) {
									cooldown.put(shooter, 2);
									shot.add(a);
									new BukkitRunnable() {
										@Override
										public void run() {
											if(cooldown.containsKey(shooter)) {
												if(cooldown.get(shooter) == 0) {
													cooldown.remove(shooter);
													this.cancel();
												}else {
													cooldown.put(shooter, cooldown.get(shooter)-1);
												}
											}else {
												this.cancel();
											}
										}
										
									}.runTaskTimer(Main.getPlugin(), 0, 20);
									new BukkitRunnable() {
										@Override
										public void run() {
											if(!a.isDead()) {
												ParticleUtil.playParticles(EnumParticle.SPELL_MOB, a.getLocation(), 0, 0, 0, 0, 5);
											}else {
												this.cancel();
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
