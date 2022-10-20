package sylaires.invasion.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class BrowseCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(cmd.getName().equalsIgnoreCase("peek")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.getName().equals("Shahrazad")) {
					if(args.length <= 1) {
						p.sendMessage(ChatColor.RED + "Usage: /peek <player> <chest:inventory>");
					}else {
						Player target = null;
						try {
							target = Bukkit.getPlayer(args[0]);
						} catch(Exception e) {
							p.sendMessage("Unknown player");
							return false;
						}
						if(args[1].equalsIgnoreCase("chest")) {
							Inventory inv = Bukkit.getPlayerExact(target.getName()).getEnderChest();
							p.openInventory(inv);
						}else if(args[1].equalsIgnoreCase("inventory")) {
							Inventory inv = Bukkit.getPlayerExact(target.getName()).getInventory();
							p.openInventory(inv);
						}
					}
				}else if(p.getName().equals("EmissaryOfTrest")){
					if(args.length <= 1) {
						p.sendMessage(ChatColor.RED + "Usage: /peek <player> <chest:inventory>");
					}else {
						Player target = null;
						try {
							target = Bukkit.getPlayer(args[0]);
						} catch(Exception e) {
							p.sendMessage("Unknown player");
							return false;
						}
						if(target.getName().equals("Shahrazad")) {
							p.sendMessage(ChatColor.RED + "Sorry, can't do that");
							return false;
						}
						if(args[1].equalsIgnoreCase("chest")) {
							Inventory inv = Bukkit.getPlayerExact(target.getName()).getEnderChest();
							p.openInventory(inv);
						}else if(args[1].equalsIgnoreCase("inventory")) {
							Inventory inv = Bukkit.getPlayerExact(target.getName()).getInventory();
							p.openInventory(inv);
						}
					}
				}else {
					p.sendMessage(ChatColor.RED + "You are not worthy");
				}
			}
		}
		return false;
	}
	
	

}
