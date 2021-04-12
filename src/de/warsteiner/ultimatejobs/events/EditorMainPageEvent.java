package de.warsteiner.ultimatejobs.events;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.editor.EditorGUI;
import de.warsteiner.ultimatejobs.editor.EditorMainGUI;
import de.warsteiner.ultimatejobs.editor.PlayerEdit;
import de.warsteiner.ultimatejobs.editor.PlayerGUI;
import de.warsteiner.ultimatejobs.editor.ReloadGUI;

public class EditorMainPageEvent implements Listener {
	
 
	 @EventHandler
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

	 				 String title = "§bUltimateJobs §8: §c"+UltimateJobs.getPlugin().getDescription().getVersion();
	 				 
	 				String reload = "§bUltimateJobs §8: §cPlugin Reload";
	 				String dis = e.getCurrentItem().getItemMeta().getDisplayName();
 
	 				
	 				
	 			 
	 				
	 				
	 				if (e.getView().getTitle().startsWith("§bUltimateJobs §8: §7Player §8: §4")) {
	 					e.setCancelled(true);
	 					
	 					String[] n2 = e.getView().getTitle().split(":");
	 					
	 					String name = n2[2].replaceAll("§4", " ").replaceAll(" ", "");
	 					
	 					if(dis.equalsIgnoreCase("§8< §bRemove Jobs §8>")) {
	 						p.sendMessage("§c§lThis feature is in work!");
	 					//	p.openInventory(PlayerEdit.Load_Job_RemovePage(p, name));
	 					}
	 					
	 					if(dis.equalsIgnoreCase("§8< §bManage Jobs §8>")) {
	 						p.sendMessage("§c§lThis feature is in work!");
	 					//	p.openInventory(PlayerEdit.Load_Job_RemovePage(p, name));
	 					}
	 					
	 					if(dis.equalsIgnoreCase("§8< §bAdd Jobs §8>")) {
	 						p.sendMessage("§c§lThis feature is in work!");
	 					//	p.openInventory(PlayerEdit.Load_Job_RemovePage(p, name));
	 					}
	 					
	 					if(dis.equalsIgnoreCase("§8< §cGo Back §8>")) {
	 						
	 						int latestpage = e.getCurrentItem().getAmount();
	 						
	 						 int n = latestpage;
	 							int next_page = 36*n;
	 							int from = next_page-36;
	 							
	 							p.openInventory(PlayerGUI.load(p, from, next_page, n));
	 						
	 					}
	 					
	 				}
	 				
	 				
	 				
	 				
	 				
	 				
	 				if (e.getView().getTitle().startsWith("§bUltimateJobs §8: §cPlayers §8: §a#")) {
	 					e.setCancelled(true);
	 					
	 					String[] page = e.getView().getTitle().split(":");
	 					
	 					String t = page[2].replaceAll("#", " ")
	 							.replaceAll("§a", "  ")
	 							.replaceAll(" ", "");
	 				 
	 					int g = Integer.valueOf(t);
	 				
	 					if(dis.contains("§8-")
	 							&& dis.contains("§8-")) {
	 						
	 						String name = dis.replaceAll("§7", "   ").replaceAll("§8", "   ").replaceAll("-", " ").replaceAll("-", " ").replaceAll(" ", "");
	 								
	 						if(UltimateJobs.getData().getUUIDByName(name) == null) {
	 							p.sendMessage("§cThat player doesnt exist!");
	 							return;
	 						} else {
	 							p.openInventory(PlayerEdit.load(p, name, g));
	 						}
	 					}
	 					
	 					if(dis.equalsIgnoreCase("§8< §7Next Page §8>")) {
	 						 int n = g+1;
	 							int next_page = 36*n;
	 							int from = next_page-36;
	 						if(UltimateJobs.getData().getPlayerList().size() >= from+1) {
	 						 
	 							
	 							p.openInventory(PlayerGUI.load(p, from, next_page, n));
	 							
	 						} else {
	 							p.sendMessage("§cThere is no next page!");
	 						}
	 					}
	 					
	 					if(dis.equalsIgnoreCase("§8< §cGo Back §8>")) {
	 						p.openInventory(EditorMainGUI.load(p));
	 					}
	 					
	 					if(dis.equalsIgnoreCase("§8< §7Go Back §8>")) {
	 						 int n = g-1;
	 						 int current = g*36;
	 						if(g != 1) {
	 						 
	 							int back_page = current-36;
	 					 
	 							int from = back_page-36;
	 						 
	 							
	 							p.openInventory(PlayerGUI.load(p, from, back_page, n));
	 							
	 						} else {
	 							p.sendMessage("§cThere is no other page!");
	 						}
	 					}
	 					
	 				}
	 				
	 				if (e.getView().getTitle().equalsIgnoreCase(reload)) {
		 				e.setCancelled(true);
		 				if(dis.equalsIgnoreCase("§8< §aYes §8>")) {
		 					p.closeInventory();
		 					p.sendMessage("§cReloading UltimateJobs... §8(§aWatch console§8)");
		 					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
							/*    */           
							  Bukkit.dispatchCommand((CommandSender)console, "plugman reload UltimateJobs");
		 				} else {
		 					if(dis.equalsIgnoreCase("§8< §cNo §8>")) {
		 						p.openInventory(EditorMainGUI.load(p));
		 					}
		 				}
	 				}
	 				
	 /*  54 */     if (e.getView().getTitle().equalsIgnoreCase(title)) {
		 				e.setCancelled(true);
		 			 
		 				if(dis.equalsIgnoreCase("§8< §7Manage Players §8>")) {
		 					p.openInventory(PlayerGUI.load(p, 0, 36, 1));
		 				} else if(dis.equalsIgnoreCase("§8< §7Plugin Reload §8>")) {
		 					p.openInventory(ReloadGUI.load(p));
		 				} else if(dis.equalsIgnoreCase("§8< §7Open Settings §8>")) {
		 					//p.openInventory(EditorGUI.load(p));
		 					p.sendMessage("§cThis feature is in work!");
		 				}
	 }
	 }


}
