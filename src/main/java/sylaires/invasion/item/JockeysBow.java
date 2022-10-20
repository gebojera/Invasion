package sylaires.invasion.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class JockeysBow implements Listener {
	
	@EventHandler
	public void onShoot(EntityShootBowEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(p.getItemInHand().getType() == Material.BOW) {
				if(p.getItemInHand().getItemMeta() != null) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.WHITE + "Jockey's Bow")) {
							e.setCancelled(true);
							@SuppressWarnings("deprecation")
							Arrow a = p.shootArrow();
							a.setVelocity(a.getVelocity().multiply(3.5));
							a.setCritical(true);
							a.setKnockbackStrength(2);
							p.playSound(p.getLocation(), Sound.ARROW_HIT, 20, 0.5F);
						}
					}
				}
			}
		}
	}

}
