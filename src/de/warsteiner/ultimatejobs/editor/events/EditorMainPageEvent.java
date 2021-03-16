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

	 				 String title = "";
	 				
	 /*  54 */     if (e.getView().getTitle().equalsIgnoreCase(title)) {
		 
		 
	 }
	 }


}
