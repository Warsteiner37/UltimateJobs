package de.warsteiner.ultimatejobs.jobs;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.WorldManager;

public class Job_HeadHunter implements Listener {
	
	public static ItemStack generateSkull(String owner) {
		/* 156 */     ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		/* 157 */     SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
		/* 158 */     skullMeta.setOwner(owner);
		/* 159 */     itemStack.setItemMeta((ItemMeta)skullMeta);
		/* 160 */     return itemStack;
		/*     */   }
	
	@EventHandler
	public void onKIll(EntityDeathEvent e) {
		 
	     if(e.getEntity().getKiller() != null) {
			 Player killer = e.getEntity().getKiller();
			 
		 
			 EntityType ent_type = e.getEntity().getType();
			 
				String job = JobAPI.getCurrentJob(killer.getUniqueId());
			 	
			  
				if(ent_type != EntityType.PLAYER) {
					return;
				}
				
			 if (!UltimateJobs.checkFlag(killer.getLocation(), "can-work-headhunter", killer)) {
 
		    	 return;
		  }
		   if (!WorldManager.canWork(killer)) {
		      return;
		    } 
		   if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
			   UltimateJobs.getData().QuestActionCount(killer, ""+ent_type);
		   }
		   if(!job.equalsIgnoreCase("HeadHunter")) {
			   return;
		   }
		   
		   String name = e.getEntity().getName();
	 
	 
		   String[] b = UltimateJobs.getJobsConfig().getCustomConfig().getString(job+".Get_Money").split(":");
  
				   	FileConfiguration cfg = UltimateJobs.getJobsConfig().getCustomConfig();
					if(cfg.getBoolean("HeadHunter.Can_Get_Skulls")) {
	 
						 
							   double randDouble = Math.random();
							   
							   double m = 0;
							   
							   String dis = cfg.getString("HeadHunter.DisplayForKilled")
									   .replaceAll("<skull>", name)
									   .replaceAll("&", "§");
							   
							   if(SkillsAPIForJobs.isSkillEnabled("Skull", job)) {
									 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+killer.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Skull"));
									  
								   m = Double.valueOf(SkillsAPIForJobs.getChance(job, "Skull", level));
							   } else {
								   m = Double.valueOf(cfg.getString("HeadHunter.Default_Chance"));
							   }
							   
							 
								 ItemStack item = generateSkull(name);
								 
								 ItemMeta meta = item.getItemMeta();
								 
								 meta.setDisplayName(dis);
								 
								 item.setItemMeta(meta);
								 
								  Location loc = e.getEntity().getLocation();
								  World world = loc.getWorld();
								  world.dropItemNaturally(loc, item);
								 
								 
								 if(randDouble <= m) {
							 
								 
						   }
					   }
						  
					   
					   Double money = Double.valueOf(b[0]);
				 
					   Integer chance = Integer.valueOf(b[1]);
					   
					   Double exp = Double.valueOf(b[2]);
					   
					   Integer vanilla = Integer.valueOf(b[3]);
					   
					   Integer p2 = Integer.valueOf(b[4]);
					   
					   Random r = new Random();
					    int chance2 = r.nextInt(100);
				                    
				       if (chance2 < chance) {
				    	   UltimateJobs.getRewardHandler().sendRewardMessage(killer, money, exp,vanilla,p2);
				     
				       }
					    
	     }
	}

}


















