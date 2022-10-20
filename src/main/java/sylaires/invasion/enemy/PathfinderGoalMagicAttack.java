package sylaires.invasion.enemy;

import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.IRangedEntity;
import net.minecraft.server.v1_8_R3.PathfinderGoal;
import sylaires.invasion.attack.IMagicAttack;
import sylaires.invasion.main.Main;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class PathfinderGoalMagicAttack extends PathfinderGoal {

	private EntityInsentient entity;
	private EntityLiving target;
	private int cooldown;
	private int timer;
	private IMagicAttack attack;
	
	public PathfinderGoalMagicAttack(IRangedEntity entity, IMagicAttack attack, int cooldown) {
		this.entity = (EntityInsentient) entity;
		this.cooldown = cooldown;
		this.timer = 0;
		this.attack = attack;
	}
	
	public boolean a() {
		EntityLiving target = entity.getGoalTarget();
		if(target == null) {
			return false;
		}
		this.target = target;
		c();
		return true;
	}
	
	public void c() {
		if(timer == 0) {
			attack.shoot();
			timer = cooldown;
			
			new BukkitRunnable() {

				@Override
				public void run() {
					timer = 0;
				}
				
			}.runTaskLater(Main.getPlugin(), cooldown*20);
		}
	}
	
	
	
	

}
