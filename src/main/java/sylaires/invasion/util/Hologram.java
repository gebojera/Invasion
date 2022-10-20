package sylaires.invasion.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class Hologram {
	
	private Location loc;
	private String name;
	private List<String> lines = new ArrayList<String>();
	private List<ArmorStand> stands = new ArrayList<ArmorStand>();
	
	public Hologram(Location loc, String name) {
		this.loc = loc;
		this.name = name;
	}
	
	public void addLine(String line) {
		lines.add(line);
	}
	
	public void spawn() {
		ArmorStand stand = loc.getWorld().spawn(loc, ArmorStand.class);
		stand.setCustomName(name);
		stand.setCustomNameVisible(true);
		stand.setVisible(false);
		stand.setCanPickupItems(false);
		stand.setGravity(false);
		for(String s : lines) {
			Location newloc = null;
			try {
				newloc = new Location(loc.getWorld(), loc.getX(), stands.get(stands.size()-1).getLocation().getY()-0.25, loc.getZ());
			} catch(Exception e) {
				newloc = new Location(loc.getWorld(), loc.getX(), loc.getY()-0.25, loc.getZ());
			}
			ArmorStand stand1 = loc.getWorld().spawn(newloc, ArmorStand.class);
			stand1.setCustomName(s);
			stand1.setCustomNameVisible(true);
			stand1.setVisible(false);
			stand1.setCanPickupItems(false);
			stand1.setGravity(false);
			stands.add(stand1);

			Bukkit.broadcastMessage("Spawned");
		}
	}
	
	public Location getLocation() {
		return this.loc;
	}

}
