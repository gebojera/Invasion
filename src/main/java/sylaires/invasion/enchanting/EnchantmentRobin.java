package sylaires.invasion.enchanting;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentRobin implements Listener {
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Barrage");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Fires a stream of 5 extra arrows");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	ArrayList<Player> shooting = new ArrayList<Player>();
	
	@EventHandler
	public void onShoot(ProjectileLaunchEvent e) {
		if(e.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) e.getEntity();
			if(a.getShooter() instanceof Player) {
				Player shooter = (Player) a.getShooter();
					if(shooter.getItemInHand().getItemMeta() != null) {
						if(shooter.getItemInHand().getItemMeta().getLore() != null) {
							if(shooter.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Barrage")) {
								if(!shooting.contains(shooter)) {
									shooting.add(shooter);
									new BukkitRunnable() {
										int arrows = 0;
										@SuppressWarnings("deprecation")
										@Override
										public void run() {
											arrows++;
											shooter.shootArrow();
											shooter.playSound(shooter.getLocation(), Sound.SHOOT_ARROW, 20, 1);
											if(arrows == 5) {
												shooting.remove(shooter);
												this.cancel();
											}
										}
										
									}.runTaskTimer(Main.getPlugin(), 0, 2);
								}
							}
						}
					}
					
				
			}
		}
	}

}
