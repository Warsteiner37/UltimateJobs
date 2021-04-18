package de.warsteiner.ultimatejobs.reward;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.PlayerLevelExpChangeEvent;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.ActionBar;
import de.warsteiner.ultimatejobs.utils.BossBarHandler; 
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.PlayerData;
import de.warsteiner.ultimatejobs.utils.RandomNumberHandler;
import net.milkbowl.vault.economy.Economy;

public class RewardHandler {
	
	public void sendRewardMessage(Player p, String mat, String levelexp, String vanilla, String points, String mode, String money) {
		new PlayerLevelExpChangeEvent(p);
		Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {
 
			@Override
			public void run() {
				String uuid = ""+p.getUniqueId();
				
				 Economy eco = UltimateJobs.getEconomy();
				
				String job = JobAPI.getCurrentJob(p.getUniqueId());
				 
				 
				int rre3 = 0;
				
				   if(SkillsAPIForJobs.isEnabled()) {
					   if(SkillsAPIForJobs.isSkillEnabled("Vanilla", job)) {
						  
							 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Vanilla"));
							 
							 String multi = SkillsAPIForJobs.getNextLevelMulti(job, "Vanilla", level+1);
						 
							 Double idk = Double.valueOf(multi);
							 
						 double rrrr =  idk*Integer.valueOf(vanilla);
							 
						 Double lvl22 = Math.ceil(rrrr);

					 
						 int a32 = lvl22.intValue();
					 
						 rre3 = a32;
					   }
				   }
				
				 
				if(UltimateJobs.getJobsConfig().getCustomConfig().getBoolean("Get_Vanilla_Exp")) {
					p.giveExp(Integer.valueOf(vanilla)+rre3);
				}
				 
			 
				int global = UltimateJobs.getData().getGlobalPoints(""+uuid);
				int r3 = global + Integer.valueOf(points);
				UltimateJobs.getData().setGlobalPoints(""+uuid, r3);
				
				
				int jo = UltimateJobs.getData().getPointsByJob(""+uuid, job);
				int r4 = jo + Integer.valueOf(points);
				UltimateJobs.getData().setPointsByJob(""+uuid, job, r4);
			
				 
				double rechnrung = 0;
 
				double multi_for_money_2 = UltimateJobs.getLevelAPI().getMoneyMulti(job, ""+uuid, ""+UltimateJobs.getData().getLevel(""+uuid, job));
			 
				 
 
				double money2 =0;
				
				
				if( UltimateJobs.getPlugin().getConfig().getBoolean("Use_Levels") == true) {
					money2 =  Double.valueOf(money)*multi_for_money_2;
				 
				}
		 
				
				double re2 = 0;
				
				   if(SkillsAPIForJobs.isEnabled()) {
					   if(SkillsAPIForJobs.isSkillEnabled("Money", job)) {
						  
							 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Money"));
							 
							 String multi = SkillsAPIForJobs.getNextLevelMulti(job, "Money", level+1);
						 
							 double rrrr = Double.valueOf(multi)*Double.valueOf(money);
							  
							 re2 = rrrr;
							 
					   }
				   }
				   
				double final_money = rechnrung+Double.valueOf(money)+money2+re2;
				
				String m = mode.toUpperCase();
 
				double re3 = 0;
				
				   if(SkillsAPIForJobs.isEnabled()) {
					   if(SkillsAPIForJobs.isSkillEnabled("Exp", job)) {
						  
							 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Exp"));
							 
							 String multi = SkillsAPIForJobs.getNextLevelMulti(job, "Exp", level);
						 
							 double rrrr = Double.valueOf(multi)*Double.valueOf(levelexp);
							  
							 re3 = rrrr;
							 
					   }
				   }
				
					double count_one = UltimateJobs.getData().getCountOne(""+uuid, job);
					int count_two = UltimateJobs.getData().getCountTwo(""+uuid, job);
					
					UltimateJobs.getData().setCountOne(""+uuid, job, count_one+final_money);
					UltimateJobs.getData().setCountTwo(""+uuid, job, count_two+1);
				 
				double final_exp = Double.valueOf(levelexp)+re3;
 
				double current_exp = UltimateJobs.getData().getExp(""+uuid, job);
				 
				 
				UltimateJobs.getData().setExp(""+uuid, job, current_exp+final_exp);
				
				String formated_money = UltimateJobs.getLevelAPI().getFormatedMoney(final_money);
				String formated_exo = UltimateJobs.getLevelAPI().getFormatedMoney(final_exp);
				 
				if(m.equalsIgnoreCase("VAULT")) {
					eco.depositPlayer(p, Double.valueOf(formated_money));
				}    
				
				 FileConfiguration cfg = UltimateJobs.getPlugin().getConfig();
   
				 if(cfg.getString("RewardMode").toUpperCase().equalsIgnoreCase("PUBLIC")) {
					 if(cfg.getBoolean("Options_Rewards.public.Message")) {
						 String message = cfg.getString("Options_Rewards.public.Message_Type")
								  .replaceAll("<job_need>", ""+UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId()))		  .replaceAll("<job_level>", ""+UltimateJobs.getData().getLevel(uuid, job))            .replaceAll("<job_exp>", ""+UltimateJobs.getLevelAPI().getFormatedExp(job, uuid)).replaceAll("<exp>", formated_exo).replaceAll("<money>", formated_money)	.replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)) .replaceAll("&", "§");
						 p.sendMessage(message);
						 return;
					 }
					 
					 if(cfg.getBoolean("Options_Rewards.public.Actionbar")) {
						 String message = cfg.getString("Options_Rewards.public.Actionbar_Type")
								  .replaceAll("<job_need>", ""+UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId()))		  .replaceAll("<job_level>", ""+UltimateJobs.getData().getLevel(uuid, job))            .replaceAll("<job_exp>", ""+UltimateJobs.getLevelAPI().getFormatedExp(job, uuid)).replaceAll("<exp>", formated_exo).replaceAll("<money>", formated_money)	.replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)) .replaceAll("&", "§");
						 ActionBar.sendActionbar(p, message);
						 return;
					 }
					 
					 if(cfg.getBoolean("Options_Rewards.public.BossBar")) {
						 String message = cfg.getString("Options_Rewards.public.BossBarConfig.Message")
								  .replaceAll("<job_need>", ""+UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId()))		  .replaceAll("<job_level>", ""+UltimateJobs.getData().getLevel(uuid, job))            .replaceAll("<job_exp>", ""+UltimateJobs.getLevelAPI().getFormatedExp(job, uuid)).replaceAll("<exp>", formated_exo).replaceAll("<money>", formated_money)	.replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)) .replaceAll("&", "§");
					 
						 String color = cfg.getString("Options_Rewards.public.BossBarConfig.Color");
						 String style = cfg.getString("Options_Rewards.public.BossBarConfig.Style");
						 String ticks = cfg.getString("Options_Rewards.public.BossBarConfig.Ticks");
						 
						 UltimateJobs.getBossBarHandler().sendBar(p, BarColor.valueOf(color), BarStyle.valueOf(style), Integer.valueOf(ticks) , message);
						 return;
					 }
				 } else {
					 
					 String type = cfg.getString("Options_Rewards."+job+".Type").toUpperCase();
					 
					 if(type.equalsIgnoreCase("BOSSBAR")) {
						 
						 String message = cfg.getString("Options_Rewards."+job+".Message") .replaceAll("<job_need>", ""+UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId()))		  .replaceAll("<job_level>", ""+UltimateJobs.getData().getLevel(uuid, job))            .replaceAll("<job_exp>", ""+UltimateJobs.getLevelAPI().getFormatedExp(job, uuid)).replaceAll("<exp>", formated_exo).replaceAll("<money>", formated_money)	.replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)) .replaceAll("&", "§");
						 
						 
						 String color = cfg.getString("Options_Rewards."+job+".Color");
						 String style = cfg.getString("Options_Rewards."+job+".Style");
						 String ticks = cfg.getString("Options_Rewards."+job+".Ticks");
						 
						 UltimateJobs.getBossBarHandler().sendBar(p, BarColor.valueOf(color), BarStyle.valueOf(style), Integer.valueOf(ticks) , message);
						 return;
					 } else  if(type.equalsIgnoreCase("MESSAGE")) {
						 
						 String message = cfg.getString("Options_Rewards."+job+".Message") .replaceAll("<job_need>", ""+UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId()))		  .replaceAll("<job_level>", ""+UltimateJobs.getData().getLevel(uuid, job))            .replaceAll("<job_exp>", ""+UltimateJobs.getLevelAPI().getFormatedExp(job, uuid)).replaceAll("<exp>", formated_exo).replaceAll("<money>", formated_money)	.replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)) .replaceAll("&", "§");
						 
 
						 p.sendMessage(message);
						 
						 return ;
					 }   else  if(type.equalsIgnoreCase("ACTIONBAR")) {
						 
						 String message = cfg.getString("Options_Rewards."+job+".Message") .replaceAll("<job_need>", ""+UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId()))		  .replaceAll("<job_level>", ""+UltimateJobs.getData().getLevel(uuid, job))            .replaceAll("<job_exp>", ""+UltimateJobs.getLevelAPI().getFormatedExp(job, uuid)).replaceAll("<exp>", formated_exo).replaceAll("<money>", formated_money)	.replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)) .replaceAll("&", "§");
						 
 
						 ActionBar.sendActionbar(p, message);
						 
						 return;
					 } 
					 
				 }
				
			}
			
		});
		
		 
		
	}

}





















