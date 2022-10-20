package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentErupt implements Listener {
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Corpse Eruption");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Causes a massive explosion of fire damage upon killing an enemy");
		ItemStackUtil.addLoreToItem(item, "  ");
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity().getKiller();
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getLore() != null) {
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Corpse Eruption")) {
								ParticleUtil.playParticles(EnumParticle.LAVA, e.getEntity().getLocation(), 0.3, 0, 0.3, 2, 100);
								p.playSound(e.getEntity().getLocation(), Sound.EXPLODE, 20, 0.9f);
								for(Entity e1 : e.getEntity().getNearbyEntities(6, 6, 6)) {
									if(!(e1 instanceof Player) && !(e1 instanceof ArmorStand)) {
										if(e1 instanceof LivingEntity) {
											LivingEntity le = (LivingEntity) e1;
											le.damage(20.0, p);
											le.setFireTicks(20*60);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
