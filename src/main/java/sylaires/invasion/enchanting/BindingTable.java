package sylaires.invasion.enchanting;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.main.PlayerProfile;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class BindingTable implements Listener {
	
	public enum Rarity {
		MUNDANE, RARE, EPIC, LEGENDARY
	}
	
	public static HashMap<Player, EnchantingProcess> processes = new HashMap<Player, EnchantingProcess>();
	
	public static void open(Player p) {
		Inventory i = Bukkit.createInventory(null, 27, ChatColor.LIGHT_PURPLE + "Enchant Item:");
		for(int slot = 0; slot < 27; slot++) {
			ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
			ItemStackUtil.addNameToItem(glass, " ");
			i.setItem(slot, glass);
		}
		i.setItem(11, null);
		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
		ItemStackUtil.addNameToItem(book, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "ENCHANT ITEM");
		ItemStackUtil.addLoreToItem(book, ChatColor.RED + "Click an item from your inventory to enchant it");
		i.setItem(15, book);
		p.openInventory(i);
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		ItemStack item = e.getCurrentItem();
		Inventory inv = e.getInventory();
			if(inv.getName().equals(ChatColor.LIGHT_PURPLE + "Enchant Item:")) {
				e.setCancelled(true);
				if(item == null) {
					return;
				}
				if(item.getType() == Material.STAINED_GLASS_PANE) {
					p.playSound(p.getLocation(), Sound.VILLAGER_NO, 20, 1f);
				}else {
					if(item.getType() == Material.ENCHANTED_BOOK) {
						ItemStack enchitem = inv.getItem(11);
						if(enchitem == null) {
							p.sendMessage(ChatColor.RED + "You must place an item in the enchanting slot to begin!");
							p.playSound(p.getLocation(), Sound.VILLAGER_NO, 20, 1f);
						}else {
							if(true) {
								PlayerProfile profile = new PlayerProfile(p);
								if(item.getItemMeta().getLore().contains("" + ChatColor.LIGHT_PURPLE + "100" + ChatColor.LIGHT_PURPLE + " Shards")) {
									if(profile.getShards() < 100) {
										p.sendMessage(ChatColor.RED + "You do not have enough shards to do this!");
										p.playSound(p.getLocation(), Sound.VILLAGER_NO, 20, 1f);
									}else {
										profile.setShards(profile.getShards() - 100);
										//Enchant item
										EnchantingProcess ench = new EnchantingProcess(enchitem, p);
										ench.enchantItem();
										processes.put(p, ench);
										p.closeInventory();
									}
								}else if(item.getItemMeta().getLore().contains("" + ChatColor.LIGHT_PURPLE + "1000" + ChatColor.LIGHT_PURPLE + " Shards")) {
									if(profile.getShards() < 1000) {
										p.sendMessage(ChatColor.RED + "You do not have enough shards to do this!");
										p.playSound(p.getLocation(), Sound.VILLAGER_NO, 20, 1f);
									}else {
										profile.setShards(profile.getShards() - 1000);
										//Enchant item
										EnchantingProcess ench = new EnchantingProcess(enchitem, p);
										ench.enchantItem();
										processes.put(p, ench);
										p.closeInventory();
									}
								}else if(item.getItemMeta().getLore().contains("" + ChatColor.LIGHT_PURPLE + "500" + ChatColor.LIGHT_PURPLE + " Shards")) {
									if(profile.getShards() < 500) {
										p.sendMessage(ChatColor.RED + "You do not have enough shards to do this!");
										p.playSound(p.getLocation(), Sound.VILLAGER_NO, 20, 1f);
									}else {
										profile.setShards(profile.getShards() - 500);
										//Enchant item
										EnchantingProcess ench = new EnchantingProcess(enchitem, p);
										ench.enchantItem();
										processes.put(p, ench);
										p.closeInventory();
									}
								}else if(item.getItemMeta().getLore().contains("" + ChatColor.LIGHT_PURPLE + "5000" + ChatColor.LIGHT_PURPLE + " Shards")) {
									if(profile.getShards() < 5000) {
										p.sendMessage(ChatColor.RED + "You do not have enough shards to do this!");
										p.playSound(p.getLocation(), Sound.VILLAGER_NO, 20, 1f);
									}else {
										profile.setShards(profile.getShards() - 5000);
										//Enchant item
										EnchantingProcess ench = new EnchantingProcess(enchitem, p);
										ench.enchantItem();
										processes.put(p, ench);
										p.closeInventory();
									}
								}
							}else {
								p.sendMessage(ChatColor.RED + "Wait your turn! Someone is already enchanting.");
								p.playSound(p.getLocation(), Sound.VILLAGER_NO, 20, 1f);
							}
						}
					}else if(item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.DIAMOND_CHESTPLATE || item.getType() == Material.BOW || item.getType() == Material.GOLD_HELMET || item.getType() == Material.DIAMOND_LEGGINGS || item.getType() == Material.LEATHER_BOOTS) {
						if(inv.contains(item)) { //If the item was in the slot
							inv.setItem(11, null);
							p.getInventory().addItem(item);
							p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 30, 1.1F);
							//Update book
							ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
							ItemStackUtil.addNameToItem(book, "" + ChatColor.GOLD + ChatColor.BOLD + "ENCHANT ITEM");
							ItemStackUtil.addLoreToItem(book, ChatColor.RED + "Click an item from your inventory to enchant it");
							inv.setItem(15, book);
						}else { //If it was in player's inventory
							if(inv.getItem(11) == null) {
								p.getInventory().setItem(e.getSlot(), null);
								inv.setItem(11, item);
								p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 30, 1.1F);
								//Update book
								ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
								ItemStackUtil.addNameToItem(book, "" + ChatColor.GOLD + ChatColor.BOLD + "ENCHANT ITEM");
								if(item.getItemMeta() != null) {
									if(item.getItemMeta().getDisplayName() != null) {
										ItemStackUtil.addLoreToItem(book, ChatColor.AQUA + "This item cannot be enchanted further!");
									}else {
										ItemStackUtil.addLoreToItem(book, ChatColor.AQUA + "Mundane" + ChatColor.GOLD + ChatColor.BOLD + " --> " + ChatColor.AQUA + "Enchanted");
										if(item.getType() == Material.BOW) {
											ItemStackUtil.addLoreToItem(book, "" + ChatColor.LIGHT_PURPLE + "500" + ChatColor.LIGHT_PURPLE + " Shards");
										}else if(item.getType() == Material.GOLD_HELMET) {
											ItemStackUtil.addLoreToItem(book, "" + ChatColor.LIGHT_PURPLE + "1000" + ChatColor.LIGHT_PURPLE + " Shards");
										}else if(item.getType() == Material.DIAMOND_LEGGINGS) {
											ItemStackUtil.addLoreToItem(book, "" + ChatColor.LIGHT_PURPLE + "1000" + ChatColor.LIGHT_PURPLE + " Shards");
										}else if(item.getType() == Material.LEATHER_BOOTS) {
											ItemStackUtil.addLoreToItem(book, "" + ChatColor.LIGHT_PURPLE + "5000" + ChatColor.LIGHT_PURPLE + " Shards");
										}else {
											ItemStackUtil.addLoreToItem(book, "" + ChatColor.LIGHT_PURPLE+ "100" + ChatColor.LIGHT_PURPLE + " Shards");
										}
										ItemStackUtil.addLoreToItem(book, ChatColor.YELLOW + "Click to enchant item!");
									}
								}
								inv.setItem(15, book);
							}
							}
					}
				}
			}
		}
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if(e.getInventory().getName().equals(ChatColor.LIGHT_PURPLE + "Enchant Item:")) {
			Inventory i = e.getInventory();
			ItemStack enchitem = i.getItem(11);
			if(enchitem != null) {
				if(!processes.containsKey(p)) {
					p.getInventory().addItem(enchitem);
				}
			}
		}
	}
	
	public static boolean hasEnch(String enchname, ItemStack i) {
		if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + enchname + " I")) {
			return true;
		}else if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + enchname + " II")) {
			return true;
		}else if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + enchname + " III")) {
			return true;
		}else if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + enchname + " IV")) {
			return true;
		}else if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + enchname + " V")) {
			return true;
		}
		if(i.getItemMeta().getLore().contains(ChatColor.GOLD + enchname)) {
			return true;
		}
		return false;
	}

}
