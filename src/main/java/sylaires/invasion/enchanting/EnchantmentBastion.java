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

public class EnchantmentBastion implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Bastion I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-5%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Bastion II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-10%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Bastion III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-15%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Bastion IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-20%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Bastion V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-25%" + ChatColor.GRAY + " damage");
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
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion I")) {
							double reduct = e.getDamage()*0.05;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion II")) {
							double reduct = e.getDamage()*0.1;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion III")) {
							double reduct = e.getDamage()*0.15;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion IV")) {
							double reduct = e.getDamage()*0.2;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion V")) {
							double reduct = e.getDamage()*0.25;
							e.setDamage(e.getDamage()-reduct);
						}
						
					}
				}
			}
			if(p.getInventory().getHelmet() != null) {
				ItemStack item = p.getInventory().getHelmet();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion I")) {
							double reduct = e.getDamage()*0.05;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion II")) {
							double reduct = e.getDamage()*0.1;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion III")) {
							double reduct = e.getDamage()*0.15;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion IV")) {
							double reduct = e.getDamage()*0.2;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion V")) {
							double reduct = e.getDamage()*0.25;
							e.setDamage(e.getDamage()-reduct);
						}
						
					}
				}
			}
			if(p.getInventory().getChestplate() != null) {
				ItemStack item = p.getInventory().getChestplate();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion I")) {
							double reduct = e.getDamage()*0.05;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion II")) {
							double reduct = e.getDamage()*0.1;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion III")) {
							double reduct = e.getDamage()*0.15;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion IV")) {
							double reduct = e.getDamage()*0.2;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion V")) {
							double reduct = e.getDamage()*0.25;
							e.setDamage(e.getDamage()-reduct);
						}
						
					}
				}
			}
			if(p.getInventory().getLeggings() != null) {
				ItemStack item = p.getInventory().getLeggings();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion I")) {
							double reduct = e.getDamage()*0.05;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion II")) {
							double reduct = e.getDamage()*0.1;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion III")) {
							double reduct = e.getDamage()*0.15;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion IV")) {
							double reduct = e.getDamage()*0.2;
							e.setDamage(e.getDamage()-reduct);
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Bastion V")) {
							double reduct = e.getDamage()*0.25;
							e.setDamage(e.getDamage()-reduct);
						}
						
					}
				}
			}
		}
	}

}
