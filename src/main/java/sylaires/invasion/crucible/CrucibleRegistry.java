package sylaires.invasion.crucible;

import java.util.ArrayList;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class CrucibleRegistry {
	
	private static ArrayList<ICrucibleRecipe> recipes = new ArrayList<ICrucibleRecipe>();
	
	public static void registerRecipes() {
		recipes.add(new FacetedDiamondRecipe());
		
	}
	
	public static ArrayList<ICrucibleRecipe> getRecipes() {
		return recipes;
	}

}
