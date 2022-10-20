package sylaires.invasion.main;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import sylaires.invasion.enemy.EnemyCragRam;
import sylaires.invasion.enemy.EnemyDruid;
import sylaires.invasion.enemy.EnemyForestSpider;
import sylaires.invasion.enemy.EnemyGhostlyMiner;
import sylaires.invasion.enemy.EnemyLostMiner;
import sylaires.invasion.enemy.EnemyPaladin;
import sylaires.invasion.enemy.EnemySilverfish;
import sylaires.invasion.enemy.EnemyWhiteWalker;
import sylaires.invasion.enemy.EntityTypes;
import sylaires.invasion.main.Game.GameState;
import sylaires.invasion.main.Locations.spawnType;
import sylaires.invasion.util.TitleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class Wave {
	
	private int num;
	private int enemyCount;
	private spawnType region1;
	private spawnType region2;
	private spawnType region3;
	private spawnType region4;
	private int reqEssence;
	private int stockedEssence;
	private boolean terminated;
	//private boolean special;
	private int playerCount = Bukkit.getOnlinePlayers().size();
	//private Event event - Yet to be implemented
	int duration = 0;
	boolean timed = false;
	
	ArrayList<spawnType> regions = new ArrayList<spawnType>();
	
	public Wave(int which) {
		this.num = which;
		
		Random rand = new Random();
		//Que wave number logic
		int randreg = rand.nextInt(4);
		switch(randreg) {
		case 0: region1 = spawnType.FOREST; break;
		case 1: region1 = spawnType.CRAG; break;
		case 2: region1 = spawnType.MINE; break;
		case 3: region1 = spawnType.RUINS; break;
		}
		regions.add(region1);
		if(num >= 20) {
			int randreg1 = rand.nextInt(4);
			switch(randreg1) {
			case 0: region2 = spawnType.FOREST; break;
			case 1: region2 = spawnType.CRAG; break;
			case 2: region2 = spawnType.MINE; break;
			case 3: region2 = spawnType.RUINS; break;
			}
			while(region1 == region2) {
				randreg1 = rand.nextInt(4);
				switch(randreg1) {
				case 0: region2 = spawnType.FOREST; break;
				case 1: region2 = spawnType.CRAG; break;
				case 2: region2 = spawnType.MINE; break;
				case 3: region2 = spawnType.RUINS; break;
				}
			}
			regions.add(region2);
		}
		if(num >= 50) {
			int randreg1 = rand.nextInt(4);
			switch(randreg1) {
			case 0: region3 = spawnType.FOREST; break;
			case 1: region3 = spawnType.CRAG; break;
			case 2: region3 = spawnType.MINE; break;
			case 3: region3 = spawnType.RUINS; break;
			}
			while(region3 == region2 || region1 == region3) {
				randreg1 = rand.nextInt(4);
				switch(randreg1) {
				case 0: region3 = spawnType.FOREST; break;
				case 1: region3 = spawnType.CRAG; break;
				case 2: region3 = spawnType.MINE; break;
				case 3: region3 = spawnType.RUINS; break;
				}
			}
			regions.add(region3);
		}
		if(num >= 100) {
			int randreg1 = rand.nextInt(4);
			switch(randreg1) {
			case 0: region4 = spawnType.FOREST; break;
			case 1: region4 = spawnType.CRAG; break;
			case 2: region4 = spawnType.MINE; break;
			case 3: region4 = spawnType.RUINS; break;
			}
			while(region4 == region2 || region4 == region1 || region4 == region3) {
				randreg1 = rand.nextInt(4);
				switch(randreg1) {
				case 0: region4 = spawnType.FOREST; break;
				case 1: region4 = spawnType.CRAG; break;
				case 2: region4 = spawnType.MINE; break;
				case 3: region4 = spawnType.RUINS; break;
				}
			}
			regions.add(region4);
		}
		if(num%10 != 0) { //Is not a multiple of 10
			enemyCount = (num + (playerCount*4)/2);
			if(enemyCount < 2) {
				enemyCount = 2;
			}
			if(enemyCount > 25) {
				enemyCount = 25;
			}
		}else {
			enemyCount = (num + (playerCount*4));
		}
		reqEssence = (num*10)*(playerCount*10);
		stockedEssence = reqEssence/10;
		new BukkitRunnable() {
			int timer = 10;
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage(ChatColor.AQUA + "Wave " + ChatColor.RED + ChatColor.BOLD + num + ChatColor.AQUA + " starting in " + ChatColor.WHITE + timer + ChatColor.AQUA + " seconds!");
				}
				timer--;
				if(timer == 0) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						p.sendMessage(ChatColor.AQUA + "Wave " + ChatColor.RED + ChatColor.BOLD + num + ChatColor.AQUA + " has started!");
						pulseEnemies();
						p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 2f, 0.9f);
						if(!(num >= 100)) {
							String subtitle = ChatColor.GOLD + "Enemies spawning in: ";
									int regnum = 0;
									for(spawnType region : regions) {
										regnum++;
										if(region == spawnType.FOREST) {
											subtitle = subtitle + "" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Forest";
										}else if(region == spawnType.MINE) {
											subtitle = subtitle + "" + ChatColor.RED + ChatColor.BOLD + "Mine";
										}else if(region == spawnType.RUINS) {
											subtitle = subtitle + "" + ChatColor.BLUE + ChatColor.BOLD + "Ruins";
										}else if(region == spawnType.CRAG) {
											subtitle = subtitle + "" + ChatColor.GRAY + ChatColor.BOLD + "Crag";								
										}
										if(regnum != regions.size()) {
											subtitle = subtitle + ChatColor.GRAY + ", ";
										}
									}
							TitleUtil.sendTitleToPlayer(p, 0, 260, 10, "" + ChatColor.AQUA + ChatColor.BOLD + "Wave " + ChatColor.RED + ChatColor.BOLD + num + ChatColor.AQUA + " has started!", subtitle);
							clock();
						}else {
							TitleUtil.sendTitleToPlayer(p, 0, 260, 10, "" + ChatColor.AQUA + ChatColor.BOLD + "Wave " + ChatColor.RED + ChatColor.BOLD + num + ChatColor.AQUA + " has started!", 
									ChatColor.GOLD + "All directions!");
						}
					}
					this.cancel();
				}
			}
			
		}.runTaskTimer(Main.getPlugin(), 0, 20);
	}
	
	private void pulseEnemies() { //Spawns enemies once, then on a loop. Is canceled when wave switches
		for(spawnType region : regions) {
			if(region == spawnType.FOREST) {
				int tier2 = enemyCount/5; //1 tier 2 enemy for every 5 tier 1s
				int tier3 = tier2/3; 
				int tier4 = tier3/2;
				int tier5 = tier4-1;
				while(tier2 > 0) {
					tier2--;
					EntityTypes.spawnEntity(new EnemyDruid(Main.getWorld(), num), Locations.getMobSpawn(spawnType.FOREST, 1));
					EntityTypes.spawnEntity(new EnemyDruid(Main.getWorld(), num), Locations.getMobSpawn(spawnType.FOREST, 2));
				}
				
				
				for(int i = 0; i <= enemyCount; i++) {
					EntityTypes.spawnEntity(new EnemyForestSpider(Main.getWorld(), num), Locations.getMobSpawn(spawnType.FOREST, 1));
					EntityTypes.spawnEntity(new EnemyForestSpider(Main.getWorld(), num), Locations.getMobSpawn(spawnType.FOREST, 2));
				}
			}else if(region == spawnType.RUINS) {
				int tier2 = enemyCount/5; //1 tier 2 enemy for every 5 tier 1s
				int tier3 = tier2/3; 
				int tier4 = tier3/2;
				int tier5 = tier4-1;
				while(tier2 > 0) {
					tier2--;
					EntityTypes.spawnEntity(new EnemyPaladin(Main.getWorld(), num), Locations.getMobSpawn(spawnType.RUINS, 1));
					EntityTypes.spawnEntity(new EnemyPaladin(Main.getWorld(), num), Locations.getMobSpawn(spawnType.RUINS, 2));
				}
				
				
				for(int i = 0; i <= enemyCount; i++) {
					EntityTypes.spawnEntity(new EnemySilverfish(Main.getWorld(), num), Locations.getMobSpawn(spawnType.RUINS, 1));
					EntityTypes.spawnEntity(new EnemySilverfish(Main.getWorld(), num), Locations.getMobSpawn(spawnType.RUINS, 2));
				}
			}else if(region == spawnType.MINE) {
				int tier2 = enemyCount/5; //1 tier 2 enemy for every 5 tier 1s
				int tier3 = tier2/3; 
				int tier4 = tier3/2;
				int tier5 = tier4-1;
				while(tier2 > 0) {
					tier2--;
					EntityTypes.spawnEntity(new EnemyGhostlyMiner(Main.getWorld(), num), Locations.getMobSpawn(spawnType.MINE, 1));
					EntityTypes.spawnEntity(new EnemyGhostlyMiner(Main.getWorld(), num), Locations.getMobSpawn(spawnType.MINE, 2));
				}
				
				
				for(int i = 0; i <= enemyCount; i++) {
					EntityTypes.spawnEntity(new EnemyLostMiner(Main.getWorld(), num), Locations.getMobSpawn(spawnType.MINE, 1));
					EntityTypes.spawnEntity(new EnemyLostMiner(Main.getWorld(), num), Locations.getMobSpawn(spawnType.MINE, 2));
				}
			}else if(region == spawnType.CRAG) {
				int tier2 = enemyCount/5; //1 tier 2 enemy for every 5 tier 1s
				int tier3 = tier2/3; 
				int tier4 = tier3/2;
				int tier5 = tier4-1;
				while(tier2 > 0) {
					tier2--;
					EntityTypes.spawnEntity(new EnemyWhiteWalker(Main.getWorld(), num), Locations.getMobSpawn(spawnType.CRAG, 1));
					EntityTypes.spawnEntity(new EnemyWhiteWalker(Main.getWorld(), num), Locations.getMobSpawn(spawnType.CRAG, 2));
				}
				
				
				for(int i = 0; i <= enemyCount; i++) {
					EntityTypes.spawnEntity(new EnemyCragRam(Main.getWorld(), num), Locations.getMobSpawn(spawnType.CRAG, 1));
					EntityTypes.spawnEntity(new EnemyCragRam(Main.getWorld(), num), Locations.getMobSpawn(spawnType.CRAG, 2));
				}
			}
		}
		
		new BukkitRunnable() {

			@Override
			public void run() {
				new BukkitRunnable() {

					@Override
					public void run() {
						if(terminated) {
							this.cancel();
						}else if(Main.getGame().getState() != GameState.INGAME) {
							this.cancel();
						}else {
							for(Player p : Bukkit.getOnlinePlayers()) {
								p.playSound(p.getLocation(), Sound.ZOMBIE_IDLE, 1, 0.5f);
							}
							//Paste above spawning
							
							for(spawnType region : regions) {
								if(region == spawnType.FOREST) {
									int tier2 = enemyCount/5; //1 tier 2 enemy for every 5 tier 1s
									int tier3 = enemyCount/15; //Every 15 tier 1
									int tier4 = enemyCount/25; //Every 25
									int tier5 = enemyCount/50; //Every 50
									while(tier2 > 0) {
										tier2--;
										EntityTypes.spawnEntity(new EnemyDruid(Main.getWorld(), num), Locations.getMobSpawn(spawnType.FOREST, 1));
										EntityTypes.spawnEntity(new EnemyDruid(Main.getWorld(), num), Locations.getMobSpawn(spawnType.FOREST, 2));
									}
									
									
									for(int i = 0; i <= enemyCount; i++) {
										EntityTypes.spawnEntity(new EnemyForestSpider(Main.getWorld(), num), Locations.getMobSpawn(spawnType.FOREST, 1));
										EntityTypes.spawnEntity(new EnemyForestSpider(Main.getWorld(), num), Locations.getMobSpawn(spawnType.FOREST, 2));
									}
								}else if(region == spawnType.RUINS) {
									int tier2 = enemyCount/5;
									int tier3 = enemyCount/15;
									int tier4 = enemyCount/25; 
									int tier5 = enemyCount/50;
									while(tier2 > 0) {
										tier2--;
										EntityTypes.spawnEntity(new EnemyPaladin(Main.getWorld(), num), Locations.getMobSpawn(spawnType.RUINS, 1));
										EntityTypes.spawnEntity(new EnemyPaladin(Main.getWorld(), num), Locations.getMobSpawn(spawnType.RUINS, 2));
									}
									
									
									for(int i = 0; i <= enemyCount; i++) {
										EntityTypes.spawnEntity(new EnemySilverfish(Main.getWorld(), num), Locations.getMobSpawn(spawnType.RUINS, 1));
										EntityTypes.spawnEntity(new EnemySilverfish(Main.getWorld(), num), Locations.getMobSpawn(spawnType.RUINS, 2));
									}
								}else if(region == spawnType.MINE) {
									int tier2 = enemyCount/5;
									int tier3 = enemyCount/15;
									int tier4 = enemyCount/25; 
									int tier5 = enemyCount/50;
									while(tier2 > 0) {
										tier2--;
										EntityTypes.spawnEntity(new EnemyGhostlyMiner(Main.getWorld(), num), Locations.getMobSpawn(spawnType.MINE, 1));
										EntityTypes.spawnEntity(new EnemyGhostlyMiner(Main.getWorld(), num), Locations.getMobSpawn(spawnType.MINE, 2));
									}
									
									
									for(int i = 0; i <= enemyCount; i++) {
										EntityTypes.spawnEntity(new EnemyLostMiner(Main.getWorld(), num), Locations.getMobSpawn(spawnType.MINE, 1));
										EntityTypes.spawnEntity(new EnemyLostMiner(Main.getWorld(), num), Locations.getMobSpawn(spawnType.MINE, 2));
									}
								}else if(region == spawnType.CRAG) {
									int tier2 = enemyCount/5;
									int tier3 = enemyCount/15;
									int tier4 = enemyCount/25; 
									int tier5 = enemyCount/50;
									while(tier2 > 0) {
										tier2--;
										EntityTypes.spawnEntity(new EnemyWhiteWalker(Main.getWorld(), num), Locations.getMobSpawn(spawnType.CRAG, 1));
										EntityTypes.spawnEntity(new EnemyWhiteWalker(Main.getWorld(), num), Locations.getMobSpawn(spawnType.CRAG, 2));
									}
									
									
									for(int i = 0; i <= enemyCount; i++) {
										EntityTypes.spawnEntity(new EnemyCragRam(Main.getWorld(), num), Locations.getMobSpawn(spawnType.CRAG, 1));
										EntityTypes.spawnEntity(new EnemyCragRam(Main.getWorld(), num), Locations.getMobSpawn(spawnType.CRAG, 2));
									}
								}
							}
						}
						
					}
					
				}.runTaskTimer(Main.getPlugin(), 0, 1200);
				
			}
			
		}.runTaskLater(Main.getPlugin(), 900);
	}
	
	public int getReqEssence() {
		return reqEssence;
	}
	public int getStocked() {
		return stockedEssence;
	}
	public void addStocked(int toAdd) {
		this.stockedEssence += toAdd;
	}
	
	public void subStocked(int sub) {
		this.stockedEssence -= sub;
	}
	
	public void terminate() {
		this.terminated = true;
	}
	
	public boolean isTerminated() {
		return this.terminated;
	}
	
	public void setTimed() {
		timed = true;
		this.duration = 180;
	}
	
	public boolean isTimed() {
		return timed;
	}
	
	private void clock() {
		new BukkitRunnable() {

			@Override
			public void run() {
				duration--;
				Scoreboards.updateForAll();
				if(duration == 0) {
					for(Player p : Bukkit.getOnlinePlayers()) {
						TitleUtil.sendTitleToPlayer(p, 0, 200, 0, "" + ChatColor.GREEN + ChatColor.BOLD + "Wave Complete!", ChatColor.GOLD + "You survived another wave!");
						PlayerProfile prof = new PlayerProfile(p);
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.4f, 1.5f);
						if(Main.getGame().getWave() > 100) {
							int goldreward = 90*Main.getGame().getWave();
							prof.setGold(prof.getGold() + goldreward);
							int shards = 5*Main.getGame().getWave();
							prof.setShards(prof.getShards() + shards);
							p.sendMessage(ChatColor.AQUA + "Survival Bonus!" + ChatColor.GOLD + " +" + goldreward + " gold," + ChatColor.LIGHT_PURPLE + " +" + shards + " shards");
						}else {
							int goldreward = 20*Main.getGame().getWave();
							prof.setGold(prof.getGold() + goldreward);
							p.sendMessage(ChatColor.AQUA + "Survival Bonus!" + ChatColor.GOLD + " +" + goldreward + " gold");
						}
					}
					Main.getGame().nextWave();
					this.cancel();
				}
			}
			
		}.runTaskTimer(Main.getPlugin(), 0, 20);
	}
	
}
