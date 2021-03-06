package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;

public class Job_Farmer implements Listener {

	@EventHandler
	/*    */   public void onBreak(BlockBreakEvent e) {
	/* 14 */     if (e.isCancelled()) {
	/*    */       return;
	/*    */     }
	
	
	Block block = e.getBlock();
 	
	if(isFullyGrownOld(block) == false) {
		return;
	}
	
	Player p = e.getPlayer();
 	
 	String job = JobAPI.getCurrentJob(p.getUniqueId());
 
    if (!UltimateJobs.checkFlag(p.getLocation(), "can-work-farmer", e.getPlayer())) {
    	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.EventCancelByWorldGuard")) {
    		e.setCancelled(true);
    	}
 
    	 return;
  }
   if (!WorldManager.canWork(p)) {
      return;
    } 
   
   if(!job.equalsIgnoreCase("Farmer")) {
	   return;
   }
   
   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
	   UltimateJobs.getData().QuestActionCount(p, ""+e.getBlock().getType());
   }
   if(!JobAPI.IsSupported(job, ""+block.getType(), true)) {
 
	   return;
   }
  
   List<String> list = JobAPI.getSupportedList(job);
   
   for(String a : list) {
	   //"STONE:1:90:1" 
	   
	   String[] b = a.split(":");
	   
Material ty = Material.valueOf(b[0]);
	   
	   if(ty != block.getType()) { 
		   continue;
	   }
 
	   
	   String levelexp = b[2];
	   String vanilla = b[3];
	   String points = b[4];

	   String mode = b[5];
	   
	   String money = b[6];
	   
	   Integer chance = Integer.valueOf(b[1]);

	   Random r = new Random();
	    int chance2 = r.nextInt(100);
                    
       if (chance2 < chance) { // String mat, String levelexp, String vanilla, String points, mode
    	   UltimateJobs.getRewardHandler().sendRewardMessage(p, ""+ty, levelexp, vanilla, points, mode, money);
    	 
    	   continue;
       }
	   
	 
	   
   }
   
  }

	 
	
	/*    */   public static boolean isFullyGrownOld(Block block) {
		/* 11 */     MaterialData md = block.getState().getData();
		/*    */     
					if(block.getType() == Material.MELON
							|| block.getType() == Material.PUMPKIN) {
						return true;
					}
		
		/* 13 */     if (md instanceof Crops) {
		/* 14 */       return (((Crops)md).getState() == CropState.RIPE);
		/*    */     }
		/* 16 */     return false;
		/*    */   }
		 
	
}
