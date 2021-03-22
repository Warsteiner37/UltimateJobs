package de.warsteiner.ultimatejobs.skills;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.gui.JobsGUIManager;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import net.milkbowl.vault.economy.Economy;

public class PlayerClickAtSkillsPerJob  implements Listener
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
/*     */     	 
if(e.getCurrentItem().getItemMeta() == null) {
	return;
}

if(e.getCurrentItem().getItemMeta().getDisplayName() == null) {
	return;
}FileConfiguration cfg = UltimateJobs.getSkillsPerJob().getCustomConfig();

/*  53 */     Player p = (Player)e.getWhoClicked();
String job = JobAPI.getCurrentJob(p.getUniqueId());
				String title = cfg.getString("Design.Name").replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)).replaceAll("&", "§");
				 
				
/*  54 */     if (e.getView().getTitle().equalsIgnoreCase(title)) {
/*  55 */       e.setCancelled(true);

 
FileConfiguration m = UltimateJobs.MessageHandler().getCustomConfig();
/*     */       
/*  57 */       String displaynameofitem = e.getCurrentItem().getItemMeta().getDisplayName();
 
			 	String prefix = m.getString("Prefix").replaceAll("&", "§");
 
  
						 List<String> list = cfg.getStringList("SillJobs."+job+".Use");
						 
						 for (int i = 0; i < list.size(); i++) {
							 
							 String b = list.get(i);
 
								 String dis = cfg.getString("SillJobs."+job+"."+b+".Display").replaceAll("&", "§");
							 
								 if(displaynameofitem.equalsIgnoreCase(dis)) {
									 int i3 = i+1;
									 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, i3);
									 int points = UltimateJobs.getData().getSkillPointsOfJob(""+p.getUniqueId(), job);
									 List<String> l = cfg.getStringList("SillJobs."+job+"."+b+".Levels");
									 Integer price = Integer.valueOf(SkillsAPIForJobs.getNextLevelPrice(job, b, level+1));
								 //Skill_Max
									 if(level <= l.size()-1) {
										 if(points >= price) {
											 int newd = level+1;
											 UltimateJobs.getData().setSkilledLevelOfJob(""+p.getUniqueId(), job, i3, newd);
											 int newp = points-1;
											 UltimateJobs.getData().setSkillPointsOfJob(""+p.getUniqueId(), job, newp);
											 p.sendMessage(prefix+m.getString("Upgrade_Skill").replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)).replaceAll("&", "§"));
											 if(cfg.getString("Design.Mode").toUpperCase().equalsIgnoreCase("UPDATE")) {
												 SkillsGUI_PerJob.setSkillItems(p);
											 } else {
												 p.closeInventory();
											 }
											 return;
										 } else {
											 p.sendMessage(prefix+m.getString("Need_Points").replaceAll("&", "§"));
											 return;
										 }
									 } else {
										 p.sendMessage(prefix+m.getString("Skill_Max").replaceAll("&", "§"));
										 return;
									 }
									 
								 }
								 
						 }
			 	
			 
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
 							}  
 							
 						}
 					}
 					
 				 
/*     */       }
/*  75 */       
/*     */   }
/*     */ }




