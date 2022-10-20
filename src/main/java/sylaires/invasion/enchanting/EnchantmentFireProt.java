package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentFireProt implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fire Protection I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-50%" + ChatColor.GRAY + " damage from fire");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fire Protection II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-60%" + ChatColor.GRAY + " damage from fire");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fire Protection III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-70%" + ChatColor.GRAY + " damage from fire");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fire Protection IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-80%" + ChatColor.GRAY + " damage from fire");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Fire Protection V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-100%" + ChatColor.GRAY + " damage from fire");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK) {
				if(p.getInventory().getBoots() != null) {
					ItemStack item = p.getInventory().getBoots();
					if(item.getItemMeta() != null) {
						if(item.getItemMeta().getLore() != null) {
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection I")) {
								double reduct = e.getDamage()*0.5;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection II")) {
								double reduct = e.getDamage()*0.6;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection III")) {
								double reduct = e.getDamage()*0.7;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection IV")) {
								double reduct = e.getDamage()*0.8;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection V")) {
								double reduct = e.getDamage()*1;
								e.setDamage(e.getDamage() - reduct);
							}
							
						}
					}
				}
				if(p.getInventory().getHelmet() != null) {
					ItemStack item = p.getInventory().getHelmet();
					if(item.getItemMeta() != null) {
						if(item.getItemMeta().getLore() != null) {
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection I")) {
								double reduct = e.getDamage()*0.5;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection II")) {
								double reduct = e.getDamage()*0.6;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection III")) {
								double reduct = e.getDamage()*0.7;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection IV")) {
								double reduct = e.getDamage()*0.8;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection V")) {
								double reduct = e.getDamage()*1;
								e.setDamage(e.getDamage() - reduct);
							}
							
						}
					}
				}
				if(p.getInventory().getChestplate() != null) {
					ItemStack item = p.getInventory().getChestplate();
					if(item.getItemMeta() != null) {
						if(item.getItemMeta().getLore() != null) {
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection I")) {
								double reduct = e.getDamage()*0.5;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection II")) {
								double reduct = e.getDamage()*0.6;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection III")) {
								double reduct = e.getDamage()*0.7;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection IV")) {
								double reduct = e.getDamage()*0.8;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection V")) {
								double reduct = e.getDamage()*1;
								e.setDamage(e.getDamage() - reduct);
							}
							
						}
					}
				}
				if(p.getInventory().getLeggings() != null) {
					ItemStack item = p.getInventory().getLeggings();
					if(item.getItemMeta() != null) {
						if(item.getItemMeta().getLore() != null) {
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection I")) {
								double reduct = e.getDamage()*0.5;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection II")) {
								double reduct = e.getDamage()*0.6;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection III")) {
								double reduct = e.getDamage()*0.7;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection IV")) {
								double reduct = e.getDamage()*0.8;
								e.setDamage(e.getDamage() - reduct);
							}
							if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Fire Protection V")) {
								double reduct = e.getDamage()*1;
								e.setDamage(e.getDamage() - reduct);
							}
							
						}
					}
				}
			}
		}
	}

}
