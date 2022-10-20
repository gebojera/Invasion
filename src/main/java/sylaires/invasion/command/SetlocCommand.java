package sylaires.invasion.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sylaires.invasion.main.Locations;
import sylaires.invasion.main.Locations.spawnType;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class SetlocCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setloc")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 0) {
					p.sendMessage(ChatColor.RED + "Usage: /setloc <nexus:lobby:newmobspawn>");
				}else if(args.length >= 1) {
					if(args[0].equalsIgnoreCase("spawn")) {
						p.sendMessage(ChatColor.GREEN + "Set spawn to your location");
						Locations.setSpawn(p.getLocation());
					}else if(args[0].equalsIgnoreCase("lobby")) {
						p.sendMessage(ChatColor.GREEN + "Set lobby to your location");
						Locations.setLobby(p.getLocation());
					}else if(args[0].equalsIgnoreCase("mobspawn")) {
						if(args.length < 3) {
							p.sendMessage(ChatColor.RED + "Usage: /setloc mobspawn <region> <which>");
						}else {
							if(args[1].equalsIgnoreCase("forest")) {
								if(args[2].equalsIgnoreCase("1")) {
									Locations.setMobSpawn(p.getLocation(), spawnType.FOREST, 1);
									p.sendMessage(ChatColor.GREEN + "Success!");
								}else if(args[2].equalsIgnoreCase("2")) {
									Locations.setMobSpawn(p.getLocation(), spawnType.FOREST, 2);
									p.sendMessage(ChatColor.GREEN + "Success!");
								}else {
									p.sendMessage(ChatColor.RED + "Invalid spawn number");
								}
							}else if(args[1].equalsIgnoreCase("ruins")) {
								if(args[2].equalsIgnoreCase("1")) {
									Locations.setMobSpawn(p.getLocation(), spawnType.RUINS, 1);
									p.sendMessage(ChatColor.GREEN + "Success!");
								}else if(args[2].equalsIgnoreCase("2")) {
									Locations.setMobSpawn(p.getLocation(), spawnType.RUINS, 2);
									p.sendMessage(ChatColor.GREEN + "Success!");
								}else {
									p.sendMessage(ChatColor.RED + "Invalid spawn number");
								}
							}else if(args[1].equalsIgnoreCase("crag")) {
								if(args[2].equalsIgnoreCase("1")) {
									Locations.setMobSpawn(p.getLocation(), spawnType.CRAG, 1);
									p.sendMessage(ChatColor.GREEN + "Success!");
								}else if(args[2].equalsIgnoreCase("2")) {
									Locations.setMobSpawn(p.getLocation(), spawnType.CRAG, 2);
									p.sendMessage(ChatColor.GREEN + "Success!");
								}else {
									p.sendMessage(ChatColor.RED + "Invalid spawn number");
								}
							}else if(args[1].equalsIgnoreCase("mine")) {
								if(args[2].equalsIgnoreCase("1")) {
									Locations.setMobSpawn(p.getLocation(), spawnType.MINE, 1);
									p.sendMessage(ChatColor.GREEN + "Success!");
								}else if(args[2].equalsIgnoreCase("2")) {
									Locations.setMobSpawn(p.getLocation(), spawnType.MINE, 2);
									p.sendMessage(ChatColor.GREEN + "Success!");
								}else {
									p.sendMessage(ChatColor.RED + "Invalid spawn number");
								}
							}else {
								p.sendMessage(ChatColor.RED + "Invalid region!");
							}
						}
					}else if(args[0].equalsIgnoreCase("crucible")) {
						Locations.setCrucible(p.getLocation());
						p.sendMessage(ChatColor.GREEN + "Set the Crucible to your location");
					}else if(args[0].equalsIgnoreCase("forge")) {
						Locations.setForge(p.getLocation());
						p.sendMessage(ChatColor.GREEN + "Set the Forge to your location");
					}else if(args[0].equalsIgnoreCase("ench")) {
						Locations.setEnch(p.getLocation());
						p.sendMessage(ChatColor.GREEN + "Set the Binding Table to your location");
					}else if(args[0].equalsIgnoreCase("nexus")) {
						Locations.setNexus(p.getLocation());
						p.sendMessage(ChatColor.GREEN + "Set the Nexus to your location");
					}
				}
			}
		}
		return false;
	}
	
	

}
