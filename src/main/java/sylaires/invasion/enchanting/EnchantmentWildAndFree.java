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

public class EnchantmentWildAndFree implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wild and Free I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-10%" + ChatColor.GRAY + " damage when open sky is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wild and Free II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-20%" + ChatColor.GRAY + " damage when open sky is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wild and Free III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-30%" + ChatColor.GRAY + " damage when open sky is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wild and Free IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-40%" + ChatColor.GRAY + " damage when open sky is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Wild and Free V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "You receive " + ChatColor.BLUE + "-50%" + ChatColor.GRAY + " damage when open sky is above you");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getInventory().getBoots() != null) {
				ItemStack item = p.getInventory().getBoots();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free I")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									if(check == 40) {
										double reduct = e.getDamage()*0.1;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free II")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									if(check == 40) {
										double reduct = e.getDamage()*0.2;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free III")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									if(check == 40) {
										double reduct = e.getDamage()*0.3;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free IV")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									if(check == 40) {
										double reduct = e.getDamage()*0.4;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free V")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									if(check == 40) {
										double reduct = e.getDamage()*0.5;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						
					}
				}
			}
			if(p.getInventory().getHelmet() != null) {
				ItemStack item = p.getInventory().getHelmet();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free I")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.1;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free II")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.2;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free III")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.3;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free IV")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.4;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free V")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.5;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						
					}
				}
			}
			if(p.getInventory().getChestplate() != null) {
				ItemStack item = p.getInventory().getChestplate();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free I")) {
							for(int check = 0; check <= 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									if(check == 40) {
										double reduct = e.getDamage()*0.1;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free II")) {
							for(int check = 0; check <= 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									if(check == 40) {
										double reduct = e.getDamage()*0.2;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free III")) {
							for(int check = 0; check <= 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									if(check == 40) {
										double reduct = e.getDamage()*0.3;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free IV")) {
							for(int check = 0; check <= 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									double reduct = e.getDamage()*0.4;
									e.setDamage(e.getDamage()-reduct);
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free V")) {
							for(int check = 0; check <= 40; check++) {
								Location up = new Location(Main.getWorld(), p.getLocation().getX(), p.getLocation().getY()+check, p.getLocation().getZ());
								Block b = up.getBlock();
								if(b.getType() == Material.AIR || b.getType() == Material.BARRIER) {
									if(check == 40) {
										double reduct = e.getDamage()*0.5;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						
					}
				}
			}
			if(p.getInventory().getLeggings() != null) {
				ItemStack item = p.getInventory().getLeggings();
				if(item.getItemMeta() != null) {
					if(item.getItemMeta().getLore() != null) {
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free I")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.1;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free II")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.2;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free III")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.3;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free IV")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.4;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
									return;
								}
							}
						}
						if(item.getItemMeta().getLore().contains(ChatColor.YELLOW + "Wild and Free V")) {
							for(int check = 0; check <= 40; check++) {
								Location up = p.getLocation().add(new Location(Main.getWorld(), 0, check, 0));
								Block b = up.getBlock();
								if(b.getType() == Material.AIR) {
									if(check == 40) {
										double reduct = e.getDamage()*0.5;
										e.setDamage(e.getDamage()-reduct);
									}
								}else {
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
