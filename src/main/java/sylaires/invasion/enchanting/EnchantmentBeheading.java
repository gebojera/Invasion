package sylaires.invasion.enchanting;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import sylaires.invasion.util.ItemStackUtil;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class EnchantmentBeheading implements Listener {
	
	public static void apply(ItemStack i, int level) {
		if(level == 1) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Beheading I");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing Zombies, Skeletons or Creepers has a " + ChatColor.RED + "20%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to award a special consumable");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 2) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Beheading II");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing Zombies, Skeletons or Creepers has a " + ChatColor.RED + "30%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to award a special consumable");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 3) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Beheading III");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing Zombies, Skeletons or Creepers has a " + ChatColor.RED + "40%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to award a special consumable");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 4) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Beheading IV");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing Zombies, Skeletons or Creepers has a " + ChatColor.RED + "50%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to award a special consumable");
			ItemStackUtil.addLoreToItem(i, " ");
		}else if(level == 5) {
			ItemStackUtil.addLoreToItem(i, ChatColor.YELLOW + "Beheading V");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "Killing Zombies, Skeletons or Creepers has a " + ChatColor.RED + "70%");
			ItemStackUtil.addLoreToItem(i, ChatColor.GRAY + "chance to award a special consumable");
			ItemStackUtil.addLoreToItem(i, " ");
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		if(e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity().getKiller();
			if(p.getItemInHand() != null) {
				if(p.getItemInHand().getType() == Material.DIAMOND_SWORD) {
					if(p.getItemInHand().getItemMeta().getDisplayName() != null) {
						if(p.getItemInHand().getItemMeta().getLore() != null) {
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Beheading I")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 20) {
									if(e.getEntity() instanceof Zombie) {
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Severed Zombie Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength I (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Creeper) {
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.GREEN + ChatColor.BOLD + "Severed Creeper Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "4 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.LIGHT_PURPLE + "Regeneration II (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Skeleton) {
										Skeleton s = (Skeleton) e.getEntity();
										if(s.getSkeletonType() == SkeletonType.WITHER) {
											p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Severed Wither Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "10 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength III (20s)");
											p.getInventory().addItem(head);
										}else {
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.GRAY + ChatColor.BOLD + "Severed Skeleton Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.AQUA + "Speed I (10s)");
											p.getInventory().addItem(head);
										}
									}
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Beheading II")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 30) {
									if(e.getEntity() instanceof Zombie) {
									
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Severed Zombie Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength I (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Creeper) {
										
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.GREEN + ChatColor.BOLD + "Severed Creeper Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "4 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.LIGHT_PURPLE + "Regeneration II (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Skeleton) {
										Skeleton s = (Skeleton) e.getEntity();
										if(s.getSkeletonType() == SkeletonType.WITHER) {
											
											p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Severed Wither Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "10 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength III (20s)");
											p.getInventory().addItem(head);
										}else {
											
											p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.GRAY + ChatColor.BOLD + "Severed Skeleton Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.AQUA + "Speed I (10s)");
											p.getInventory().addItem(head);
										}
									}
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Beheading III")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 40) {
									if(e.getEntity() instanceof Zombie) {
										
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Severed Zombie Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength I (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Creeper) {
										
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.GREEN + ChatColor.BOLD + "Severed Creeper Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "4 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.LIGHT_PURPLE + "Regeneration II (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Skeleton) {
										Skeleton s = (Skeleton) e.getEntity();
										if(s.getSkeletonType() == SkeletonType.WITHER) {
											
											p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Severed Wither Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "10 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength III (20s)");
											p.getInventory().addItem(head);
										}else {
											
											p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.GRAY + ChatColor.BOLD + "Severed Skeleton Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.AQUA + "Speed I (10s)");
											p.getInventory().addItem(head);
										}
									}
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Beheading IV")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 50) {
									if(e.getEntity() instanceof Zombie) {
										
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Severed Zombie Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength I (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Creeper) {
										
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.GREEN + ChatColor.BOLD + "Severed Creeper Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "4 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.LIGHT_PURPLE + "Regeneration II (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Skeleton) {
										Skeleton s = (Skeleton) e.getEntity();
										if(s.getSkeletonType() == SkeletonType.WITHER) {
											
											p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Severed Wither Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "10 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength III (20s)");
											p.getInventory().addItem(head);
										}else {
											
											p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.GRAY + ChatColor.BOLD + "Severed Skeleton Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.AQUA + "Speed I (10s)");
											p.getInventory().addItem(head);
										}
									}
								}
							}
							if(p.getItemInHand().getItemMeta().getLore().contains(ChatColor.YELLOW + "Beheading V")) {
								Random rand = new Random();
								int random = rand.nextInt(100);
								if(random <= 70) {
									if(e.getEntity() instanceof Zombie) {
										
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Severed Zombie Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength I (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Creeper) {
										
										p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
										ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
										ItemStackUtil.addNameToItem(head, "" + ChatColor.GREEN + ChatColor.BOLD + "Severed Creeper Head");
										ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "4 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.LIGHT_PURPLE + "Regeneration II (10s)");
										p.getInventory().addItem(head);
									}else if(e.getEntity() instanceof Skeleton) {
										Skeleton s = (Skeleton) e.getEntity();
										if(s.getSkeletonType() == SkeletonType.WITHER) {
											
											p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Severed Wither Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "10 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.RED + "Strength III (20s)");
											p.getInventory().addItem(head);
										}else {
											
											p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 20, 1.3f);
											ItemStack head = new ItemStack(Material.SKULL_ITEM, 1);
											ItemStackUtil.addNameToItem(head, "" + ChatColor.GRAY + ChatColor.BOLD + "Severed Skeleton Head");
											ItemStackUtil.addLoreToItem(head, ChatColor.YELLOW + "When Eaten: " + ChatColor.GOLD + "2 Absorbtion" + "❤" + ChatColor.GRAY + " and " + ChatColor.AQUA + "Speed I (10s)");
											p.getInventory().addItem(head);
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
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getItem() != null) {
				ItemStack i = e.getItem();
				if(i.getItemMeta() != null) {
					if(i.getItemMeta().getDisplayName() != null) {
						if(i.getType() == Material.SKULL_ITEM) {
							if(i.getItemMeta().getDisplayName().equals("" + ChatColor.DARK_GREEN + ChatColor.BOLD + "Severed Zombie Head")) { //Zombie
								if(i.getAmount() > 1) {
									i.setAmount(i.getAmount()-1);
									e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 0, true));
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0, true));
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EAT, 20, 1);
								}else {
									e.getPlayer().getInventory().remove(i);
									e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 0, true));
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0, true));
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EAT, 20, 1);
								}
							}else if(i.getItemMeta().getDisplayName().equals("" + ChatColor.GRAY + ChatColor.BOLD + "Severed Skeleton Head")) {
								if(i.getAmount() > 1) {
									i.setAmount(i.getAmount()-1);
									e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 0, true));
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0, true));
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EAT, 20, 1);
								}else {
									e.getPlayer().getInventory().remove(i);
									e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 0, true));
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0, true));
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EAT, 20, 1);
								}
							}else if(i.getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + ChatColor.BOLD + "Severed Creeper Head")) {
								if(i.getAmount() > 1) {
									i.setAmount(i.getAmount()-1);
									e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 1, true));
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, true));
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EAT, 20, 1);
								}else {
									e.getPlayer().getInventory().remove(i);
									e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 1, true));
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1, true));
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EAT, 20, 1);
								}
							}else if(i.getItemMeta().getDisplayName().equals("" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Severed Wither Head")) {
								if(i.getAmount() > 1) {
									i.setAmount(i.getAmount()-1);
									e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 4, true));
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 2, true));
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EAT, 20, 1);
								}else {
									e.getPlayer().getInventory().remove(i);
									e.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 6000, 4, true));
									e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 2, true));
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.EAT, 20, 1);
								}
							}
						} 
					}
				}
			}
		}
	}

}
