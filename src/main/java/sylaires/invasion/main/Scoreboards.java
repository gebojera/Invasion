package sylaires.invasion.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import sylaires.invasion.listeners.MobDrops;
import sylaires.invasion.main.Game.GameState;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class Scoreboards {
	
	public static void update(Player p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective ob = board.registerNewObjective("test", "dummy");
		ob.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "INVASION");
		ob.setDisplaySlot(DisplaySlot.SIDEBAR);
		PlayerProfile profile = new PlayerProfile(p);
		
		Score game = ob.getScore("" + ChatColor.AQUA + ChatColor.BOLD + "GAME: " + ChatColor.WHITE + ChatColor.BOLD + Main.getGame().getState().toString());
		game.setScore(11);
		
		Score line = ob.getScore(ChatColor.GOLD + "------------------------");
		line.setScore(10);
		
		Score waves = ob.getScore("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "WAVE: " + ChatColor.RED + ChatColor.BOLD + Main.getGame().getWave());
		waves.setScore(9);
		Score essence = null;
		if(Main.getGame().getWavee() == null) {
			essence = ob.getScore(ChatColor.GOLD + "Nexus: " + ChatColor.WHITE + "0/0");
		}else if(TheNexus.isDead()){
			essence = ob.getScore(ChatColor.GOLD + "Nexus: " + ChatColor.RED + ChatColor.BOLD + "DEAD");
		}else {
			essence = ob.getScore(ChatColor.GOLD + "Nexus: " + ChatColor.WHITE + Main.getGame().getWavee().getStocked() + "/" + Main.getGame().getWavee().getReqEssence());
		}
		essence.setScore(8);
		
		Score essence1 = null;
		if(MobDrops.heldEssences.get(p) == null) {
			essence1 = ob.getScore(ChatColor.YELLOW + "Your Essence: " + ChatColor.WHITE + "0");
		}else {
			essence1 = ob.getScore(ChatColor.YELLOW + "Your Essence: " + ChatColor.WHITE + MobDrops.heldEssences.get(p));
		}
		essence1.setScore(7);
		
		Score space1 = ob.getScore("  ");
		space1.setScore(6);
		
		Score gold = ob.getScore(ChatColor.WHITE + "Gold: " + ChatColor.GOLD + profile.getGold());
		gold.setScore(5);
		
		Score space2 = ob.getScore("     ");
		space2.setScore(4);
		
		Score shards = ob.getScore(ChatColor.WHITE + "Shards: " + ChatColor.LIGHT_PURPLE + profile.getShards());
		shards.setScore(3);
		
		Score space3 = ob.getScore("    ");
		space3.setScore(2);
		
		Score wavetime = null;
		if(Main.getGame().getState() != GameState.ENDING && Main.getGame().getState() == GameState.INGAME) {
			if(TheNexus.isDead()) {
				int minutes = Main.getGame().getWavee().duration/60;
				int seconds = Main.getGame().getWavee().duration-(minutes*60);
				String strSeconds = String.valueOf(seconds);
				if(seconds < 10) {
					strSeconds = "0" + strSeconds;
				}
				wavetime = ob.getScore("" + ChatColor.YELLOW + ChatColor.BOLD + "WAVE ENDS: " + ChatColor.WHITE + minutes + ":" + strSeconds);
			}else {
				wavetime = ob.getScore("" + ChatColor.YELLOW + ChatColor.BOLD + "WAVE ENDS: " + ChatColor.GREEN + "Nexus Alive");
			}
		}else if(Main.getGame().getState() == GameState.STARTING || Main.getGame().getState() == GameState.WAITING){
			wavetime = ob.getScore("" + ChatColor.YELLOW + ChatColor.BOLD + "WAVE ENDS: " + ChatColor.GREEN + "Lobby");
		}else {
			wavetime = ob.getScore("" + ChatColor.YELLOW + ChatColor.BOLD + "WAVE ENDS: " + ChatColor.RED + "Game Over");
		}
		wavetime.setScore(1);
		p.setScoreboard(board);
	}
	
	public static void updateForAll() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			update(p);
		}
	}

}
