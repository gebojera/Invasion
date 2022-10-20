package sylaires.invasion.enchanting;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentGrapple implements Listener {
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Grappler");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Shoot a block or entity to pull yourself towards it");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	ArrayList<Arrow> shot = new ArrayList<Arrow>();
	
	@EventHandler
	public void onHit(ProjectileHitEvent e) {
		if(e.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) e.getEntity();
			if(a.getShooter() instanceof Player) {
				Player shooter = (Player) a.getShooter();
						if(shot.contains(a)) {
							Vector v = getVectorForPoints(shooter.getLocation(), a.getLocation());
							shooter.setVelocity(v);
							shooter.playSound(shooter.getLocation(), Sound.BAT_TAKEOFF, 10, 0.9f);
							shot.remove(a);
							a.remove();
				}
			}
		}
	}

	
	@EventHandler
	public void onShoot(ProjectileLaunchEvent e) {
		if(e.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) e.getEntity();
			if(a.getShooter() instanceof Player) {
				Player shooter = (Player) a.getShooter();
					if(shooter.getItemInHand().getItemMeta() != null) {
						if(shooter.getItemInHand().getItemMeta().getLore() != null) {
							if(shooter.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Grappler")) {
								if(!shooter.isSneaking()) {
									shot.add(a);
								}
							}
						}
					
				}
			}
		}
	}
	
	private Vector getVectorForPoints(Location l1, Location l2) {
        double g = -0.08;
        double d = l2.distance(l1);
        double t = d;
        double vX = (1.0+0.07*t) * (l2.getX() - l1.getX())/t;
        double vY = (1.0+0.03*t) * (l2.getY() - l1.getY())/t - 0.5*g*t;
        double vZ = (1.0+0.07*t) * (l2.getZ() - l1.getZ())/t;
        return new Vector(vX, vY, vZ);
}

}
