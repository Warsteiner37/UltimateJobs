package de.warsteiner.ultimatejobs.editor.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.warsteiner.ultimatejobs.UltimateJobs;

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
	 /*     */     
	 /*  53 */     Player p = (Player)e.getWhoClicked();

	 				 String title = "§bUltimateJobs §8: §c"+UltimateJobs.getPlugin().getDescription().getVersion();
	 				
	 /*  54 */     if (e.getView().getTitle().equalsIgnoreCase(title)) {
		 				e.setCancelled(true);
		 				String dis = e.getCurrentItem().getItemMeta().getDisplayName();
		 				if(dis.equalsIgnoreCase("§8< §7Manage Players §8>")) {
		 					p.sendMessage("§cThis feature is already in work!");
		 				} else if(dis.equalsIgnoreCase("§8< §7Plugin Reload §8>")) {
		 					p.sendMessage("§cThis feature is already in work!");
		 				} else if(dis.equalsIgnoreCase("§8< §7Open Settings §8>")) {
		 					p.sendMessage("§cThis feature is already in work!");
		 				}
	 }
	 }


}
