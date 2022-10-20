package sylaires.invasion.enchanting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentHearts implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Hearts I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your max health is raised by " + ChatColor.RED + "2❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Hearts II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your max health is raised by " + ChatColor.RED + "4❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Hearts III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your max health is raised by " + ChatColor.RED + "6❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Hearts IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your max health is raised by " + ChatColor.RED + "8❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Hearts V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your max health is raised by " + ChatColor.RED + "10❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	
	public static void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.getInventory().getChestplate() != null) {
						ItemStack chest = p.getInventory().getChestplate();
						if(chest.getItemMeta() != null) {
							if(chest.getItemMeta().getLore() != null) {
								if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts I")) {
									p.setMaxHealth(24);
								}else if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts II")) {
									p.setMaxHealth(28);
								}else if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts III")) {
									p.setMaxHealth(32);
								}else if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts IV")) {
									p.setMaxHealth(36);
								}else if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts V")) {
									p.setMaxHealth(40);
								}else {
									p.setMaxHealth(20);
								}
							}else {
								p.setMaxHealth(20);
							}
						}else {
							p.setMaxHealth(20);
						}
					}else {
						p.setMaxHealth(20);
					}
				}
				
			} }, 0, 3);
	}
	

}
