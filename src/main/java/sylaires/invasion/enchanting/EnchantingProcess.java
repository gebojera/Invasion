package sylaires.invasion.enchanting;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.enchanting.BindingTable.Rarity;
import sylaires.invasion.main.Locations;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantingProcess {
	
	private ItemStack item;
	private Player p;
	
	public EnchantingProcess(ItemStack item, Player p) {
		this.item = item;
		this.p = p;
	}
	
	public void enchantItem() {
		new BukkitRunnable() {
			int counter = 0;
			@Override
			public void run() {
				Location particles = new Location(Main.getWorld(), Locations.getEnch().getX(), Locations.getEnch().getY()+2, Locations.getEnch().getZ());
				ParticleUtil.playParticles(EnumParticle.SPELL_WITCH, particles, 0.1, 0, 0.1, 0, 2);
				if(counter < 30) {
						p.playSound(particles, Sound.CREEPER_HISS, 20, 0.9f);
				}
				if(counter >= 30 && counter <= 35) {
						p.playSound(particles, Sound.CREEPER_HISS, 20, 1f);
				}
				if(counter >= 35 && counter <= 40) {
						p.playSound(particles, Sound.CREEPER_HISS, 20, 1.1f);
				}
				if(counter >= 40 && counter <= 45) {
						p.playSound(particles, Sound.CREEPER_HISS, 20, 1.2f);
				}
				if(counter >= 45 && counter <= 50) {
						p.playSound(particles, Sound.CREEPER_HISS, 20, 1.3f);
				}
				if(counter >= 50 && counter <= 55) {
						p.playSound(particles, Sound.CREEPER_HISS, 20, 1.4f);
				}
				if(counter >= 55 && counter <= 58) {
						p.playSound(particles, Sound.CREEPER_HISS, 20, 1.5f);
				}
				if(counter == 58) {
					p.playSound(particles, Sound.EXPLODE, 20, 1.4f);
				}
				if(counter == 60) {
					ParticleUtil.playParticles(EnumParticle.ENCHANTMENT_TABLE, particles, 0, 0, 0, 1, 50);
					Rarity rare = calcEnch(item);
					if(rare == Rarity.RARE) {
						for(Player p1 : Bukkit.getOnlinePlayers()) {
							p1.playSound(particles, Sound.VILLAGER_YES, 20, 0.8f);
						}
						ParticleUtil.playParticles(EnumParticle.FLAME, particles, 0.1, 0, 0.1, 0.4, 30);
						Bukkit.broadcastMessage("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "ENCHANTMENT! " + ChatColor.YELLOW + p.getName() + " has enchanted a " + ChatColor.GOLD + ChatColor.BOLD + "RARE" + ChatColor.YELLOW + " item!");
					}else if(rare == Rarity.EPIC) {
						for(Player p1 : Bukkit.getOnlinePlayers()) {
							p1.playSound(particles, Sound.WITHER_SPAWN, 20, 1.2f);
						}
						ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, particles, 0.1, 0, 0.1, 0.4, 30);
						Bukkit.broadcastMessage("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "ENCHANTMENT! " + ChatColor.YELLOW + p.getName() + " has enchanted an " + ChatColor.BLUE + ChatColor.BOLD + "EPIC" + ChatColor.YELLOW + " item!");
					}else if(rare == Rarity.LEGENDARY) {
						for(Player p1 : Bukkit.getOnlinePlayers()) {
							p1.playSound(particles, Sound.ENDERDRAGON_GROWL, 20, 1f);
						}
						ParticleUtil.playParticles(EnumParticle.PORTAL, particles, 0.1, 0, 0.1, 0.4, 30);
						ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, particles, 0.1, 0, 0.1, 0.4, 30);
						ParticleUtil.playParticles(EnumParticle.FLAME, particles, 0.1, 0, 0.1, 0.4, 30);
						Bukkit.broadcastMessage("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "ENCHANTMENT! " + ChatColor.YELLOW + p.getName() + " has enchanted a " + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY" + ChatColor.YELLOW + " item!");
					}
					p.getInventory().addItem(item);
					p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 30, 1.1F);
					BindingTable.processes.remove(p);
					this.cancel();
				}else {
					counter++;
				}
			}
			
		}.runTaskTimer(Main.getPlugin(), 0, 1);
	}
	
	private Rarity calcEnch(ItemStack item) {
		Rarity rarity = Rarity.MUNDANE;
		if(item.getType() == Material.DIAMOND_SWORD) { //Enchant swords
			if(item.getItemMeta().getDisplayName() == null) { //If it's a plain sword
				ItemStackUtil.addNameToItem(item, ChatColor.LIGHT_PURPLE + "Enchanted Diamond Sword");
				ItemStackUtil.addEnchantment(item, Enchantment.ARROW_DAMAGE, 0);
				ItemStackUtil.addFlag(ItemFlag.HIDE_ENCHANTS, item);
				Random rand = new Random();
				int randrarity = rand.nextInt(100); 
				if(randrarity <= 79) { //80% chance for a common - First enchantment
					int randcommon = rand.nextInt(10);
					int randlevel = rand.nextInt(5); 
					int level = 0;
					switch(randlevel) {
					case 0: level = 1; break;
					case 1: level = 2; break;
					case 2: level = 3; break;
					case 3: level = 4; break;
					case 4: level = 5; break;
					}
					switch(randcommon) {
					case 0: EnchantmentBeheading.apply(item, level); break;
					case 1: EnchantmentNether.apply(item, level); break;
					case 2: EnchantmentParry.apply(item, level); break;
					case 3: EnchantmentSharpness.apply(item, level); break;
					case 4: EnchantmentShatter.apply(item, level); break;
					case 5: EnchantmentSmite.apply(item, level); break;
					case 6: EnchantmentSwordDurable.apply(item, level); break;
					case 7: EnchantmentSwordHeavy.apply(item, level); break;
					case 8: EnchantmentSwordMarine.apply(item, level); break;
					case 9: EnchantmentWealthy.apply(item, level); break;
					}
				}else if(randrarity > 79 && randrarity <= 89) { //10% chance for rare
					int randrare = rand.nextInt(7);
					int randlevel = rand.nextInt(5); 
					int level = 0;
					switch(randlevel) {
					case 0: level = 1; break;
					case 1: level = 2; break;
					case 2: level = 3; break;
					case 3: level = 4; break;
					case 4: level = 5; break;
					}
					switch(randrare) {
					case 0: EnchantmentMassacre.apply(item, level); break;
					case 1: EnchantmentMedic.apply(item, level); break;
					case 2: EnchantmentRepulsion.apply(item, level); break;
					case 3: EnchantmentScavenger.apply(item, level); break;
					case 4: EnchantmentSwordFiery.apply(item, level); break;
					case 5: EnchantmentTrueEdge.apply(item, level); break;
					case 6: EnchantmentWither.apply(item, level); break;
					}
					if(rarity != Rarity.EPIC && rarity != Rarity.LEGENDARY) {
						rarity = Rarity.RARE;
					}
					
				}else if(randrarity > 89 && randrarity <= 96) { //7% chance for epic
					int randepic = rand.nextInt(5);
					int randlevel = rand.nextInt(5); 
					int level = 0;
					switch(randlevel) {
					case 0: level = 1; break;
					case 1: level = 2; break;
					case 2: level = 3; break;
					case 3: level = 4; break;
					case 4: level = 5; break;
					}
					switch(randepic) {
					case 0: EnchantmentVampiric.apply(item, level); break;
					case 1: EnchantmentObliterate.apply(item, level); break;
					case 2: EnchantmentLaunch.apply(item, level); break;
					case 3: EnchantmentBrutalize.apply(item, level); break;
					case 4: EnchantmentStun.apply(item, level); break;
					}
					if(rarity != Rarity.LEGENDARY) {
						rarity = Rarity.EPIC;
					}
				}else if(randrarity > 96 && randrarity <= 99) { //3% chance of legendary
					int randleg = rand.nextInt(6);
					switch(randleg) {
					case 0: EnchantmentCrusadersSmite.apply(item); break;
					case 1: EnchantmentGodforged.apply(item); break;
					case 2: EnchantmentErupt.apply(item); break;
					case 3: EnchantmentTerrabeam.apply(item); break;
					case 4: EnchantmentBloodPact.apply(item); break;
					case 5: EnchantmentAssassinate.apply(item); break;
					}
					rarity = Rarity.LEGENDARY;
				}
				
				//--------------------------------------------
				
				randrarity = rand.nextInt(100);
				if(randrarity <= 79) { //80% chance for a common - Second enchantment
					int randcommon = rand.nextInt(10);
					int randlevel = rand.nextInt(5); 
					int level = 0;
					switch(randlevel) {
					case 0: level = 1; break;
					case 1: level = 2; break;
					case 2: level = 3; break;
					case 3: level = 4; break;
					case 4: level = 5; break;
					}
					switch(randcommon) {
					case 0: if(BindingTable.hasEnch("Beheading", item)) {
						break;
					}else {
						EnchantmentBeheading.apply(item, level); break;
					}
					case 1: if(BindingTable.hasEnch("Nether Purger", item)) {
						break;
					}else {
						EnchantmentNether.apply(item, level); break;
					}
					case 2: if(BindingTable.hasEnch("Parry", item)) {
						break;
					}else {
						EnchantmentParry.apply(item, level); break;
					}
					case 3: if(BindingTable.hasEnch("Sharpness", item)) {
						break;
					}else {
						EnchantmentSharpness.apply(item, level); break;
					}
					case 4: if(BindingTable.hasEnch("Shatter", item)) {
						break;
					}else {
						EnchantmentShatter.apply(item, level); break;
					}
					case 5: if(BindingTable.hasEnch("Smite", item)) {
						break;
					}else {
						EnchantmentSmite.apply(item, level); break;
					}
					case 6: if(BindingTable.hasEnch("Durable", item)) {
						break;
					}else {
						EnchantmentSwordDurable.apply(item, level); break;
					}
					case 7: if(BindingTable.hasEnch("Crush", item)) {
						break;
					}else {
						EnchantmentSwordHeavy.apply(item, level); break;
					}
					case 8: if(BindingTable.hasEnch("marine", item)) {
						break;
					}else {
						EnchantmentSwordMarine.apply(item, level); break;
					}
					case 9: if(BindingTable.hasEnch("Wealthy", item)) {
						break;
					}else {
						EnchantmentWealthy.apply(item, level); break;
					}
					}
				}else if(randrarity > 79 && randrarity <= 89) { //10% chance for rare
					int randrare = rand.nextInt(7);
					int randlevel = rand.nextInt(5); 
					int level = 0;
					switch(randlevel) {
					case 0: level = 1; break;
					case 1: level = 2; break;
					case 2: level = 3; break;
					case 3: level = 4; break;
					case 4: level = 5; break;
					}
					switch(randrare) {
					case 0: if(BindingTable.hasEnch("Massacre", item)) {
						break;
					}else {
						EnchantmentMassacre.apply(item, level); break;
					}
					case 1: if(BindingTable.hasEnch("Medic", item)) {
						break;
					}else {
						EnchantmentMedic.apply(item, level); break;
					}
					case 2: if(BindingTable.hasEnch("Repulsor", item)) {
						break;
					}else {
						EnchantmentRepulsion.apply(item, level); break;
					}
					case 3: if(BindingTable.hasEnch("Scavenger", item)) {
						break;
					}else {
						EnchantmentScavenger.apply(item, level); break;
					}
					case 4: if(BindingTable.hasEnch("Fiery", item)) {
						break;
					}else {
						EnchantmentSwordFiery.apply(item, level); break;
					}
					case 5: if(BindingTable.hasEnch("True Edge", item)) {
						break;
					}else {
						EnchantmentTrueEdge.apply(item, level); break;
					}
					case 6: if(BindingTable.hasEnch("Wither", item)) {
						break;
					}else {
						EnchantmentWither.apply(item, level); break;
					}
					}
					if(rarity != Rarity.EPIC && rarity != Rarity.LEGENDARY) {
						rarity = Rarity.RARE;
					}
				}else if(randrarity > 89 && randrarity <= 96) { //7% chance for epic
					int randepic = rand.nextInt(5);
					int randlevel = rand.nextInt(5); 
					int level = 0;
					switch(randlevel) {
					case 0: level = 1; break;
					case 1: level = 2; break;
					case 2: level = 3; break;
					case 3: level = 4; break;
					case 4: level = 5; break;
					}
					switch(randepic) {
					case 0: if(BindingTable.hasEnch("Vampiric", item)) {
						break;
					}else {
						EnchantmentVampiric.apply(item, level); break;
					}
					case 1: if(BindingTable.hasEnch("Obliterate", item)) {
						break;
					}else {
						EnchantmentObliterate.apply(item, level); break;
					}
					case 2: if(BindingTable.hasEnch("Launch", item)) {
						break;
					}else {
						EnchantmentLaunch.apply(item, level); break;
					}
					case 3: if(BindingTable.hasEnch("Brutalize", item)) {
						break;
					}else {
						EnchantmentBrutalize.apply(item, level); break;
					}
					case 4: if(BindingTable.hasEnch("Stun", item)) {
						break;
					}else {
						EnchantmentStun.apply(item, level); break;
					}
					}
					if(rarity != Rarity.LEGENDARY) {
						rarity = Rarity.EPIC;
					}
				}else if(randrarity > 96 && randrarity <= 99) { //3% chance of legendary
					int randleg = rand.nextInt(6);
					switch(randleg) {
					case 0: if(BindingTable.hasEnch("Crusader's Smite", item)) {
						break;
					}else {
						EnchantmentCrusadersSmite.apply(item); break;
					}
					case 1: if(BindingTable.hasEnch("God-Forged", item)) {
						break;
					}else {
						EnchantmentGodforged.apply(item); break;
					}
					case 2: if(BindingTable.hasEnch("Corpse Eruption", item)) {
						break;
					}else {
						EnchantmentErupt.apply(item); break;
					}
					case 3: if(BindingTable.hasEnch("Terrabeam", item)) {
						break;
					}else {
						EnchantmentTerrabeam.apply(item); break;
					}
					case 4: if(BindingTable.hasEnch("Blood Pact", item)) {
						break;
					}else {
						EnchantmentBloodPact.apply(item); break;
					}
					case 5: if(BindingTable.hasEnch("Assassinate", item)) {
						break;
					}else {
						EnchantmentAssassinate.apply(item); break;
					}
					}
					rarity = Rarity.LEGENDARY;
			}
				randrarity = rand.nextInt(100);
				if(randrarity <= 79) { //80% chance for a common - Third enchantment
					int randcommon = rand.nextInt(10);
					int randlevel = rand.nextInt(5); 
					int level = 0;
					switch(randlevel) {
					case 0: level = 1; break;
					case 1: level = 2; break;
					case 2: level = 3; break;
					case 3: level = 4; break;
					case 4: level = 5; break;
					}
					switch(randcommon) {
					case 0: if(BindingTable.hasEnch("Beheading", item)) {
						break;
					}else {
						EnchantmentBeheading.apply(item, level); break;
					}
					case 1: if(BindingTable.hasEnch("Nether Purger", item)) {
						break;
					}else {
						EnchantmentNether.apply(item, level); break;
					}
					case 2: if(BindingTable.hasEnch("Parry", item)) {
						break;
					}else {
						EnchantmentParry.apply(item, level); break;
					}
					case 3: if(BindingTable.hasEnch("Sharpness", item)) {
						break;
					}else {
						EnchantmentSharpness.apply(item, level); break;
					}
					case 4: if(BindingTable.hasEnch("Shatter", item)) {
						break;
					}else {
						EnchantmentShatter.apply(item, level); break;
					}
					case 5: if(BindingTable.hasEnch("Smite", item)) {
						break;
					}else {
						EnchantmentSmite.apply(item, level); break;
					}
					case 6: if(BindingTable.hasEnch("Durable", item)) {
						break;
					}else {
						EnchantmentSwordDurable.apply(item, level); break;
					}
					case 7: if(BindingTable.hasEnch("Crush", item)) {
						break;
					}else {
						EnchantmentSwordHeavy.apply(item, level); break;
					}
					case 8: if(BindingTable.hasEnch("marine", item)) {
						break;
					}else {
						EnchantmentSwordMarine.apply(item, level); break;
					}
					case 9: if(BindingTable.hasEnch("Wealthy", item)) {
						break;
					}else {
						EnchantmentWealthy.apply(item, level); break;
					}
					}
				}else if(randrarity > 79 && randrarity <= 89) { //10% chance for rare
					int randrare = rand.nextInt(7);
					int randlevel = rand.nextInt(5); 
					int level = 0;
					switch(randlevel) {
					case 0: level = 1; break;
					case 1: level = 2; break;
					case 2: level = 3; break;
					case 3: level = 4; break;
					case 4: level = 5; break;
					}
					switch(randrare) {
					case 0: if(BindingTable.hasEnch("Massacre", item)) {
						break;
					}else {
						EnchantmentMassacre.apply(item, level); break;
					}
					case 1: if(BindingTable.hasEnch("Medic", item)) {
						break;
					}else {
						EnchantmentMedic.apply(item, level); break;
					}
					case 2: if(BindingTable.hasEnch("Repulsor", item)) {
						break;
					}else {
						EnchantmentRepulsion.apply(item, level); break;
					}
					case 3: if(BindingTable.hasEnch("Scavenger", item)) {
						break;
					}else {
						EnchantmentScavenger.apply(item, level); break;
					}
					case 4: if(BindingTable.hasEnch("Fiery", item)) {
						break;
					}else {
						EnchantmentSwordFiery.apply(item, level); break;
					}
					case 5: if(BindingTable.hasEnch("True Edge", item)) {
						break;
					}else {
						EnchantmentTrueEdge.apply(item, level); break;
					}
					case 6: if(BindingTable.hasEnch("Wither", item)) {
						break;
					}else {
						EnchantmentWither.apply(item, level); break;
					}
					}
					if(rarity != Rarity.EPIC && rarity != Rarity.LEGENDARY) {
						rarity = Rarity.RARE;
					}
				}else if(randrarity > 89 && randrarity <= 96) { //7% chance for epic
					int randepic = rand.nextInt(5);
					int randlevel = rand.nextInt(5); 
					int level = 0;
					switch(randlevel) {
					case 0: level = 1; break;
					case 1: level = 2; break;
					case 2: level = 3; break;
					case 3: level = 4; break;
					case 4: level = 5; break;
					}
					switch(randepic) {
					case 0: if(BindingTable.hasEnch("Vampiric", item)) {
						break;
					}else {
						EnchantmentVampiric.apply(item, level); break;
					}
					case 1: if(BindingTable.hasEnch("Obliterate", item)) {
						break;
					}else {
						EnchantmentObliterate.apply(item, level); break;
					}
					case 2: if(BindingTable.hasEnch("Launch", item)) {
						break;
					}else {
						EnchantmentLaunch.apply(item, level); break;
					}
					case 3: if(BindingTable.hasEnch("Brutalize", item)) {
						break;
					}else {
						EnchantmentBrutalize.apply(item, level); break;
					}
					case 4: if(BindingTable.hasEnch("Stun", item)) {
						break;
					}else {
						EnchantmentStun.apply(item, level); break;
					}
					}
					if(rarity != Rarity.LEGENDARY) {
						rarity = Rarity.EPIC;
					}
				}else if(randrarity > 96 && randrarity <= 99) { //3% chance of legendary
					int randleg = rand.nextInt(6);
					switch(randleg) {
					case 0: if(BindingTable.hasEnch("Crusader's Smite", item)) {
						break;
					}else {
						EnchantmentCrusadersSmite.apply(item); break;
					}
					case 1: if(BindingTable.hasEnch("God-Forged", item)) {
						break;
					}else {
						EnchantmentGodforged.apply(item); break;
					}
					case 2: if(BindingTable.hasEnch("Corpse Eruption", item)) {
						break;
					}else {
						EnchantmentErupt.apply(item); break;
					}
					case 3: if(BindingTable.hasEnch("Terrabeam", item)) {
						break;
					}else {
						EnchantmentTerrabeam.apply(item); break;
					}
					case 4: if(BindingTable.hasEnch("Blood Pact", item)) {
						break;
					}else {
						EnchantmentBloodPact.apply(item); break;
					}
					case 5: if(BindingTable.hasEnch("Assassinate", item)) {
						break;
					}else {
						EnchantmentAssassinate.apply(item); break;
					}
					}
					rarity = Rarity.LEGENDARY;
			}
	}
	}else if(item.getType() == Material.DIAMOND_CHESTPLATE) {
		if(item.getItemMeta().getDisplayName() == null) { 
			ItemStackUtil.addNameToItem(item, ChatColor.LIGHT_PURPLE + "Enchanted Diamond Chestplate");
			ItemStackUtil.addEnchantment(item, Enchantment.ARROW_DAMAGE, 0);
			ItemStackUtil.addFlag(ItemFlag.HIDE_ENCHANTS, item);
			Random rand = new Random();
			int randrarity = rand.nextInt(100); 
			if(randrarity <= 79) { //80% chance for a common - First enchantment
				int randcommon = rand.nextInt(8);
				int randlevel = rand.nextInt(5); 
				int level = 0;
				switch(randlevel) {
				case 0: level = 1; break;
				case 1: level = 2; break;
				case 2: level = 3; break;
				case 3: level = 4; break;
				case 4: level = 5; break;
				}
				switch(randcommon) {
				case 0: EnchantmentCavedweller.apply(item, level); break;
				case 1: EnchantmentWildAndFree.apply(item, level); break;
				case 2: EnchantmentBastion.apply(item, level); break;
				case 3: EnchantmentFireProt.apply(item, level); break;
				case 4: EnchantmentSturdyMail.apply(item, level); break;
				case 5: EnchantmentArmorDurable.apply(item, level); break;
				case 6: EnchantmentReflectivePlate.apply(item, level); break;
				case 7: EnchantmentArchery.apply(item, level); break;
				}
			}else if(randrarity > 79 && randrarity <= 89) { //10% chance for rare
				int randrare = rand.nextInt(4);
				int randlevel = rand.nextInt(5); 
				int level = 0;
				switch(randlevel) {
				case 0: level = 1; break;
				case 1: level = 2; break;
				case 2: level = 3; break;
				case 3: level = 4; break;
				case 4: level = 5; break;
				}
				switch(randrare) {
				case 0: EnchantmentThorns.apply(item, level); break;
				case 1: EnchantmentAntidote.apply(item, level); break;
				case 2: EnchantmentInstinct.apply(item, level); break;
				case 3: EnchantmentHearts.apply(item, level); break;
				}
				if(rarity != Rarity.EPIC && rarity != Rarity.LEGENDARY) {
					rarity = Rarity.RARE;
				}
				
			}else if(randrarity > 89 && randrarity <= 96) { //7% chance for epic
				int randepic = rand.nextInt(4);
				int randlevel = rand.nextInt(5); 
				int level = 0;
				switch(randlevel) {
				case 0: level = 1; break;
				case 1: level = 2; break;
				case 2: level = 3; break;
				case 3: level = 4; break;
				case 4: level = 5; break;
				}
				switch(randepic) {
				case 0: EnchantmentUnassailable.apply(item, level); break;
				case 1: EnchantmentRegen.apply(item, level); break;
				case 2: EnchantmentDivineIntervention.apply(item, level); break;
				case 3: EnchantmentChallenger.apply(item, level); break;
				}
				if(rarity != Rarity.LEGENDARY) {
					rarity = Rarity.EPIC;
				}
			}else if(randrarity > 96 && randrarity <= 99) { //3% chance of legendary
				int randleg = rand.nextInt(4);
				switch(randleg) {
				case 0: EnchantmentGodforged.apply(item); break;
				case 1: EnchantmentPhoenix.apply(item); break;
				case 2: EnchantmentShadowform.apply(item); break;
				case 3: EnchantmentSanguinare.apply(item); break;
				}
				rarity = Rarity.LEGENDARY;
			}
			
			//--------------------------------------------
			
			randrarity = rand.nextInt(100);
			if(randrarity <= 79) { //80% chance for a common - Second enchantment
				int randcommon = rand.nextInt(8);
				int randlevel = rand.nextInt(5); 
				int level = 0;
				switch(randlevel) {
				case 0: level = 1; break;
				case 1: level = 2; break;
				case 2: level = 3; break;
				case 3: level = 4; break;
				case 4: level = 5; break;
				}
				switch(randcommon) {
				case 0: if(BindingTable.hasEnch("Cavedweller", item)) {
					break;
				}else {
					EnchantmentCavedweller.apply(item, level); break;
				}
				case 1: if(BindingTable.hasEnch("Wild and Free", item)) {
					break;
				}else {
					EnchantmentWildAndFree.apply(item, level); break;
				}
				case 2: if(BindingTable.hasEnch("Bastion", item)) {
					break;
				}else {
					EnchantmentBastion.apply(item, level); break;
				}
				case 3: if(BindingTable.hasEnch("Fire Protection", item)) {
					break;
				}else {
					EnchantmentFireProt.apply(item, level); break;
				}
				case 4: if(BindingTable.hasEnch("Sturdy Mail", item)) {
					break;
				}else {
					EnchantmentSturdyMail.apply(item, level); break;
				}
				case 5: if(BindingTable.hasEnch("Durable", item)) {
					break;
				}else {
					EnchantmentArmorDurable.apply(item, level); break;
				}
				case 6: if(BindingTable.hasEnch("Reflection", item)) {
					break;
				}else {
					EnchantmentReflectivePlate.apply(item, level); break;
				}
				case 7: if(BindingTable.hasEnch("Archery", item)) {
					break;
				}else {
					EnchantmentArchery.apply(item, level); break;
				}
				}
			}else if(randrarity > 79 && randrarity <= 89) { //10% chance for rare
				int randrare = rand.nextInt(4);
				int randlevel = rand.nextInt(5); 
				int level = 0;
				switch(randlevel) {
				case 0: level = 1; break;
				case 1: level = 2; break;
				case 2: level = 3; break;
				case 3: level = 4; break;
				case 4: level = 5; break;
				}
				switch(randrare) {
				case 0: if(BindingTable.hasEnch("Thorns", item)) {
					break;
				}else {
					EnchantmentThorns.apply(item, level); break;
				}
				case 1: if(BindingTable.hasEnch("Antidote", item)) {
					break;
				}else {
					EnchantmentAntidote.apply(item, level); break;
				}
				case 2: if(BindingTable.hasEnch("Instinct", item)) {
					break;
				}else {
					EnchantmentInstinct.apply(item, level); break;
				}
				case 3: if(BindingTable.hasEnch("Hearts", item)) {
					break;
				}else {
					EnchantmentHearts.apply(item, level); break;
				}
				}
				if(rarity != Rarity.EPIC && rarity != Rarity.LEGENDARY) {
					rarity = Rarity.RARE;
				}
			}else if(randrarity > 89 && randrarity <= 96) { //7% chance for epic
				int randepic = rand.nextInt(4);
				int randlevel = rand.nextInt(5); 
				int level = 0;
				switch(randlevel) {
				case 0: level = 1; break;
				case 1: level = 2; break;
				case 2: level = 3; break;
				case 3: level = 4; break;
				case 4: level = 5; break;
				}
				switch(randepic) {
				case 0: if(BindingTable.hasEnch("Unassailable", item)) {
					break;
				}else {
					EnchantmentUnassailable.apply(item, level); break;
				}
				case 1: if(BindingTable.hasEnch("Regeneration", item)) {
					break;
				}else {
					EnchantmentRegen.apply(item, level); break;
				}
				case 2: if(BindingTable.hasEnch("Divine Intervention", item)) {
					break;
				}else {
					EnchantmentDivineIntervention.apply(item, level); break;
				}
				case 3: if(BindingTable.hasEnch("Challenger", item)) {
					break;
				}else {
					EnchantmentChallenger.apply(item, level); break;
				}
				}
				if(rarity != Rarity.LEGENDARY) {
					rarity = Rarity.EPIC;
				}
			}else if(randrarity > 96 && randrarity <= 99) { //3% chance of legendary
				int randleg = rand.nextInt(4);
				switch(randleg) {
				case 0: if(BindingTable.hasEnch("God-Forged", item)) {
					break;
				}else {
					EnchantmentGodforged.apply(item); break;
				}
				case 1: if(BindingTable.hasEnch("Phoenix", item)) {
					break;
				}else {
					EnchantmentPhoenix.apply(item); break;
				}
				case 2: if(BindingTable.hasEnch("Shadowform", item)) {
					break;
				}else {
					EnchantmentShadowform.apply(item); break;
				}
				case 3: if(BindingTable.hasEnch("Sanguinare", item)) {
					break;
				}else {
					EnchantmentSanguinare.apply(item); break;
				}
				}
				rarity = Rarity.LEGENDARY;
		}
			randrarity = rand.nextInt(100);
			if(randrarity <= 79) { //80% chance for a common - Third enchantment
				int randcommon = rand.nextInt(8);
				int randlevel = rand.nextInt(5); 
				int level = 0;
				switch(randlevel) {
				case 0: level = 1; break;
				case 1: level = 2; break;
				case 2: level = 3; break;
				case 3: level = 4; break;
				case 4: level = 5; break;
				}
				switch(randcommon) {
				case 0: if(BindingTable.hasEnch("Cavedweller", item)) {
					break;
				}else {
					EnchantmentCavedweller.apply(item, level); break;
				}
				case 1: if(BindingTable.hasEnch("Wild and Free", item)) {
					break;
				}else {
					EnchantmentWildAndFree.apply(item, level); break;
				}
				case 2: if(BindingTable.hasEnch("Bastion", item)) {
					break;
				}else {
					EnchantmentBastion.apply(item, level); break;
				}
				case 3: if(BindingTable.hasEnch("Fire Protection", item)) {
					break;
				}else {
					EnchantmentFireProt.apply(item, level); break;
				}
				case 4: if(BindingTable.hasEnch("Sturdy Mail", item)) {
					break;
				}else {
					EnchantmentSturdyMail.apply(item, level); break;
				}
				case 5: if(BindingTable.hasEnch("Durable", item)) {
					break;
				}else {
					EnchantmentArmorDurable.apply(item, level); break;
				}
				case 6: if(BindingTable.hasEnch("Reflection", item)) {
					break;
				}else {
					EnchantmentReflectivePlate.apply(item, level); break;
				}
				case 7: if(BindingTable.hasEnch("Archery", item)) {
					break;
				}else {
					EnchantmentArchery.apply(item, level); break;
				}
				}
			}else if(randrarity > 79 && randrarity <= 89) { //10% chance for rare
				int randrare = rand.nextInt(4);
				int randlevel = rand.nextInt(5); 
				int level = 0;
				switch(randlevel) {
				case 0: level = 1; break;
				case 1: level = 2; break;
				case 2: level = 3; break;
				case 3: level = 4; break;
				case 4: level = 5; break;
				}
				switch(randrare) {
				case 0: if(BindingTable.hasEnch("Thorns", item)) {
					break;
				}else {
					EnchantmentThorns.apply(item, level); break;
				}
				case 1: if(BindingTable.hasEnch("Antidote", item)) {
					break;
				}else {
					EnchantmentAntidote.apply(item, level); break;
				}
				case 2: if(BindingTable.hasEnch("Instinct", item)) {
					break;
				}else {
					EnchantmentInstinct.apply(item, level); break;
				}
				case 3: if(BindingTable.hasEnch("Hearts", item)) {
					break;
				}else {
					EnchantmentHearts.apply(item, level); break;
				}
				}
				if(rarity != Rarity.EPIC && rarity != Rarity.LEGENDARY) {
					rarity = Rarity.RARE;
				}
			}else if(randrarity > 89 && randrarity <= 96) { //7% chance for epic
				int randepic = rand.nextInt(4);
				int randlevel = rand.nextInt(5); 
				int level = 0;
				switch(randlevel) {
				case 0: level = 1; break;
				case 1: level = 2; break;
				case 2: level = 3; break;
				case 3: level = 4; break;
				case 4: level = 5; break;
				}
				switch(randepic) {
				case 0: if(BindingTable.hasEnch("Unassailable", item)) {
					break;
				}else {
					EnchantmentUnassailable.apply(item, level); break;
				}
				case 1: if(BindingTable.hasEnch("Regeneration", item)) {
					break;
				}else {
					EnchantmentRegen.apply(item, level); break;
				}
				case 2: if(BindingTable.hasEnch("Divine Intervention", item)) {
					break;
				}else {
					EnchantmentDivineIntervention.apply(item, level); break;
				}
				case 3: if(BindingTable.hasEnch("Challenger", item)) {
					break;
				}else {
					EnchantmentChallenger.apply(item, level); break;
				}
				}
				if(rarity != Rarity.LEGENDARY) {
					rarity = Rarity.EPIC;
				}
			}else if(randrarity > 96 && randrarity <= 99) { //3% chance of legendary
				int randleg = rand.nextInt(4);
				switch(randleg) {
				case 0: if(BindingTable.hasEnch("God-Forged", item)) {
					break;
				}else {
					EnchantmentGodforged.apply(item); break;
				}
				case 1: if(BindingTable.hasEnch("Phoenix", item)) {
					break;
				}else {
					EnchantmentPhoenix.apply(item); break;
				}
				case 2: if(BindingTable.hasEnch("Shadowform", item)) {
					break;
				}else {
					EnchantmentShadowform.apply(item); break;
				}
				case 3: if(BindingTable.hasEnch("Sanguinare", item)) {
					break;
				}else {
					EnchantmentSanguinare.apply(item); break;
				}
				}
				rarity = Rarity.LEGENDARY;
		}
	
}
}else if(item.getType() == Material.BOW){
	if(item.getItemMeta().getDisplayName() == null) { 
		ItemStackUtil.addNameToItem(item, ChatColor.LIGHT_PURPLE + "Enchanted Bow");
		ItemStackUtil.addEnchantment(item, Enchantment.ARROW_DAMAGE, 0);
		ItemStackUtil.addFlag(ItemFlag.HIDE_ENCHANTS, item);
		int enchNum = 0;
		Random rand = new Random();
		enchNum++;
		int rande = rand.nextInt(9); //Apply first ench
		switch(rande) { 
		case 0: EnchantmentEnder.apply(item); break;
		case 1: EnchantmentRobin.apply(item); break;
		case 2: EnchantmentGrapple.apply(item); break;
		case 3: EnchantmentSniper.apply(item); break;
		case 4: EnchantmentNetheric.apply(item); break;
		case 5: EnchantmentGodforged.apply(item); break;
		case 6: EnchantmentMissle.apply(item); break;
		case 7: EnchantmentHoming.apply(item); break;
		case 8: EnchantmentTrueShot.apply(item); break;
		}
		rarity = Rarity.MUNDANE;
		int next = rand.nextInt(100);
		while(next <= 49) {
			rande = rand.nextInt(9); //Apply another
			switch(rande) { 
			case 0: if(BindingTable.hasEnch("Ender", item)) {
				break;
			}else {
				enchNum++;
				EnchantmentEnder.apply(item); break;
			}
			case 1: if(BindingTable.hasEnch("Barrage", item)) {
				break;
			}else {
				enchNum++;
				EnchantmentRobin.apply(item); break;
			}
			case 2: if(BindingTable.hasEnch("Grappler", item)) {
				break;
			}else {
				enchNum++;
				EnchantmentGrapple.apply(item); break;
			}
			case 3: if(BindingTable.hasEnch("Sniper", item)) {
				break;
			}else {
				enchNum++;
				EnchantmentSniper.apply(item); break;
			}
			case 4: if(BindingTable.hasEnch("Netheric", item)) {
				break;
			}else {
				enchNum++;
				EnchantmentNetheric.apply(item); break;
			}
			case 5: if(BindingTable.hasEnch("God-Forged", item)) {
				break;
			}else {
				enchNum++;
				EnchantmentGodforged.apply(item); break;
			}
			case 6: if(BindingTable.hasEnch("Explosive", item)) {
				break;
			}else {
				enchNum++;
				EnchantmentMissle.apply(item); break;
			}
			case 7: if(BindingTable.hasEnch("Homing", item)) {
				break;
			}else {
				enchNum++;
				EnchantmentHoming.apply(item); break;
			}
			case 8: if(BindingTable.hasEnch("True Shot", item)) {
				break;
			}else {
				enchNum++;
				EnchantmentTrueShot.apply(item); break;
			}
			}
			if(enchNum == 4) {
				break;
			}else {
				next = rand.nextInt(100);
			}
		}
		
		if(enchNum == 2) {
			ItemStackUtil.addNameToItem(item, ChatColor.LIGHT_PURPLE + "Rare Enchanted Bow");
			rarity = Rarity.RARE;
		}else if(enchNum == 3) {
			ItemStackUtil.addNameToItem(item, ChatColor.LIGHT_PURPLE + "Epic Enchanted Bow");
			rarity = Rarity.EPIC;
		}else if(enchNum == 4) {
			ItemStackUtil.addNameToItem(item, "" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Legendary Enchanted Bow");
			rarity = Rarity.LEGENDARY;
		}
		
		

}
	
}
		return rarity;
}
}
