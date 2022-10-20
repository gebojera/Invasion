package sylaires.invasion.enemy;

import org.bukkit.Location;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.PathfinderGoal;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class PathfinderGoalFollowTargetAtRange extends PathfinderGoal {
	
	private EntityInsentient follower;
	private double distance;
	private EntityLiving following;
	private Location targetLoc;
	private float speed;
	
	public PathfinderGoalFollowTargetAtRange(EntityInsentient follower, double distance, float speed) {
		this.follower = follower;
		this.distance = distance;
		this.speed = speed;
	}

	public boolean a() {
		following = follower.getGoalTarget();
		if(following == null) {
			return false;
		}else {
			c();
			return true;
		}
	}
	
	public void c() {
		if(following.getBukkitEntity().getLocation().distance(follower.getBukkitEntity().getLocation()) < distance) {
			follower.getNavigation().n();
		}else {
			targetLoc = following.getBukkitEntity().getLocation();
			follower.getNavigation().a(targetLoc.getX(), targetLoc.getY(), targetLoc.getZ(), speed);
		}
	}
}
