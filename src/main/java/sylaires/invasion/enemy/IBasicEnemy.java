package sylaires.invasion.enemy;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

import sylaires.invasion.main.Locations.spawnType;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public interface IBasicEnemy {
	
	public double getGold(); public int getEssence(); public spawnType getRegion();  public int getStage(); public HashMap<ItemStack, Integer> getDrops();

}
