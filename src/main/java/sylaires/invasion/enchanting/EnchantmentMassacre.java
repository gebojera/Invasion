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

public class EnchantmentMassacre implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Massacre I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "50%" + ChatColor.GRAY + " increased damage when 8 or more enemies");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "are nearby");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Massacre II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "60%" + ChatColor.GRAY + " increased damage when 8 or more enemies");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "are nearby");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Massacre III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "70%" + ChatColor.GRAY + " increased damage when 8 or more enemies");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "are nearby");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Massacre IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "80%" + ChatColor.GRAY + " increased damage when 8 or more enemies");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "are nearby");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Massacre V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "100%" + ChatColor.GRAY + " increased damage when 8 or more enemies");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "are nearby");
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
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Massacre I")) {
								if(p.getNearbyEntities(8, 8, 8).size() >= 8) {
									double extradmg = e.getDamage()*0.5;
									e.setDamage(e.getDamage() + extradmg);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Massacre II")) {
								if(p.getNearbyEntities(8, 8, 8).size() >= 8) {
									double extradmg = e.getDamage()*0.6;
									e.setDamage(e.getDamage() + extradmg);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Massacre III")) {
								if(p.getNearbyEntities(8, 8, 8).size() >= 8) {
									double extradmg = e.getDamage()*0.7;
									e.setDamage(e.getDamage() + extradmg);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Massacre IV")) {
								if(p.getNearbyEntities(8, 8, 8).size() >= 8) {
									double extradmg = e.getDamage()*0.8;
									e.setDamage(e.getDamage() + extradmg);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Massacre V")) {
								if(p.getNearbyEntities(8, 8, 8).size() >= 8) {
									double extradmg = e.getDamage()*1;
									e.setDamage(e.getDamage() + extradmg);
								}
							}
						}
					}
				}
			}
		}
	}

}
