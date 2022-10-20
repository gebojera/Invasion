package sylaires.invasion.item;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public interface IBasicItem {
	
	public ItemStack getItemStack(); public String getName(); public List<String> getLore(); public Material getMaterial(); 

}
