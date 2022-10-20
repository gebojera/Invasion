package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentAntidote implements Listener {

	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Antidote I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You take " + ChatColor.BLUE + "-50%" + ChatColor.GRAY + " damage from Poison");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Antidote II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You take " + ChatColor.BLUE + "-50%" + ChatColor.GRAY + " damage from Poison and Wither");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Antidote III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You take " + ChatColor.BLUE + "-50%" + ChatColor.GRAY + " damage from Wither");
			ItemStackUtil.addLoreToItem(i, ChatColor.BLUE + "You are immune to Poison");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Antidote IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.BLUE + "You are immune to Poison and Wither");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Antidote V");
			ItemStackUtil.addLoreToItem(i, ChatColor.BLUE + "Poison and Wither heal you equal to their strength");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDmg(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getInventory().getChestplate() != null) {
				ItemStack chest = p.getInventory().getChestplate();
				if(chest.getItemMeta() != null) {
					if(chest.getItemMeta().getLore() != null) {
						if(e.getCause() == DamageCause.WITHER || e.getCause() == DamageCause.POISON) {
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Antidote V")) {
								e.setCancelled(true);
								double heal = e.getDamage();
								if(p.getHealth() + heal > p.getMaxHealth()) {
									p.setHealth(p.getMaxHealth());
								}else {
									p.setHealth(p.getHealth() + heal);
								}
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Antidote I")) {
								double reduct = e.getDamage()*0.5;
								if(e.getCause() == DamageCause.POISON) {
									e.setDamage(e.getDamage()-reduct);
								}
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Antidote II")) {
								double reduct = e.getDamage()*0.5;
								e.setDamage(e.getDamage()-reduct);
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Antidote III")) {
								double reduct = e.getDamage()*0.5;
								if(e.getCause() == DamageCause.POISON) {
									e.setDamage(0);
								}else {
									e.setDamage(e.getDamage()-reduct);
								}
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Antidote IV")) {
								e.setDamage(0);
							}
						}
					}
				}
			}
		}
	}

}
