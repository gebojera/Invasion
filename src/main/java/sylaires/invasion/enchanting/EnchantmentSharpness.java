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

public class EnchantmentSharpness implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Sharpness I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "10%" + ChatColor.GRAY + " extra damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Sharpness II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "20%" + ChatColor.GRAY + " extra damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Sharpness III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "30%" + ChatColor.GRAY + " extra damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Sharpness IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "40%" + ChatColor.GRAY + " extra damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Sharpness V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "50%" + ChatColor.GRAY + " extra damage");
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
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Sharpness I")) {
								double extradmg = e.getDamage()*0.1;
								e.setDamage(e.getDamage() + extradmg);
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Sharpness II")) {
								double extradmg = e.getDamage()*0.2;
								e.setDamage(e.getDamage() + extradmg);
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Sharpness III")) {
								double extradmg = e.getDamage()*0.3;
								e.setDamage(e.getDamage() + extradmg);
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Sharpness IV")) {
								double extradmg = e.getDamage()*0.4;
								e.setDamage(e.getDamage() + extradmg);
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Sharpness V")) {
								double extradmg = e.getDamage()*0.5;
								e.setDamage(e.getDamage() + extradmg);
							}
						}
					}
				}
			}
		}
	}

}
