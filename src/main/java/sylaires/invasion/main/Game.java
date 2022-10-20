package sylaires.invasion.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import sylaires.invasion.enchanting.EnchantmentDivineIntervention;
import sylaires.invasion.enchanting.EnchantmentPhoenix;
import sylaires.invasion.listeners.MobDrops;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class Game {
	
	public enum GameState {
		WAITING, STARTING, INGAME, TERMINATED, ENDING, EVENT
	}
	
	private int counter;
	private GameState state;
	private int wave;
	private boolean started;
	private Wave current;
	
	public Game() {
		this.state = GameState.WAITING; //Game is waiting when plugin is initialized - started by command
		this.counter = 0;
		this.wave = 0;
		this.started = false;
	}
	
	public void start() { //Triggered by a command
		if(state != GameState.TERMINATED) {
			this.state = GameState.STARTING;
			this.counter = 30;
			this.started = true;
			Scoreboards.updateForAll();
			new BukkitRunnable() { //Tick down the counter until it's time to start

				@Override
				public void run() {
					counter--;
					if(counter == 0) {
						initialize();
						Messages.broadcast(ChatColor.GREEN + "Game has started - good luck!");
						Scoreboards.updateForAll();
						this.cancel();
					}else if(counter == 20) {
						Messages.broadcast(ChatColor.AQUA + "Game starting in " + ChatColor.WHITE + counter + ChatColor.AQUA + " seconds!");
					}else if(counter == 10) {
						Messages.broadcast(ChatColor.AQUA + "Game starting in " + ChatColor.WHITE + counter + ChatColor.AQUA + " seconds!");
					}else if(counter <= 5 && counter != 0) {
							Messages.broadcast(ChatColor.AQUA + "Game starting in " + ChatColor.WHITE + counter + ChatColor.AQUA + " seconds!");
					}
					
				}
				
			}.runTaskTimer(Main.getPlugin(), 0, 20);
		}
	}
	
	private void initialize() { //Start the game in earnest 
		this.setState(GameState.INGAME);
		Main.getWorld().setTime(16000);
		TheNexus.register();
		//Teleport players
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(Locations.getSpawn());
		}
		nextWave();
		Scoreboards.updateForAll();
	}
	
	public void end() { //Teleport players, end game
		Main.getWorld().setTime(1000);
		//Teleport
		for(Player p : Bukkit.getOnlinePlayers()) {
			p.teleport(Locations.getLobby());
			p.setGameMode(GameMode.ADVENTURE);
			MobDrops.heldEssences.put(p, 0);
		}
		EnchantmentPhoenix.activations.clear(); //Refresh all phoenix enchantments
		EnchantmentDivineIntervention.activated.clear();
		setState(GameState.WAITING);
		setCounter(0);
		setStopped();
		setWave(0);
		nullWave();
		TheNexus.reset();
		for(Entity e : Main.getWorld().getEntities()) {
			if(!(e instanceof Player) && !(e instanceof ArmorStand) && !(e instanceof Item)) {
				e.remove();
			}
		}
		Scoreboards.updateForAll();
	}
	
	public int getCounter() {
		return counter;
	}
	
	public GameState getState() {
		return state;
	}
	
	public int getWave() {
		return wave;
	}
	
	public Wave getWavee() {
		return current;
	}
	
	public void nullWave() {
		this.current = null;
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public void setCounter(int count) {
		counter = count;
	}
	
	public void setState(GameState newstate) {
		this.state = newstate;
	}
	
	public void setStarted() {
		started = true;
	}
	
	public void setStopped() {
		started = false;
	}
	
	public void setWave(int wave) {
		this.wave = wave;
	}
	
	
	public void nextWave() { //Method triggered whenever essence is completed
		wave++;
		if(this.current != null) {
			this.current.terminate();
		}
		Wave wavee = new Wave(wave);
		this.current = wavee;
		if(TheNexus.isDead()) {
			wavee.setTimed();
		}
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			MobDrops.heldEssences.put(p, 0);
		}
		
		for(Entity e : Main.getWorld().getEntities()) {
			if(!(e instanceof Player) && !(e instanceof ArmorStand) && !(e instanceof Item)) {
				e.remove();
			}
		}
		
		Scoreboards.updateForAll();
		
	}
	
}
