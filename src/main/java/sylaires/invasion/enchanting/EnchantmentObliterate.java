package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
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

public class EnchantmentObliterate implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Obliterate I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly kills targets below " + ChatColor.RED + "25%" + ChatColor.GRAY + " healh");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Obliterate II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly kills targets below " + ChatColor.RED + "30%" + ChatColor.GRAY + " healh");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Obliterate III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly kills targets below " + ChatColor.RED + "35%" + ChatColor.GRAY + " healh");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Obliterate IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly kills targets below " + ChatColor.RED + "40%" + ChatColor.GRAY + " healh");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Obliterate V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly kills targets below " + ChatColor.RED + "50%" + ChatColor.GRAY + " healh");
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
							if(e.getEntity() instanceof LivingEntity) {
								LivingEntity le = (LivingEntity) e.getEntity();
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Obliterate I")) {
									double hlthpercent = le.getMaxHealth()*0.25;
									if(le.getHealth() < hlthpercent) {
										e.setDamage(20000000);
									}
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Obliterate II")) {
									double hlthpercent = le.getMaxHealth()*0.3;
									if(le.getHealth() < hlthpercent) {
										e.setDamage(20000000);
									}
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Obliterate III")) {
									double hlthpercent = le.getMaxHealth()*0.35;
									if(le.getHealth() < hlthpercent) {
										e.setDamage(20000000);
									}
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Obliterate IV")) {
									double hlthpercent = le.getMaxHealth()*0.4;
									if(le.getHealth() < hlthpercent) {
										e.setDamage(20000000);
									}
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Obliterate V")) {
									double hlthpercent = le.getMaxHealth()*0.5;
									if(le.getHealth() < hlthpercent) {
										e.setDamage(20000000);
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
