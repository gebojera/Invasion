package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentScavenger implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Scavenger I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy heals you by " + ChatColor.RED + "2❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Scavenger II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy heals you by " + ChatColor.RED + "3❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Scavenger III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy heals you by " + ChatColor.RED + "4❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Scavenger IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy heals you by " + ChatColor.RED + "5❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Scavenger V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing an enemy heals you by " + ChatColor.RED + "6❤");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity().getKiller();
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getLore() != null) {
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Scavenger I")) {
								if(p.getHealth() + 4 > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth()+4);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Scavenger II")) {
								if(p.getHealth() + 6 > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth()+6);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Scavenger III")) {
								if(p.getHealth() + 8 > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth()+8);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Scavenger IV")) {
								if(p.getHealth() + 10 > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth()+10);
								}
							}if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Scavenger V")) {
								if(p.getHealth() + 12 > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth()+12);
								}
							}
						}
					}
				}
			}
		}
	}

}
