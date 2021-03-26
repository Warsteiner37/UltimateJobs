package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Beehive;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;

public class Job_BeeKeeper  implements Listener {

	  @EventHandler
	  public void onBreak(PlayerInteractEvent e) {
	 	if (e.isCancelled()) {
		 return;
		 }
 
	 	Player p = e.getPlayer();
	 	
	 	String job = JobAPI.getCurrentJob(p.getUniqueId());
 
	 	
	    if (!UltimateJobs.checkFlag(p.getLocation(), "can-work-beekeeper", e.getPlayer())) {
	    	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.EventCancelByWorldGuard")) {
	    		e.setCancelled(true);
	    	}
 
	    	 return;
	  }
	   if (!WorldManager.canWork(p)) {
	      return;
	    } 
	   
	   if(!job.equalsIgnoreCase("BeeKeeper")) {
		   return;
	   }
	   Action action = e.getAction();
	 		/*  91 */     Block clickedBlock = e.getClickedBlock();
	   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
		   UltimateJobs.getData().QuestActionCount(p, ""+clickedBlock.getType());
	   }
 
	     String list = UltimateJobs.getJobsConfig().getCustomConfig().getString(job+".Get_Money");
	  
		/*  97 */     if (action == Action.RIGHT_CLICK_BLOCK && clickedBlock != null && (
				/*  98 */       clickedBlock.getType().equals(Material.BEEHIVE) || clickedBlock.getType().equals(Material.BEE_NEST))) {
			
			 @NotNull Material item = p.getItemInHand().getType();
			 
			 if(item != null) {
				if(item == Material.GLASS_BOTTLE) {
					
					/*  99 */    BlockData bdata = clickedBlock.getBlockData();
					  Beehive beehive = (Beehive)bdata; 
					  
					  if( beehive.getHoneyLevel() >= 1) {
					
					
						  if(SkillsAPIForJobs.isEnabled()) {
							   if(SkillsAPIForJobs.isSkillEnabled("Refill", job)) {
								   double randDouble = Math.random();
									 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Refill"));
									 
								   if(randDouble <= Double.valueOf(SkillsAPIForJobs.getChance(job, "Refill", level))) {
									   beehive.setHoneyLevel(beehive.getHoneyLevel()+1);
								   }
							   }
						  }
						  
					// "15:80:0.25:3:1"
					
					 String[] b = list.split(":");
					 
					   String levelexp = b[1];
					   String vanilla = b[2];
					   String points = b[3];
			 
					   String mode = b[4];
					   
					   String money = b[5];
					   
					   Integer chance = Integer.valueOf(b[0]);
			 
					   Random r = new Random();
					    int chance2 = r.nextInt(100);
				                    
				       if (chance2 < chance) { // String mat, String levelexp, String vanilla, String points, mode
				    	   UltimateJobs.getRewardHandler().sendRewardMessage(p, ""+clickedBlock.getType(), levelexp, vanilla, points, mode, money);
				    	  
				       }
				}
			 }
			 }
		 
			
		}
	   
 
	   
	  }
	
}

