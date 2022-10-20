package sylaires.invasion.enchanting;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentTrueShot implements Listener {
	
	ArrayList<Arrow> shot = new ArrayList<Arrow>();
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "True Shot");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Arrows deal true damage (ignore armor)");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	@EventHandler
	public void onShoot(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Arrow) {
			Arrow a = (Arrow) e.getDamager();
			if(a.getShooter() instanceof Player) {
				if(shot.contains(a)) {
					if(e.getEntity() instanceof LivingEntity) {
							LivingEntity le = (LivingEntity) e.getEntity();
							if(le.getHealth()-e.getDamage() < 0) {
								le.damage(2000000);
							}else {
								le.setHealth(le.getHealth()-e.getDamage());
							}
							le.damage(0.0);
							e.setCancelled(true);
							shot.remove(a);
							a.remove();
					}
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
					if(shooter.getItemInHand().getItemMeta() != null) {
						if(shooter.getItemInHand().getItemMeta().getLore() != null) {
							if(shooter.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "True Shot")) {
								shot.add(a);
							}
						}
				}
			}
		}
	}

}
