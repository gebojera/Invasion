package sylaires.invasion.crucible;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class Crucible implements Listener {
	
	public static void open(Player p) {
		Inventory i = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Crucible:");
		for(int slot = 0; slot < 27; slot++) {
			ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
			ItemStackUtil.addNameToItem(glass, " ");
			i.setItem(slot, glass);
		}
		ItemStack trans = new ItemStack(Material.GLOWSTONE_DUST);
		ItemStackUtil.addNameToItem(trans, ChatColor.YELLOW + "Transmutations");
		ItemStackUtil.addLoreToItem(trans, ChatColor.GREEN + "Convert items using the power of Alchemy");
		i.setItem(11, trans);
		
		ItemStack bottle = new ItemStack(Material.WATER);
		ItemStackUtil.addNameToItem(bottle, ChatColor.LIGHT_PURPLE + "Alchemy");
		ItemStackUtil.addLoreToItem(bottle, ChatColor.GOLD + "Brew powerful consumables using rare materials");
		i.setItem(13, bottle);
		
		ItemStack book = new ItemStack(Material.BLAZE_POWDER);
		ItemStackUtil.addNameToItem(book, "" + ChatColor.GOLD + "Augmentations");
		ItemStackUtil.addLoreToItem(book, ChatColor.YELLOW + "Combine powerful items to enhance your equipment");
		i.setItem(15, book);
		p.openInventory(i);
	}

}
