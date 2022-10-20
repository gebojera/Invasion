package sylaires.invasion.enemy;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntitySheep;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import sylaires.invasion.enchanting.EnchantmentShadowform;
import sylaires.invasion.main.Locations;
import sylaires.invasion.main.Locations.spawnType;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.EnemyUtil;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyCragRam extends EntitySheep implements IBasicEnemy {
	
	
	private boolean isCharging;
	private double gold;
	private int essence;
	private spawnType region;
	private int stage;
	private HashMap<ItemStack, Integer> drops = new HashMap<ItemStack, Integer>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EnemyCragRam(org.bukkit.World w, int wave) {
		super(((CraftWorld)w).getHandle());
		
		List goalB = (List)EnemyUtil.getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
		List goalC = (List)EnemyUtil.getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
        List targetB = (List)EnemyUtil.getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        List targetC = (List)EnemyUtil.getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
		
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.8D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.goalSelector.a(7, new PathfinderGoalMid(this, Locations.getSpawn(), 0.7));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        
        if(wave < 30) {
        	stage = 1; //Level 2 
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(30);
            this.heal(50f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.25);
            this.persistent = true;
          //  this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(5.0);
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave < 60 && wave >= 30) {
        	stage = 2; //level 3
        	this.getAttributeInstance(GenericAttributes.maxHealth).setValue(60);
            this.heal(50f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.35);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave < 100 && wave >= 60) {
        	stage = 3;
        	this.getAttributeInstance(GenericAttributes.maxHealth).setValue(85);
            this.heal(50f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.35);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave >= 100 && wave >= 100) { //Overtime mob
        	stage = 4;
        	this.getAttributeInstance(GenericAttributes.maxHealth).setValue(7*(wave/2));
            this.heal((float) 10*(wave/2));
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.5);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }
        
        gold = (2*stage)*(0.5*wave);
        essence = 15*stage;
        region = spawnType.CRAG; 
	}

	public double getGold() {
		return gold;
	}

	public int getEssence() {
		return essence;
	}

	public spawnType getRegion() {
		return region;
	}

	public int getStage() {
		return stage;
	}
	
	public void setCharging(boolean what) {
		this.isCharging = what;
	}
	
	public boolean isCharging() {
		return this.isCharging;
	}
	
	@Override
	public void collide(Entity entity) {
		if(this.isCharging) {
			org.bukkit.entity.Entity en = entity.getBukkitEntity();
			if(en instanceof Player) {
				Player p = (Player) en;
				if(!EnchantmentShadowform.shadowed.contains(p)) {
					p.playSound(p.getLocation(), Sound.ZOMBIE_WOODBREAK, 1.4f, 0.9f);
					if(this.getStage() == 1 || this.getStage() == 2) {
						p.damage(6.0);
						Vector kb = Main.getVectorForPoints(p.getLocation(), new Location(Main.getWorld(), this.locX, this.locY, this.locZ));
						p.setVelocity(kb.multiply(-0.5));
					}else {
						p.damage(10.0);
						Vector kb = Main.getVectorForPoints(p.getLocation(), new Location(Main.getWorld(), this.locX, this.locY, this.locZ));
						p.setVelocity(kb.multiply(-0.7));
					}
				}
			}
			this.isCharging = false;
		}else {
			org.bukkit.entity.Entity en = entity.getBukkitEntity();
			org.bukkit.entity.Entity ram = this.getBukkitEntity();
			if(en instanceof Player) {
				Player p = (Player) en;
				if(!EnchantmentShadowform.shadowed.contains(p)) {
					if(this.getStage() == 1 || this.getStage() == 2) {
						p.damage(4.5, ram);
						
					}else {
						p.damage(8.0, ram);
					}
				}
			}
		}
	}
	
	public HashMap<ItemStack, Integer> getDrops() {
		ItemStack dia = new ItemStack(Material.WOOL);
		ItemStackUtil.addNameToItem(dia, ChatColor.WHITE + "Dense Wool");
		ItemStackUtil.addLoreToItem(dia, ChatColor.GRAY + "Uncommon Item");
		ItemStackUtil.addLoreToItem(dia, ChatColor.YELLOW + "Used in various Forge and Crucible recipes");
		drops.put(dia, 3);
		return drops;
	}

}
