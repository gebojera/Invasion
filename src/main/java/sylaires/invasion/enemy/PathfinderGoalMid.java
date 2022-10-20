package sylaires.invasion.enemy;

import org.bukkit.Location;
import org.bukkit.material.EnderChest;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import sylaires.invasion.main.TheNexus;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class PathfinderGoalMid extends PathfinderGoal {
	
	private double speed;
	private EntityInsentient entity;
	private Location to;
	
	public boolean a() {
		if(this.entity.getGoalTarget() == null) {
			if(!TheNexus.isDead()) {
				c();
			}
		}
		return true;
	}
	
	public PathfinderGoalMid(EntityInsentient entity, Location to, double speed) {
		this.entity = entity;
		this.to = to;
		this.speed = speed;
		
	}
	
	public void c() {
		PathEntity path = ((EntityInsentient) entity).getNavigation().a(to.getX(), to.getY(), to.getZ());
        // Move to that path at 'speed' speed.
        ((EntityInsentient) entity).getNavigation().a(path, speed);
	}

}
