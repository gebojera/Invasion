package sylaires.invasion.enchanting;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_8_R3.EntityLightning;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntityWeather;
import sylaires.invasion.main.Main;
import sylaires.invasion.util.ItemStackUtil;
import sylaires.invasion.util.ParticleUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentCrusadersSmite implements Listener {
	
	HashMap<Player, Integer> cooldown = new HashMap<Player, Integer>();
	
	public static void apply(ItemStack item) {
		ItemStackUtil.addLoreToItem(item, "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "LEGENDARY");
		ItemStackUtil.addLoreToItem(item, ChatColor.GOLD + "Crusader's Smite");
		ItemStackUtil.addLoreToItem(item, ChatColor.GRAY + "Destroys all undead and strikes other enemies with powerful lightning");
		ItemStackUtil.addLoreToItem(item, " ");
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getLore() != null) {
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.GOLD + "Crusader's Smite")) {
								if(!cooldown.containsKey(p)) {
									cooldown.put(p, 3);
									if(e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof ArmorStand)) {
										Entity entity = e.getEntity();
										LivingEntity le = (LivingEntity) e.getEntity();
										for(Player p1 : Bukkit.getOnlinePlayers()) {
											((CraftPlayer)p1).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityWeather(new EntityLightning(((CraftPlayer)p).getHandle().world, entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ())));
										}
										for(Player p1 : Bukkit.getOnlinePlayers()) {
											p1.playSound(entity.getLocation(), Sound.AMBIENCE_THUNDER, 30, 1);
											p1.playSound(entity.getLocation(), Sound.EXPLODE, 20, 1f);
										}
										le.setFireTicks(20*30);
										e.setDamage(e.getDamage()*2);
									}
									
									new BukkitRunnable() {

										@Override
										public void run() {
											if((cooldown.get(p) - 1) < 1) {
												cooldown.remove(p);
												this.cancel();
											}else {
												cooldown.put(p, cooldown.get(p)-1);
											}
											
										}
										
									}.runTaskTimer(Main.getPlugin(), 0, 20);
								}
								if(e.getEntity() instanceof Zombie || e.getEntity() instanceof Skeleton) {
									LivingEntity le = (LivingEntity) e.getEntity();
									e.setDamage(200000);
									ParticleUtil.playParticles(EnumParticle.SMOKE_NORMAL, le.getLocation(), 0.3, 0.1, 0.3, 0, 40);
								}
							}
						}
					}
				}
			}
		}
	}

}
