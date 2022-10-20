package sylaires.invasion.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sylaires.invasion.enemy.EnemyCragRam;
import sylaires.invasion.enemy.EnemyDruid;
import sylaires.invasion.enemy.EnemyForestSpider;
import sylaires.invasion.enemy.EnemyGhostlyMiner;
import sylaires.invasion.enemy.EnemyLich;
import sylaires.invasion.enemy.EnemyPaladin;
import sylaires.invasion.enemy.EnemySilverfish;
import sylaires.invasion.enemy.EnemyWhiteWalker;
import sylaires.invasion.enemy.EntityTypes;
import sylaires.invasion.main.Main;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class SpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(args.length == 0) {
					p.sendMessage(ChatColor.RED + "Usage: /spawn <druid:forestspider:paladin>");
				}else {
					if(args[0].equalsIgnoreCase("druid")) {
						EntityTypes.spawnEntity(new EnemyDruid(Main.getWorld(), Main.getGame().getWave()), p.getLocation());
					}else if(args[0].equalsIgnoreCase("forestspider")){
						EntityTypes.spawnEntity(new EnemyForestSpider(Main.getWorld(), Main.getGame().getWave()), p.getLocation());
					}else if(args[0].equalsIgnoreCase("silverfish")) {
						EntityTypes.spawnEntity(new EnemySilverfish(Main.getWorld(), Main.getGame().getWave()), p.getLocation());
					}else if(args[0].equalsIgnoreCase("paladin")) {
						EntityTypes.spawnEntity(new EnemyPaladin(Main.getWorld(), Main.getGame().getWave()), p.getLocation());
					}else if(args[0].equalsIgnoreCase("ghostlyminer")) {
						EntityTypes.spawnEntity(new EnemyGhostlyMiner(Main.getWorld(), Main.getGame().getWave()), p.getLocation());
					}else if(args[0].equalsIgnoreCase("cragram")) {
						EntityTypes.spawnEntity(new EnemyCragRam(Main.getWorld(), Main.getGame().getWave()), p.getLocation());
					}else if(args[0].equalsIgnoreCase("whitewalker")) {
						EntityTypes.spawnEntity(new EnemyWhiteWalker(Main.getWorld(), Main.getGame().getWave()), p.getLocation());
					}else if(args[0].equalsIgnoreCase("lich")) {
						EntityTypes.spawnEntity(new EnemyLich(Main.getWorld(), Main.getGame().getWave()), p.getLocation());
					}
				}
			}
		}
		return false;
	}
	
	

}
