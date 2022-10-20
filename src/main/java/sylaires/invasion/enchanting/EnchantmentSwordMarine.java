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

public class EnchantmentSwordMarine implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Marine I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "30%" + ChatColor.GRAY + " extra damage while in water");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Marine II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "40%" + ChatColor.GRAY + " extra damage while in water");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Marine III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "50%" + ChatColor.GRAY + " extra damage while in water");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Marine IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "60%" + ChatColor.GRAY + " extra damage while in water");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Marine V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "100%" + ChatColor.GRAY + " extra damage while in water");
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
							if(p.getLocation().getBlock().getType() == Material.WATER || p.getEyeLocation().getBlock().getType() == Material.WATER) {
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Marine I")) {
									double extradmg = e.getDamage()*0.3;
									e.setDamage(e.getDamage() + extradmg);
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Marine II")) {
									double extradmg = e.getDamage()*0.4;
									e.setDamage(e.getDamage() + extradmg);
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Marine III")) {
									double extradmg = e.getDamage()*0.5;
									e.setDamage(e.getDamage() + extradmg);
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Marine IV")) {
									double extradmg = e.getDamage()*0.6;
									e.setDamage(e.getDamage() + extradmg);
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Marine V")) {
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
