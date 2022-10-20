package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentNetheric implements Listener {
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Netheric");
		ItemStackUtil.addLoreToItem(item, ChatColor.RED + "Arrows deal massive fire damage");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Enemies on fire take extra damage");
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
							if(shooter.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Netheric")) {
								if(e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof ArmorStand) && !(e.getEntity() instanceof Player)) {
									LivingEntity le = (LivingEntity) e.getEntity();
									if(le.getFireTicks() > 0) {
										e.setDamage(e.getDamage()*1.5);
									}
									le.setFireTicks(300);
									new BukkitRunnable() {
										int counter = 30;
										@Override
										public void run() {
											counter--;
											if(counter == 0) {
												this.cancel();
											}
											le.damage(8.0);
											
										}
										
									}.runTaskTimer(Main.getPlugin(), 0, 10);
								}
								
							}
						}
					}
					
				
			}
		}
	}

}
