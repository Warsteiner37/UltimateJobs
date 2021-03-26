package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;

public class Job_Baker implements Listener {
	
	@EventHandler
	/*    */   public void onBreak(CraftItemEvent e) {
	 	if (e.isCancelled()) {
		 return;
		 }
 
	 	Material block = e.getInventory().getResult().getType();
	 	Player p = (Player) e.getWhoClicked();
	 
	 	String job = JobAPI.getCurrentJob(p.getUniqueId());
 
	    if (!UltimateJobs.checkFlag(p.getLocation(), "can-work-baker", p)) {
	    	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.EventCancelByWorldGuard")) {
	    		e.setCancelled(true);
	    	}
 
	    	 return;
	  }
	   if (!WorldManager.canWork(p)) {
	      return;
	    } 
	   
	   if(!job.equalsIgnoreCase("Baker")) {
		   return;
	   }
	   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
		   UltimateJobs.getData().QuestActionCount(p, ""+block);
	   }
	   if(!JobAPI.IsSupported(job, ""+block, true)) {
 
		   return;
	   }
	  
	   List<String> list = JobAPI.getSupportedList(job);
	   
	   for(String a : list) {
		  
	 
		   String[] b = a.split(":");
		   
		   Material ty = Material.valueOf(b[0]);
		   
		   if(ty != block) { 
			   continue;
		   }
			   
		   int amount = e.getInventory().getResult().getAmount();
		   
		  
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
