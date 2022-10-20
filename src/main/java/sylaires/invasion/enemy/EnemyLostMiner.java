package sylaires.invasion.enemy;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import sylaires.invasion.main.Locations;
import sylaires.invasion.main.Locations.spawnType;
import sylaires.invasion.util.EnemyUtil;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyLostMiner extends EntityZombie implements IBasicEnemy {

	private double gold;
	private int essence;
	private spawnType region;
	private int stage;
	private HashMap<ItemStack, Integer> drops = new HashMap<ItemStack, Integer>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EnemyLostMiner(org.bukkit.World w, int wave) {
		super(((CraftWorld)w).getHandle());
		
		List goalB = (List)EnemyUtil.getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
		List goalC = (List)EnemyUtil.getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
        List targetB = (List)EnemyUtil.getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        List targetC = (List)EnemyUtil.getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
        
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(3, new PathfinderGoalLeapAtTarget(this, 0.4F));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.8D));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(6, new PathfinderGoalRandomLookaround(this));
        this.goalSelector.a(7, new PathfinderGoalMid(this, Locations.getSpawn(), 0.7));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        
        if(wave < 30) {
        	stage = 1; //Level 2
        	//Boots
            ItemStack i = new ItemStack(Material.LEATHER_BOOTS);
            //Legs
            ItemStack leg = new ItemStack(Material.CHAINMAIL_LEGGINGS);
            //Chest
            ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
            //Wand
            ItemStack wand = new ItemStack(Material.IRON_PICKAXE);
            this.setEquipment(1, CraftItemStack.asNMSCopy(i));
            this.setEquipment(2, CraftItemStack.asNMSCopy(leg));
            this.setEquipment(3, CraftItemStack.asNMSCopy(chest));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(25);
            this.heal(50f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(7.0);
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave < 60 && wave >= 30) {
        	stage = 2; //level 3
        	//Boots
            ItemStack i = new ItemStack(Material.LEATHER_BOOTS);
            //Legs
            ItemStack leg = new ItemStack(Material.CHAINMAIL_LEGGINGS);
            //Chest
            ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
            //Wand
            ItemStack wand = new ItemStack(Material.IRON_PICKAXE);
            this.setEquipment(1, CraftItemStack.asNMSCopy(i));
            this.setEquipment(2, CraftItemStack.asNMSCopy(leg));
            this.setEquipment(3, CraftItemStack.asNMSCopy(chest));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(45);
            this.heal(50f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.4);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(9.0);
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave < 100 && wave >= 60) {
        	stage = 3;
        	//Boots
            ItemStack i = new ItemStack(Material.LEATHER_BOOTS);
            //Legs
            ItemStack leg = new ItemStack(Material.IRON_LEGGINGS);
            //Chest
            ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
            //Wand
            ItemStack wand = new ItemStack(Material.IRON_PICKAXE);
            this.setEquipment(1, CraftItemStack.asNMSCopy(i));
            this.setEquipment(2, CraftItemStack.asNMSCopy(leg));
            this.setEquipment(3, CraftItemStack.asNMSCopy(chest));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(60);
            this.heal(50f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.4);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(12.0);
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave >= 100 && wave >= 100) { //Overtime mob
        	stage = 4;
        	//Boots
            ItemStack i = new ItemStack(Material.LEATHER_BOOTS);
            //Legs
            ItemStack leg = new ItemStack(Material.DIAMOND_LEGGINGS);
            //Chest
            ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
            //Wand
            ItemStack wand = new ItemStack(Material.IRON_PICKAXE);
            this.setEquipment(1, CraftItemStack.asNMSCopy(i));
            this.setEquipment(2, CraftItemStack.asNMSCopy(leg));
            this.setEquipment(3, CraftItemStack.asNMSCopy(chest));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(8*(wave/2));
            this.heal((float) 10*(wave/2));
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.6);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(0.6*(wave/2));
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }
        
        gold = (2*stage)*(0.5*wave);
        essence = 15*stage;
        region = spawnType.MINE; 
		
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
	
	public HashMap<ItemStack, Integer> getDrops() {
		ItemStack dia = new ItemStack(Material.IRON_INGOT);
		ItemStackUtil.addNameToItem(dia, "Iron Ingot");
		ItemStackUtil.addLoreToItem(dia, ChatColor.GRAY + "Common Item");
		ItemStackUtil.addLoreToItem(dia, ChatColor.YELLOW + "Used in basic Forge recipes");
		drops.put(dia, 10);
		ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);
		ItemStackUtil.addNameToItem(flesh, "Decayed Flesh");
		ItemStackUtil.addLoreToItem(dia, ChatColor.GRAY + "Common Item");
		ItemStackUtil.addLoreToItem(dia, ChatColor.YELLOW + "Can be transmuted into tallow at the Crucible");
		drops.put(flesh, 15);
		return drops;
	}
	

}
