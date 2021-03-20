/*    */ package de.warsteiner.ultimatejobs.utils;
/*    */ 
/*    */ import me.clip.placeholderapi.expansion.PlaceholderExpansion;
/*    */ import org.bukkit.entity.Player;

import de.warsteiner.ultimatejobs.UltimateJobs;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlaceHolder
/*    */   extends PlaceholderExpansion
/*    */ {
/*    */   public String getIdentifier() {
/* 14 */     return "Jobs";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPlugin() {
/* 19 */     return "UltimateJobs";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getAuthor() {
/* 24 */     return "Warsteiner37";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getVersion() {
/* 29 */     return "v1.0";
/*    */   }
/*    */ 
/*    */   
/*    */   public String onPlaceholderRequest(Player player, String identifier) {
/* 34 */     if (player == null || identifier == null) {
/* 35 */       return "PlayerIsNull";
/*    */     }
if (identifier.equalsIgnoreCase("level"))
/*    */     {
/* 39 */       return PlaceUtils.getPlayerLevelInCurrentJob(player.getUniqueId());
/*    */     }
if (identifier.equalsIgnoreCase("exp"))
/*    */     {
/* 39 */       return PlaceUtils.getPlayerExpinCurrentJob(player.getUniqueId());
/*    */     }
if (identifier.equalsIgnoreCase("quest"))
/*    */     {
/* 39 */       return UltimateJobs.getData().getRandomQuest(""+player.getUniqueId());
/*    */     }
			if (identifier.equalsIgnoreCase("skills"))
/*    */     {
/* 39 */       return PlaceUtils.SkillPointsPerMode(player.getUniqueId());
/*    */     }
			if (identifier.equalsIgnoreCase("job"))
/*    */     {
/* 39 */       return PlaceUtils.getPlayerJob(player.getUniqueId());
/*    */     }
			
			if (identifier.equalsIgnoreCase("job_none_color"))
			/*    */     {
			/* 39 */       return PlaceUtils.getPlayerJobWithoutColor(player.getUniqueId());
			/*    */     }
 
/* 49 */     return identifier;
/*    */   }
/*    */ }


 