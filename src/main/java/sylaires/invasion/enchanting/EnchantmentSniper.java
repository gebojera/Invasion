package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentSniper implements Listener {
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Sniper");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Arrows that fly 30 or more blocks");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "deal" + ChatColor.RED + " 20x " + ChatColor.GRAY + "damage");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	@EventHandler
	public void onShoot(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Arrow) {
			Arrow a = (Arrow) e.getDamager();
			if(a.getShooter() instanceof Player) {
				Player shooter = (Player) a.getShooter();
					if(shooter.getItemInHand().getItemMeta() != null) {
						if(shooter.getItemInHand().getItemMeta().getLore() != null) {
							if(shooter.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Sniper")) {
								double distance = shooter.getLocation().distance(e.getEntity().getLocation());
								if(distance >= 30) {
									e.setDamage(e.getDamage()*20);
									shooter.playSound(shooter.getLocation(), Sound.ZOMBIE_WOODBREAK, 5, 2);
								}
								
							}
						}
					}
					
				
			}
		}
	}

}
