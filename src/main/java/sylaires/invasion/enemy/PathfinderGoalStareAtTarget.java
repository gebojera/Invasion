package sylaires.invasion.enemy;


import org.bukkit.entity.Entity;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import sylaires.invasion.main.Main;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class PathfinderGoalStareAtTarget extends PathfinderGoal {
	
	private EntityInsentient entity;
	private EntityLiving target;
	
	public PathfinderGoalStareAtTarget(EntityInsentient entity) {
		this.entity = entity;
	}
	
	public boolean a() {
		target = entity.getGoalTarget();
		if(target == null) {
			return false;
		}else {
			c();
			return true;
		}
	}
	
	public void c() {
		Entity e = entity.getBukkitEntity();
		e.teleport(Main.faceLocation(e, target.getBukkitEntity().getLocation()));
	}

}
