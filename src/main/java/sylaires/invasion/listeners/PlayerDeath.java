package sylaires.invasion.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import sylaires.invasion.main.Game.GameState;
import sylaires.invasion.main.Locations;
import sylaires.invasion.main.Main;
import sylaires.invasion.main.Scoreboards;
import sylaires.invasion.main.TheNexus;
import sylaires.invasion.util.TitleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class PlayerDeath implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			int alive = 0;
			for(Player p1 : Bukkit.getOnlinePlayers()) {
				if(p1.getGameMode() == GameMode.ADVENTURE) {
					alive++;
				}
			}
			if((alive-1) == 0 && TheNexus.isDead()) { //End game - check Nexus just in case all players happened to have been dead at once
				e.setDeathMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " has died!" + ChatColor.RED + " There are no players remaining!");
				for(Player p1 : Bukkit.getOnlinePlayers()) {
					p1.sendMessage(ChatColor.RED + "The game is over!");
					TitleUtil.sendTitleToPlayer(p, 0, 200, 20, "" + ChatColor.DARK_RED + ChatColor.BOLD + "GAME OVER!", ChatColor.RED + "All players are dead!");
					p1.playSound(p1.getLocation(), Sound.ENDERDRAGON_DEATH, 1.5f, 1.6f);
					p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0));
					p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 200, 20));
					
				}
				Main.getGame().getWavee().terminate();
				Main.getGame().setState(GameState.ENDING);
				new BukkitRunnable() {

					@Override
					public void run() {
						Main.getGame().end();
					}
					
				}.runTaskLater(Main.getPlugin(), 200);
				
			}else if(TheNexus.isDead()) { //Players drop like flies
				e.setDeathMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " has died!" + ChatColor.GREEN + " There are " + alive + " players remaining!");
			}else {
				e.setDeathMessage(ChatColor.RED + p.getName() + ChatColor.YELLOW + " has died!");
				p.sendMessage(ChatColor.RED + "You have lost all held essence!");
				MobDrops.heldEssences.put(p, 0);
			}
			Scoreboards.update(p);
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		if(Main.getGame().getState() == GameState.STARTING || Main.getGame().getState() == GameState.WAITING) {
			e.setRespawnLocation(Locations.getLobby());
		}else{
			e.setRespawnLocation(Locations.getSpawn());
		}
		p.setGameMode(GameMode.SPECTATOR);
		if(TheNexus.isDead()) { //You're dead permanently
			TitleUtil.sendTitleToPlayer(p, 0, 120, 0, "" + ChatColor.RED + ChatColor.BOLD + "YOU DIED!", ChatColor.RED + "The Nexus has fallen, you will not respawn");
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0));
		}else {
			TitleUtil.sendTitleToPlayer(p, 0, 120, 0, "" + ChatColor.RED + ChatColor.BOLD + "YOU DIED!", ChatColor.GOLD + "Respawning...");
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0));
			new BukkitRunnable() {

				@Override
				public void run() {
					p.setGameMode(GameMode.ADVENTURE);
					if(Main.getGame().getState() == GameState.STARTING || Main.getGame().getState() == GameState.WAITING) {
						p.teleport(Locations.getLobby());
					}else{
						p.teleport(Locations.getSpawn());
					}
				}
				
			}.runTaskLater(Main.getPlugin(), 120);
		}
	}

}
