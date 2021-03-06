package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;
 
public class Job_Shepherd implements Listener {
	
	@EventHandler
	/*    */   public void playerShearEvent(PlayerShearEntityEvent e) {
		
	 	if (e.isCancelled()) {
			 return;
			 }
		
		if (e.getEntity() instanceof Sheep) {
			/*    */ 
			/*    */       
			 Sheep sheep = (Sheep)e.getEntity();
				/*    */       
				/* 29 */       DyeColor color = sheep.getColor();
	 
		 	Player p = e.getPlayer();
		 	
		 	String job = JobAPI.getCurrentJob(p.getUniqueId());
	 
		    if (!UltimateJobs.checkFlag(p.getLocation(), "can-work-shepherd", e.getPlayer())) {
		    	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.EventCancelByWorldGuard")) {
		    		e.setCancelled(true);
		    	}
 
		    	 return;
		  }
		    
		   if (!WorldManager.canWork(p)) {
		      return;
		    } 
		   
		   if(!job.equalsIgnoreCase("Shepherd")) {
			   return;
		   }
		   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
			   UltimateJobs.getData().QuestActionCount(p, ""+color);
		   }
		   if(!JobAPI.IsSupported(job, ""+color, false)) {
 
			   return;
		   }
		  
		   List<String> list = JobAPI.getSupportedList(job);
		   
		   for(String a : list) {
			   //"STONE:1:90:1" 
			   
			   String[] b = a.split(":");
			   
	   DyeColor ty = DyeColor.valueOf(b[0]);
			   
			   if(ty != color) { 
				   continue;
			   }
			   
			   if(SkillsAPIForJobs.isEnabled()) {
				   if(SkillsAPIForJobs.isSkillEnabled("Dupe", job)) {
					   double randDouble = Math.random();
						 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Dupe"));
						   if(randDouble <= Double.valueOf(SkillsAPIForJobs.getChance(job, "Dupe", level))) {
							   Location d = sheep.getLocation();
								 World world = d.getWorld();
								 
								 Material redWoolMaterial = Material.getMaterial(ty + "_WOOL");
								 ItemStack item = new ItemStack(redWoolMaterial,1);
								 world.dropItemNaturally(d, item);
							 
								  e.setCancelled(true);
						   }
				   }
				   if(SkillsAPIForJobs.isSkillEnabled("Refill", job)) {
					   double randDouble = Math.random();
						 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Refill"));
					 
					   if(randDouble <= Double.valueOf(SkillsAPIForJobs.getChance(job, "Refill", level))) {
						   Location d = sheep.getLocation();
							 World world = d.getWorld();
							 
							 Material redWoolMaterial = Material.getMaterial(ty + "_WOOL");
							 ItemStack item = new ItemStack(redWoolMaterial,1);
							 world.dropItemNaturally(d, item);
						 
							  e.setCancelled(true);
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
