package sylaires.invasion.enchanting;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.enemy.EnemyPaladin;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentMissle implements Listener {
	
	ArrayList<Arrow> shot = new ArrayList<Arrow>();

	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Explosive");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Arrows explode on impact");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "dealing" + ChatColor.RED + " 50 " + ChatColor.GRAY + "extra damage");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "to all nearby");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	@EventHandler
	public void onShoot(ProjectileHitEvent e) {
		if(e.getEntity() instanceof Arrow) {
			Arrow a = (Arrow) e.getEntity();
			if(a.getShooter() instanceof Player) {
				Player shooter = (Player) a.getShooter();
						if(shot.contains(a)) {
							ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, a.getLocation(), 0.5, 0.5, 0.5, 0.02, 70);
							ParticleUtil.playParticles(EnumParticle.EXPLOSION_LARGE, a.getLocation(), 0.5, 0.5, 0.5, 0, 8);
							shooter.playSound(a.getLocation(), Sound.EXPLODE, 0.9f, 1.2f);
							for(Entity e1 : a.getNearbyEntities(5, 5, 5)) {
								if(e1 instanceof Player) {
									Player p = (Player) e1;
									p.playSound(a.getLocation(), Sound.EXPLODE, 1, 1.2f);
								}else if(e1 instanceof LivingEntity) {
									LivingEntity le = (LivingEntity) e1;
									le.damage(50, (Entity) a.getShooter());
									if(e1 instanceof EnemyPaladin) {
										
									}else {
										Vector vec = getVectorForPoints(a.getLocation(), le.getLocation());
										le.setVelocity(vec);
									}
								}
							}
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
				if(!shooter.isSneaking()) {
					if(shooter.getItemInHand().getItemMeta() != null) {
						if(shooter.getItemInHand().getItemMeta().getLore() != null) {
							if(shooter.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Explosive")) {
								shot.add(a);
									new BukkitRunnable() {
										@Override
										public void run() {
											if(!a.isDead()) {
												ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, a.getLocation(), 0, 0, 0, 0, 5);
											}else {
												this.cancel();
											}
										}
										
									}.runTaskTimer(Main.getPlugin(), 0, 1);
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
