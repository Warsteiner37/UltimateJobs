/*    */ package de.warsteiner.ultimatejobs.utils;
/*    */ 
/*    */ import org.bukkit.boss.BossBar;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ 
/*    */ public class BarCountdown
/*    */   extends BukkitRunnable
/*    */ {
/*    */   private final BossBar bar;
/*    */   private Integer time_left;
/*    */   private Integer total_time;
/*    */   
/*    */   public BarCountdown(BossBar b, int times) {
/* 14 */     this.bar = b;
/* 15 */     this.total_time = Integer.valueOf(times);
/* 16 */     this.time_left = Integer.valueOf(times);
/*    */   }
/*    */   
/*    */   public void run() {
/* 20 */     if (this.time_left.intValue() > 0) {
/* 21 */       this.bar.setProgress(this.time_left.doubleValue() / this.total_time.doubleValue());
/* 22 */       Integer integer1 = this.time_left, integer2 = this.time_left = Integer.valueOf(this.time_left.intValue() - 1);
/*    */     } else {
/* 24 */       cancel();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\hypas\Desktop\UltimateJobs.jar!\de\warsteine\\ultimatejob\\utils\BarCountdown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */