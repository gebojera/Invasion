package sylaires.invasion.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import sylaires.invasion.listeners.MobDrops;
import sylaires.invasion.main.Main;
import sylaires.invasion.main.Game.GameState;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EssenceCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
		if(cmd.getName().equalsIgnoreCase("essence")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(Main.getGame().getState() == GameState.INGAME) {
					MobDrops.heldEssences.put(p, Main.getGame().getWavee().getReqEssence());
				}
			}
		}
		return false;
	}
	
	

}
