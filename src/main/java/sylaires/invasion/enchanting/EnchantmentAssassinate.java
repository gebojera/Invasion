package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentAssassinate implements Listener {
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Assassinate");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Instantly destroys enemies that are");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "not targeting you");
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
							if(e.getEntity() instanceof Creature) {
								Creature ce = (Creature) e.getEntity();
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Assassinate")) {
									if(ce.getTarget() != null) {
										if(!ce.getTarget().equals(p)) {
											e.setDamage(1000000);
										}
									}else {
										e.setDamage(1000000);
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
