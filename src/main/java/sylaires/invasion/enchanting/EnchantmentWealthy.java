package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentWealthy implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wealthy I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy earns you " + ChatColor.GOLD + "10%" + ChatColor.GRAY + " extra gold.");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wealthy II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy earns you " + ChatColor.GOLD + "20%" + ChatColor.GRAY + " extra gold.");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wealthy III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy earns you " + ChatColor.GOLD + "30%" + ChatColor.GRAY + " extra gold.");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wealthy IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy earns you " + ChatColor.GOLD + "40%" + ChatColor.GRAY + " extra gold.");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wealthy V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy earns you " + ChatColor.GOLD + "50%" + ChatColor.GRAY + " extra gold.");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	

}
