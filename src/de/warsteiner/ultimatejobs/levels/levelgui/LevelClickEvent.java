package de.warsteiner.ultimatejobs.levels.levelgui;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.gui.JobsGUIManager;
 
import de.warsteiner.ultimatejobs.utils.JobAPI;
import net.milkbowl.vault.economy.Economy;

public class LevelClickEvent implements Listener {

	 
	/*     */   @EventHandler
	/*     */   public void onInvClick(InventoryClickEvent e) {
	/*  42 */     if (e.getClickedInventory() == null) {
	/*     */       return;
	/*     */     }
	/*  45 */     if (e.getCurrentItem() == null) {
	/*     */       return;
	/*     */     }
	/*     */     
	/*  49 */     if (e.getView().getTitle() == null) {
	/*     */       return;
	/*     */     }
	/*     */     	 
	 if(e.getCurrentItem().getItemMeta() == null) {
			return;
		}

		if(e.getCurrentItem().getItemMeta().getDisplayName() == null) {
			return;
		}
	/*  53 */     Player p = (Player)e.getWhoClicked();

				 if(!UltimateJobs.getData().existPlayerPageData(""+p.getUniqueId()))  {
					 return;
				 }
				 
				 String[] current_page_as_list = UltimateJobs.getData().getPage(""+p.getUniqueId()).split(":");
			 
				 /* 39 */       String page = current_page_as_list[0].replaceAll("PAGE_", " ").replaceAll(" ", "");
				 /* 41 */       String from = current_page_as_list[1].replaceAll("FROM_", " ").replaceAll(" ", "");
				 /* 43 */       String to = current_page_as_list[2].replaceAll("UPTO_", " ").replaceAll(" ", "");
 
	/*  54 */     if (e.getView().getTitle().equalsIgnoreCase(UltimateJobs.getLevelGUI().getCustomConfig().getString("Design.Name").replaceAll("<page>", ""+page).replaceAll("<job>", JobAPI.JobNameWithColor(JobAPI.getCurrentJob(p.getUniqueId()))).replaceAll("&", "§"))) {
	/*  55 */       e.setCancelled(true);

	FileConfiguration cfg = UltimateJobs.getLevelGUI().getCustomConfig();
	 
	FileConfiguration m = UltimateJobs.MessageHandler().getCustomConfig();
	/*     */       
	/*  57 */       String displaynameofitem = e.getCurrentItem().getItemMeta().getDisplayName();
	/*     */     
	/*  59 */       Economy eco = UltimateJobs.getEconomy();

				 	JobAPI api = UltimateJobs.getJobAPI();
				 	 List<String> list = cfg.getStringList("Design.LevelSlots");
				 	
				 	
				 	String prefix = m.getString("Prefix").replaceAll("&", "§");
				 	 
	 					List<String> custom_items = cfg.getStringList("Design.Items");
	 					
	 					for(String b : custom_items) {
	 						
	 						String dis = cfg.getString("Items."+b+".Display").replaceAll("<name>", p.getName()).replaceAll("&", "§");
	 						
	 						if(displaynameofitem.equalsIgnoreCase(dis)) {
	 							
	 							String[] action = cfg.getString("Items."+b+".Action").split(":");
	 						 
	 							String w = action[1];
	 							
	 							if(w.equalsIgnoreCase("CLOSE")) {
	 								p.closeInventory();
	 									
	 								return;
	 							}   else if(w.equalsIgnoreCase("COMMAND")) {
	 								
	 								String command = action[2];
	 								
	 								p.closeInventory();
	 								
	 								p.performCommand(command);
	 								
	 								return;
	 							} else  if(w.equalsIgnoreCase("MAINPAGE")) {
	 								JobsGUIManager.open(p);
	 								return;
	 							}  else if(w.equalsIgnoreCase("NOTHING")) {
	 								return;
	 							}   else if(w.equalsIgnoreCase("NEXTPAGE")) {
	 							   if (UltimateJobs.getJobsConfig().getCustomConfig().getStringList(JobAPI.getCurrentJob(p.getUniqueId())+".Levels").size() >= Integer.valueOf(to).intValue()) {
	 								  /* 58 */             int new_from = Integer.valueOf(from).intValue();
	 								 /* 59 */             int a2 = new_from + list.size();
	 								 /* 60 */             int new_to = Integer.valueOf(to).intValue();
	 								 /* 61 */             int a3 = new_to + list.size();
	 								 /* 62 */             int new_page = Integer.valueOf(page).intValue() + 1;
	 								 /* 64 */             String pg = "PAGE_" + new_page + ":FROM_" + a2 + ":UPTO_" + a3;
	 								 /* 66 */            UltimateJobs.getData().setPage(pg, ""+p.getUniqueId());
	 								 /* 68 */             p.openInventory(LevelCreatingGUI.load(p));
	 								   return;
	 							   } else {
	 								   String s = m.getString("Prefix") + cfg.getString("Messages.No_Next_Page");
	 								   p.sendMessage(s.replaceAll("&", "§"));
	 								   return;
	 							   }
	 							} else if(w.equalsIgnoreCase("BACKPAHE")) {
	 								if (Integer.valueOf(page).intValue() != 1) {
	 									/* 76 */             int new_from = Integer.valueOf(from).intValue();
	 									/* 77 */             int a2 = new_from - list.size();
	 									/* 78 */             int new_to = Integer.valueOf(to).intValue();
	 									/* 79 */             int a3 = new_to - list.size();
	 									/* 80 */             int new_page = Integer.valueOf(page).intValue() - 1;
	 									/* 82 */             String pg = "PAGE_" + new_page + ":FROM_" + a2 + ":UPTO_" + a3;
	 									/* 84 */             UltimateJobs.getData().setPage(pg, ""+p.getUniqueId());
	 									/* 86 */             p.openInventory(LevelCreatingGUI.load(p));
	 									return;
	 							 
	 								} else {
		 								   String s = m.getString("Prefix") + cfg.getString("Messages.No_Back_Page");
		 								   p.sendMessage(s.replaceAll("&", "§"));
		 								   return;
		 							   }
	 							}
	 							
	 						}
	 					}
	 					
	 				 
	/*     */       }
	/*  75 */       
	/*     */   }
	/*     */ }


