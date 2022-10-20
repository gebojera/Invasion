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

public class EnchantmentParry implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Parry I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Blocking with this sword reduces incoming damage by " + ChatColor.RED + "20%");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Parry II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Blocking with this sword reduces incoming damage by " + ChatColor.RED + "30%");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Parry III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Blocking with this sword reduces incoming damage by " + ChatColor.RED + "40%");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Parry IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Blocking with this sword reduces incoming damage by " + ChatColor.RED + "50%");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Parry V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Blocking with this sword reduces incoming damage by " + ChatColor.RED + "60%");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onAttacked(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.isBlocking()) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta() != null) {
						if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
							ItemStack i = p.getItemInHand();
							if(i.getItemMeta().getLore() != null) {
								if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Parry" + ChatColor.RED + " I")) {
									double reduct = e.getDamage()*0.2;
									e.setDamage(e.getDamage()-reduct);
								}
								if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Parry" + ChatColor.RED + " II")) {
									double reduct = e.getDamage()*0.3;
									e.setDamage(e.getDamage()-reduct);
								}
								if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Parry" + ChatColor.RED + " III")) {
									double reduct = e.getDamage()*0.4;
									e.setDamage(e.getDamage()-reduct);
								}
								if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Parry" + ChatColor.RED + " IV")) {
									double reduct = e.getDamage()*0.5;
									e.setDamage(e.getDamage()-reduct);
								}
								if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Parry" + ChatColor.RED + " V")) {
									double reduct = e.getDamage()*0.6;
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
