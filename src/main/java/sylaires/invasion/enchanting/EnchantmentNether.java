package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentNether implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Nether Purger I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "This sword deals " + ChatColor.RED + "30%" + ChatColor.GRAY + " extra damage against nether enemies");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Nether Purger II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "This sword deals " + ChatColor.RED + "40%" + ChatColor.GRAY + " extra damage against nether enemies");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Nether Purger III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "This sword deals " + ChatColor.RED + "50%" + ChatColor.GRAY + " extra damage against nether enemies");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Nether Purger IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "This sword deals " + ChatColor.RED + "60%" + ChatColor.GRAY + " extra damage against nether enemies");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Nether Purger V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "This sword deals " + ChatColor.RED + "70%" + ChatColor.GRAY + " extra damage against nether enemies");
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
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Nether Purger I")) {
								if(e.getEntity() instanceof Blaze || e.getEntity() instanceof MagmaCube || e.getEntity() instanceof Wither || e.getEntity() instanceof Ghast || e.getEntity() instanceof PigZombie) {
									double extradmg = e.getDamage()*0.3;
									e.setDamage(e.getDamage() + extradmg);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Nether Purger II")) {
								if(e.getEntity() instanceof Blaze || e.getEntity() instanceof MagmaCube || e.getEntity() instanceof Wither || e.getEntity() instanceof Ghast || e.getEntity() instanceof PigZombie) {
									double extradmg = e.getDamage()*0.4;
									e.setDamage(e.getDamage() + extradmg);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Nether Purger III")) {
								if(e.getEntity() instanceof Blaze || e.getEntity() instanceof MagmaCube || e.getEntity() instanceof Wither || e.getEntity() instanceof Ghast || e.getEntity() instanceof PigZombie) {
									double extradmg = e.getDamage()*0.5;
									e.setDamage(e.getDamage() + extradmg);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Nether Purger IV")) {
								if(e.getEntity() instanceof Blaze || e.getEntity() instanceof MagmaCube || e.getEntity() instanceof Wither || e.getEntity() instanceof Ghast || e.getEntity() instanceof PigZombie) {
									double extradmg = e.getDamage()*0.6;
									e.setDamage(e.getDamage() + extradmg);
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Nether Purger V")) {
								if(e.getEntity() instanceof Blaze || e.getEntity() instanceof MagmaCube || e.getEntity() instanceof Wither || e.getEntity() instanceof Ghast || e.getEntity() instanceof PigZombie) {
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
