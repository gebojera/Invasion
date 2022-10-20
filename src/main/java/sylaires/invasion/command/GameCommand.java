package sylaires.invasion.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sylaires.invasion.main.Main;
import sylaires.invasion.main.Messages;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class GameCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(cmd.getName().equalsIgnoreCase("game")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 0) {
					p.sendMessage(ChatColor.RED + "Usage: /game <start:stop>");
				}else {
					if(args[0].equalsIgnoreCase("start")) {
						if(Main.getGame().isStarted()) {
							p.sendMessage(ChatColor.RED + "Game is already started!");
						}else {
							p.sendMessage(ChatColor.GREEN + "Game started!");
							Messages.broadcast(ChatColor.GREEN + "Game started by admin!");
							Main.getGame().start();
						}
					}else if(args[0].equalsIgnoreCase("stop")) {
						if(!Main.getGame().isStarted()) {
							p.sendMessage(ChatColor.RED + "Game is already stopped!");
						}else {
							p.sendMessage(ChatColor.GREEN + "Stopped game!");
							Messages.broadcast(ChatColor.RED + "Game stopped by admin.");
							Main.getGame().end();
						}
					}
				}
			}
		}
		return false;
	}
	
	

}
