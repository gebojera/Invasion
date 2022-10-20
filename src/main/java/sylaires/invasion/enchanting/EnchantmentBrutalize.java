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

public class EnchantmentBrutalize implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Brutalize I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "10%" + ChatColor.GRAY + " extra damage per");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "50 health the target is missing");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Brutalize II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "20%" + ChatColor.GRAY + " extra damage per");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "50 health the target is missing");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Brutalize III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "30%" + ChatColor.GRAY + " extra damage per");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "50 health the target is missing");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Brutalize IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "40%" + ChatColor.GRAY + " extra damage per");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "50 health the target is missing");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Brutalize V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Deal " + ChatColor.RED + "50%" + ChatColor.GRAY + " extra damage per");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "50 health the target is missing");
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
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Brutalize I")) {
									double missing = le.getMaxHealth()-le.getHealth();
									double increase = ((missing/50)*0.1)*e.getDamage();
									e.setDamage(increase+e.getDamage());
									
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Brutalize II")) {
									double missing = le.getMaxHealth()-le.getHealth();
									double increase = ((missing/50)*0.2)*e.getDamage();
									e.setDamage(increase+e.getDamage());
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Brutalize III")) {
									double missing = le.getMaxHealth()-le.getHealth();
									double increase = ((missing/50)*0.3)*e.getDamage();
									e.setDamage(increase+e.getDamage());
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Brutalize IV")) {
									double missing = le.getMaxHealth()-le.getHealth();
									double increase = ((missing/50)*0.4)*e.getDamage();
									e.setDamage(increase+e.getDamage());
								}
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Brutalize V")) {
									double missing = le.getMaxHealth()-le.getHealth();
									double increase = ((missing/50)*0.5)*e.getDamage();
									e.setDamage(increase+e.getDamage());
								}
							}
						}
					}
				}
			}
		}
	}

}
