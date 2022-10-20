package sylaires.invasion.enchanting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentRegen implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Regeneration I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have permanent " + ChatColor.RED + "Regen I");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Regeneration II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have permanent " + ChatColor.RED + "Regen II");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Regeneration III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have permanent " + ChatColor.RED + "Regen III");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Regeneration IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have permanent " + ChatColor.RED + "Regen IV");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Regeneration V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You have permanent " + ChatColor.RED + "Regen V");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	public static void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.getInventory().getChestplate() != null) {
						ItemStack chest = p.getInventory().getChestplate();
						if(chest.getItemMeta() != null) {
							if(chest.getItemMeta().getLore() != null) {
								if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Regeneration I")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0));
								}else if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Regeneration II")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 1));
								}else if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Regeneration III")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 2));
								}else if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Regeneration IV")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 3));
								}else if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Regeneration V")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 4));
								}
							}
						}
					}
				}
				
			} }, 0, 3);
	}
}

