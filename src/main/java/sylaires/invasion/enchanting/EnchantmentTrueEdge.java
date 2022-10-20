package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentTrueEdge implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "True Edge I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "10%" + ChatColor.GRAY + " extra true damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "True Edge II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "20%" + ChatColor.GRAY + " extra true damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "True Edge III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "30%" + ChatColor.GRAY + " extra true damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "True Edge IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "40%" + ChatColor.GRAY + " extra true damage");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, "" + ChatColor.GOLD + ChatColor.BOLD + "RARE");
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "True Edge V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword deal " + ChatColor.RED + "50%" + ChatColor.GRAY + " extra true damage");
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
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "True Edge I")) {
									try {
										double extradmg = e.getDamage()*0.1;
										le.setHealth(le.getHealth()-extradmg);
									} catch(Exception ex) {
										le.damage(200000);
									}
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "True Edge II")) {
									try {
										double extradmg = e.getDamage()*0.2;
										le.setHealth(le.getHealth()-extradmg);
									} catch(Exception ex) {
										le.damage(200000);
									}
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "True Edge III")) {
									try {
										double extradmg = e.getDamage()*0.3;
										le.setHealth(le.getHealth()-extradmg);
									} catch(Exception ex) {
										le.damage(200000);
									}
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "True Edge IV")) {
									try {
										double extradmg = e.getDamage()*0.4;
										le.setHealth(le.getHealth()-extradmg);
									} catch(Exception ex) {
										le.damage(200000);
									}
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "True Edge V")) {
									try {
										double extradmg = e.getDamage()*0.5;
										le.setHealth(le.getHealth()-extradmg);
									} catch(Exception ex) {
										le.damage(200000);
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
