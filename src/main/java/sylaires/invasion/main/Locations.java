package sylaires.invasion.main;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import sylaires.invasion.main.Game.GameState;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class Locations {
	
	public enum spawnType {
		FOREST, MINE, CRAG, RUINS
	}
	
	private static Location lobby;
	private static Location spawn;
	private static Location forge;
	private static Location crucible;
	private static Location ench;
	
	private static Location forestSpawn1;
	private static Location forestSpawn2;
	
	private static Location mineSpawn1;
	private static Location mineSpawn2;
	
	private static Location cragSpawn1;
	private static Location cragSpawn2;
	
	private static Location ruinsSpawn1;
	private static Location ruinsSpawn2;
	
	private static Location nexus;
	
	public static void loadLocs() {
		FileManager file = FileManager.getDataFile("locations");
		if(file.getKeys().isEmpty()) {
			Bukkit.getLogger().info("Locations file is empty, game cannot start!");
			Main.getGame().setState(GameState.TERMINATED);
		}else {
			try {
				lobby = new Location(Main.getWorld(), file.get("lobby.X"), file.get("lobby.Y"), file.get("lobby.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading the lobby location - has it yet to be set?");
			}
			
			try {
				spawn = new Location(Main.getWorld(), file.get("spawn.X"), file.get("spawn.Y"), file.get("spawn.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading the spawn location - has it yet to be set?");
			}
			
			try {
				forge = new Location(Main.getWorld(), file.get("forge.X"), file.get("forge.Y"), file.get("forge.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading the Forge location - has it yet to be set?");
			}
			try {
				crucible = new Location(Main.getWorld(), file.get("crucible.X"), file.get("crucible.Y"), file.get("crucible.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading the Crucible location - has it yet to be set?");
			}
			try {
				ench = new Location(Main.getWorld(), file.get("ench.X"), file.get("ench.Y"), file.get("ench.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading the Binding Table location - has it yet to be set?");
			}
			
			try {
				nexus = new Location(Main.getWorld(), file.get("nexus.X"), file.get("nexus.Y"), file.get("nexus.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading the Nexus location - has it yet to be set?");
			}
			
			try {
				forestSpawn1 = new Location(Main.getWorld(), file.get("forest1.X"), file.get("forest1.Y"), file.get("forest1.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading a Forest location - has it yet to be set?");
			}try {
				forestSpawn2 = new Location(Main.getWorld(), file.get("forest2.X"), file.get("forest2.Y"), file.get("forest2.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading a Forest location - has it yet to be set?");
			}try {
				cragSpawn1 = new Location(Main.getWorld(), file.get("crag1.X"), file.get("crag1.Y"), file.get("crag1.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading a Crag location - has it yet to be set?");
			}try {
				cragSpawn2 = new Location(Main.getWorld(), file.get("crag2.X"), file.get("crag2.Y"), file.get("crag2.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading a Crag location - has it yet to be set?");
			}try {
				mineSpawn1 = new Location(Main.getWorld(), file.get("mine1.X"), file.get("mine1.Y"), file.get("mine1.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading a Mine location - has it yet to be set?");
			}try {
				mineSpawn2 = new Location(Main.getWorld(), file.get("mine2.X"), file.get("mine2.Y"), file.get("mine2.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading a Mine location - has it yet to be set?");
			}try {
				ruinsSpawn1 = new Location(Main.getWorld(), file.get("ruins1.X"), file.get("ruins1.Y"), file.get("ruins1.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading a Ruins location - has it yet to be set?");
			}try {
				ruinsSpawn2 = new Location(Main.getWorld(), file.get("ruins2.X"), file.get("ruins2.Y"), file.get("ruins2.Z"));
			} catch (Exception e) {
				Bukkit.getLogger().info("There seems to be an error reading a Ruins location - has it yet to be set?");
			}
		}		
	}
	
	public static Location getSpawn() {
		return spawn;
	}
	
	public static Location getLobby() {
		return lobby;
	}
	
	public static Location getForge() {
		return forge;
	}
	
	public static Location getEnch() {
		return ench;
	}
	
	public static Location getCrucible() {
		return crucible;
	}
	
	public static Location getNexus() {
		return nexus;
	}
	
	public static Location getMobSpawn(spawnType spawn, int which) {
		if(spawn == spawnType.FOREST) {
			if(which == 1) {
				return forestSpawn1;
			}else if (which == 2) {
				return forestSpawn2;
			}
		}
		if(spawn == spawnType.CRAG) {
			if(which == 1) {
				return cragSpawn1;
			}else if (which == 2) {
				return cragSpawn2;
			}
		}
		if(spawn == spawnType.MINE) {
			if(which == 1) {
				return mineSpawn1;
			}else if (which == 2) {
				return mineSpawn2;
			}
		}
		if(spawn == spawnType.RUINS) {
			if(which == 1) {
				return ruinsSpawn1;
			}else if (which == 2) {
				return ruinsSpawn2;
			}
		}
		return null;
	}
	
	public static void setSpawn(Location newspawn) {
		spawn = newspawn;
		FileManager file = FileManager.getDataFile("locations");
		file.set("spawn.X", newspawn.getX());
		file.set("spawn.Y", newspawn.getY());
		file.set("spawn.Z", newspawn.getZ());
	}
	
	public static void setLobby(Location newlobby) {
		lobby = newlobby;
		FileManager file = FileManager.getDataFile("locations");
		file.set("lobby.X", newlobby.getX());
		file.set("lobby.Y", newlobby.getY());
		file.set("lobby.Z", newlobby.getZ());
	}
	
	public static void setEnch(Location loc) {
		ench = loc;
		FileManager file = FileManager.getDataFile("locations");
		file.set("ench.X", loc.getX());
		file.set("ench.Y", loc.getY());
		file.set("ench.Z", loc.getZ());
	}
	public static void setCrucible(Location loc) {
		crucible = loc;
		FileManager file = FileManager.getDataFile("locations");
		file.set("crucible.X", loc.getX());
		file.set("crucible.Y", loc.getY());
		file.set("crucible.Z", loc.getZ());
	}
	public static void setForge(Location loc) {
		forge = loc;
		FileManager file = FileManager.getDataFile("locations");
		file.set("forge.X", loc.getX());
		file.set("forge.Y", loc.getY());
		file.set("forge.Z", loc.getZ());
	}
	
	public static Location getCenter(Location loc) {
	    return new Location(loc.getWorld(),
	        getRelativeCoord(loc.getBlockX()),
	        getRelativeCoord(loc.getBlockY()),
	        getRelativeCoord(loc.getBlockZ()));
	}
	
	public static void setNexus(Location loc) {
		nexus = loc;
		FileManager file = FileManager.getDataFile("locations");
		file.set("nexus.X", loc.getX());
		file.set("nexus.Y", loc.getY());
		file.set("nexus.Z", loc.getZ());
	}
	
	public static void setMobSpawn(Location loc, spawnType type, int which) {
		FileManager file = FileManager.getDataFile("locations");
		if(type == spawnType.FOREST) {
			if(which == 1) {
				file.set("forest1.X", loc.getX());
				file.set("forest1.Y", loc.getY());
				file.set("forest1.Z", loc.getZ());
			}else if(which == 2) {
				file.set("forest2.X", loc.getX());
				file.set("forest2.Y", loc.getY());
				file.set("forest2.Z", loc.getZ());
			}else {
				return;
			}
		}
		if(type == spawnType.CRAG) {
			if(which == 1) {
				file.set("crag1.X", loc.getX());
				file.set("crag1.Y", loc.getY());
				file.set("crag1.Z", loc.getZ());
			}else if(which == 2) {
				file.set("crag2.X", loc.getX());
				file.set("crag2.Y", loc.getY());
				file.set("crag2.Z", loc.getZ());
			}else {
				return;
			}
		}
		if(type == spawnType.RUINS) {
			if(which == 1) {
				file.set("ruins1.X", loc.getX());
				file.set("ruins1.Y", loc.getY());
				file.set("ruins1.Z", loc.getZ());
			}else if(which == 2) {
				file.set("ruins2.X", loc.getX());
				file.set("ruins2.Y", loc.getY());
				file.set("ruins2.Z", loc.getZ());
			}else {
				return;
			}
		}
		if(type == spawnType.MINE) {
			if(which == 1) {
				file.set("mine1.X", loc.getX());
				file.set("mine1.Y", loc.getY());
				file.set("mine1.Z", loc.getZ());
			}else if(which == 2) {
				file.set("mine2.X", loc.getX());
				file.set("mine2.Y", loc.getY());
				file.set("mine2.Z", loc.getZ());
			}else {
				return;
			}
		}
	}
	 
	private static double getRelativeCoord(int i) {
	    double d = i;
	    d = d < 0 ? d - .5 : d + .5;
	    return d;
	}

}
