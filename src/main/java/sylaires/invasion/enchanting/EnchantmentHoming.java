package sylaires.invasion.enchanting;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentHoming implements Listener {
	
	ArrayList<Arrow> isHoming = new ArrayList<Arrow>();
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Homing");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Your arrows will seek out targets");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	@EventHandler
	public void onShoot(ProjectileLaunchEvent e) {
		
		if(e.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getEntity();
			if(arrow.getShooter() instanceof Player) {
				Player player = (Player) arrow.getShooter();
				if(player.getItemInHand().getItemMeta() != null) {
					if(player.getItemInHand().getItemMeta().getLore() != null) {
						if(player.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Homing")) {
							isHoming.add(arrow);
							new BukkitRunnable() {

								@Override
								public void run() {
									if(arrow.isOnGround() || arrow.isDead()) {
										arrow.remove();
										this.cancel();
									}
									for(Entity e1 : arrow.getNearbyEntities(7, 7, 7)) {
										if(e1 instanceof LivingEntity && !(e1 instanceof Player) && !(e1 instanceof ArmorStand)) {
											LivingEntity le = (LivingEntity) e1;
											Vector newdir = Main.getVectorForPoints(arrow.getLocation(), le.getLocation());
											arrow.setVelocity(newdir.multiply(0.75));
										}
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
