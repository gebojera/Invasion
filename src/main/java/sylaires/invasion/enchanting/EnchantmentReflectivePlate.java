package sylaires.invasion.enchanting;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentReflectivePlate implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Reflection I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have a " + ChatColor.BLUE + "30%" + ChatColor.GRAY + " chance to reflect arrows");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Reflection II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have a " + ChatColor.BLUE + "40%" + ChatColor.GRAY + " chance to reflect arrows");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Reflection III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have a " + ChatColor.BLUE + "50%" + ChatColor.GRAY + " chance to reflect arrows");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Reflection IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have a " + ChatColor.BLUE + "60%" + ChatColor.GRAY + " chance to reflect arrows");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Reflection V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have a " + ChatColor.BLUE + "70%" + ChatColor.GRAY + " chance to reflect arrows");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onShot(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Arrow) {
			Arrow a = (Arrow) e.getDamager();
			if(e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if(p.getInventory().getChestplate() != null) {
					ItemStack chest = p.getInventory().getChestplate();
					if(chest.getItemMeta() != null) {
						if(chest.getItemMeta().getLore() != null) {
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Reflection I")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 30) {
									a.setVelocity(new Vector(0, 0, 0));
									e.setCancelled(true);
									p.playSound(p.getLocation(), Sound.ANVIL_LAND, 30, 1.6f);
								}
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Reflection II")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 40) {
									a.setVelocity(new Vector(0, 0, 0));
									e.setCancelled(true);
									p.playSound(p.getLocation(), Sound.ANVIL_LAND, 30, 1.6f);
								}
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Reflection III")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 50) {
									a.setVelocity(new Vector(0, 0, 0));
									e.setCancelled(true);
									p.playSound(p.getLocation(), Sound.ANVIL_LAND, 30, 1.6f);
								}
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Reflection IV")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 60) {
									a.setVelocity(new Vector(0, 0, 0));
									e.setCancelled(true);
									p.playSound(p.getLocation(), Sound.ANVIL_LAND, 30, 1.6f);
								}
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Reflection V")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 70) {
									a.setVelocity(new Vector(0, 0, 0));
									e.setCancelled(true);
									p.playSound(p.getLocation(), Sound.ANVIL_LAND, 30, 1.6f);
								}
							}
						}
					}
				}
			}
		}
	}

}
