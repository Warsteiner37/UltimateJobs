package de.warsteiner.ultimatejobs.skills;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
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

public class PlayerClickAtSkillsPerPlayer  implements Listener
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
}
/*  53 */     Player p = (Player)e.getWhoClicked();

				 
				
/*  54 */     if (e.getView().getTitle().equalsIgnoreCase(UltimateJobs.getPerPlayerSkillsConfig().getCustomConfig().getString("Design.Name").replaceAll("&", "§"))) {
/*  55 */       e.setCancelled(true);

FileConfiguration cfg = UltimateJobs.getPerPlayerSkillsConfig().getCustomConfig();
 
FileConfiguration m = UltimateJobs.MessageHandler().getCustomConfig();
/*     */       
/*  57 */       String displaynameofitem = e.getCurrentItem().getItemMeta().getDisplayName();
/*     */     
/*  59 */       Economy eco = UltimateJobs.getEconomy();

			 	JobAPI api = UltimateJobs.getJobAPI();
			 	
			 	String prefix = m.getString("Prefix").replaceAll("&", "§");
			 	
			 	List<String> skills = cfg.getStringList("Design.Skills");
			 	
			 	for(String a : skills) {
			 		
					 String[] action = cfg.getString("Items."+a+".Action").split(":");
					 
					 int current_level = UltimateJobs.getData().getSkillPointLevel(""+p.getUniqueId(), action[1]);
					 
					double multi = UltimateJobs.getSkillAPI().getMultiByName(action[1], ""+p.getUniqueId());
					
					Integer price =  Integer.valueOf(UltimateJobs.getSkillAPI().getNextLevelPrice(a, current_level+1));
					String next =  UltimateJobs.getSkillAPI().getNextLevelMulti(a, current_level+1);
					 
			 		String display = cfg.getString("Items."+a+".Display").replaceAll("<n>", ""+next).replaceAll("<price>", ""+price).replaceAll("<m>", ""+multi).replaceAll("<level>", ""+current_level).replaceAll("<name>", p.getName()).replaceAll("&", "§");
			 	
			 		if(displaynameofitem.equalsIgnoreCase(display)) {
			 			
			 		//	int lv = UltimateJobs.getData().getSkillPointLevel(""+p.getUniqueId(), "");
			 			
			 			if(UltimateJobs.getData().getSkillPoints(""+p.getUniqueId()) >= price) {
			 				int points = UltimateJobs.getData().getSkillPoints(""+p.getUniqueId());
			 				 UltimateJobs.getData().setSkillPoints(""+p.getUniqueId(), points-1);
			 		 
			 				 UltimateJobs.getData().setSkillPointLevel(""+p.getUniqueId(), action[1], current_level+1);
			 				 if(cfg.getBoolean("Design.Message_On_By")) {
			 					 p.sendMessage(prefix+cfg.getString("Design.M_Type").replaceAll("&", "§"));
			 				 }
			 				 
			 				 if(cfg.getBoolean("Design.Reopen_by_buy")) {
			 					p.openInventory(SkillsGUI_PerPlayer.load(p));
			 				 } else {
			 					 p.closeInventory();
			 				 }
			 				 
			 			} else {
			 				p.sendMessage(prefix+cfg.getString("Messages.Need").replaceAll("&", "§"));
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











