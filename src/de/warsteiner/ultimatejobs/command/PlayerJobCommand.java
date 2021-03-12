package de.warsteiner.ultimatejobs.command;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.config.CommandConfig;
import de.warsteiner.ultimatejobs.gui.JobsGUIManager;
import de.warsteiner.ultimatejobs.levels.levelgui.LevelCreatingGUI;
import de.warsteiner.ultimatejobs.quests.QuestGUI;
import de.warsteiner.ultimatejobs.ranking.GlobalRankingGUI;
import de.warsteiner.ultimatejobs.ranking.JobRankingGUI;
import de.warsteiner.ultimatejobs.skills.SkillsGUI;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.ReloadHandler;

public class PlayerJobCommand implements CommandExecutor {

	public static String calculateTime(long seconds) {
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);

        String format = UltimateJobs.getQuestAPI().getCustomConfig().getString("Time_Format");
        
        
        
        return format
        		.replaceAll("%day%", ""+day)
        		.replaceAll("%hours%", ""+hours)
        		.replaceAll("%minutes%", ""+minute)
        		.replaceAll("%seconds%", ""+second)
        	 .replaceAll("&", "§");

    }
	
	public static void openQuest(Player p) {
		long dann = UltimateJobs.getData().getCool(""+p.getUniqueId());
		 
		 FileConfiguration cfg = UltimateJobs.getQuestAPI().getCustomConfig();
		long secondsLeft = ((dann/1000)+cfg.getLong("New_Quest")) - System.currentTimeMillis()/1000;
		 


		    if(secondsLeft>0) {
		    	
		 
		    	String t = calculateTime(secondsLeft);

		    	
		    	if(cfg.getBoolean("Get_Message_Wating_Time.Enabled")) {
		    		p.sendMessage(cfg.getString("Get_Message_Wating_Time.Message").replaceAll("<new>", ""+t).replaceAll("&", "§"));
		    	}
		} else {
			UltimateJobs.getData().UpdateQuests(""+p.getUniqueId(),  p);
			if(cfg.getBoolean("Get_Message_If_New_Quest.Enabled")) {
				p.sendMessage(cfg.getString("Get_Message_If_New_Quest.Message").replaceAll("&", "§"));
			}
		 
		}
						
						p.openInventory(QuestGUI.load(p));
	}

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
	Player p = (Player) s;
		
		FileConfiguration tab = UltimateJobs.getCommandConfig().getCustomConfig();
		FileConfiguration m = UltimateJobs.MessageHandler().getCustomConfig();
		
		String uuid = ""+ p.getUniqueId();
		
		 
  
		 
		String job = JobAPI.getCurrentJob(p.getUniqueId());
		
			if(args.length == 0) {
				
				
				if(UltimateJobs.getPlugin().getConfig().getBoolean("Plugin.Use_GUI")) {
			      Inventory inv = JobsGUIManager.load(p);
			       p.openInventory(inv);
				}
			       return true;
			       
			}  if(args.length == 1 && args[0].equalsIgnoreCase(tab.getString("Options.Help.Usage"))) {
				
				if(!tab.getBoolean("Options.Help.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				 
				List<String> a = tab.getStringList("Messages.Help");
				
				for(String aa : a) {
					p.sendMessage(aa
						.replaceAll("<name>", p.getName())	.replaceAll("&", "§"));
				}
				
			} else if(args.length == 1 && args[0].equalsIgnoreCase(tab.getString("Options.Leave.Usage"))) {
				
				if(!tab.getBoolean("Options.Leave.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(job.equalsIgnoreCase("None")) {
					String a = m.getString("Prefix")+tab.getString("Messages.No_Job");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				JobAPI.setallJobsNotActive(p, p.getUniqueId());
				String a = m.getString("Prefix")+m.getString("Job_Leave") ;
				 p.sendMessage(a
						 .replaceAll("&", "§").replaceAll("<job>", job));
				 return true;
			} else if(args.length == 1 && args[0].equalsIgnoreCase(tab.getString("Options.Skills.Usage"))) {
				
				if(!tab.getBoolean("Options.Skills.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				} 
				
				if(!UltimateJobs.getSkillsConfig().getCustomConfig().getBoolean("Enable_Skills")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
			 }
				
				if(!UltimateJobs.getSkillsConfig().getCustomConfig().getBoolean("Enable_Skills")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				 
				p.openInventory(SkillsGUI.load(p));
				 return true;
			}  else if(args.length == 1 && args[0].equalsIgnoreCase(tab.getString("Options.Levels.Usage"))) {
				
				if(!tab.getBoolean("Options.Levels.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				 if(!UltimateJobs.getLevelGUI().getCustomConfig().getBoolean("Enable_LevelGUI")) {
						String a = m.getString("Prefix")+tab.getString("Messages.Usage");
						p.sendMessage(a.replaceAll("&", "§"));
						return true;
				 }
				
				if(job.equalsIgnoreCase("None")) {
					String a = m.getString("Prefix")+tab.getString("Messages.No_Job");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
			     if(UltimateJobs.getData().existPlayerPageData(uuid)) {
                   p.openInventory(LevelCreatingGUI.load(p));
                     return true;
                 } else {

                      int a = UltimateJobs.getLevelGUI().getCustomConfig().getStringList("Design.LevelSlots").size()+1;

                      String pg = "PAGE_1:FROM_1:UPTO_"+a;

                      UltimateJobs.getData().setPage(pg, uuid);

                    p.openInventory(LevelCreatingGUI.load(p));
                     return true;
                 }
				 
			} else if(args.length == 1 && args[0].equalsIgnoreCase(tab.getString("Options.Quests.Usage"))) {
				
				if(!tab.getBoolean("Options.Quests.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				 
				if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") == false) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
 

 openQuest(p);
				 return true;
			} else if(args.length == 1 && args[0].equalsIgnoreCase(tab.getString("Options.Exp.Usage"))) {
				
				if(!tab.getBoolean("Options.Exp.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(!UltimateJobs.getLevelConfig().getCustomConfig().getBoolean("Use_Levels")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(job.equalsIgnoreCase("None")) {
					String a = m.getString("Prefix")+tab.getString("Messages.No_Job");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				 
				int level = UltimateJobs.getData().getLevel(uuid, job);
				String exp =UltimateJobs.getLevelAPI().getFormatedExp(job, uuid);
				double need = UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId());
				
				if(UltimateJobs.getLevelAPI().PlayerIsMaxLevel(uuid, job)) {
					String a = m.getString("Prefix")+tab.getString("Messages.Max_Self");
					p.sendMessage(a.replaceAll("<level>", ""+level).replaceAll("&", "§"));
				} else {
					String a = m.getString("Prefix")+tab.getString("Messages.Exp_Self");
					p.sendMessage(a.replaceAll("<need>", ""+need).replaceAll("<exp>", ""+exp).replaceAll("<level>", ""+level).replaceAll("&", "§"));
				}
				return true;
			}  else if(args.length == 1 && args[0].equalsIgnoreCase(tab.getString("Options.Level.Usage"))) {
				
				if(!tab.getBoolean("Options.Level.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(job.equalsIgnoreCase("None")) {
					String a = m.getString("Prefix")+tab.getString("Messages.No_Job");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(!UltimateJobs.getLevelConfig().getCustomConfig().getBoolean("Use_Levels")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				int level = UltimateJobs.getData().getLevel(uuid, job);
				String exp = UltimateJobs.getLevelAPI().getFormatedExp(job, uuid);
				double need = UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId());
				if(UltimateJobs.getLevelAPI().PlayerIsMaxLevel(uuid, job)) {
					String a = m.getString("Prefix")+tab.getString("Messages.Max_Self");
					p.sendMessage(a.replaceAll("<need>", ""+need).replaceAll("<exp>", ""+exp).replaceAll("<level>", ""+level).replaceAll("&", "§"));
					
				} else {
					String a = m.getString("Prefix")+tab.getString("Messages.Level_Self");
					p.sendMessage(a.replaceAll("<need>", ""+need).replaceAll("<exp>", ""+exp).replaceAll("<level>", ""+level).replaceAll("&", "§"));
				}
				return true;
			}  else if(args.length == 1 && args[0].equalsIgnoreCase(tab.getString("Options.Stats.Usage"))) {
				
				
				if(!tab.getBoolean("Options.Stats.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(job.equalsIgnoreCase("None")) {
					String a = m.getString("Prefix")+tab.getString("Messages.No_Job");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
		 
				int level = UltimateJobs.getData().getLevel(uuid, job);
				String exp = UltimateJobs.getLevelAPI().getFormatedExp(job, uuid);
				double need = UltimateJobs.getLevelAPI().getNeed(job, p.getUniqueId());
				
				double my = UltimateJobs.getData().getCountOne(uuid, job);
				int b2 = UltimateJobs.getData().getCountTwo(uuid, job);
				
				String s2 = tab.getString("Messages.Stats_State."+job+".Message");
				String s3 = tab.getString("Messages.Stats_State."+job+".Name");
				
				List<String> a =  tab.getStringList("Messages.Stats_Other");
			 for(String b : a) {
				 p.sendMessage(b.replaceAll("<s>", s2).replaceAll("<p>", ""+UltimateJobs.getData().getPointsByJob(uuid, job)).replaceAll("<id>", s3).replaceAll("<name>", p.getName()).replaceAll("<d>", ""+b2).replaceAll("<m>", ""+UltimateJobs.getLevelAPI().getFormatedMoney(my)).replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)).replaceAll("<need>", ""+need).replaceAll("<exp>", ""+exp).replaceAll("<level>", ""+level).replaceAll("&", "§"));
			 }
			 return true;
			}  else if(args.length == 1 && args[0].equalsIgnoreCase(tab.getString("Options.Top.Usage"))) {
				
				if(!tab.getBoolean("Options.Top.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(!UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Enable_Top")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(!UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Top_Global")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
			 
				 
				p.openInventory(GlobalRankingGUI.load(p));
			 
			}	else if(args.length == 2 && args[0].equalsIgnoreCase(tab.getString("Options.Top.Usage"))
					&& args[1] != null) {
				
				if(!tab.getBoolean("Options.Top.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
			 
				if(!UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Enable_Top")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
			 
				if(!UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Top_per_Job")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
 
				String ar = args[1];
				
				if(JobAPI.fromDisplayWithOutColorToOrginalOD(ar).equalsIgnoreCase("NONE_FOUND")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Doesnt_Exist_Job");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				String id = JobAPI.fromDisplayWithOutColorToOrginalOD(ar);
				
				p.openInventory(JobRankingGUI.load(p, id, JobAPI.JobNameWithColor(id)));
			 
 
				return true;
			} else if(args.length == 2 && args[0].equalsIgnoreCase(tab.getString("Options.Exp.Usage"))
					&& args[1] != null) {
				
				if(!tab.getBoolean("Options.Exp.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(!UltimateJobs.getLevelConfig().getCustomConfig().getBoolean("Use_Levels")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
					String name = args[1];
				
				if(UltimateJobs.getData().getUUIDByName(name) == null) {
					String a = m.getString("Prefix")+tab.getString("Messages.Doesnt_Exist");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				UUID u = UUID.fromString(UltimateJobs.getData().getUUIDByName(name).toString());
				
				String pjob = JobAPI.getCurrentJob(u);
				
				if(pjob.equalsIgnoreCase("None")) {
					String a = m.getString("Prefix")+tab.getString("Messages.No_Job");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
			 
				
				 
				
				int level = UltimateJobs.getData().getLevel(""+u, pjob);
				String exp = UltimateJobs.getLevelAPI().getFormatedExp(pjob, ""+u);
				double need = UltimateJobs.getLevelAPI().getNeed(pjob, u);
				
				if(UltimateJobs.getLevelAPI().PlayerIsMaxLevel(""+u, pjob)) {
					String a = m.getString("Prefix")+tab.getString("Messages.Max_Other");
					p.sendMessage(a.replaceAll("<name>", name).replaceAll("<job>", ""+JobAPI.JobNameWithColor(pjob)).replaceAll("<level>", ""+level).replaceAll("&", "§"));
				} else {
				
				String a = m.getString("Prefix")+tab.getString("Messages.Exp_Other");
				p.sendMessage(a.replaceAll("<name>", name).replaceAll("<need>", ""+need).replaceAll("<exp>", ""+exp).replaceAll("<level>", ""+level).replaceAll("&", "§"));
				}
				return true;
			} else if(args.length == 2 && args[0].equalsIgnoreCase(tab.getString("Options.Level.Usage"))
					&& args[1] != null) {
				
				if(!tab.getBoolean("Options.Level.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				if(!UltimateJobs.getLevelConfig().getCustomConfig().getBoolean("Use_Levels")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
					String name = args[1];
				
				if(UltimateJobs.getData().getUUIDByName(name) == null) {
					String a = m.getString("Prefix")+tab.getString("Messages.Doesnt_Exist");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				UUID u = UUID.fromString(UltimateJobs.getData().getUUIDByName(name).toString());
				
				String pjob = JobAPI.getCurrentJob(u);
				
				if(pjob.equalsIgnoreCase("None")) {
					String a = m.getString("Prefix")+tab.getString("Messages.No_Job");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
			 
				
				 
				
				int level = UltimateJobs.getData().getLevel(""+u, pjob);
				String exp = UltimateJobs.getLevelAPI().getFormatedExp(pjob, ""+u);
				double need = UltimateJobs.getLevelAPI().getNeed(pjob, u);
				if(UltimateJobs.getLevelAPI().PlayerIsMaxLevel(""+u, pjob)) {
					String a = m.getString("Prefix")+tab.getString("Messages.Max_Other");
					p.sendMessage(a.replaceAll("<name>", name).replaceAll("<job>", ""+JobAPI.JobNameWithColor(pjob)).replaceAll("<level>", ""+level).replaceAll("&", "§"));
				} else {
				String a = m.getString("Prefix")+tab.getString("Messages.Level_Other");
				p.sendMessage(a.replaceAll("<name>", name).replaceAll("<need>", ""+need).replaceAll("<exp>", ""+exp).replaceAll("<level>", ""+level).replaceAll("&", "§"));
				}
				return true;
			}  else if(args.length == 2 && args[0].equalsIgnoreCase(tab.getString("Options.Stats.Usage")) 	&& args[1] != null) {
				
				
				
				if(!tab.getBoolean("Options.Stats.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				String name = args[1];
				
				if(UltimateJobs.getData().getUUIDByName(name) == null) {
					String a = m.getString("Prefix")+tab.getString("Messages.Doesnt_Exist");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				UUID u = UUID.fromString(UltimateJobs.getData().getUUIDByName(name).toString());
				
				String pjob = JobAPI.getCurrentJob(u);
				
				if(pjob.equalsIgnoreCase("None")) {
					String a = m.getString("Prefix")+tab.getString("Messages.No_Job");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
			 
				
				 
				
				int level = UltimateJobs.getData().getLevel(""+u, pjob);
				String exp = UltimateJobs.getLevelAPI().getFormatedExp(pjob, ""+u);
				double need = UltimateJobs.getLevelAPI().getNeed(pjob, u);
				
				double my = UltimateJobs.getData().getCountOne(""+
				u, pjob);
				int b2 = UltimateJobs.getData().getCountTwo(""+u, pjob);
				
				String s2 = tab.getString("Messages.Stats_State."+pjob+".Message");
				String s3 = tab.getString("Messages.Stats_State."+pjob+".Name");
				
				List<String> a =  tab.getStringList("Messages.Stats_Other");
			 for(String b : a) {
				 p.sendMessage(b.replaceAll("<s>", s2).replaceAll("<p>", ""+UltimateJobs.getData().getPointsByJob(""+u, pjob)).replaceAll("<id>", s3).replaceAll("<name>", name).replaceAll("<d>", ""+b2).replaceAll("<m>", ""+UltimateJobs.getLevelAPI().getFormatedMoney(my)).replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(pjob)).replaceAll("<need>", ""+need).replaceAll("<exp>", ""+exp).replaceAll("<level>", ""+level).replaceAll("&", "§"));
			 }
			 return true;
			} else if(args.length == 2 && args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage")) 
					&& args[1].equalsIgnoreCase(tab.getString("Options.Version.Usage"))) {
				
				if(!tab.getBoolean("Options.Admin.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!tab.getBoolean("Options.Version.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!p.hasPermission("Options.Version.Permission")) {
					String a = m.getString("Prefix")+m.getString("No_Perm");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				String c = m.getString("Prefix")+"§7This Server is running Version§8: §a§l"+UltimateJobs.getPlugin().getDescription().getVersion()+" §7with API§8: §b§l"+UltimateJobs.getPlugin().getDescription().getAPIVersion();
				p.sendMessage(c.replaceAll("&", "§"));
				
			} else if(args.length == 2 && args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage")) 
					&& args[1].equalsIgnoreCase(tab.getString("Options.Discord.Usage"))) {
				
				if(!tab.getBoolean("Options.Admin.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!tab.getBoolean("Options.Discord.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!p.hasPermission("Options.Discord.Permission")) {
					String a = m.getString("Prefix")+m.getString("No_Perm");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				String c = m.getString("Prefix")+"§7Join Our Discord§8: §a§lhttps://discord.ultimate-plugins.de";
				p.sendMessage(c.replaceAll("&", "§"));
				
			} else if(args.length == 3 && args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage")) 
					&& args[1].equalsIgnoreCase(tab.getString("Options.Reload.Usage"))
							&& args[2] != null) {
				
				if(!tab.getBoolean("Options.Admin.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!tab.getBoolean("Options.Reload.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!p.hasPermission("Options.Reload.Permission")) {
					String a = m.getString("Prefix")+m.getString("No_Perm");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				String file = args[2];
				File f = new File("plugins/UltimateJobs",  file);
				
				if(f.exists()) {
					
					ReloadHandler.reload(p, file);
					
				} else {
					String a = m.getString("Prefix")+"§cThat file doesnt exist. Plesae try again!";
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
			 
			} 	else if(args.length == 5 && args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage")) 
					&& args[1].equalsIgnoreCase(tab.getString("Options.SetExp.Usage"))
					&& args[2] != null
					&& args[3] != null
					&& args[4] != null) {
			 
				if(!tab.getBoolean("Options.Admin.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!tab.getBoolean("Options.SetExp.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!p.hasPermission("Options.SetExp.Permission")) {
					String a = m.getString("Prefix")+m.getString("No_Perm");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				String jofplayer = args[4];
				
				if(!JobAPI.IsJob(jofplayer)) {
					String a = m.getString("Prefix")+"§cThis Job doesnt exist!";
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				String l = args[3];

				double num;

				try {
				  num = Double.parseDouble(l);
				} catch (NumberFormatException ex){
					String a = m.getString("Prefix")+"§cError by Command Arg! (Message is not an int)";
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				 
				String name = args[2];
			 
				
					if(UltimateJobs.getData().getUUIDByName(name) == null) {
						String a = m.getString("Prefix")+tab.getString("Messages.Doesnt_Exist");
						p.sendMessage(a.replaceAll("&", "§"));
						return true;
					}
					
					UUID u = UUID.fromString(UltimateJobs.getData().getUUIDByName(name).toString());
				 
					UltimateJobs.getData().setExp(""+u, jofplayer, num);
					
			        String c = m.getString("Prefix")+"§7You have set "+name+"'s §7Exp to §b"+num+" Exp in Job§8: §3"+jofplayer;
					p.sendMessage(c.replaceAll("&", "§"));
					
			} else if(args.length == 5 && args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage")) 
					&& args[1].equalsIgnoreCase(tab.getString("Options.SetLevel.Usage"))
					&& args[2] != null
					&& args[3] != null
					&& args[4] != null) {
			 
				if(!tab.getBoolean("Options.Admin.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!tab.getBoolean("Options.SetLevel.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!p.hasPermission("Options.SetLevel.Permission")) {
					String a = m.getString("Prefix")+m.getString("No_Perm");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				String jofplayer = args[4];
				
				if(!JobAPI.IsJob(jofplayer)) {
					String a = m.getString("Prefix")+"§cThis Job doesnt exist!";
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				String l = args[3];

				int num;

				try {
				  num = Integer.parseInt(l);
				} catch (NumberFormatException ex){
					String a = m.getString("Prefix")+"§cError by Command Arg! (Message is not an int)";
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				 
				String name = args[2];
			 
				
					if(UltimateJobs.getData().getUUIDByName(name) == null) {
						String a = m.getString("Prefix")+tab.getString("Messages.Doesnt_Exist");
						p.sendMessage(a.replaceAll("&", "§"));
						return true;
					}
					
					UUID u = UUID.fromString(UltimateJobs.getData().getUUIDByName(name).toString());
					 
				 
					UltimateJobs.getData().setLevel(""+u, jofplayer, num);
					
					 
					
			        String c = m.getString("Prefix")+"§7You have set "+name+"'s §7Level to §b#"+num+" §7in Job§8: §3"+jofplayer;
					p.sendMessage(c.replaceAll("&", "§"));
					
			}  else if(args.length == 4 && args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage")) 
					&& args[1].equalsIgnoreCase(tab.getString("Options.AddJob.Usage"))
					&& args[2] != null
					&& args[3] != null ) {
			 
				if(!tab.getBoolean("Options.Admin.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!tab.getBoolean("Options.AddJob.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!p.hasPermission("Options.AddJob.Permission")) {
					String a = m.getString("Prefix")+m.getString("No_Perm");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				String jofplayer = args[3];
				
				String name = args[2];
				 
				
				if(UltimateJobs.getData().getUUIDByName(name) == null) {
					String a = m.getString("Prefix")+tab.getString("Messages.Doesnt_Exist");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				UUID u = UUID.fromString(UltimateJobs.getData().getUUIDByName(name).toString());
				 
				
				if(!JobAPI.IsJob(jofplayer)) {
					String a = m.getString("Prefix")+"§cThis Job doesnt exist!";
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
 

				if(UltimateJobs.getData().hasJob(u, jofplayer)) {
					String a = m.getString("Prefix")+"§cThe Player already owns that Job!";
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				 
				UltimateJobs.getData().addJobToBought(u, jofplayer);
				 
			        String c = m.getString("Prefix")+"§7You added §b"+name+" §7the Job§8: §a"+jofplayer;
					p.sendMessage(c.replaceAll("&", "§"));
					
			} else if(args.length == 4 && args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage")) 
					&& args[1].equalsIgnoreCase(tab.getString("Options.RemoveJob.Usage"))
					&& args[2] != null
					&& args[3] != null ) {
			 
				if(!tab.getBoolean("Options.Admin.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!tab.getBoolean("Options.RemoveJob.Enabled")) {
					String a = m.getString("Prefix")+tab.getString("Messages.Usage");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				if(!p.hasPermission("Options.RemoveJob.Permission")) {
					String a = m.getString("Prefix")+m.getString("No_Perm");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				String jofplayer = args[3];
				
				String name = args[2];
				 
				
				if(UltimateJobs.getData().getUUIDByName(name) == null) {
					String a = m.getString("Prefix")+tab.getString("Messages.Doesnt_Exist");
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				
				UUID u = UUID.fromString(UltimateJobs.getData().getUUIDByName(name).toString());
				 
				
				if(!JobAPI.IsJob(jofplayer)) {
					String a = m.getString("Prefix")+"§cThis Job doesnt exist!";
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
 

				if(!UltimateJobs.getData().hasJob(u, jofplayer)) {
					String a = m.getString("Prefix")+"§cThe Player dont owns that Job!";
					p.sendMessage(a.replaceAll("&", "§"));
					return true;
				}
				 
				UltimateJobs.getData().remoBought(u, jofplayer);
				 
			        String c = m.getString("Prefix")+"§7You removed §b"+name+" §7the Job§8: §a"+jofplayer;
					p.sendMessage(c.replaceAll("&", "§"));
					
			} else {
				String a = m.getString("Prefix")+tab.getString("Messages.Usage");
				p.sendMessage(a.replaceAll("&", "§"));
				return true;
			}
		return false;
	}
}
















