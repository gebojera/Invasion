package sylaires.invasion.listeners;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import sylaires.invasion.main.Game.GameState;
import sylaires.invasion.main.Locations;
import sylaires.invasion.main.Main;
import sylaires.invasion.main.Scoreboards;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class MiscListeners implements Listener{
	
	@EventHandler
	public void onDmg(EntityDamageEvent e) {
		if(e.getCause() == DamageCause.FALL || e.getCause() == DamageCause.DROWNING) {
			if(e.getEntity() instanceof Player) {
				e.setCancelled(true);
			}
		}
		if(e.getCause() == DamageCause.VOID) {
			if(e.getEntity() instanceof Player) {
				e.setCancelled(true);
				Player p = (Player) e.getEntity();
				if(Main.getGame().getState() == GameState.INGAME) {
					p.teleport(Locations.getSpawn());
				}else {
					p.teleport(Locations.getLobby());
				}
			}
		}
		if(e.getEntity() instanceof Player) {
			if(Main.getGame().getState() != GameState.INGAME && Main.getGame().getState() != GameState.ENDING) {
			//	e.setCancelled(true);
			}else if(Main.getGame().getState() == GameState.ENDING) {
				Player p = (Player) e.getEntity();
				p.setHealth(p.getMaxHealth());
			}
		}
		
		
	}
	
	@EventHandler
	public void onGrav(EntityChangeBlockEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + ChatColor.AQUA + " has joined");
		e.getPlayer().setFoodLevel(20);
		e.getPlayer().setGameMode(GameMode.ADVENTURE);
		if(Main.getGame().getState() == GameState.INGAME) {
			e.getPlayer().teleport(Locations.getSpawn());
		}else {
			e.getPlayer().teleport(Locations.getLobby());
		}
		MobDrops.heldEssences.put(e.getPlayer(), 0);
		Scoreboards.update(e.getPlayer());
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		Bukkit.broadcastMessage(ChatColor.YELLOW + e.getPlayer().getName() + ":" + ChatColor.WHITE + " " + e.getMessage());
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + ChatColor.GRAY + " has left");
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(!e.getPlayer().isOp()) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(!e.getPlayer().isOp()) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void manipulate(PlayerArmorStandManipulateEvent e)
	{
		
	        if(!e.getRightClicked().isVisible())
	        {
	            e.setCancelled(true);
	        }
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			e.setCancelled(true);
		}
		if(e.getDamager() instanceof Arrow) {
			Arrow a = (Arrow) e.getDamager();
			if(e.getEntity() instanceof LivingEntity) {
				LivingEntity le = (LivingEntity) e.getEntity();
				le.damage(0.000001, (Entity) a.getShooter());
				
			}
		}
	}
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockExplode(EntityExplodeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onBreakDoor(EntityBreakDoorEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onCombust(BlockIgniteEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onEntityCombust(EntityCombustEvent e) {
		if(Main.getGame().getState() != GameState.INGAME) {
			e.setCancelled(true);
		}else if(e.getEntity() instanceof ArmorStand) {
			e.setCancelled(true);
		}
	}

}
