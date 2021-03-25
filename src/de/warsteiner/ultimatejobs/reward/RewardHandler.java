package de.warsteiner.ultimatejobs.reward;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.PlayerLevelExpChangeEvent;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.ActionBar;
import de.warsteiner.ultimatejobs.utils.BossBarHandler; 
import de.warsteiner.ultimatejobs.utils.JobAPI;
import net.milkbowl.vault.economy.Economy;

public class RewardHandler {
	
	public void sendRewardMessage(Player p, double money, double exp, int vanilla, int points) {
		new PlayerLevelExpChangeEvent(p);
		Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {
//	  p.giveExp(1);
			@Override
			public void run() {
				UUID uuid = p.getUniqueId();
				
				 Economy eco = UltimateJobs.getEconomy();
				
				String job = JobAPI.getCurrentJob(uuid);
				
				double multi_for_money = UltimateJobs.getSkillAPI().getMoneyMulti(""+uuid);
				
				double multi_for_money_2 = UltimateJobs.getLevelAPI().getMoneyMulti(job, ""+uuid, ""+UltimateJobs.getData().getLevel(""+uuid, job));
			 
				double rechnrung = 0;
		 
				if( UltimateJobs.getSkillsMainConfig().getCustomConfig().getBoolean("Enable_Skills") == true) {
					rechnrung = money*multi_for_money;
				}
				double money2 =0;
				
				int rre3 = 0;
				
				   if(SkillsAPIForJobs.isEnabled()) {
					   if(SkillsAPIForJobs.isSkillEnabled("Vanilla", job)) {
						  
							 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Vanilla"));
							 
							 String multi = SkillsAPIForJobs.getNextLevelMulti(job, "Vanilla", level+1);
						 
							// int rrrr = (int) (Integer.valueOf(multi)*money);
							  
							 // if(rrrr == 0) {
								 rre3 = 1;
								 //	 } else {
								 //	 rre3 = rrrr;
								 // }
					   }
				   }
				
				 
				if(UltimateJobs.getJobsConfig().getCustomConfig().getBoolean("Get_Vanilla_Exp")) {
					p.giveExp(vanilla+rre3);
				}
		 
				if( UltimateJobs.getPlugin().getConfig().getBoolean("Use_Levels") == true) {
					money2 =  money*multi_for_money_2;
					   Bukkit.broadcastMessage("§a2 -"+money2);
				}
		 
				
				double re2 = 0;
				
				   if(SkillsAPIForJobs.isEnabled()) {
					   if(SkillsAPIForJobs.isSkillEnabled("Money", job)) {
						  
							 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Money"));
							 
							 String multi = SkillsAPIForJobs.getNextLevelMulti(job, "Money", level+1);
						 
							 double rrrr = Double.valueOf(multi)*money;
							  
							 re2 = rrrr;
							 
					   }
				   }
				   
				double final_money = rechnrung+money+money2+re2;

				double multi_for_exp = UltimateJobs.getSkillAPI().getExpMulti(""+uuid);
				
				double rechnung = exp*multi_for_exp;
		 
				double re3 = 0;
				
				   if(SkillsAPIForJobs.isEnabled()) {
					   if(SkillsAPIForJobs.isSkillEnabled("Exp", job)) {
						  
							 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, SkillsAPIForJobs.getPosOfSkillInList(job, "Exp"));
							 
							 String multi = SkillsAPIForJobs.getNextLevelMulti(job, "Exp", level);
						 
							 double rrrr = Double.valueOf(multi)*exp;
							  
							 re3 = rrrr;
							 
					   }
				   }
				
				 
				double final_exp = rechnung+exp+re3;
				
				double current_exp = UltimateJobs.getData().getExp(""+uuid, job);
				 
			 
				UltimateJobs.getData().setExp(""+uuid, job, current_exp+final_exp);
				
				int global = UltimateJobs.getData().getGlobalPoints(""+uuid);
				int r3 = global + points;
				UltimateJobs.getData().setGlobalPoints(""+uuid, r3);
				
				
				int jo = UltimateJobs.getData().getPointsByJob(""+uuid, job);
				int r4 = jo + points;
				UltimateJobs.getData().setPointsByJob(""+uuid, job, r4);
				
				double count_one = UltimateJobs.getData().getCountOne(""+uuid, job);
				int count_two = UltimateJobs.getData().getCountTwo(""+uuid, job);
				
				UltimateJobs.getData().setCountOne(""+uuid, job, count_one+final_money);
				UltimateJobs.getData().setCountTwo(""+uuid, job, count_two+1);
				
				String formated_money = UltimateJobs.getLevelAPI().getFormatedMoney(final_money);
				String formated_exo = UltimateJobs.getLevelAPI().getFormatedMoney(final_exp);
				
				eco.depositPlayer(p, Double.valueOf(formated_money));
				
				String prefix = UltimateJobs.MessageHandler().getCustomConfig().getString("Prefix");
				
				if(UltimateJobs.getPlugin().getConfig().getString("Options.Reward_Type").equalsIgnoreCase("MESSAGE")) {
					p.sendMessage(UltimateJobs.getPlugin().getConfig().getString("Config.Message")
						.replaceAll("<money>", formated_money)	.replaceAll("<exp>", formated_exo).replaceAll("<prefix>", prefix).replaceAll("&", "§"));
					return;
				} else if(UltimateJobs.getPlugin().getConfig().getString("Options.Reward_Type").equalsIgnoreCase("BOSSBAR")) {
					
					String a1 = UltimateJobs.getPlugin().getConfig().getString("Config.Bossbar.Message")
							.replaceAll("<money>", formated_money)	.replaceAll("<exp>", formated_exo).replaceAll("<prefix>", prefix).replaceAll("&", "§");
					
					String color = UltimateJobs.getPlugin().getConfig().getString("Config.Bossbar.Color");
					
					Integer time = Integer.valueOf(UltimateJobs.getPlugin().getConfig().getInt("Config.Bossbar.Time"));
					
					String style = UltimateJobs.getPlugin().getConfig().getString("Config.Bossbar.Style");
				 
					  UltimateJobs.getBossBarHandler().sendBar(p, BarColor.valueOf(color), BarStyle.valueOf(style), time , a1);
					
				} else if(UltimateJobs.getPlugin().getConfig().getString("Options.Reward_Type").equalsIgnoreCase("ACTIONBAR")) {
					
					String a1 = UltimateJobs.getPlugin().getConfig().getString("Config.Actionbar")
							.replaceAll("<money>", formated_money)	.replaceAll("<exp>", formated_exo).replaceAll("<prefix>", prefix).replaceAll("&", "§");
					
					ActionBar.sendActionbar(p, a1);
					
				} else if(UltimateJobs.getPlugin().getConfig().getString("Options.Reward_Type").equalsIgnoreCase("TITLE")) {
					
					String a1 = UltimateJobs.getPlugin().getConfig().getString("Config.Title.First")
							.replaceAll("<money>", formated_money)	.replaceAll("<exp>", formated_exo).replaceAll("<prefix>", prefix).replaceAll("&", "§");
					
					String a2 = UltimateJobs.getPlugin().getConfig().getString("Config.Title.Second")
							.replaceAll("<money>", formated_money)	.replaceAll("<exp>", formated_exo).replaceAll("<prefix>", prefix).replaceAll("&", "§");
					
					p.sendTitle(a1, a2);
				}
			}
			
		});
		
		 
		
	}

}
