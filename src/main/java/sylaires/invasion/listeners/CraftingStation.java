package sylaires.invasion.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import sylaires.invasion.enchanting.BindingTable;
import sylaires.invasion.main.Locations;
import sylaires.invasion.main.Main;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class CraftingStation implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent e) {
		Player p = e.getPlayer();
			if(e.getRightClicked() instanceof ArmorStand) {
				ArmorStand a = (ArmorStand) e.getRightClicked();
				e.setCancelled(true);
				if(a.getLocation().getX() == Locations.getEnch().getX() && a.getLocation().getZ() == Locations.getEnch().getZ()) {
					if(true) {
						BindingTable.open(p);
						p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 30, 1.1F);
					}else {
						p.sendMessage(ChatColor.RED + "Enchanting is currently underway!");
					}
				}else if(a.getLocation().getX() == Locations.getForge().getX() && a.getLocation().getZ() == Locations.getForge().getZ()) {
					//Open forge
				}else if(a.getLocation().getX() == Locations.getCrucible().getX() && a.getLocation().getZ() == Locations.getCrucible().getZ()) {
					//Open crucible
				}
			}
	}
	
	@EventHandler
	public void onBlockClick(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block b = e.getClickedBlock();
				if(b.getType() == Material.ENDER_PORTAL_FRAME) {
					Location enchloc = Locations.getEnch().getBlock().getLocation().add(new Location(Main.getWorld(), 0, 1, 0));
					if(b.getLocation().equals(enchloc)) {
						e.setCancelled(true);
						if(true) {
							BindingTable.open(e.getPlayer());
							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.CHICKEN_EGG_POP, 30, 1.1F);
						}else {
							e.getPlayer().sendMessage(ChatColor.RED + "Enchanting is currently underway!");
						}
					}
				}else if(b.getType() == Material.CAULDRON) {
					Location crucloc = Locations.getCrucible().getBlock().getLocation().add(new Location(Main.getWorld(), 0, 1, 0));
					if(b.getLocation().equals(crucloc)) {
						//Open crucible
					}
				}
			}
		}
}
