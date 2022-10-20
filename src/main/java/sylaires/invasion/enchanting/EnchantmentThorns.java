package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentThorns implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Thorns I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attackers take " + ChatColor.RED + "60%" + ChatColor.GRAY + " of their damage onto themselves");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Thorns II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attackers take " + ChatColor.RED + "70%" + ChatColor.GRAY + " of their damage onto themselves");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Thorns III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attackers take " + ChatColor.RED + "80%" + ChatColor.GRAY + " of their damage onto themselves");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Thorns IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attackers take " + ChatColor.RED + "90%" + ChatColor.GRAY + " of their damage onto themselves");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Thorns V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attackers take " + ChatColor.RED + "100%" + ChatColor.GRAY + " of their damage onto themselves");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(e.getDamager() instanceof LivingEntity) {
				LivingEntity attacker = (LivingEntity) e.getDamager();
				if(p.getInventory().getChestplate() != null) {
					ItemStack item = p.getInventory().getChestplate();
					if(item.getItemMeta() != null) {
						if(item.getItemMeta().getLore() != null) {
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Thorns I")) {
								double reflect = e.getDamage()*0.6;
								attacker.damage(reflect);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Thorns II")) {
								double reflect = e.getDamage()*0.7;
								attacker.damage(reflect);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Thorns III")) {
								double reflect = e.getDamage()*0.8;
								attacker.damage(reflect);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Thorns IV")) {
								double reflect = e.getDamage()*0.9;
								attacker.damage(reflect);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Thorns V")) {
								double reflect = e.getDamage()*1;
								attacker.damage(reflect);
							}
							
						}
					}
				}
			}
		}
	}

}
