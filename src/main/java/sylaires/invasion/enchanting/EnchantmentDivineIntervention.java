package sylaires.invasion.enchanting;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentDivineIntervention implements Listener {
	
	public static ArrayList<Player> activated = new ArrayList<Player>();
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Divine Intervention I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "50%" + ChatColor.GRAY + " chance for fatal damage to be negated");
			ItemStackUtil.addLoreToItem(i, ChatColor.BLUE + "Can only activate once per game");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Divine Intervention II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "60%" + ChatColor.GRAY + " chance for fatal damage to be negated");
			ItemStackUtil.addLoreToItem(i, ChatColor.BLUE + "Can only activate once per game");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Divine Intervention III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "70%" + ChatColor.GRAY + " chance for fatal damage to be negated");
			ItemStackUtil.addLoreToItem(i, ChatColor.BLUE + "Can only activate once per game");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Divine Intervention IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "80%" + ChatColor.GRAY + " chance for fatal damage to be negated");
			ItemStackUtil.addLoreToItem(i, ChatColor.BLUE + "Can only activate once per game");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Divine Intervention V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "100%" + ChatColor.GRAY + " chance for fatal damage to be negated");
			ItemStackUtil.addLoreToItem(i, ChatColor.BLUE + "Can only activate once per game");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onFakeDeath(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getInventory().getChestplate() != null) {
				ItemStack chest = p.getInventory().getChestplate();
				if(chest.getItemMeta() != null) {
					if(chest.getItemMeta().getLore() != null) {
						if(p.getHealth() - e.getDamage() < 0.1) {
							if(!activated.contains(p)) {
								if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Divine Intervention V")) {
									e.setCancelled(true);
									p.setHealth(p.getMaxHealth());
									p.sendMessage(ChatColor.AQUA + "You were saved by the grace of the divine!");
									p.playSound(p.getLocation(), Sound.WITHER_IDLE, 30, 1.7f);
									activated.add(p);
								}
								if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Divine Intervention IV")) {
									Random rand = new Random();
									int random = rand.nextInt(100);
									if(random <= 80) {
										e.setCancelled(true);
										p.setHealth(p.getMaxHealth());
										p.sendMessage(ChatColor.AQUA + "You were saved by the grace of the divine!");
										p.playSound(p.getLocation(), Sound.WITHER_IDLE, 30, 1.7f);
										activated.add(p);
									}
								}
								if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Divine Intervention III")) {
									Random rand = new Random();
									int random = rand.nextInt(100);
									if(random <= 70) {
										e.setCancelled(true);
										p.setHealth(p.getMaxHealth());
										p.sendMessage(ChatColor.AQUA + "You were saved by the grace of the divine!");
										p.playSound(p.getLocation(), Sound.WITHER_IDLE, 30, 1.7f);
										activated.add(p);
									}
								}
								if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Divine Intervention II")) {
									Random rand = new Random();
									int random = rand.nextInt(100);
									if(random <= 60) {
										e.setCancelled(true);
										p.setHealth(p.getMaxHealth());
										p.sendMessage(ChatColor.AQUA + "You were saved by the grace of the divine!");
										p.playSound(p.getLocation(), Sound.WITHER_IDLE, 30, 1.7f);
										activated.add(p);
									}
								}
								if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Divine Intervention I")) {
									Random rand = new Random();
									int random = rand.nextInt(100);
									if(random <= 50) {
										e.setCancelled(true);
										p.setHealth(p.getMaxHealth());
										p.sendMessage(ChatColor.AQUA + "You were saved by the grace of the divine!");
										p.playSound(p.getLocation(), Sound.WITHER_IDLE, 30, 1.7f);
										activated.add(p);
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
