/*    */ package de.warsteiner.ultimatejobs.utils;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.entity.Player;

import de.warsteiner.ultimatejobs.UltimateJobs;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldManager
/*    */ {
/*    */   public static boolean canWork(Player p) {
/* 11 */   	String mode = UltimateJobs.getJobsConfig().getCustomConfig().getString("WorldMode");
			if(mode.equalsIgnoreCase("public")) {
				  List<String> worldname = UltimateJobs.getPlugin().getJobsConfig().getCustomConfig().getStringList("Enabled_Worlds");
				  /*    */     
				  /* 13 */     if (worldname.contains(p.getWorld().getName())) {
				  /* 14 */       return true;
				  /*    */     }
				  /* 16 */     return false;
				  /*    */   }
 
			  else {
				  List<String> worldname = UltimateJobs.getPlugin().getJobsConfig().getCustomConfig().getStringList(JobAPI.getCurrentJob(p.getUniqueId())+".Enabled_Worlds");
				  /*    */     
				  /* 13 */     if (worldname.contains(p.getWorld().getName())) {
				  /* 14 */       return true;
				  /*    */     }
				  /* 16 */     return false;
				  /*    */   }
			}
/*    */ }

 