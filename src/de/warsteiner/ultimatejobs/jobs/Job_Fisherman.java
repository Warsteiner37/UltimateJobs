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
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;

public class Job_Fisherman implements Listener {
	
	/*    */   @EventHandler
	/*    */   public void onFish(PlayerFishEvent e) {
	/* 14 */     if (e.isCancelled()) {
	/*    */       return;
	/*    */     }
 
	/* 23 */     if (e.getCaught() != null) {
	 
			String id = e.getCaught().getName().toUpperCase().replaceAll(" ", "_");
		 
			 	Player p = e.getPlayer();
			 	
			 	String job = JobAPI.getCurrentJob(p.getUniqueId());
 
			    if (!UltimateJobs.checkFlag(p.getLocation(), "can-work-fisherman", e.getPlayer())) {
			    	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.EventCancelByWorldGuard")) {
			    		e.setCancelled(true);
			    	}
 
			    	 return;
			  }
			   if (!WorldManager.canWork(p)) {
			      return;
			    } 
			   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
				   UltimateJobs.getData().QuestActionCount(p, ""+id);
			   }
			   if(!job.equalsIgnoreCase("Fisherman")) {
				   return;
			   }
 
			   List<String> list = JobAPI.getSupportedList(job);
			   
			   for(String a : list) {
				   //"STONE:1:90:1" 
				   
				   String[] b = a.split(":");
				   
		   String ty = String.valueOf(b[0]);
			 
				   if(!ty.equalsIgnoreCase(id)) { 
					   continue;
				   }
				   
				   if(SkillsAPIForJobs.isEnabled()) {
					   if(SkillsAPIForJobs.isSkillEnabled("Dupe", job)) {
						   double randDouble = Math.random();
							 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Dupe"));
							 
						   if(randDouble <= Double.valueOf(SkillsAPIForJobs.getChance(job, "Dupe", level))) {
						       Location loc = p.getLocation();
						       String m = null;
						       if(SkillsAPIForJobs.getItemStackForDupe(""+id).equalsIgnoreCase("NONE")) {
						    	   m = ""+id;
						       } else {
						    	   m = SkillsAPIForJobs.getItemStackForDupe(""+id);
						       }
						       ItemStack s = new ItemStack(Material.valueOf(m),1);
						       loc.getWorld().dropItemNaturally(loc, s);
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
