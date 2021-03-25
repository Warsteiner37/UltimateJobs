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
	    				/* 113 */      if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
	    	                Bukkit.getConsoleSender().sendMessage("§4§lWarning§8: §7There is an Error. #7");
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
	   				/* 113 */      if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
		                 Bukkit.getConsoleSender().sendMessage("§4§lWarning§8: §7There is an Error. #6");
		}
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
	    			   
	    			   Double money = Double.valueOf(b[1]);
	    			   
	    			   Integer chance = Integer.valueOf(b[2]);
	    			   
	    			   Double exp = Double.valueOf(b[3]);
	    			   
	    			   Integer vanilla = Integer.valueOf(b[4]);
	    			   
	    			   Integer p2 = Integer.valueOf(b[5]);
	    			   
	    			   Random r = new Random();
	    			    int chance2 = r.nextInt(100);
	    		                    
	    		       if (chance2 < chance) {
	    		    	   
	    		    	  
	    		    	   UltimateJobs.getRewardHandler().sendRewardMessage(p, money, exp,vanilla,p2);
	    		    	   continue;
	    		       }
	    		 
	    			 
	    			   
	    		   }
	    		   
	    		  }
	    	  
	}
 
	
}
