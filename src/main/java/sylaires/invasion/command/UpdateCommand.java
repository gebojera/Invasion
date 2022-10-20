package sylaires.invasion.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sylaires.invasion.main.Main;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class UpdateCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(cmd.getName().equalsIgnoreCase("update")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				List<String> lines = new ArrayList<String>();
				lines.add("Edited Regeneration - now is passive instead of activated");
				lines.add("Edited Blood Pact - now deals 2x damage at the cost of health each hit");
				lines.add("Fixed several enchantments not registering kills (Phoenix, Sanguinare...)");
				lines.add("Buffed scavenger significantly");
				lines.add("Fixed several small bugs regarding Launch and Regeneration");
				lines.add("Fixed typos in some enchantment descriptions");
				lines.add("Fixed a bug where certain players would be unable to obtain the Phoenix enchantment");
				lines.add("Fixed a bug where faraway enemies would not path to the Nexus");
				lines.add("Fixed a bug where enemies would still path to the Nexus after it was destroyed");
				lines.add("Balance tweaks to several existing enemies");
				lines.add("Enemy count now far more dependent on playercount");
				lines.add("Mob Rendering - Through the wizardry of manipulating the tick of the internal server, Mobs will continue progress to Nexus even when not loaded (something previously thought to be impossible)");
				lines.add("Added Crag Ram as a Tier I enemy to the Crag (Can charge at players dealing extra damage)");
				lines.add("Added White Walker as a Tier II enemy to the Crag (Radiates a slowing aura and deals 20-50% true damage)");
				lines.add("Added Lost Miner as a Tier I enemy to the Mine");
				lines.add("Added Ghostly Miner as a Tier II enemy to the Mine (Turns invisible when targeting players)");
				lines.add("Both the Ghostly Miner and the White Walker are 'hunting' enemies - enemies that will only target players and will ignore the nexus");
				lines.add("Generally enhanced enemy AI and fixed countless bugs");
				lines.add("The game no longer relies on the Nexus. Once it is destroyed, respawn is disabled and waves become timed (3min) instead");
				lines.add("Fixed a bug where you could add essence to the Nexus after it had been destroyed");
				lines.add("Increased the frequency of 'The Nexus is under attack!' alerts");
				lines.add("Player death and respawning has been redone");
				lines.add("Fixed a bug where lava would do ungodly amounts of damage");
				lines.add("Fixed a bug where reloading the server would break scoreboards for players already online");
				lines.add("Fixed a bug where killing enemies with arrows would not count as kills");
				lines.add("Fixed a bug where the current wave did not end properly when all players died, resulting in lag and repeated errors in the console");
				lines.add("Fixed a bug where the clock of waves after the Nexus had been destroyed would begin ticking before they had started");
				
				
				p.sendMessage(ChatColor.AQUA + "-=-=-=-[Update " + Main.getVersion() + "]-=-=-=-");
				for(String str : lines) {
					p.sendMessage(ChatColor.YELLOW + "-" + str);
				}
			}
		}
		return false;
	}
	
	

}
