package sylaires.invasion.enchanting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentSanguinare {
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Sanguinare");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "You absorb " + ChatColor.RED + "1❤" + ChatColor.GRAY + " health per second from those nearby");
		ItemStackUtil.addLoreToItem(item, "  ");
	}
	
	public static void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					if(p.getInventory().getChestplate() != null) {
						ItemStack chest = p.getInventory().getChestplate();
						if(chest.getItemMeta() != null) {
							if(chest.getItemMeta().getLore() != null) {
								if(chest.getItemMeta().getLore().contains(ChatColor.GOLD + "Sanguinare")) {
									for(Entity e : p.getNearbyEntities(5, 5, 5)) {
										if(e instanceof LivingEntity) {
											LivingEntity le = (LivingEntity) e;
											if(!(le instanceof Player)) {
												if(!(le instanceof ArmorStand)) {
													le.damage(2, p);
													if(p.getHealth()+2 > p.getMaxHealth()) {
														p.setHealth(p.getMaxHealth());
													}else {
														p.setHealth(p.getHealth()+2);
													}
													
													ParticleUtil.playParticles(EnumParticle.REDSTONE, le.getEyeLocation(), 0.3, 0.3, 0.3, 0, 50);
													ParticleUtil.playParticles(EnumParticle.REDSTONE, p.getLocation(), 0.3, 0.3, 0.3, 0, 30);
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
			} }, 0, 20);
	}

}
