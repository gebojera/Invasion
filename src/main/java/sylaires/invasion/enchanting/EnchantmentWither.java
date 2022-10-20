package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentWither implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wither I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict Wither I (10s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wither II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict Wither I (20s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wither III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict Wither II (10s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wither IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict Wither II (20s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wither V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict Wither III (15s)");
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
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wither I")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 0, true));
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wither II")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 0, true));
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wither III")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 1, true));
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wither IV")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 1, true));
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wither V")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 300, 2, true));
								}
							}
						}
					}
				}
			}
		}
	}

}
