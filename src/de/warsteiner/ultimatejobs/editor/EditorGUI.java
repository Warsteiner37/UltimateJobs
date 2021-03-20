package de.warsteiner.ultimatejobs.editor;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class EditorGUI {

	public static Inventory load(final Player p) {
		 
		/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * 6,
				"§bUltimateJobs §8: §cEdit");
		 
		Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
		 
		 
				if(inv.getItem(10) == null) {
					ItemStack d = new ItemStack(Material.GRAY_DYE,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7Chat§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bJob Chat Module");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(10, d);
				}
				
				if(inv.getItem(11) == null) {
					ItemStack d = new ItemStack(Material.STICK,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7Command§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bJob Command");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(11, d);
				}
				
				if(inv.getItem(12) == null) {
					ItemStack d = new ItemStack(Material.PAPER,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7Main Config§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bEdit important settings");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(12, d);
				}
			 
				if(inv.getItem(13) == null) {
					ItemStack d = new ItemStack(Material.GREEN_DYE,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7Jobs Config§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bEdit the Jobs");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(13, d);
				}
				
				if(inv.getItem(14) == null) {
					ItemStack d = new ItemStack(Material.BEACON,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7LevelGUI§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bLevelGUI Module");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(14, d);
				}
				
				if(inv.getItem(15) == null) {
					ItemStack d = new ItemStack(Material.EXPERIENCE_BOTTLE,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7Level Config§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bEdit the Levels");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(15, d);
				}
				
				if(inv.getItem(16) == null) {
					ItemStack d = new ItemStack(Material.BOOK,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7MainGUI§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bEdit the Job GUI");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(16, d);
				}
 
				
				if(inv.getItem(19) == null) {
					ItemStack d = new ItemStack(Material.PLAYER_HEAD,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7Messages§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bEdit the Messages");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(19, d);
				}
				
				if(inv.getItem(20) == null) {
					ItemStack d = new ItemStack(Material.COOKIE,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7PlaceHolders§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bEdit the PlaceHolders");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(20, d);
				}	
				if(inv.getItem(21) == null) {
					ItemStack d = new ItemStack(Material.SHEARS,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7Quests§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bQuests Module");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(21, d);
				}
				if(inv.getItem(22) == null) {
					ItemStack d = new ItemStack(Material.GOLD_NUGGET,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7RewardHandler§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bEdit the Rewardhandler");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(22, d);
				}
				if(inv.getItem(23) == null) {
					ItemStack d = new ItemStack(Material.BONE,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7Skills§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bSkills Module");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(23, d);
				}
						 
				if(inv.getItem(24) == null) {
					ItemStack d = new ItemStack(Material.NOTE_BLOCK,1);
					/* 42 */ ItemMeta dm = d.getItemMeta();
					/* 43 */ dm.setDisplayName("§8< §8(§7Sounds§8) §8>");
					ArrayList<String> dore = new ArrayList<String>();
					 
						dore.add("§bSounds Module");
			 
					dm.setLore(dore);
					
					/* 44 */ d.setItemMeta(dm);
					/*     */
							inv.setItem(24, d);
				}
				 
					if(inv.getItem(25) == null) {
						ItemStack d = new ItemStack(Material.RAIL,1);
						/* 42 */ ItemMeta dm = d.getItemMeta();
						/* 43 */ dm.setDisplayName("§8< §8(§7TopGUI§8) §8>");
						ArrayList<String> dore = new ArrayList<String>();
						 
							dore.add("§bTopGUI Module");
				 
						dm.setLore(dore);
						
						/* 44 */ d.setItemMeta(dm);
						/*     */
								inv.setItem(25, d);
					}
								ItemStack d1 = new ItemStack(Material.BARRIER,1);
								/* 42 */ ItemMeta dm1 = d1.getItemMeta();
								/* 43 */ dm1.setDisplayName("§8< §cGo Back §8>");
								ArrayList<String> d1ore = new ArrayList<String>();
								d1ore.add("§8-> §bGo back to main page");
								dm1.setLore(d1ore);
								
								/* 44 */ d1.setItemMeta(dm1);
								/*     */
										inv.setItem(49, d1);
 
		 
					/* 41 */ ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
					/* 42 */ ItemMeta meta = item.getItemMeta();
					/* 43 */ meta.setDisplayName("§c ");
					/* 44 */ item.setItemMeta(meta);
					/*     */
 
							inv.setItem(36, item);
							inv.setItem(37, item);
							inv.setItem(38, item);
							inv.setItem(39, item);
							inv.setItem(40, item);
							inv.setItem(41, item);
							inv.setItem(42, item);
							inv.setItem(43, item);
							inv.setItem(44, item);
			}  
		});
		return inv;

		}
 
}
 