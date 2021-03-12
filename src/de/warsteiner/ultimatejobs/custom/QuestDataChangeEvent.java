package de.warsteiner.ultimatejobs.custom;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class QuestDataChangeEvent extends Event
/*    */ {
/* 13 */   private static HandlerList list = new HandlerList();
/*    */   
/*    */   public UUID id;
/*    */   
/*    */   public Player p;
			public String job;
			public int quest;
			public String questname;
/*    */
/*    */   public QuestDataChangeEvent(Player p, String job, int quest, String questname) {
/* 22 */     this.p = p;
this.questname = questname;
			this.job = job;
			this.quest = quest;
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