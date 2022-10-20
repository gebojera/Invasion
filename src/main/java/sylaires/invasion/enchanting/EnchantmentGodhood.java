package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentGodhood implements Listener {
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Godhood");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Can jump in midair, take " + ChatColor.BLUE + "80%" + ChatColor.GRAY + " reduced damage");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "deal " + ChatColor.RED + "100%" + ChatColor.GRAY + " increased damage and if a source would deal");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "fatal damage to you, negate that damage.");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY +  "This item loses 200 durability every time you negate fatal damage");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Wither Enemies deal " + ChatColor.RED + "80%" + ChatColor.GRAY + " increased damage to you instead");
		ItemStackUtil.addLoreToItem(item, "  ");
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getInventory().getChestplate() != null) {
				ItemStack chest = p.getInventory().getChestplate();
				if(chest.getItemMeta() != null) {
					if(chest.getItemMeta().getLore() != null) {
						if(chest.getItemMeta().getLore().contains(ChatColor.GOLD + "Godhood")) {
							double reduct = e.getDamage()*0.8;
							e.setDamage(e.getDamage() - reduct);
							if(p.getHealth()-e.getDamage() < 0.01) {
								e.setCancelled(true);
								p.setHealth(p.getMaxHealth());
								chest.setDurability((short) (chest.getDurability()+150));
								p.sendMessage(ChatColor.GOLD + "Your Godhood has prevented you from dying!... but at the cost of 150 durability");
								ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, p.getLocation(), 0.3, 0.1, 0.3, 0, 100);
								p.playSound(p.getLocation(), Sound.ANVIL_LAND, 30, 1.6f);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onFlightAttempt(PlayerToggleFlightEvent event) {
		Player player = event.getPlayer();
	    if(player.getInventory().getChestplate() != null) {
	    	ItemStack chest = player.getInventory().getChestplate();
	    	if(chest.getItemMeta() != null) {
	    		if(chest.getItemMeta().getLore() != null) {
	    			if(chest.getItemMeta().getLore().contains(ChatColor.GOLD + "Godhood")) {
	    				if(event.isFlying() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
	    			           
	    		            player.setFlying(false);
	    		            Vector jump = player.getLocation().getDirection().multiply(0.2).setY(1.1);
	    		            player.setVelocity(player.getVelocity().add(jump));
	    		            player.playSound(player.getLocation(), Sound.GHAST_FIREBALL, 30, 1.7f);
	    		            ParticleUtil.playParticles(EnumParticle.CLOUD, player.getLocation(), 0.3, 0, 0.3, 0, 50);
	    		            event.setCancelled(true);
	    		            return;
	    		        }
	    			}
	    		}
	    	}
	    }
        
	 
	}
	
	@EventHandler
	public void onEquip(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getSlotType() == SlotType.ARMOR) {
			if(e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getLore() != null) { 
				if(e.getCurrentItem().getItemMeta().getLore().contains(ChatColor.GOLD + "Godhood")) {
					p.setAllowFlight(false);
				}
			}
			if(e.getCursor().getItemMeta() != null && e.getCursor().getItemMeta().getLore() != null) {
				if(e.getCursor().getItemMeta().getLore().contains(ChatColor.GOLD + "Godhood")) {
					p.setAllowFlight(true);
				}
			}
			
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Player p = e.getPlayer();
			if(p.getItemInHand() != null) {
				ItemStack i = p.getItemInHand();
				if(i.getType() == Material.DIAMOND_CHESTPLATE) {
					if(p.getInventory().getChestplate() != null) { //Swap the chestplates
						ItemStack chest = p.getInventory().getChestplate();
						p.getInventory().setChestplate(null);
						if(chest.getItemMeta() != null) {
							if(chest.getItemMeta().getLore() != null) {
								if(chest.getItemMeta().getLore().contains(ChatColor.GOLD + "Godhood")) {
									p.setAllowFlight(false);
								}
								if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts I")) {
									p.setMaxHealth(20);
								}if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts II")) {
									p.setMaxHealth(20);
								}if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts III")) {
									p.setMaxHealth(20);
								}if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts VI")) {
									p.setMaxHealth(20);
								}if(chest.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts V")) {
									p.setMaxHealth(20);
								}
							}
						}
						if(i.getItemMeta() != null) {
							if(i.getItemMeta().getLore() != null) {
								if(i.getItemMeta().getLore().contains(ChatColor.GOLD + "Godhood")) {
									p.setAllowFlight(true);
								}
								if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts I")) {
									p.setMaxHealth(24);
								}if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts II")) {
									p.setMaxHealth(28);
								}if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts III")) {
									p.setMaxHealth(32);
								}if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts VI")) {
									p.setMaxHealth(36);
								}if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts V")) {
									p.setMaxHealth(40);
								}
							}
						}
						
						new BukkitRunnable() {

							@Override
							public void run() {
								p.getInventory().addItem(chest);
								p.playSound(p.getLocation(), Sound.HORSE_ARMOR, 20, 1);
							}
							
						}.runTaskLater(Main.getPlugin(), 3);
					}else { //Equipping as normal
						if(i.getItemMeta().getLore() != null) {
							if(i.getItemMeta().getLore().contains(ChatColor.GOLD + "Godhood")) {
								p.setAllowFlight(true);
							}
							if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts I")) {
								p.setMaxHealth(24);
							}if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts II")) {
								p.setMaxHealth(28);
							}if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts III")) {
								p.setMaxHealth(32);
							}if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts VI")) {
								p.setMaxHealth(36);
							}if(i.getItemMeta().getLore().contains(ChatColor.YELLOW + "Hearts V")) {
								p.setMaxHealth(40);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(p.getInventory().getChestplate() != null) {
				ItemStack chest = p.getInventory().getChestplate();
				if(chest.getItemMeta() != null) {
					if(chest.getItemMeta().getLore() != null) {
						if(chest.getItemMeta().getLore().contains(ChatColor.GOLD + "Godhood")) {
							e.setDamage(e.getDamage()*2);
						}
					}
				}
			}
		}
	}

}
