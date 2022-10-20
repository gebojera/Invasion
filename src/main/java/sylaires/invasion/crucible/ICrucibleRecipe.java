package sylaires.invasion.crucible;

import org.bukkit.inventory.ItemStack;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public interface ICrucibleRecipe {
	
	public ItemStack getResult(); public double getGoldCost(); public int getShardCost(); public ItemStack[] getItemsReq(); 

}
