package sylaires.invasion.main;

import java.text.DecimalFormat;
import java.util.UUID;

import org.bukkit.entity.Player;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class PlayerProfile {
	
	private double gold;
	private int highWave;
	private int deaths;
	private int shards;
	private String name;
	private UUID id;
	Player p;
	
	public PlayerProfile(Player p) {
		this.p = p;
		this.name = p.getName();
		this.id = p.getUniqueId();
		FileManager file = FileManager.getDataFile(p.getUniqueId().toString());
		if(!file.contains("gold")) {
			file.set("gold", 0.00);
		}
		if(!file.contains("deaths")) {
			file.set("deaths", 0);
		}
		if(!file.contains("highWave")) {
			file.set("highWave", 0);
		}
		if(!file.contains("shards")) {
			file.set("shards", 0);
		}
		gold = file.get("gold");
		shards = file.get("shards");
		deaths = file.get("deaths");
		highWave = file.get("highWave");
		file.set("name", name);
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public double getGold() {
		DecimalFormat format = new DecimalFormat("#.00");
		gold = Double.valueOf(format.format(gold));
		return gold;
	}
	
	public int getShards() {
		return shards;
	}
	
	public int getDeaths() {
		return deaths;
	}
	
	public int getHigh() {
		return highWave;
	}
	
	public String getName() {
		return name;
	}
	
	public UUID getUUID() {
		return id;
	}
	
	public void setGold(Double gold) {
		this.gold = gold;
		DecimalFormat format = new DecimalFormat("#.00");
		gold = Double.valueOf(format.format(gold));
		FileManager file = FileManager.getDataFile(p.getUniqueId().toString());
		file.set("gold", gold);
		Scoreboards.update(p);
	}
	
	public void setShards(int shards) {
		this.shards = shards;
		FileManager file = FileManager.getDataFile(p.getUniqueId().toString());
		file.set("shards", shards);
		Scoreboards.update(p);
	}
	
	public void setDeaths(int deaths) {
		this.deaths = deaths;
		FileManager file = FileManager.getDataFile(p.getUniqueId().toString());
		file.set("deaths", deaths);
	}
	
	public void setHighest(int highest) {
		this.highWave = highest;
		FileManager file = FileManager.getDataFile(p.getUniqueId().toString());
		file.set("highWave", highest);
		Scoreboards.update(p);
	}

}
