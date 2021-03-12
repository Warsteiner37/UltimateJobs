package de.warsteiner.ultimatejobs.custom;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class QuestCompleteEvent extends Event
/*    */ {
/* 13 */   private static HandlerList list = new HandlerList();
/*    */   
/*    */   public UUID id;
/*    */   
/*    */   public Player p;
/*    */
/*    */   public QuestCompleteEvent(Player p) {
/* 22 */     this.p = p;
/* 24 */     Bukkit.getPluginManager().callEvent(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public HandlerList getHandlers() {
/* 29 */     return list;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 33 */     return list;
/*    */   }
}