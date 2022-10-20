package sylaires.invasion.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import sylaires.invasion.command.BrowseCommand;
import sylaires.invasion.command.EssenceCommand;
import sylaires.invasion.command.GameCommand;
import sylaires.invasion.command.SetlocCommand;
import sylaires.invasion.command.SpawnCommand;
import sylaires.invasion.command.UpdateCommand;
import sylaires.invasion.crucible.CrucibleRegistry;
import sylaires.invasion.enchanting.BindingTable;
import sylaires.invasion.enchanting.EnchantmentAntidote;
import sylaires.invasion.enchanting.EnchantmentArmorDurable;
import sylaires.invasion.enchanting.EnchantmentAssassinate;
import sylaires.invasion.enchanting.EnchantmentBastion;
import sylaires.invasion.enchanting.EnchantmentBeheading;
import sylaires.invasion.enchanting.EnchantmentBloodPact;
import sylaires.invasion.enchanting.EnchantmentBrutalize;
import sylaires.invasion.enchanting.EnchantmentCavedweller;
import sylaires.invasion.enchanting.EnchantmentCrusadersSmite;
import sylaires.invasion.enchanting.EnchantmentDivineIntervention;
import sylaires.invasion.enchanting.EnchantmentEnder;
import sylaires.invasion.enchanting.EnchantmentErupt;
import sylaires.invasion.enchanting.EnchantmentFireProt;
import sylaires.invasion.enchanting.EnchantmentGodhood;
import sylaires.invasion.enchanting.EnchantmentGrapple;
import sylaires.invasion.enchanting.EnchantmentHearts;
import sylaires.invasion.enchanting.EnchantmentHoming;
import sylaires.invasion.enchanting.EnchantmentInstinct;
import sylaires.invasion.enchanting.EnchantmentLaunch;
import sylaires.invasion.enchanting.EnchantmentMassacre;
import sylaires.invasion.enchanting.EnchantmentMedic;
import sylaires.invasion.enchanting.EnchantmentMissle;
import sylaires.invasion.enchanting.EnchantmentNether;
import sylaires.invasion.enchanting.EnchantmentNetheric;
import sylaires.invasion.enchanting.EnchantmentObliterate;
import sylaires.invasion.enchanting.EnchantmentParry;
import sylaires.invasion.enchanting.EnchantmentPhoenix;
import sylaires.invasion.enchanting.EnchantmentReflectivePlate;
import sylaires.invasion.enchanting.EnchantmentRegen;
import sylaires.invasion.enchanting.EnchantmentRepulsion;
import sylaires.invasion.enchanting.EnchantmentRobin;
import sylaires.invasion.enchanting.EnchantmentSanguinare;
import sylaires.invasion.enchanting.EnchantmentScavenger;
import sylaires.invasion.enchanting.EnchantmentShadowform;
import sylaires.invasion.enchanting.EnchantmentSharpness;
import sylaires.invasion.enchanting.EnchantmentShatter;
import sylaires.invasion.enchanting.EnchantmentSmite;
import sylaires.invasion.enchanting.EnchantmentSniper;
import sylaires.invasion.enchanting.EnchantmentStun;
import sylaires.invasion.enchanting.EnchantmentSturdyMail;
import sylaires.invasion.enchanting.EnchantmentSwordDurable;
import sylaires.invasion.enchanting.EnchantmentSwordFiery;
import sylaires.invasion.enchanting.EnchantmentSwordHeavy;
import sylaires.invasion.enchanting.EnchantmentSwordMarine;
import sylaires.invasion.enchanting.EnchantmentTerrabeam;
import sylaires.invasion.enchanting.EnchantmentThorns;
import sylaires.invasion.enchanting.EnchantmentTrueEdge;
import sylaires.invasion.enchanting.EnchantmentTrueShot;
import sylaires.invasion.enchanting.EnchantmentUnassailable;
import sylaires.invasion.enchanting.EnchantmentVampiric;
import sylaires.invasion.enchanting.EnchantmentWealthy;
import sylaires.invasion.enchanting.EnchantmentWildAndFree;
import sylaires.invasion.enchanting.EnchantmentWither;
import sylaires.invasion.enemy.CustomTarget;
import sylaires.invasion.enemy.EnemyAttackEffects;
import sylaires.invasion.enemy.EnemyCragRamCharge;
import sylaires.invasion.enemy.EnemyDruidCurse;
import sylaires.invasion.enemy.EnemyGhostlyMinerInvis;
import sylaires.invasion.enemy.EnemyRegistry;
import sylaires.invasion.enemy.EnemyWhiteWalkerAura;
import sylaires.invasion.enemy.EnemyWhiteWalkerTrueDmg;
import sylaires.invasion.enemy.SpawnRenderer;
import sylaires.invasion.item.JockeysBow;
import sylaires.invasion.listeners.CraftingStation;
import sylaires.invasion.listeners.MiscListeners;
import sylaires.invasion.listeners.MobDrops;
import sylaires.invasion.listeners.MobTouchDamage;
import sylaires.invasion.listeners.NexusListener;
import sylaires.invasion.listeners.PlayerDeath;
import sylaires.invasion.main.Game.GameState;
import sylaires.invasion.util.Hologram;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class Main extends JavaPlugin {
	
	private static String version;
	private static Game game;
	
	public static Plugin getPlugin() {
		return Bukkit.getPluginManager().getPlugin("Invasion");
	}
	
	public static World getWorld() {
		return Bukkit.getWorld("world");
	}
	
	public static Game getGame() {
		return game;
	}
	
	public static String getVersion() {
		return version;
	}
	
	public void onEnable() {
		Main.version = this.getDescription().getVersion();
		cmds();
		listeners();
		Game game = new Game();
		Main.game = game;
		Locations.loadLocs();
		initMessages();
		EnemyRegistry.registerAll();
		CrucibleRegistry.registerRecipes();
		getWorld().setGameRuleValue("keepInventory", "true");
		
		Hologram holo = new Hologram(Locations.getForge(), "" + ChatColor.RED + ChatColor.BOLD + "The Forge");
		holo.addLine("Craft & Repair");
		holo.spawn();
		
		Hologram holo1 = new Hologram(Locations.getEnch(), "" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Binding Table");
		holo1.addLine("Enchant Items");
		holo1.spawn();
		
		Hologram holo2 = new Hologram(Locations.getCrucible(), "" + ChatColor.DARK_GREEN + ChatColor.BOLD + "The Crucible");
		holo2.addLine("Brew & Transmute");
		holo2.spawn();
		
		Hologram holo3 = new Hologram(Locations.getNexus(), "" + ChatColor.GOLD + ChatColor.BOLD + "The Nexus");
		holo3.addLine("Protect this at all costs!");
		holo3.spawn();
		
		EnchantmentSanguinare.run();
		EnchantmentHearts.run();
		EnchantmentRegen.run();
		Scoreboards.updateForAll();
	}
	
	public void onDisable() {
		for(Entity e : getWorld().getEntities()) {
			if(e.getCustomName() != null) {
				if(e instanceof ArmorStand) {
					e.remove();
				}
			}
		}
	}
	
	private void cmds() {
		getCommand("setloc").setExecutor(new SetlocCommand());
		getCommand("game").setExecutor(new GameCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("update").setExecutor(new UpdateCommand());
		getCommand("essence").setExecutor(new EssenceCommand());
		getCommand("peek").setExecutor(new BrowseCommand());
	}
	
	private void listeners() {
		Bukkit.getPluginManager().registerEvents(new MiscListeners(), this);
		Bukkit.getPluginManager().registerEvents(new MobTouchDamage(), this);
		Bukkit.getPluginManager().registerEvents(new MobDrops(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerDeath(), this);
		Bukkit.getPluginManager().registerEvents(new JockeysBow(), this);
		Bukkit.getPluginManager().registerEvents(new CraftingStation(), this);
		Bukkit.getPluginManager().registerEvents(new BindingTable(), this);
		Bukkit.getPluginManager().registerEvents(new NexusListener(), this);
		
		//Enchantments
		Bukkit.getPluginManager().registerEvents(new EnchantmentBeheading(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentMedic(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentNether(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentParry(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentSharpness(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentShatter(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentSmite(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentSwordDurable(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentSwordHeavy(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentSwordMarine(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentWealthy(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentRepulsion(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentWither(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentMassacre(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentScavenger(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentTrueEdge(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentSwordFiery(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentVampiric(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentLaunch(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentObliterate(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentCrusadersSmite(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentErupt(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentCavedweller(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentWildAndFree(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentBastion(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentUnassailable(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentFireProt(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentSturdyMail(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentArmorDurable(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentRegen(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentThorns(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentPhoenix(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentDivineIntervention(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentShadowform(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentGodhood(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentAntidote(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentReflectivePlate(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentHearts(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentTerrabeam(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentBrutalize(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentStun(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentBloodPact(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentAssassinate(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentInstinct(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentEnder(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentRobin(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentGrapple(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentSniper(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentNetheric(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentMissle(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentHoming(), this);
		Bukkit.getPluginManager().registerEvents(new EnchantmentTrueShot(), this);
		
		Bukkit.getPluginManager().registerEvents(new SpawnRenderer(), this);
		Bukkit.getPluginManager().registerEvents(new CustomTarget(), this);
		//Enemy abilities
		Bukkit.getPluginManager().registerEvents(new EnemyDruidCurse(), this);
		Bukkit.getPluginManager().registerEvents(new EnemyAttackEffects(), this);
		Bukkit.getPluginManager().registerEvents(new EnemyGhostlyMinerInvis(), this);
		Bukkit.getPluginManager().registerEvents(new EnemyCragRamCharge(), this);
		Bukkit.getPluginManager().registerEvents(new EnemyWhiteWalkerAura(), this);
		Bukkit.getPluginManager().registerEvents(new EnemyWhiteWalkerTrueDmg(), this);
	}
	
	private void initMessages() {
		
		new BukkitRunnable() {
			int waitingCounter = 60;
			@Override
			public void run() {
				if(game.getState() != GameState.TERMINATED) {
					if(game.getState() == GameState.WAITING) {
						waitingCounter--;
						if(waitingCounter == 0) {
							Messages.broadcast(ChatColor.AQUA + "Waiting to start!");
							waitingCounter = 60;
						}
					}
				}
			}
			
		}.runTaskTimer(this, 0, 20);
	}
	
	public static Vector getVectorForPoints(Location l1, Location l2) {
        double g = -0.08;
        double d = l2.distance(l1);
        double t = d;
        double vX = (1.0+0.07*t) * (l2.getX() - l1.getX())/t;
        double vY = (1.0+0.03*t) * (l2.getY() - l1.getY())/t - 0.5*g*t;
        double vZ = (1.0+0.07*t) * (l2.getZ() - l1.getZ())/t;
        return new Vector(vX, vY, vZ);
}
	
	public static Location faceLocation(Entity entity, Location to) {
        if (entity.getWorld() != to.getWorld()) {
            return null;
        }
        Location fromLocation = entity.getLocation();

        double xDiff = to.getX() - fromLocation.getX();
        double yDiff = to.getY() - fromLocation.getY();
        double zDiff = to.getZ() - fromLocation.getZ();

        double distanceXZ = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
        double distanceY = Math.sqrt(distanceXZ * distanceXZ + yDiff * yDiff);

        double yaw = Math.toDegrees(Math.acos(xDiff / distanceXZ));
        double pitch = Math.toDegrees(Math.acos(yDiff / distanceY)) - 90.0D;
        if (zDiff < 0.0D) {
            yaw += Math.abs(180.0D - yaw) * 2.0D;
        }
        Location loc = entity.getLocation();
        loc.setYaw((float) (yaw - 90.0F));
        loc.setPitch((float) (pitch - 90.0F));
        return loc;
    }

}
