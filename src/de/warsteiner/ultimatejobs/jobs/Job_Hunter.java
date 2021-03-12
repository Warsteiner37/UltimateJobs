package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
 
import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;
 
public class Job_Hunter implements Listener {
	
	 @EventHandler
	 public void onKill(EntityDeathEvent e) {
	 
     if(e.getEntity().getKiller() != null) {
		 Player killer = e.getEntity().getKiller();
		 
		 String ent_name = e.getEntity().getName().replaceAll("&", "§");
		 EntityType ent_type = e.getEntity().getType();
		 
			String job = JobAPI.getCurrentJob(killer.getUniqueId());
		 	
		  
		 if (!UltimateJobs.checkFlag(killer.getLocation(), "can-work-hunter", killer)) {
			 
				/* 113 */      if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
	                Bukkit.getConsoleSender().sendMessage("§4§lWarning§8: §7There is an Error. #7");
	}
	    	 return;
	  }
	   if (!WorldManager.canWork(killer)) {
	      return;
	    } 
	   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
		   UltimateJobs.getData().QuestActionCount(killer, ""+ent_type);
	   }
	   if(!job.equalsIgnoreCase("Hunter")) {
		   return;
	   }
	   
	   List<String> list = JobAPI.getSupportedList(job);
 
	   for(String a : list) {
		   //"STONE:1:90:1" 
		   
		   String[] b = a.split(":");
		   
		   String mode = b[0];
		   
		   if(mode.equalsIgnoreCase("ID")) {
				if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
					System.out.println("§a§lINFO: §fcalling event DeathEvent with entity: "+ent_type);
				}
			   if(EntityType.valueOf(b[1]) == null) {
					if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
						System.out.println("§4§lERROR: §fThere is a wrong id in youre config! Type: "+b[1]);
					}
					continue;
			   }
			   EntityType ty = EntityType.valueOf(b[1]);
			   
				  // if(ty != block.getType()) { 
					 //  >//continue;
				 //  }
			   
			   	if(ent_type != ty) {
			   		continue;
			   	}
				   
				   Double money = Double.valueOf(b[2]);
				   
				   Integer chance = Integer.valueOf(b[3]);
				   
				   Double exp = Double.valueOf(b[4]);
				   
				   Integer vanilla = Integer.valueOf(b[5]);
				   
				   Integer p2 = Integer.valueOf(b[6]);
				   
				   Random r = new Random();
				    int chance2 = r.nextInt(100);
			                    
			       if (chance2 < chance) {
			    	   UltimateJobs.getRewardHandler().sendRewardMessage(killer, money, exp,vanilla,p2);
			    	   continue;  
			       }
				    continue; 
		   }  if(mode.equalsIgnoreCase("DIS")) {
			  
			   String ty = String.valueOf(b[1]).replaceAll("&", "§");
			   
				  // if(ty != block.getType()) { 
					 //  >//continue;
				 //  }
			   
			   	if(!ent_name.equalsIgnoreCase(ty)) {
			   		continue;
			   	}
				   
				   Double money = Double.valueOf(b[2]);
				   
				   Integer chance = Integer.valueOf(b[3]);
				   
				   Double exp = Double.valueOf(b[4]);
				   
				   Integer vanilla = Integer.valueOf(b[5]);
				   
				   Integer p2 = Integer.valueOf(b[6]);
				   
				   Random r = new Random();
				    int chance2 = r.nextInt(100);
			                    
			       if (chance2 < chance) {
			    	   UltimateJobs.getRewardHandler().sendRewardMessage(killer, money, exp,vanilla,p2);
			    	   continue; 
			       }
			       
		   }  
		 
		   
	   }
		 
	 }
	 }

}
