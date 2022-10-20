package sylaires.invasion.crucible;

import org.bukkit.inventory.ItemStack;

import sylaires.invasion.item.ItemFacetedDiamond;
import sylaires.invasion.item.ItemRoughDiamond;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class FacetedDiamondRecipe implements ICrucibleRecipe {

	private double gold = 500;
	private int shards = 5;
	private ItemStack result = new ItemFacetedDiamond().getItemStack();
	private ItemStack[] itemsReq = {new ItemRoughDiamond().getItemStack()};
	
	public ItemStack getResult() {
		return result;
	}

	public double getGoldCost() {
		return gold;
	}

	public int getShardCost() {
		return shards;
	}

	public ItemStack[] getItemsReq() {
		return itemsReq;
	}
	
	

}
