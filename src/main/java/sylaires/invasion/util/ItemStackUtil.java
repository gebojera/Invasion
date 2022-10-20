package sylaires.invasion.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagDouble;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class ItemStackUtil {
	
	public static ItemStack addNameToItem(ItemStack i, String name) {
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(name);
		i.setItemMeta(meta);
		return i;
	}
	
	public static ItemStack addLoreToItem(ItemStack i, String addLore) {
		ItemMeta meta = i.getItemMeta();
		List<String> lore;
		if(meta.getLore() == null) {
			lore = new ArrayList<String>();
		}else {
			lore = meta.getLore();
		}
		lore.add(addLore);
		meta.setLore(lore);
		i.setItemMeta(meta);
		return i;
	}
	
	public static ItemStack setUnbreakable(ItemStack i) {
		ItemMeta meta = i.getItemMeta();
		meta.spigot().setUnbreakable(true);
		i.setItemMeta(meta);
		return i;
	}
	
	public static ItemStack addEnchantment(ItemStack i, Enchantment e, int level) {
		ItemMeta meta = i.getItemMeta();
		meta.addEnchant(e, level, true);
		i.setItemMeta(meta);
		return i;
	}
	
	public static ItemStack hideAttributes(ItemStack i) {
		ItemMeta meta = i.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		i.setItemMeta(meta);
		return i;
	}
	
	public static ItemStack addFlag(ItemFlag flag, ItemStack i) {
		ItemMeta meta = i.getItemMeta();
		meta.addItemFlags(flag);
		i.setItemMeta(meta);
		return i;
	}
	
	public static ItemStack changeDamage(ItemStack item, double damage) {
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		 NBTTagCompound compound = nmsStack.getTag();
	        if (compound == null) {
	           compound = new NBTTagCompound();
	            nmsStack.setTag(compound);
	            compound = nmsStack.getTag();
	        }
	        NBTTagList modifiers = new NBTTagList();
	        NBTTagCompound healthboost = new NBTTagCompound();
	        healthboost.set("AttributeName", new NBTTagString("generic.attackDamage"));
	        healthboost.set("Name", new NBTTagString("generic.attackDamage"));
	        healthboost.set("Amount", new NBTTagDouble(damage));
	        healthboost.set("Operation", new NBTTagInt(0));
	        healthboost.set("UUIDLeast", new NBTTagInt(894654));
	        healthboost.set("UUIDMost", new NBTTagInt(2872));
	        modifiers.add(healthboost);
	        compound.set("AttributeModifiers", modifiers);
	        nmsStack.setTag(compound);
	        item = CraftItemStack.asBukkitCopy(nmsStack);
	        return item;
	}
	
	 public static ItemStack dyeLeather(ItemStack item, Color color) {
		  LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		  meta.setColor(Color.fromRGB(color.asRGB()));
		  item.setItemMeta(meta);
		  return item;
		  }
	 
	 public static ItemStack makePotion(PotionEffectType type, int duration, int amplifier, boolean splash) {
		 ItemStack potion = null;
		 if(splash) {
			 potion = new ItemStack(Material.POTION, 1, (short) 16384);
		 }else {
			 potion = new ItemStack(Material.POTION);
		 }
		 PotionMeta meta = (PotionMeta) potion.getItemMeta();
		 meta.addCustomEffect(new PotionEffect(type, duration, amplifier), true);
		potion.setItemMeta(meta);
		return potion;
	 }


}
