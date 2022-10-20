package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentShatter implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Shatter I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly destroys skeletons, and deals " + ChatColor.RED + "30%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "extra damage to armored targets");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Shatter II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly destroys skeletons, and deals " + ChatColor.RED + "40%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "extra damage to armored targets");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Shatter III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly destroys skeletons, and deals " + ChatColor.RED + "50%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "extra damage to armored targets");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Shatter IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly destroys skeletons, and deals " + ChatColor.RED + "60%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "extra damage to armored targets");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Shatter V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Instantly destroys skeletons, and deals " + ChatColor.RED + "70%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "extra damage to armored targets");
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
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Shatter I")) {
								if(e.getEntity() instanceof Skeleton) {
									e.setDamage(20000000.0);
								}else if(e.getEntity() instanceof LivingEntity) {
									LivingEntity le = (LivingEntity) e.getEntity();
									if(le.getEquipment().getBoots() != null || le.getEquipment().getChestplate() != null || le.getEquipment().getLeggings() != null || le.getEquipment().getHelmet() != null) {
										double extradmg = e.getDamage()*0.3;
										e.setDamage(e.getDamage() + extradmg);
									}
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Shatter II")) {
								if(e.getEntity() instanceof Skeleton) {
									e.setDamage(20000000.0);
								}else if(e.getEntity() instanceof LivingEntity) {
									LivingEntity le = (LivingEntity) e.getEntity();
									if(le.getEquipment().getBoots() != null || le.getEquipment().getChestplate() != null || le.getEquipment().getLeggings() != null || le.getEquipment().getHelmet() != null) {
										double extradmg = e.getDamage()*0.4;
										e.setDamage(e.getDamage() + extradmg);
									}
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Shatter III")) {
								if(e.getEntity() instanceof Skeleton) {
									e.setDamage(20000000.0);
								}else if(e.getEntity() instanceof LivingEntity) {
									LivingEntity le = (LivingEntity) e.getEntity();
									if(le.getEquipment().getBoots() != null || le.getEquipment().getChestplate() != null || le.getEquipment().getLeggings() != null || le.getEquipment().getHelmet() != null) {
										double extradmg = e.getDamage()*0.5;
										e.setDamage(e.getDamage() + extradmg);
									}
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Shatter IV")) {
								if(e.getEntity() instanceof Skeleton) {
									e.setDamage(20000000.0);
								}else if(e.getEntity() instanceof LivingEntity) {
									LivingEntity le = (LivingEntity) e.getEntity();
									if(le.getEquipment().getBoots() != null || le.getEquipment().getChestplate() != null || le.getEquipment().getLeggings() != null || le.getEquipment().getHelmet() != null) {
										double extradmg = e.getDamage()*0.6;
										e.setDamage(e.getDamage() + extradmg);
									}
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Shatter V")) {
								if(e.getEntity() instanceof Skeleton) {
									e.setDamage(20000000.0);
								}else if(e.getEntity() instanceof LivingEntity) {
									LivingEntity le = (LivingEntity) e.getEntity();
									if(le.getEquipment().getBoots() != null || le.getEquipment().getChestplate() != null || le.getEquipment().getLeggings() != null || le.getEquipment().getHelmet() != null) {
										double extradmg = e.getDamage()*0.7;
										e.setDamage(e.getDamage() + extradmg);
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
