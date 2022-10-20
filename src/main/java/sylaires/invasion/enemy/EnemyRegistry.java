package sylaires.invasion.enemy;

import java.util.ArrayList;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyRegistry {
	
	public static ArrayList<EntityTypes> forest = new ArrayList<EntityTypes>();
	public static ArrayList<EntityTypes> mine = new ArrayList<EntityTypes>();
	public static ArrayList<EntityTypes> crag = new ArrayList<EntityTypes>();
	public static ArrayList<EntityTypes> ruins = new ArrayList<EntityTypes>();
	
	public static void registerAll() {
		forest.add(EntityTypes.DRUID);
		
	}

}
