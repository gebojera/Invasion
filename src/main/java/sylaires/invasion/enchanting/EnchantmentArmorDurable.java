package sylaires.invasion.enchanting;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentArmorDurable implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "There is a " + ChatColor.RED + "20%" + ChatColor.GRAY + " chance for durability");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "to not be effected on this item");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "There is a " + ChatColor.RED + "30%" + ChatColor.GRAY + " chance for durability");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "to not be effected on this item");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "There is a " + ChatColor.RED + "40%" + ChatColor.GRAY + " chance for durability");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "to not be effected on this item");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "There is a " + ChatColor.RED + "50%" + ChatColor.GRAY + " chance for durability");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "to not be effected on this item");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Durable V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "There is a " + ChatColor.RED + "70%" + ChatColor.GRAY + " chance for durability");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "to not be effected on this item");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onDurability(PlayerItemDamageEvent e) {
		ItemStack i = e.getItem();
		if(i.getItemMeta() != null) {
			if(i.getItemMeta().getLore() != null) {
				if(i.getType() != Material.DIAMOND_SWORD) {
					if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable I")) {
						Random rand = new Random();
						int random = rand.nextInt(100);
						if(random <= 20) {
							e.setCancelled(true);
						}
					}
					if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable II")) {
						Random rand = new Random();
						int random = rand.nextInt(100);
						if(random <= 30) {
							e.setCancelled(true);
						}
					}
					if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable III")) {
						Random rand = new Random();
						int random = rand.nextInt(100);
						if(random <= 40) {
							e.setCancelled(true);
						}
					}
					if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable IV")) {
						Random rand = new Random();
						int random = rand.nextInt(100);
						if(random <= 50) {
							e.setCancelled(true);
						}
					}
					if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Durable V")) {
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
