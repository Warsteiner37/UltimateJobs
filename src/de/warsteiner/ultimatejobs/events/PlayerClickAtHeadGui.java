package de.warsteiner.ultimatejobs.events;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.command.SellHeadCommand;

public class PlayerClickAtHeadGui  implements Listener
/*     */ {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
	 	if (e.isCancelled()) {
		 return;
		 }
	 	if(e.canBuild() == false) {
	 		return;
	 	} 
	 	if(e.getItemInHand() == null) {
	 		return;
	 	}
	 	  ItemStack item = e.getPlayer().getItemInHand();
	 
	 	if(SellHeadCommand.isValid(item)) {
	 	 	 
	 		if(UltimateJobs.getHunterConfig().getCustomConfig().getBoolean("Skulls.Can_Place_Skulls") == false) {
	 		 
	 			e.setCancelled(true);
	 		}
	 	}
	 	
	}
	
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

}
}