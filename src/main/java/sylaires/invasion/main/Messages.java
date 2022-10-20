package sylaires.invasion.main;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class Messages {
	
	public static void broadcast(String message) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "INVASION" + ChatColor.WHITE + "] " + message);
		}
	}
	
	public static void broadcast(String message, Player exempt) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			if(p != exempt) {
				p.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "INVASION" + ChatColor.WHITE + "] " + message);
			}
		}
	}
	
	public static void broadcast(String message, List<Player> exempt) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!exempt.contains(p)) {
				p.sendMessage(ChatColor.WHITE + "[" + ChatColor.RED + "INVASION" + ChatColor.WHITE + "] " + message);
			}
		}
	}


}
