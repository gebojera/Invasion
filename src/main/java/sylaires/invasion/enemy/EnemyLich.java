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
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.IRangedEntity;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import sylaires.invasion.attack.LichAttack;
import sylaires.invasion.item.ItemRoughDiamond;
import sylaires.invasion.main.Locations;
import sylaires.invasion.main.Locations.spawnType;
import sylaires.invasion.util.EnemyUtil;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnemyLich extends EntityZombie implements IRangedEntity, IBasicEnemy {
	
	private double gold;
	private int essence;
	private spawnType region;
	private int stage;
	private HashMap<ItemStack, Integer> drops = new HashMap<ItemStack, Integer>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public EnemyLich(org.bukkit.World w, int wave) {
		super(((CraftWorld)w).getHandle());
		
		List goalB = (List)EnemyUtil.getPrivateField("b", PathfinderGoalSelector.class, goalSelector); goalB.clear();
		List goalC = (List)EnemyUtil.getPrivateField("c", PathfinderGoalSelector.class, goalSelector); goalC.clear();
        List targetB = (List)EnemyUtil.getPrivateField("b", PathfinderGoalSelector.class, targetSelector); targetB.clear();
        List targetC = (List)EnemyUtil.getPrivateField("c", PathfinderGoalSelector.class, targetSelector); targetC.clear();
        
        this.goalSelector.a(1, new PathfinderGoalFloat(this));
        this.goalSelector.a(4, new PathfinderGoalMagicAttack(this, new LichAttack(this, this.getGoalTarget()), 8));
        this.goalSelector.a(5, new PathfinderGoalRandomStroll(this, 0.8D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(7, new PathfinderGoalFollowTargetAtRange(this, 6, 0.8f));
        this.goalSelector.a(7, new PathfinderGoalMid(this, Locations.getSpawn(), 0.7));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true));
        
        if(wave < 30) {
        	stage = 1; 
            //Legs
            ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStackUtil.dyeLeather(leg, Color.BLACK);
            //Chest
            ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStackUtil.dyeLeather(chest, Color.BLACK);
            //Wand
            ItemStack wand = new ItemStack(Material.STICK);
            ItemStackUtil.addEnchantment(wand, Enchantment.ARROW_DAMAGE, 0);
            this.setEquipment(2, CraftItemStack.asNMSCopy(leg));
            this.setEquipment(3, CraftItemStack.asNMSCopy(chest));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(150);
            this.heal(500f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave < 60 && wave >= 30) {
        	stage = 2; 
        	ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        	ItemStackUtil.addEnchantment(boots, Enchantment.ARROW_DAMAGE, 0);
        	//Legs
            ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStackUtil.dyeLeather(leg, Color.BLACK);
            //Chest
            ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStackUtil.dyeLeather(chest, Color.BLACK);
            //Wand
            ItemStack wand = new ItemStack(Material.STICK);
            ItemStackUtil.addEnchantment(wand, Enchantment.ARROW_DAMAGE, 0);
            this.setEquipment(1, CraftItemStack.asNMSCopy(boots));
            this.setEquipment(2, CraftItemStack.asNMSCopy(leg));
            this.setEquipment(3, CraftItemStack.asNMSCopy(chest));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(200);
            this.heal(500f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.4);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave < 100 && wave >= 60) {
        	stage = 3;
        	ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        	ItemStackUtil.addEnchantment(boots, Enchantment.ARROW_DAMAGE, 0);
        	//Legs
            ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStackUtil.dyeLeather(leg, Color.BLACK);
            //Chest
            ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStackUtil.dyeLeather(chest, Color.BLACK);
            //Wand
            ItemStack wand = new ItemStack(Material.BLAZE_ROD);
            ItemStackUtil.addEnchantment(wand, Enchantment.ARROW_DAMAGE, 0);
            this.setEquipment(1, CraftItemStack.asNMSCopy(boots));
            this.setEquipment(2, CraftItemStack.asNMSCopy(leg));
            this.setEquipment(3, CraftItemStack.asNMSCopy(chest));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(250);
            this.heal(500f);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.4);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }else if(wave >= 100 && wave >= 100) { //Overtime mob
        	stage = 4;
        	ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        	ItemStackUtil.addEnchantment(boots, Enchantment.ARROW_DAMAGE, 0);
        	//Legs
            ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
            ItemStackUtil.dyeLeather(leg, Color.BLACK);
            //Chest
            ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
            ItemStackUtil.dyeLeather(chest, Color.BLACK);
            //Wand
            ItemStack wand = new ItemStack(Material.BLAZE_ROD);
            ItemStackUtil.addEnchantment(wand, Enchantment.ARROW_DAMAGE, 0);
            this.setEquipment(1, CraftItemStack.asNMSCopy(boots));
            this.setEquipment(2, CraftItemStack.asNMSCopy(leg));
            this.setEquipment(3, CraftItemStack.asNMSCopy(chest));
            this.setEquipment(0, CraftItemStack.asNMSCopy(wand));
            this.dropEquipment(false, 0);
            this.dropEquipment(false, 1);
            this.dropEquipment(false, 2);
            this.dropEquipment(false, 3);
            this.dropEquipment(false, 4);
            this.getAttributeInstance(GenericAttributes.maxHealth).setValue(15*(wave/2));
            this.heal((float) 10*(wave/2));
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.6);
            this.persistent = true;
            this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(256);
        }
        
        gold = (8*stage)*(0.5*wave);
        essence = 60*stage;
        region = spawnType.RUINS; 
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

	@Override
	public void a(EntityLiving arg0, float arg1) {
		
	}

}
