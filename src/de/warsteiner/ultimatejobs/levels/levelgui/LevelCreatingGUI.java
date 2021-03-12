package de.warsteiner.ultimatejobs.levels.levelgui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.multi.MultiToolTipUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mysql.fabric.xmlrpc.base.Array;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.PlayerOpenLevelsGUI;
import de.warsteiner.ultimatejobs.levels.LevelAPI;
import de.warsteiner.ultimatejobs.utils.JobAPI;

public class LevelCreatingGUI {
	
public static Inventory load(Player p) {
	new PlayerOpenLevelsGUI(p);
	FileConfiguration cfg = UltimateJobs.getLevelGUI().getCustomConfig();
	
		String uuid = ""+p.getUniqueId();
		
		String job = JobAPI.getCurrentJob(p.getUniqueId());
		
		//int jobid = JobAPI.getJobActiveByID(p.getUniqueId());
		
		 String[] current_page_as_list = UltimateJobs.getData().getPage(uuid).split(":");
		
		 
	     String page = current_page_as_list[0].replaceAll("PAGE_", " ").replaceAll(" ", "");
		    String from = current_page_as_list[1].replaceAll("FROM_", " ").replaceAll(" ", "");
		      String to = current_page_as_list[2].replaceAll("UPTO_", " ").replaceAll(" ", "");
		 
		//	double lv = UltimateJobs.data.getExp(""+p.getUniqueId(), JobAPI.getJobActiveByID(p.getUniqueId()));
		  /*  51 */           //  int lvv = UltimateJobs.data.getLevel(""+p.getUniqueId(), JobAPI.getJobActiveByID(p.getUniqueId())) + 1;
		  /*  52 */         //    double i2 = lv;
		  /*  53 */           // double need = 
		  /*  54 */             
 
				  //	String page = current_page_as_list[0].replaceAll("PAGE_", " ").replaceAll(" ", "");
		
		  //	String from = current_page_as_list[1].replaceAll("FROM_", " ").replaceAll(" ", "");
		
		//	String to = current_page_as_list[2].replaceAll("UPTO_", " ").replaceAll(" ", "");
		
		//  String color_name = String.valueOf(JobAPI.color(JobAPI.getCurrentJob(p.getUniqueId()))) + JobAPI.name(jobid);
		
 
		Inventory inv = Bukkit.createInventory(null, 9*cfg.getInt("Design.Size"), cfg.getString("Design.Name").replaceAll("<page>", ""+page).replaceAll("<job>", JobAPI.JobNameWithColor(job)).replaceAll("&", "§"));
		 
		//	int page_as_int = Integer.valueOf(page);
		
		//	int start = Integer.valueOf(from);
		
	//	int end =  Integer.valueOf(to);
		
	 	Bukkit.getScheduler().runTaskAsynchronously(UltimateJobs.getPlugin(), new Runnable() {
 
	  
			@Override
			public void run() {

				List<String> list_of_placeholders = cfg.getStringList("Design.PlaceHolders");
				List<String> list_of_custom_Items = cfg.getStringList("Design.Items");
				
				 
				String exp =UltimateJobs.getLevelAPI().getFormatedExp(job, uuid);
				int a1 = UltimateJobs.getData().getLevel(""+p.getUniqueId(), job);
				double need = UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId());
				
				 for (int i = 0; i < list_of_custom_Items.size(); i++) {
					 
					 String b = list_of_custom_Items.get(i);
				 
					 boolean use = cfg.getBoolean("Items."+b+".Enabled");
					 
					 if(use == true) {
						 
			 
							 String mat = cfg.getString("Items."+b+".Material");
							 
							 String new_mat = null;
							 
							 if(Material.getMaterial(mat) == null) {
								 new_mat = mat.replaceAll("<player>", p.getName());
							 } else {
								 new_mat = mat;
							 }
							 
							 int slot = cfg.getInt("Items."+b+".Slot");
							 boolean lore = cfg.getBoolean("Items."+b+".Lore_Option");
							 boolean enchanted = cfg.getBoolean("Items."+b+".Enchanted");
							 
							 if(Material.getMaterial(mat) == null) {
								 
								 ItemStack item = generateSkull(new_mat);
								 
								 ItemMeta meta = item.getItemMeta();
								 
								 if(lore ) {
									 
									 List<String> a = cfg.getStringList("Items."+b+".Lore");
									 
									 ArrayList<String> l = new ArrayList<String>();
									 
									 for (int i2 = 0; i2 < a.size(); i2++) {
										 l.add(a.get(i2)
												.replaceAll("<job>", JobAPI.JobNameWithColor(job)) .replaceAll("<need>", ""+need)	 .replaceAll("<level>", ""+a1)	 .replaceAll("<exp>", exp).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
									 }
									 
									 meta.setLore(l);
									 
								 }
								 
								 meta.setDisplayName(cfg.getString("Items."+b+".Display") .replaceAll("<job>", JobAPI.JobNameWithColor(job)).replaceAll("<need>", ""+need)	 .replaceAll("<level>", ""+a1)	 .replaceAll("<exp>", exp).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
								 
								 if(enchanted) {
									 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
									 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
								 }
								 
								 item.setItemMeta(meta);
								 
							 
									 inv.setItem(slot, item);
							 
								  continue;
								 
							 } else {
								 
								 ItemStack item = new ItemStack(Material.valueOf(new_mat),1);
								 
								 ItemMeta meta = item.getItemMeta();
								 
								 if(lore ) {
									 
									 List<String> a = cfg.getStringList("Items."+b+".Lore");
									 
									 ArrayList<String> l = new ArrayList<String>();
									 
									 for (int i2 = 0; i2 < a.size(); i2++) {
										 l.add(a.get(i2) .replaceAll("<need>", ""+need)	.replaceAll("<job>", JobAPI.JobNameWithColor(job)) .replaceAll("<level>", ""+a1)	 .replaceAll("<exp>", exp).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
									 }
									 
									 meta.setLore(l);
									 
								 } 
								 
								 if(enchanted) {
									 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
									 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
								 }
								 
								 meta.setDisplayName(cfg.getString("Items."+b+".Display") .replaceAll("<job>", JobAPI.JobNameWithColor(job)).replaceAll("<need>", ""+need)	 .replaceAll("<level>", ""+a1)	 .replaceAll("<exp>", exp).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
								 
								 item.setItemMeta(meta);
								 
							 
									 inv.setItem(slot, item);
								 
								  continue;
								 
							 }
							 
							 
						 } 
				 }

				for(String pl : list_of_placeholders) {
					String[] t = pl.split(":");
		 
					Material material = Material.valueOf(t[0]);
					int slot = Integer.valueOf(t[1]);
					String display = t[2];
					
					ItemStack item = new ItemStack(material, 1);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(display.replaceAll("&", "§"));
					item.setItemMeta(meta);
					
					inv.setItem(slot, item); 

				}

				/*  52 */     int page_as_int = Integer.valueOf(page).intValue();
				/*  54 */     final int start = Integer.valueOf(from).intValue();
				/*  56 */     final int end = Integer.valueOf(to).intValue();
				
				 List<String> list = cfg.getStringList("Design.LevelSlots");
	 
				  int amk = 0;
				  int l = UltimateJobs.getData().getLevel(""+p.getUniqueId(), job);
				   
				  /* 175 */             for (int i = start; i < end; i++) {
				  /* 177 */               int level = i;
 
					String mat = null;
					String dis = null;
					List<String> lore = new ArrayList<String>();
					boolean enchant = false;
					
					int b = l+1;
					
					if(l >= level) {
						mat = cfg.getString("Items.LevelItems.Materials.Level_Reached");
						enchant = cfg.getBoolean("Items.LevelItems.Enchanted.Level_Reached");
						dis = cfg.getString("Items.LevelItems.Displays.Level_Reached").replaceAll("<level>", ""+i).replaceAll("&", "§");
						lore = cfg.getStringList("Items.LevelItems.Lore.Level_Reached");
					} else if(b == level) {
						mat = cfg.getString("Items.LevelItems.Materials.Working");
						enchant = cfg.getBoolean("Items.LevelItems.Enchanted.Working");
						dis = cfg.getString("Items.LevelItems.Displays.Working").replaceAll("<level>", ""+i).replaceAll("&", "§");
						lore = cfg.getStringList("Items.LevelItems.Lore.Working");
					} else {
						mat = cfg.getString("Items.LevelItems.Materials.Not_Reached");
						enchant = cfg.getBoolean("Items.LevelItems.Enchanted.Not_Reached");
						dis = cfg.getString("Items.LevelItems.Displays.Not_Reached").replaceAll("<level>", ""+i).replaceAll("&", "§");
						lore = cfg.getStringList("Items.LevelItems.Lore.Not_Reached");
					}  
				 
					
					ItemStack item = null;
					
					if(Material.getMaterial(mat) == null) {
						item = generateSkull(mat);
					} else {
						item = new ItemStack(Material.valueOf(mat),1);
					}
					
					ItemMeta meta = item.getItemMeta();
					
					meta.setDisplayName(dis);
					
					if(enchant) {
						meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
						meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
					}
					ArrayList<String> pp = new ArrayList<String>();
					for(String d : lore) {
					 
						pp.add(d
								.replaceAll("<need>", ""+need)	.replaceAll("<exp>", ""+exp)		.replaceAll("<level>", ""+i)	.replaceAll("&", "§"));
					}
					
					meta.setLore(pp);
					
					item.setItemMeta(meta);
					
					inv.setItem(Integer.valueOf(list.get(amk)), item);
					 
				  amk++;
				  
				  }
				
			}
	 	});
		return inv;
}

public static ItemStack generateSkull(String owner) {
	/* 156 */     ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
	/* 157 */     SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
	/* 158 */     skullMeta.setOwner(owner);
	/* 159 */     itemStack.setItemMeta((ItemMeta)skullMeta);
	/* 160 */     return itemStack;
	/*     */   }
				
}




































