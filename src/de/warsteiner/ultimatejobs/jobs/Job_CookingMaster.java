package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;

public class Job_CookingMaster  implements Listener {

    @EventHandler
    public void onFurnaceBurn(FurnaceBurnEvent e) {
    	 
    }
	
	 
	/*    */   @EventHandler
	/*    */   public void OnBurn(FurnaceExtractEvent  e) {
	 
 
	 
		Material item = e.getItemType();
		int amount = e.getItemAmount();
		 	Player p = e.getPlayer();
		 	
		 	String job = JobAPI.getCurrentJob(p.getUniqueId());
 
		    if (!UltimateJobs.checkFlag(p.getLocation(), "can-work-cookingmaster", e.getPlayer())) {
 
		    	 return;
		  }
		   if (!WorldManager.canWork(p)) {
		      return;
		    } 
		   
		   if(!job.equalsIgnoreCase("CookingMaster")) {
			   return;
		   }
		   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
			   UltimateJobs.getData().QuestActionCount(p, ""+item);
		   }
		   if(!JobAPI.IsSupported(job, ""+item, true)) {
 
			   return;
		   }
		  
		   List<String> list = JobAPI.getSupportedList(job);
		   
		   for(String a : list) {
			   //"STONE:1:90:1" 
			   
			   String[] b = a.split(":");
			   
	   Material ty = Material.valueOf(b[0]);
			   
			   if(ty != item) { 
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
	
}
