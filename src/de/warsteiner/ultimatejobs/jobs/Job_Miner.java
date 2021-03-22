package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForPlayer;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;
 
public class Job_Miner implements Listener {

	  @EventHandler
	  public void onBreak(BlockBreakEvent e) {
	 	if (e.isCancelled()) {
		 return;
		 }
	 
	 	Block block = e.getBlock();
	 	Player p = e.getPlayer();
	 	
	 	String job = JobAPI.getCurrentJob(p.getUniqueId());
	 	
	 	if(block.hasMetadata("placed-by-player")) {
		 	return;
	 	}
	 	
	    if (!UltimateJobs.checkFlag(p.getLocation(), "can-work-miner", e.getPlayer())) {
	    	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.EventCancelByWorldGuard")) {
	    		e.setCancelled(true);
	    	}
 
	    	 return;
	  }
	   if (!WorldManager.canWork(p)) {
	      return;
	    } 
	   
	   if(!job.equalsIgnoreCase("Miner")) {
		   return;
	   }
	   
	   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
		   UltimateJobs.getData().QuestActionCount(p, ""+e.getBlock().getType());
	   }
	   
	 
 
	   if(!JobAPI.IsSupported(job, ""+block.getType(), true)) {
 
		   return;
	   }
	   
	   if(SkillsAPIForJobs.isEnabled()) {
		   if(SkillsAPIForJobs.isSkillEnabled("Dupe", job)) {
			   double randDouble = Math.random();
				 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Dupe"));
				 
			   if(randDouble <= Double.valueOf(SkillsAPIForJobs.getChance(job, "Dupe", level))) {
			       Location loc = e.getBlock().getLocation();
			       String m = null;
			       if(SkillsAPIForJobs.getItemStackForDupe(""+e.getBlock().getType()).equalsIgnoreCase("NONE")) {
			    	   m = ""+e.getBlock().getType();
			       } else {
			    	   m = SkillsAPIForJobs.getItemStackForDupe(""+e.getBlock().getType());
			       }
			       ItemStack s = new ItemStack(Material.valueOf(m),1);
			       loc.getWorld().dropItemNaturally(loc, s);
			   }
		   }
	   }  
	  
	   List<String> list = JobAPI.getSupportedList(job);
	  
	   for(String a : list) {
		   //"STONE:1:90:1" 
		   
		   String[] b = a.split(":");
		   
   Material ty = Material.valueOf(b[0]);
		   
 
   
		   if(ty != block.getType()) { 
			   continue;
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
