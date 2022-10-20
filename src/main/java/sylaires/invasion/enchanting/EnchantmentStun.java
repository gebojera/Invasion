package sylaires.invasion.enchanting;

import java.util.HashMap;

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
import org.bukkit.scheduler.BukkitRunnable;

import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentStun implements Listener {
	
	HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Stun I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "Hits stun enemies for 5s");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "10s cooldown");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Stun II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "Hits stun enemies for 5s");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "8s cooldown");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Stun III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "Hits stun enemies for 5s");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "6s cooldown");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Stun IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "Hits stun enemies for 5s");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "4s cooldown");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.BLUE + ChatColor.BOLD + "EPIC");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Stun V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GOLD + "Hits stun enemies for 5s");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "3s cooldown");
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
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Stun I")) {
									if(!cooldown.containsKey(p)) {
										cooldown.put(p, 10);
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99, true));
										new BukkitRunnable() {
											@Override
											public void run() {
												if(cooldown.get(p) == 0) {
													cooldown.remove(p);
													this.cancel();
												}else {
													cooldown.put(p, cooldown.get(p)-1);
												}
											}
											
										}.runTaskTimer(Main.getPlugin(), 0, 20);
									}
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Stun II")) {
									if(!cooldown.containsKey(p)) {
										cooldown.put(p, 8);
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99, true));
										new BukkitRunnable() {
											@Override
											public void run() {
												if(cooldown.get(p) == 0) {
													cooldown.remove(p);
													this.cancel();
												}else {
													cooldown.put(p, cooldown.get(p)-1);
												}
											}
											
										}.runTaskTimer(Main.getPlugin(), 0, 20);
									}
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Stun III")) {
									if(!cooldown.containsKey(p)) {
										cooldown.put(p, 6);
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99, true));
										new BukkitRunnable() {
											@Override
											public void run() {
												if(cooldown.get(p) == 0) {
													cooldown.remove(p);
													this.cancel();
												}else {
													cooldown.put(p, cooldown.get(p)-1);
												}
											}
											
										}.runTaskTimer(Main.getPlugin(), 0, 20);
									}
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Stun IV")) {
									if(!cooldown.containsKey(p)) {
										cooldown.put(p, 4);
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99, true));
										new BukkitRunnable() {
											@Override
											public void run() {
												if(cooldown.get(p) == 0) {
													cooldown.remove(p);
													this.cancel();
												}else {
													cooldown.put(p, cooldown.get(p)-1);
												}
											}
											
										}.runTaskTimer(Main.getPlugin(), 0, 20);
									}
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Stun V")) {
									if(!cooldown.containsKey(p)) {
										cooldown.put(p, 3);
										le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 99, true));
										new BukkitRunnable() {
											@Override
											public void run() {
												if(cooldown.get(p) == 0) {
													cooldown.remove(p);
													this.cancel();
												}else {
													cooldown.put(p, cooldown.get(p)-1);
												}
											}
											
										}.runTaskTimer(Main.getPlugin(), 0, 20);
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
