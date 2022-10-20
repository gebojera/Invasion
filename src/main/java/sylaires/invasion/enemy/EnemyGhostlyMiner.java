package sylaires.invasion.enemy;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntitySkeleton;
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
import sylaires.invasion.item.ItemRoughDiamond;
import sylaires.invasion.main.Locations.spawnType;
import sylaires.invasion.util.EnemyUtil;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyGhostlyMiner extends EntitySkeleton implements IBasicEnemy {
	
	private double gold;
	private int essence;
	private spawnType region;
	private int stage;
	private HashMap<ItemStack, Integer> drops = new HashMap<ItemStack, Integer>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EnemyGhostlyMiner(org.bukkit.World w, int wave) {
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
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false, new Class[0]));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        
        if(wave < 30) {
        	stage = 1; //Level 2
        	//Boots
            ItemStack i = new ItemStack(Material.LEATHER_BOOTS);
            ItemStackUtil.dyeLeather(i, Color.WHITE);
            ItemStackUtil.addEnchantment(i, Enchantment.ARROW_DAMAGE, 0);
            //Wand
            ItemStack wand = new ItemStack(Material.DIAMOND_PICKAXE);
            this.setEquipment(1, CraftItemStack.asNMSCopy(i));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(10);
            this.heal(50f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.6);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(10.0);
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave < 60 && wave >= 30) {
        	stage = 2; //level 3
        	//Boots
            ItemStack i = new ItemStack(Material.LEATHER_BOOTS);
            ItemStackUtil.dyeLeather(i, Color.WHITE);
            ItemStackUtil.addEnchantment(i, Enchantment.ARROW_DAMAGE, 0);
            //Wand
            ItemStack wand = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemStackUtil.addEnchantment(wand, Enchantment.ARROW_DAMAGE, 0);
            this.setEquipment(1, CraftItemStack.asNMSCopy(i));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(25);
            this.heal(50f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.7);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(14.0);
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave < 100 && wave >= 60) {
        	stage = 3;
        	//Boots
            ItemStack i = new ItemStack(Material.LEATHER_BOOTS);
            ItemStackUtil.dyeLeather(i, Color.WHITE);
            ItemStackUtil.addEnchantment(i, Enchantment.ARROW_DAMAGE, 0);
            //Wand
            ItemStack wand = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemStackUtil.addEnchantment(wand, Enchantment.ARROW_DAMAGE, 0);
            this.setEquipment(1, CraftItemStack.asNMSCopy(i));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(45);
            this.heal(50f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.8);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(16.0);
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave >= 100 && wave >= 100) { //Overtime mob
        	stage = 4;
        	//Boots
            ItemStack i = new ItemStack(Material.LEATHER_BOOTS);
            ItemStackUtil.dyeLeather(i, Color.WHITE);
            ItemStackUtil.addEnchantment(i, Enchantment.ARROW_DAMAGE, 0);
            //Wand
            ItemStack wand = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemStackUtil.addEnchantment(wand, Enchantment.ARROW_DAMAGE, 0);
            this.setEquipment(1, CraftItemStack.asNMSCopy(i));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(4*(wave/2));
            this.heal((float) 10*(wave/2));
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.9);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(0.75*(wave/2));
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }
        
        gold = (4*stage)*(0.5*wave);
        essence = 30*stage;
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
		drops.put(new ItemRoughDiamond().getItemStack(), 5);
		return drops;
	}

}
