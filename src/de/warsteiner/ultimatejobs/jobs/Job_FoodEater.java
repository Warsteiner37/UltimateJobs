package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;

public class Job_FoodEater implements Listener {

	@EventHandler
	     public void onFoodChange(FoodLevelChangeEvent e){
		 
		if (e.isCancelled()) {
			 return;
			 }
		if(e.getItem() == null) {
			return;
		}
		
	       Player p = (Player) e.getEntity();
	       if(p instanceof Player){
	    	  Material item = e.getItem().getType();
	    	    
	    		 	
	    		 	String job = JobAPI.getCurrentJob(p.getUniqueId());
	    		 
	    		    if (!UltimateJobs.checkFlag(p.getLocation(), "can-work-FoodEater", p)) {
	    		    	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.EventCancelByWorldGuard")) {
	    		    		e.setCancelled(true);
	    		    	}
 
	    		    	 return;
	    		  }
	    		   if (!WorldManager.canWork(p)) {
	    		      return;
	    		    } 
	    		   
	    		   if(!job.equalsIgnoreCase("FoodEater")) {
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
	    			   
	    			   int set = 0;
	    			   
	    			  
	    			   if(SkillsAPIForJobs.isEnabled()) {
	    				   if(SkillsAPIForJobs.isSkillEnabled("Food", job)) {
	    					   double randDouble = Math.random();
	    						 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Food"));
	    						 
	    				   if(randDouble <= Double.valueOf(SkillsAPIForJobs.getChance(job, "Food", level))) {
	    						  
	    					       set = Integer.valueOf(SkillsAPIForJobs.getFoodLevel(level, job, "Food"));
	    					       if(p.getFoodLevel()+set >= 20) {
	    	    		    		   int n = set+p.getFoodLevel()-20;
	    	    		    		   set = n;
	    	    		    		   
	    	    		    	   }   
	    	    		    	   p.setFoodLevel(p.getFoodLevel()+set);
	    					   }
	    				   }
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
 
	
}
