package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentUnassailable implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Unassailable I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-10%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Unassailable II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-20%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Unassailable III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-30%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Unassailable IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-40%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Unassailable V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-50%" + ChatColor.GRAY + " damage");
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
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable I")) {
							double reduct = e.getDamage()*0.1;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable II")) {
							double reduct = e.getDamage()*0.2;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable III")) {
							double reduct = e.getDamage()*0.3;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable IV")) {
							double reduct = e.getDamage()*0.4;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable V")) {
							double reduct = e.getDamage()*0.5;
							e.setDamage(e.getDamage()-reduct);
						}
						
					}
				}
			}
			if(p.getInventory().getHelmet() != null) {
				ItemStack item = p.getInventory().getHelmet();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable I")) {
							double reduct = e.getDamage()*0.1;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable II")) {
							double reduct = e.getDamage()*0.2;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable III")) {
							double reduct = e.getDamage()*0.3;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable IV")) {
							double reduct = e.getDamage()*0.4;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable V")) {
							double reduct = e.getDamage()*0.5;
							e.setDamage(e.getDamage()-reduct);
						}
						
					}
				}
			}
			if(p.getInventory().getChestplate() != null) {
				ItemStack item = p.getInventory().getChestplate();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable I")) {
							double reduct = e.getDamage()*0.1;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable II")) {
							double reduct = e.getDamage()*0.2;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable III")) {
							double reduct = e.getDamage()*0.3;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable IV")) {
							double reduct = e.getDamage()*0.4;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable V")) {
							double reduct = e.getDamage()*0.5;
							e.setDamage(e.getDamage()-reduct);
						}
						
					}
				}
			}
			if(p.getInventory().getLeggings() != null) {
				ItemStack item = p.getInventory().getLeggings();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable I")) {
							double reduct = e.getDamage()*0.1;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable II")) {
							double reduct = e.getDamage()*0.2;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable III")) {
							double reduct = e.getDamage()*0.3;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable IV")) {
							double reduct = e.getDamage()*0.4;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Unassailable V")) {
							double reduct = e.getDamage()*0.5;
							e.setDamage(e.getDamage()-reduct);
						}
						
					}
				}
			}
		}
	}

}
