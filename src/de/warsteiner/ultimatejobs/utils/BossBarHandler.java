/*    */ package de.warsteiner.ultimatejobs.utils;
/*    */ 
/*    */ import de.warsteiner.ultimatejobs.UltimateJobs;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.boss.BarColor;
/*    */ import org.bukkit.boss.BarStyle;
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BossBarHandler
/*    */ {
/*    */   private static UltimateJobs plugin;
/*    */   
/*    */   public BossBarHandler(UltimateJobs plugin) {
/* 19 */     BossBarHandler.plugin = plugin;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void sendBar(Player player, BarColor color, BarStyle style, int time, String message) {
/* 25 */     BossBar bar = Bukkit.createBossBar(message, color, style, new org.bukkit.boss.BarFlag[0]);
/* 26 */     bar.addPlayer(player);
/* 27 */     (new BarCountdown(bar, time)).runTaskTimer((Plugin)plugin, 0L, 20L);
/* 28 */     plugin.getServer().getScheduler().runTaskLater((Plugin)plugin, bar::removeAll, (time));
/*    */   }
/*    */   
/*    */ 
/*    */ }


/* Location:              C:\Users\hypas\Desktop\UltimateJobs.jar!\de\warsteine\\ultimatejob\\utils\BossBarHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */