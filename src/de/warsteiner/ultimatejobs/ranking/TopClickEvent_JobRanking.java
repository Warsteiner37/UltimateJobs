package de.warsteiner.ultimatejobs.ranking;

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

public class TopClickEvent_JobRanking implements Listener {

	 
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
	/*  53 */     Player p = (Player)e.getWhoClicked();

				 for(String jobs : JobAPI.getJobsAsConfigID()) {
					 
					 String title = UltimateJobs.getTopConfig().getCustomConfig().getString("Design_PerJob.Name").replaceAll("<job>", JobAPI.JobNameWithColor(jobs)).replaceAll("&", "§");
					 
					if(title.equalsIgnoreCase(e.getView().getTitle())) {
						 e.setCancelled(true);

							FileConfiguration cfg = UltimateJobs.getTopConfig().getCustomConfig();

						 
							/*  57 */       String displaynameofitem = e.getCurrentItem().getItemMeta().getDisplayName();
						 
							 					List<String> custom_items = cfg.getStringList("Design_PerJob.Items");
							 					
							 					for(String b : custom_items) {
							 						
							 						String dis = cfg.getString("Items_PerJob."+b+".Display").replaceAll("<name>", p.getName()).replaceAll("&", "§");
							 						
							 						if(displaynameofitem.equalsIgnoreCase(dis)) {
							 							
							 							String[] action = cfg.getString("Items_PerJob."+b+".Action").split(":");
							 						 
							 							String w = action[1];
							 							
							 							if(w.equalsIgnoreCase("CLOSE")) {
							 								p.closeInventory();
							 									
							 								return;
							 							}  else  if(w.equalsIgnoreCase("MAINPAGE")) {
							 								 Inventory inv = JobsGUIManager.load(p);
							 							       p.openInventory(inv);
							 								return;
							 							} else if(w.equalsIgnoreCase("COMMAND")) {
							 								
							 								String command = action[2];
							 								
							 								p.closeInventory();
							 								
							 								p.performCommand(command);
							 								
							 								return;
							 							}   else if(w.equalsIgnoreCase("NOTHING")) {
							 								return;
							 							}  
							 						} 
							 					}
							 					
							 				}
							/*     */       }
					}
					 
				 }
				   



















