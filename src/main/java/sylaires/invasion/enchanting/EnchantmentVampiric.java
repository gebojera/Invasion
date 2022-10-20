package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentVampiric implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Vampiric I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Heals you for " + ChatColor.RED + "10%" + ChatColor.GRAY + " of damage dealt");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Vampiric II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Heals you for " + ChatColor.RED + "20%" + ChatColor.GRAY + " of damage dealt");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Vampiric III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Heals you for " + ChatColor.RED + "30%" + ChatColor.GRAY + " of damage dealt");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Vampiric IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Heals you for " + ChatColor.RED + "40%" + ChatColor.GRAY + " of damage dealt");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Vampiric V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Heals you for " + ChatColor.RED + "50%" + ChatColor.GRAY + " of damage dealt");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getLore() != null) {
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Vampiric I")) {
								double healby = e.getDamage()*0.1;
								if(p.getHealth() + healby > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth() + healby);
								}
							}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Vampiric II")) {
								double healby = e.getDamage()*0.2;
								if(p.getHealth() + healby > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth() + healby);
								}
							}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Vampiric III")) {
								double healby = e.getDamage()*0.3;
								if(p.getHealth() + healby > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth() + healby);
								}
							}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Vampiric IV")) {
								double healby = e.getDamage()*0.4;
								if(p.getHealth() + healby > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth() + healby);
								}
							}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Vampiric V")) {
								double healby = e.getDamage()*0.5;
								if(p.getHealth() + healby > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth() + healby);
								}
							}
						}
					}
				}
			}
		}
	}

}
