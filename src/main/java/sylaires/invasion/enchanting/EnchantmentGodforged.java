package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentGodforged {
	
	public static void apply(ItemStack item) {
		if(item.getType() != Material.BOW) {
			ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		}
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "God-Forged");
		ItemStackUtil.addLoreToItem(item, ChatColor.AQUA + "This item is unbreakable");
		ItemStackUtil.addLoreToItem(item, " ");
		ItemStackUtil.setUnbreakable(item);
		ItemStackUtil.addFlag(ItemFlag.HIDE_UNBREAKABLE, item);
	}
	

}
