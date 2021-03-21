package de.warsteiner.ultimatejobs.events;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.command.PlayerJobCommand;
import de.warsteiner.ultimatejobs.custom.PlayerAlreadyOwnJob;
import de.warsteiner.ultimatejobs.custom.PlayerBuyJob;
import de.warsteiner.ultimatejobs.custom.PlayerJobChange;
import de.warsteiner.ultimatejobs.gui.JobsGUIManager;
import de.warsteiner.ultimatejobs.levels.levelgui.LevelCreatingGUI;
import de.warsteiner.ultimatejobs.skills.SkillsGUI_PerJob;
import de.warsteiner.ultimatejobs.skills.SkillsGUI_PerPlayer;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import net.milkbowl.vault.economy.Economy;

public class PlayerClickEventAtMainGUI    implements Listener
/*     */ {
/*     */ 
/*     */   
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

if(e.getCurrentItem().getItemMeta() == null) {
	return;
}

if(e.getCurrentItem().getItemMeta().getDisplayName() == null) {
	return;
}
/*     */     
/*  53 */     Player p = (Player)e.getWhoClicked();

				 
				
/*  54 */     if (e.getView().getTitle().equalsIgnoreCase(UltimateJobs.getMainGUIConfig().getCustomConfig().getString("Options.Name").replaceAll("&", "§"))) {
/*  55 */       e.setCancelled(true);

FileConfiguration cfg = UltimateJobs.getMainGUIConfig().getCustomConfig();

FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();

FileConfiguration m = UltimateJobs.MessageHandler().getCustomConfig();
/*     */       
/*  57 */       String displaynameofitem = e.getCurrentItem().getItemMeta().getDisplayName();
/*     */       
/*  59 */       Economy eco = UltimateJobs.getEconomy();

			 	JobAPI api = UltimateJobs.getJobAPI();
			 	
			 	String prefix = m.getString("Prefix").replaceAll("&", "§");
 
				UUID uuid = p.getUniqueId();
 
 				if(JobAPI.IsJob(JobAPI.FromDisPlaynameToJobID(displaynameofitem))) {
 						
 					 String  org_id = JobAPI.FromDisPlaynameToJobID(displaynameofitem);
 					 
					 int price = jobs.getInt(org_id+".Price");
				 
					 if(!UltimateJobs.getData().hasJob(uuid, org_id)) {
						 
						 if (eco.getBalance((OfflinePlayer)p) >= price) {
						     eco.withdrawPlayer((OfflinePlayer)p, price);
							 p.sendMessage(prefix+m.getString("Bought").replaceAll("&", "§").replaceAll("<job>", displaynameofitem));
							 JobAPI.setJobBought(org_id, p, uuid);
							 
							 if(cfg.getBoolean("Options.Player_Gets_In_Job_If_Bought")) {
								 JobAPI.setallJobsNotActive(p, uuid);
									JobAPI.setJobActive(org_id, p, uuid);
							 }
							 
							 new PlayerBuyJob(p);
							 
							 if (cfg.getString("Options.On_Click_Mode").equalsIgnoreCase("UPDATE")) {
								 /*  69 */            JobsGUIManager.updateJobs(p, p.getOpenInventory());
								 /*     */         } else {
								 /*  71 */           p.closeInventory();
								 /*     */   }
								 /*     */             return;
								 /*     */          
							 
						 } else {
							 p.sendMessage(prefix+m.getString("Not_Eoungh_Money").replaceAll("&", "§"));
							 return;
						 }
							 
					 } else {
						 if(api.getCurrentJob(uuid).equalsIgnoreCase(org_id)) {
							 new PlayerAlreadyOwnJob(p);
							 if(cfg.getString("Options.PlayerIsInJobOption").equalsIgnoreCase("GUI")) {
								 
							 } else {
								 p.sendMessage(prefix+m.getString("Already").replaceAll("&", "§"));
							 }
							 return;
						 } else {
							JobAPI.setallJobsNotActive(p, uuid);
							JobAPI.setJobActive(org_id, p, uuid);
							new PlayerJobChange(p);
							 p.sendMessage(prefix+m.getString("Joined").replaceAll("&", "§").replaceAll("<job>", displaynameofitem));
							 if (cfg.getString("Options.On_Click_Mode").equalsIgnoreCase("UPDATE")) {
								 /*  69 */            JobsGUIManager.updateJobs(p, p.getOpenInventory());
								 /*     */         } else {
								 /*  71 */           p.closeInventory();
								 /*     */   }
								 /*     */             return;
						 }
					 }
 					
 				} else {
 					
 					List<String> custom_items = cfg.getStringList("Design.Used_Items");
 					
 					for(String b : custom_items) {
 						
 						String dis = cfg.getString("Items."+b+".Display").replaceAll("<name>", p.getName()).replaceAll("&", "§");
 						
 						if(displaynameofitem.equalsIgnoreCase(dis)) {
 							
 							String[] action = cfg.getString("Items."+b+".Action").split(":");
 						 
 							String w = action[1];
 							
 							if(w.equalsIgnoreCase("CLOSE")) {
 								p.closeInventory();
 									
 								return;
 							} else if(w.equalsIgnoreCase("LEAVE")) {
 									JobAPI.setallJobsNotActive(p, uuid);
 								 p.sendMessage(prefix+m.getString("Job_Leave").replaceAll("&", "§").replaceAll("<job>", displaynameofitem));
 								 if (cfg.getString("Options.On_Click_Mode").equalsIgnoreCase("UPDATE")) {
 									 /*  69 */                JobsGUIManager.updateJobs(p, p.getOpenInventory());
 									 JobsGUIManager.updateCustomItems(p,p.getOpenInventory());
 									 /*     */         } else {
 									 /*  71 */           p.closeInventory();
 									 /*     */   }
 								 return;
 							}  else if(w.equalsIgnoreCase("COMMAND")) {
 								
 								String command = action[2];
 								
 								p.closeInventory();
 								
 								p.performCommand(command);
 								
 								return;
 							} else  if(w.equalsIgnoreCase("LEVELS")) {
 								if(UltimateJobs.getLevelGUI().getCustomConfig().getBoolean("Enable_LevelGUI")) {
 									  if(UltimateJobs.getData().existPlayerPageData(""+uuid)) {
 	 					                   p.openInventory(LevelCreatingGUI.load(p));
 	 					                     return;
 	 					                 } else {

 	 					                	  int a = UltimateJobs.getLevelGUI().getCustomConfig().getStringList("Design.LevelSlots").size()+1;

 	 					                      String pg = "PAGE_1:FROM_1:UPTO_"+a;

 	 					                      UltimateJobs.getData().setPage(pg, ""+uuid);

 	 					                    p.openInventory(LevelCreatingGUI.load(p));
 	 					                   return;
 	 					                 }
 								} else {
 									p.sendMessage("§cThis Action is not enabled.");
 								}
 							    
 						 
 							}  else if(w.equalsIgnoreCase("NOTHING")) {
 								return;
 							} else if(w.equalsIgnoreCase("SKILLS")) {
 								if(UltimateJobs.getSkillsMainConfig().getCustomConfig().getBoolean("Enable_Skills")) {
 									 if(UltimateJobs.getSkillsMainConfig().getCustomConfig().getString("Mode").toUpperCase().equalsIgnoreCase("PER_PLAYER")) {
 									p.openInventory(SkillsGUI_PerPlayer.load(p));
 									 } else  if(UltimateJobs.getSkillsMainConfig().getCustomConfig().getString("Mode").toUpperCase().equalsIgnoreCase("PER_JOB")) {
 										if(JobAPI.getCurrentJob(p.getUniqueId()).equalsIgnoreCase("None")) {
 											p.sendMessage(m.getString("Prefix")+m.getString("No_Job").replaceAll("&", "§"));
 										} else {
 											 SkillsGUI_PerJob.open(p);
 										}
 									 }
 								} else {
 									p.sendMessage("§cThis Action is not enabled.");
 								}
 								return;
 							}   else if(w.equalsIgnoreCase("QUESTS")) {
 								 if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
 									 PlayerJobCommand.openQuest(p);
 									 return;
 								 } else {
  									p.sendMessage("§cThis Action is not enabled.");
  								}
 							}
 							
 						} 
 					}
 					
 				}
/*     */       }
/*  75 */       
/*     */   }
/*     */ }



















