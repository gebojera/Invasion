package sylaires.invasion.enchanting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentSwordHeavy implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Crush I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict slowness I (10s)");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "and weakness I (15s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Crush II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict slowness I (15s)");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "and weakness I (20s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Crush III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict slowness II (20s)");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "and weakness II (20s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Crush IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict slowness II (30s)");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "and weakness II (30s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Crush V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Attacks with this sword inflict slowness III (30s)");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "and weakness III (30s)");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getLore() != null) {
							if(e.getEntity() instanceof LivingEntity && !(e.getEntity() instanceof Player)) {
								LivingEntity le = (LivingEntity) e.getEntity();
								if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Crush I")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0, true));
									le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 0, true));
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Crush II")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 0, true));
									le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 0, true));
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Crush III")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 1, true));
									le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 1, true));
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Crush IV")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 1, true));
									le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 1, true));
								}else if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Crush V")) {
									le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 2, true));
									le.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 2, true));
								}
							}
						}
					}
				}
			}
		}
	}

}
