/*    */ package de.warsteiner.ultimatejobs.utils;
/*    */ 
/*    */ import com.sk89q.worldedit.bukkit.BukkitWorld;
/*    */ import com.sk89q.worldedit.extent.Extent;
/*    */ import com.sk89q.worldedit.util.Location;
/*    */ import com.sk89q.worldedit.world.World;
/*    */ import com.sk89q.worldguard.WorldGuard;
/*    */ import com.sk89q.worldguard.protection.flags.Flag;
/*    */ import com.sk89q.worldguard.protection.flags.StateFlag;
/*    */ import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
/*    */ import com.sk89q.worldguard.protection.managers.RegionManager;
/*    */ import com.sk89q.worldguard.protection.regions.ProtectedRegion;
/*    */ import com.sk89q.worldguard.protection.regions.RegionContainer;
/*    */ import com.sk89q.worldguard.protection.regions.RegionQuery;

import de.warsteiner.ultimatejobs.UltimateJobs;

/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WG
/*    */ {
/*    */   public static void load() {
/* 24 */     System.out.println("§4§lLoading WorldGuard Support...");
/*    */     
/* 26 */     FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
/*    */     
/* 28 */     for (String b : UltimateJobs.getFlags()) {
/* 29 */       StateFlag flag = new StateFlag(b, true);
/*    */       
/* 31 */       registry.register((Flag)flag);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean checkFlag(org.bukkit.Location location, String flag, Player p) {
/* 40 */     int priority = -1;
/* 41 */     WorldGuard instance = WorldGuard.getInstance();
/* 42 */     RegionContainer container = instance.getPlatform().getRegionContainer();
/* 43 */     BukkitWorld bukkitWorld = new BukkitWorld(p.getWorld());
/* 44 */     RegionManager regions = container.get((World)bukkitWorld);
/* 45 */     RegionQuery query = container.createQuery();
/* 46 */     Location wLoc = new Location((Extent)bukkitWorld, location.getX(), location.getY(), location.getZ());
/* 47 */     ProtectedRegion selected = null;
/* 48 */     for (ProtectedRegion r : query.getApplicableRegions(wLoc)) {
/* 49 */       if (r.getPriority() > priority) {
/* 50 */         priority = r.getPriority();
/* 51 */         selected = r;
/*    */       } 
/*    */     } 
/* 54 */     if (selected == null)
/* 55 */       selected = regions.getRegion("__global__"); 
/* 56 */     String state = "ALLOW";
/* 57 */     if (selected != null)
/* 58 */       for (Flag<?> a : (Iterable<Flag<?>>)selected.getFlags().keySet()) {
/* 59 */         if (a != null && 
/* 60 */           a.getName().equals(flag)) {
/* 61 */           state = selected.getFlags().get(a).toString();
/*    */           break;
/*    */         } 
/*    */       }  
/* 65 */   
/* 67 */     if (state.equals("ALLOW"))  {
	  return true; 
}
/* 68 */      
/* 69 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\hypas\Desktop\UltimateJobs.jar!\de\warsteine\\ultimatejobs\WG.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */