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

public class EnchantmentBloodPact implements Listener {
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Blood Pact");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "You deal 2x damage with this sword");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "but take 10% of the damage back onto");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "yourself");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getLore() != null) {
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Blood Pact")) {
								e.setDamage(e.getDamage()*2);
								p.damage(e.getDamage()*0.1);
							}
						}
					}
				}
			}
		}
	}

}
