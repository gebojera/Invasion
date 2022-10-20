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

public class EnchantmentMedic implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Medic I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Left-click a player to heal them for " + ChatColor.GREEN + "20%" + ChatColor.GRAY + " of their total health");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Medic II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Left-click a player to heal them for " + ChatColor.GREEN + "30%" + ChatColor.GRAY + " of their total health");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Medic III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Left-click a player to heal them for " + ChatColor.GREEN + "40%" + ChatColor.GRAY + " of their total health");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Medic IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Left-click a player to heal them for " + ChatColor.GREEN + "50%" + ChatColor.GRAY + " of their total health");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Medic V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Left-click a player to heal them for " + ChatColor.GREEN + "60%" + ChatColor.GRAY + " of their total health");
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
							if(e.getEntity() instanceof Player) {
								Player healed = (Player) e.getEntity();
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Medic I")) {
									double heal = healed.getMaxHealth()*0.2;
									if(heal+healed.getHealth() > healed.getMaxHealth()) {
										healed.setHealth(healed.getMaxHealth());
									}else {
										healed.setHealth(healed.getHealth() + heal);
									}
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Medic II")) {
									double heal = healed.getMaxHealth()*0.2;
									if(heal+healed.getHealth() > healed.getMaxHealth()) {
										healed.setHealth(healed.getMaxHealth());
									}else {
										healed.setHealth(healed.getHealth() + heal);
									}
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Medic III")) {
									double heal = healed.getMaxHealth()*0.2;
									if(heal+healed.getHealth() > healed.getMaxHealth()) {
										healed.setHealth(healed.getMaxHealth());
									}else {
										healed.setHealth(healed.getHealth() + heal);
									}
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Medic IV")) {
									double heal = healed.getMaxHealth()*0.2;
									if(heal+healed.getHealth() > healed.getMaxHealth()) {
										healed.setHealth(healed.getMaxHealth());
									}else {
										healed.setHealth(healed.getHealth() + heal);
									}
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Medic V")) {
									double heal = healed.getMaxHealth()*0.2;
									if(heal+healed.getHealth() > healed.getMaxHealth()) {
										healed.setHealth(healed.getMaxHealth());
									}else {
										healed.setHealth(healed.getHealth() + heal);
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
