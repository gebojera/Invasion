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

public class EnchantmentSwordFiery implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fiery I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword set targets alight for" + ChatColor.RED + " 5s");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "Targets on fire take extra damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fiery II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword set targets alight for" + ChatColor.RED + " 10s");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "Targets on fire take extra damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fiery III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword set targets alight for" + ChatColor.RED + " 15s");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "Targets on fire take extra damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fiery IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword set targets alight for" + ChatColor.RED + " 20s");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "Targets on fire take extra damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fiery V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword set targets alight for" + ChatColor.RED + " 30s");
			ItemStackUtil.addLoreToItem(i, ChatColor.RED + "Targets on fire take extra damage");
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
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Fiery I")) {
								if(e.getEntity().getFireTicks() != 0) {
									e.setDamage(e.getDamage() + 7.0);
								}
								e.getEntity().setFireTicks(100);
							}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Fiery II")) {
								if(e.getEntity().getFireTicks() != 0) {
									e.setDamage(e.getDamage() + 7.0);
								}
								e.getEntity().setFireTicks(200);
							}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Fiery III")) {
								if(e.getEntity().getFireTicks() != 0) {
									e.setDamage(e.getDamage() + 7.0);
								}
								e.getEntity().setFireTicks(300);
							}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Fiery IV")) {
								if(e.getEntity().getFireTicks() != 0) {
									e.setDamage(e.getDamage() + 7.0);
								}
								e.getEntity().setFireTicks(400);
							}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Fiery V")) {
								if(e.getEntity().getFireTicks() != 0) {
									e.setDamage(e.getDamage() + 7.0);
								}
								e.getEntity().setFireTicks(600);
							}
						}
					}
				}
			}
		}
	}
}
