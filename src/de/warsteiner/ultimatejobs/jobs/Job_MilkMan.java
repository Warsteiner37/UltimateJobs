package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;

public class Job_MilkMan implements Listener {
	
	@EventHandler
	public void onIt(PlayerInteractAtEntityEvent e) {
		Entity clicked = e.getRightClicked();
		if (clicked instanceof Cow) {
			/*    */ 
			/*    */       
			 Cow c = (Cow)clicked;
		
			 	if(e.getPlayer().getItemInHand() == null) {
			 		return;
			 	}
			 
			   if (e.isCancelled())
					/*    */       return; 
 
				 	Player p = e.getPlayer(); 
				 	String job = JobAPI.getCurrentJob(p.getUniqueId());
				 
				    if (!UltimateJobs.checkFlag(p.getLocation(), "can-work-milkman", e.getPlayer())) {
				    	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.EventCancelByWorldGuard")) {
				    		e.setCancelled(true);
				    	}
 
				    	 return;
				  }
				   if (!WorldManager.canWork(p)) {
				      return;
				    } 
				   
				   if(!job.equalsIgnoreCase("Milkman")) {
					   return;
				   }
				   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
					   UltimateJobs.getData().QuestActionCount(p, "MILK");
				   }
				   
				   @NotNull ItemStack item = e.getPlayer().getItemInHand();
 
				   if(item.getType() != Material.BUCKET) {
					   return;
				   }
				  
				   List<String> list = JobAPI.getSupportedList(job);
				   
				   for(String a : list) {
					   //"STONE:1:90:1" 
					   
					   String[] b = a.split(":");
					   
		 
					   
					   String levelexp = b[1];
					   String vanilla = b[2];
					   String points = b[3];
			 
					   String mode2 = b[4];
					   
					   String money = b[5];
					   
					   Integer chance = Integer.valueOf(b[0]);
			 
					   Random r = new Random();
					    int chance2 = r.nextInt(100);
				                    
				       if (chance2 < chance) { // String mat, String levelexp, String vanilla, String points, mode
				    	   UltimateJobs.getRewardHandler().sendRewardMessage(p, "MILK", levelexp, vanilla, points, mode2, money);
				    	  
				       }
				 
					   
				   }
				   
				  }
		}
		  
	}
	
 
