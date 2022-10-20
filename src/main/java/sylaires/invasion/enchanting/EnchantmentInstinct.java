package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentInstinct implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Instinct I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "When an enemy deals damage to you");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "you receive Speed I (10s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Instinct II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "When an enemy deals damage to you");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "you receive Speed II (10s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Instinct III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "When an enemy deals damage to you");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "you receive Speed III (10s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Instinct IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "When an enemy deals damage to you");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "you receive Speed IV (10s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Instinct V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "When an enemy deals damage to you");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "you receive Speed V (10s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
				if(e.getCause() != DamageCause.FALL && e.getCause() != DamageCause.VOID) { 
					if(p.getInventory().getChestplate() != null) {
						ItemStack item = p.getInventory().getChestplate();
						if(item.getItemMeta() != null) {
							if(item.getItemMeta().getLore() != null) {
								if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Instinct I")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0, true));
								}
								if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Instinct II")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1, true));
								}
								if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Instinct III")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2, true));
								}
								if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Instinct IV")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 3, true));
								}
								if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Instinct V")) {
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 4, true));
								}
								
							}
						}
					}
				}
			}
		}
	}


