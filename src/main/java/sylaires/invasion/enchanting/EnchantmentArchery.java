package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentArchery implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Archery I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your arrows deal " + ChatColor.RED + "+10%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Archery II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your arrows deal " + ChatColor.RED + "+20%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Archery III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your arrows deal " + ChatColor.RED + "+30%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Archery IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your arrows deal " + ChatColor.RED + "+40%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Archery V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Your arrows deal " + ChatColor.RED + "+50%" + ChatColor.GRAY + " damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onShot(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Arrow) {
			Arrow a = (Arrow) e.getDamager();
			if(a.getShooter() instanceof Player) {
				Player p = (Player) a.getShooter();
				if(p.getInventory().getChestplate() != null) {
					ItemStack chest = p.getInventory().getChestplate();
					if(chest.getItemMeta() != null) {
						if(chest.getItemMeta().getLore() != null) {
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Archery I")) {
								double add = e.getDamage()*0.1;
								e.setDamage(e.getDamage() + add);
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Archery II")) {
								double add = e.getDamage()*0.2;
								e.setDamage(e.getDamage() + add);
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Archery III")) {
								double add = e.getDamage()*0.3;
								e.setDamage(e.getDamage() + add);
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Archery IV")) {
								double add = e.getDamage()*0.4;
								e.setDamage(e.getDamage() + add);
							}
							if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Archery V")) {
								double add = e.getDamage()*0.5;
								e.setDamage(e.getDamage() + add);
							}
						}
					}
				}
			}
		}
	}

}
