package sylaires.invasion.enchanting;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentSwordDurable implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword have a " + ChatColor.RED + "20%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to not effect durability");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword have a " + ChatColor.RED + "30%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to not effect durability");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword have a " + ChatColor.RED + "40%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to not effect durability");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword have a " + ChatColor.RED + "50%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to not effect durability");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword have a " + ChatColor.RED + "70%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to not effect durability");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	
	@EventHandler
	public void onDurability(PlayerItemDamageEvent e) {
		Player p = e.getPlayer();
		if(e.getItem().getType() == Material.DIAMOND_SWORD) {
			if(e.getItem().getItemMeta().getDisplayName() != null) {
				if(e.getItem().getItemMeta().getLore() != null) {
					if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable I")) {
						Random rand = new Random();
						int random = rand.nextInt(100);
						if(random <= 20) {
							e.setCancelled(true);
						}
					}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable II")) {
						Random rand = new Random();
						int random = rand.nextInt(100);
						if(random <= 30) {
							e.setCancelled(true);
						}
					}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable III")) {
						Random rand = new Random();
						int random = rand.nextInt(100);
						if(random <= 40) {
							e.setCancelled(true);
						}
					}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable IV")) {
						Random rand = new Random();
						int random = rand.nextInt(100);
						if(random <= 50) {
							e.setCancelled(true);
						}
					}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable V")) {
						Random rand = new Random();
						int random = rand.nextInt(100);
						if(random <= 70) {
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}

}
