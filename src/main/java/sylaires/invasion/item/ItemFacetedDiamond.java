package sylaires.invasion.item;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class ItemFacetedDiamond implements IBasicItem {
	
	private ItemStack item;
	private String name;
	public List<String> lore;
	public Material material = Material.DIAMOND;
	
	public ItemStack getItemStack() {
		ItemStack diamond = new ItemStack(Material.DIAMOND);
		ItemStackUtil.addNameToItem(diamond, ChatColor.AQUA + "Faceted Diamond");
		ItemStackUtil.addLoreToItem(diamond, ChatColor.GOLD + "Rare Item");
		ItemStackUtil.addLoreToItem(diamond, ChatColor.YELLOW + "Can be used to forge weapons and armor at the Forge");
		this.lore = diamond.getItemMeta().getLore();
		this.item = diamond;
		this.name = item.getItemMeta().getDisplayName();
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
