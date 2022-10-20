package sylaires.invasion.listeners;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import sylaires.invasion.enemy.IBasicEnemy;
import sylaires.invasion.main.Main;
import sylaires.invasion.main.PlayerProfile;
import sylaires.invasion.main.Scoreboards;
import sylaires.invasion.main.TheNexus;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class MobDrops implements Listener {
	
	public static HashMap<Player, Integer> heldEssences = new HashMap<Player, Integer>();
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		Player p = e.getEntity().getKiller();
		e.getDrops().clear();
		e.setDroppedExp(0);
		if(heldEssences.get(e.getEntity().getKiller()) == null) {
			heldEssences.put(e.getEntity().getKiller(), 0);
		}
		
		net.minecraft.server.v1_8_R3.Entity en = ((CraftEntity)e.getEntity()).getHandle();
		
		if(p == null) {
			return;
		}
		
		if(en instanceof IBasicEnemy) {
			IBasicEnemy ibe = (IBasicEnemy) en;
			PlayerProfile prof = new PlayerProfile(p);
			double gold = ibe.getGold();
			
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getLore() != null) {
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wealthy I")) {
								gold += gold*0.1;
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wealthy II")) {
								gold += gold*0.2;
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wealthy III")) {
								gold += gold*0.3;
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wealthy IV")) {
								gold += gold*0.4;
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Wealthy V")) {
								gold += gold*0.5;
							}
						}
					}
				}
			}
			
			prof.setGold(prof.getGold()+gold);
			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 0.9f, 1.4f);
			
			if(!TheNexus.isDead()) {
				heldEssences.put(p, heldEssences.get(p)+ibe.getEssence());
				p.sendMessage(ChatColor.GOLD + "+" + ibe.getGold() + ChatColor.GREEN + " +" + ibe.getEssence());
			}else {
				p.sendMessage(ChatColor.GOLD + "+" + ibe.getGold());
			}
			
			Random rand = new Random();
			try {
				for(ItemStack i : ibe.getDrops().keySet()) {
					int random = rand.nextInt(100);
					if(random < ibe.getDrops().get(i)) {
						Main.getWorld().dropItem(e.getEntity().getLocation(), i);
					}
				}
			} catch(Exception e1) {
				
			}
		}
		
		
		Scoreboards.updateForAll();
	}

}
