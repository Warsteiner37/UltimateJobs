package de.warsteiner.ultimatejobs.editor.events;

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
		 					p.openInventory(PlayerGUI.load(p, 0, 45, 1));
		 				} else if(dis.equalsIgnoreCase("§8< §7Plugin Reload §8>")) {
		 					p.openInventory(ReloadGUI.load(p));
		 				} else if(dis.equalsIgnoreCase("§8< §7Open Settings §8>")) {
		 					p.openInventory(EditorGUI.load(p));
		 				}
	 }
	 }


}
