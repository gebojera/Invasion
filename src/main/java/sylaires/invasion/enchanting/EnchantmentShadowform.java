package sylaires.invasion.enchanting;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.minecraft.server.v1_8_R3.EnumParticle;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentShadowform implements Listener {
	
	public static ArrayList<Player> shadowed = new ArrayList<Player>();
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Shadowform");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "You become invisible and invincible, but unable to do damage");
		ItemStackUtil.addLoreToItem(item, ChatColor.BLUE + "Sneak to toggle");
		ItemStackUtil.addLoreToItem(item, "  ");
	}
	
	@EventHandler
	public void onSneak(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if(p.getInventory().getChestplate() != null) {
			ItemStack chest = p.getInventory().getChestplate();
			if(chest.getItemMeta() != null) {
				if(chest.getItemMeta().getLore() != null) {
					if(chest.getItemMeta().getLore().contains(ChatColor.GOLD + "Shadowform")) {
						if(!p.isSneaking()) {
							for(Player p1 : Bukkit.getOnlinePlayers()) {
								p1.hidePlayer(p);
							}
							ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, p.getLocation(), 0.2, 0, 0.2, 0, 100);
							p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 30, 1.4f);
							p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 60000000, 0, true));
							p.setWalkSpeed(1);
							shadowed.add(p);
							for(Entity e1 : p.getNearbyEntities(32, 32, 32)) {
								if(e1 instanceof Creature) {
									Creature le = (Creature) e1;
									if(le.getTarget() == p) {
										le.setTarget(null);
									}
								}
							}
						}else if(p.isSneaking()){
							shadowed.remove(p);
							for(Player p1 : Bukkit.getOnlinePlayers()) {
								p1.showPlayer(p);
							}
							ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, p.getLocation(), 0.2, 0, 0.2, 0, 100);
							p.playSound(p.getLocation(), Sound.GHAST_FIREBALL, 30, 1.4f);
							p.removePotionEffect(PotionEffectType.INVISIBILITY);
							p.setWalkSpeed((float) 0.2);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onDmg(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(shadowed.contains(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onTarget(EntityTargetEvent e) {
		if(e.getTarget() instanceof Player) {
			Player p = (Player) e.getTarget();
			if(shadowed.contains(p)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(shadowed.contains(p)) {
				e.setCancelled(true);
			}
		}
	}

}
