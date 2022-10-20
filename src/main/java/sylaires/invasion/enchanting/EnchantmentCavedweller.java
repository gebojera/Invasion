package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentCavedweller implements Listener { 
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Cavedweller I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-10%" + ChatColor.GRAY + " damage when a block is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Cavedweller II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-20%" + ChatColor.GRAY + " damage when a block is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Cavedweller III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-30%" + ChatColor.GRAY + " damage when a block is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Cavedweller IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-40%" + ChatColor.GRAY + " damage when a block is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Cavedweller V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-50%" + ChatColor.GRAY + " damage when a block is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getInventory().getChestplate() != null) {
				ItemStack item = p.getInventory().getChestplate();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Cavedweller I")) {
							for(int check = 0; check < 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() != Material.AIR && b.getType() != Material.BARRIER) {
									double reduct = e.getDamage()*0.1;
									e.setDamage(e.getDamage()-reduct);
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Cavedweller II")) {
							for(int check = 0; check < 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() != Material.AIR && b.getType() != Material.BARRIER) {
									double reduct = e.getDamage()*0.2;
									e.setDamage(e.getDamage()-reduct);
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Cavedweller III")) {
							for(int check = 0; check < 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() != Material.AIR && b.getType() != Material.BARRIER) {
									double reduct = e.getDamage()*0.3;
									e.setDamage(e.getDamage()-reduct);
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Cavedweller IV")) {
							for(int check = 0; check < 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() != Material.AIR && b.getType() != Material.BARRIER) {
									double reduct = e.getDamage()*0.4;
									e.setDamage(e.getDamage()-reduct);
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Cavedweller V")) {
							for(int check = 0; check < 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() != Material.AIR && b.getType() != Material.BARRIER) {
									double reduct = e.getDamage()*0.5;
									e.setDamage(e.getDamage()-reduct);
									return;
								}
							}
						}
						
					}
				}
			}
		}
	}

}
