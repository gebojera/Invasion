package sylaires.invasion.item;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class ItemRoughDiamond implements IBasicItem {
	
	private ItemStack item;
	private String name;
	public List<String> lore;
	public Material material = Material.DIAMOND;
	
	public ItemStack getItemStack() {
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemStackUtil.addNameToItem(diamond, ChatColor.WHITE + "Rough Diamond");
		ItemStackUtil.addLoreToItem(diamond, ChatColor.GRAY + "Uncommon Item");
		ItemStackUtil.addLoreToItem(diamond, ChatColor.YELLOW + "Can be refined at the Crucible");
		this.lore = diamond.getItemMeta().getLore();
		this.item = diamond;
		name = this.item.getItemMeta().getDisplayName();
		return item;
	}

	public String getName() {
		return name;
	}

	public List<String> getLore() {
		return lore;
	}

	public Material getMaterial() {
		return material;
	}
	
	

}
