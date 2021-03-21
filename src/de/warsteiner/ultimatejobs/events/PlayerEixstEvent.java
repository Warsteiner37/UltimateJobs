package de.warsteiner.ultimatejobs.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class PlayerEixstEvent /*    */   implements Listener {
	/*    */   @EventHandler
	/*    */   public void onPlayerJoin(final PlayerJoinEvent event) {
	/* 14 */     Bukkit.getScheduler().runTaskAsynchronously((Plugin)UltimateJobs.getPlugin(), new Runnable()
	/*    */         {
	/*    */           public void run()
	/*    */           {
	/* 18 */             if (!UltimateJobs.getData().existplayer(event.getPlayer().getUniqueId())) {
	/* 19 */               UltimateJobs.getData().createPlayer(event.getPlayer().getUniqueId());
	UltimateJobs.getData().UpdateQuests(""+event.getPlayer().getUniqueId(),  event.getPlayer());
	
	/*    */             }
	/*    */             /*    */             if(!UltimateJobs.getData().existPlayerQUestData(""+event.getPlayer().getUniqueId())) {
		UltimateJobs.getData().addPlayerToPlayerList(event.getPlayer().getName());
	}
	/* 22 */             UltimateJobs.getData().setUUID(event.getPlayer().getName(), ""+event.getPlayer().getUniqueId());
	/* 23 */             UltimateJobs.getData().setName(event.getPlayer().getName(), ""+event.getPlayer().getUniqueId());
	 
	FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();
 
	List<String> List_jobs = jobs.getStringList("Options.GUI");
	
	 for (int i = 0; i < List_jobs.size(); i++) {
		 
		 String b = List_jobs.get(i);
  
		 String permission = jobs.getString(b+".Permission");
	 
		 if(jobs.getBoolean("Options.Permissions_Support")) {
			 if(event.getPlayer().hasPermission(permission)) {
				 if(!UltimateJobs.getData().hasJob(event.getPlayer().getUniqueId(), b)) {
					 UltimateJobs.getData().addJobToBought(event.getPlayer().getUniqueId(), b);
					  continue;
				 }
			 }
		 }
	 }
	
	/*    */           }
	/*    */         });
	/*    */   }
	/*    */ }