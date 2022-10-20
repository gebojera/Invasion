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

public class EnchantmentChallenger implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Challenger I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Enemies with 200 or more health");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "deal " + ChatColor.BLUE + "-50%" + ChatColor.GRAY + " damage to you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Challenger II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Enemies with 200 or more health");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "deal " + ChatColor.BLUE + "-60%" + ChatColor.GRAY + " damage to you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Challenger III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Enemies with 200 or more health");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "deal " + ChatColor.BLUE + "-70%" + ChatColor.GRAY + " damage to you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Challenger IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Enemies with 200 or more health");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "deal " + ChatColor.BLUE + "-80%" + ChatColor.GRAY + " damage to you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Challenger V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Enemies with 200 or more health");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "deal " + ChatColor.BLUE + "-90%" + ChatColor.GRAY + " damage to you");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getInventory().getBoots() != null) {
				ItemStack item = p.getInventory().getBoots();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(e.getDamager() instanceof LivingEntity) {
							LivingEntity le = (LivingEntity) e.getDamager();
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Challenger I")) {
								if(le.getHealth() >= 200) {
									double reduct = e.getDamage()*0.5;
									e.setDamage(e.getDamage()-reduct);
								}
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Challenger II")) {
								if(le.getHealth() >= 200) {
									double reduct = e.getDamage()*0.6;
									e.setDamage(e.getDamage()-reduct);
								}
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Challenger III")) {
								if(le.getHealth() >= 200) {
									double reduct = e.getDamage()*0.7;
									e.setDamage(e.getDamage()-reduct);
								}
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Challenger IV")) {
								if(le.getHealth() >= 200) {
									double reduct = e.getDamage()*0.8;
									e.setDamage(e.getDamage()-reduct);
								}
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Challenger V")) {
								if(le.getHealth() >= 200) {
									double reduct = e.getDamage()*0.9;
									e.setDamage(e.getDamage()-reduct);
								}
							}
						}
						
					}
				}
			}
		}
	}

}
