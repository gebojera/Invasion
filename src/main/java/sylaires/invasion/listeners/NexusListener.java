package sylaires.invasion.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import sylaires.invasion.main.Game.GameState;
import sylaires.invasion.main.Locations;
import sylaires.invasion.main.Main;
import sylaires.invasion.main.Scoreboards;
import sylaires.invasion.main.TheNexus;
import sylaires.invasion.util.TitleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class NexusListener implements Listener {
	
	@EventHandler
	public void onClick(PlayerInteractAtEntityEvent event) {
		if(event.getRightClicked() instanceof ArmorStand) {
			ArmorStand a = (ArmorStand) event.getRightClicked();
			if(a.getLocation().getX() == Locations.getNexus().getX() && a.getLocation().getZ() == Locations.getNexus().getZ()) {
				if(Main.getGame().getState() == GameState.INGAME) {
					if(!TheNexus.isDead()) {
						if(MobDrops.heldEssences.get(event.getPlayer()) > 0) {
							Main.getGame().getWavee().addStocked(MobDrops.heldEssences.get(event.getPlayer()));
							MobDrops.heldEssences.put(event.getPlayer(), 0);
							event.getPlayer().sendMessage(ChatColor.GREEN + "You deposited your essence!");
							event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ORB_PICKUP, 1, 1.5f);
							Scoreboards.updateForAll();
							
							if(Main.getGame().getWavee().getStocked() >= Main.getGame().getWavee().getReqEssence()) { //Wave completes
								for(Player p : Bukkit.getOnlinePlayers()) {
									TitleUtil.sendTitleToPlayer(p, 0, 180, 20, "" + ChatColor.GREEN + ChatColor.BOLD + "Wave Complete!", ChatColor.GOLD + event.getPlayer().getName() + " gathered the remaining essence!");
									p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.4f, 1.5f);
								}
								Main.getGame().nextWave();
							}
						}
					}
				}
			}
		}
	}

}
