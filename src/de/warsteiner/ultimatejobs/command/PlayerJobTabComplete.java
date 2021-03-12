package de.warsteiner.ultimatejobs.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;

public class PlayerJobTabComplete  implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender s, Command arg1, String arg2, String[] args) {
		
		Player p = (Player) s;
		
		ArrayList<String> l = new ArrayList<String>();
		
		FileConfiguration tab = UltimateJobs.getCommandConfig().getCustomConfig();
		
		Collection<? extends Player> b = Bukkit.getOnlinePlayers();
		
		if(args.length == 1) {
			if(p.hasPermission(tab.getString("Options.Admin.Permission"))) {
				if(tab.getBoolean("Options.Admin.Enabled")) {
					l.add(tab.getString("Options.Admin.Usage"));
				}
			}
			
			if(tab.getBoolean("Options.Help.Enabled")) {
				l.add(tab.getString("Options.Help.Usage"));
			}
			if(tab.getBoolean("Options.Quests.Enabled")) {
				if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests")) {
				l.add(tab.getString("Options.Quests.Usage"));
				}
			}
			if(UltimateJobs.getSkillsConfig().getCustomConfig().getBoolean("Enable_Skills")) {
				if(tab.getBoolean("Options.Skills.Enabled")) {
					l.add(tab.getString("Options.Skills.Usage"));
				}
			}
			if(UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Enable_Top")) {
				if(tab.getBoolean("Options.Top.Enabled")) {
					l.add(tab.getString("Options.Top.Usage"));
				}
			}
 
			if(!UltimateJobs.getData().currentjob(p.getUniqueId()).equalsIgnoreCase("None")) {
				if(UltimateJobs.getLevelConfig().getCustomConfig().getBoolean("Use_Levels")) {
				if(tab.getBoolean("Options.Exp.Enabled")) {
					l.add(tab.getString("Options.Exp.Usage"));
				}
				if(tab.getBoolean("Options.Levels.Enabled")) {
					l.add(tab.getString("Options.Levels.Usage"));
				}
				if(tab.getBoolean("Options.Level.Enabled")) {
					l.add(tab.getString("Options.Level.Usage"));
				}
				}
				if(tab.getBoolean("Options.Stats.Enabled")) {
					l.add(tab.getString("Options.Stats.Usage"));
				}
			 
				if(tab.getBoolean("Options.Leave.Enabled")) {
					l.add(tab.getString("Options.Leave.Usage"));
				}
			}
			
			 
			
		}  else if(args.length == 2) {
			 
			if(tab.getBoolean("Options.Exp.Enabled")) { 
				if(args[0].equalsIgnoreCase(tab.getString("Options.Exp.Usage"))) {
					
					for(Player oo : b) {
						l.add(oo.getName());
					}
					 
				}
			}
			if(tab.getBoolean("Options.Level.Enabled")) { 
				if(args[0].equalsIgnoreCase(tab.getString("Options.Level.Usage"))) {
					
					for(Player oo : b) {
						l.add(oo.getName());
					}
					 
				}
			}
			if(tab.getBoolean("Options.Stats.Enabled")) { 
				if(args[0].equalsIgnoreCase(tab.getString("Options.Stats.Usage"))) {
					
					for(Player oo : b) {
						l.add(oo.getName());
					}
					 
				}
			}
			
			if(tab.getBoolean("Options.Top.Enabled")) {
				if(UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Enable_Top")) {
				if(UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Top_per_Job")) {
						if(args[0].equalsIgnoreCase(tab.getString("Options.Top.Usage"))) {
						
							List<String> a = JobAPI.getJobsAsCustomDisplay();
							for(String d : a) {
								l.add(d);
							}
					}
				}
				}
			}
		  
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
		 
				if(p.hasPermission("Options.SetExp.Permission")) {
				 
					if(tab.getBoolean("Options.SetExp.Enabled")) { 
				    
								l.add(tab.getString("Options.SetExp.Usage"));
						 
						 
					}
				}
			}
			
			  
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
		 
				if(p.hasPermission("Options.Reload.Permission")) {
				 
					if(tab.getBoolean("Options.Reload.Enabled")) { 
				    
								l.add(tab.getString("Options.Reload.Usage"));
						 
						 
					}
				}
			}
			
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
				 
				if(p.hasPermission("Options.SetLevel.Permission")) {
				 
					if(tab.getBoolean("Options.SetLevel.Enabled")) { 
				    
								l.add(tab.getString("Options.SetLevel.Usage"));
						 
						 
					}
				}
			}
			
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
				 
				if(p.hasPermission("Options.Discord.Permission")) {
				 
					if(tab.getBoolean("Options.Discord.Enabled")) { 
				    
								l.add(tab.getString("Options.Discord.Usage"));
						 
						 
					}
				}
			}
			
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
				 
				if(p.hasPermission("Options.Version.Permission")) {
				 
					if(tab.getBoolean("Options.Version.Enabled")) { 
				    
								l.add(tab.getString("Options.Version.Usage"));
						 
						 
					}
				}
			}
			
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
				 
				if(p.hasPermission("Options.AddJob.Permission")) {
				 
					if(tab.getBoolean("Options.AddJob.Enabled")) { 
				    
								l.add(tab.getString("Options.AddJob.Usage"));
						 
						 
					}
				}
			}
			
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
				 
				if(p.hasPermission("Options.RemoveJob.Permission")) {
				 
					if(tab.getBoolean("Options.RemoveJob.Enabled")) { 
				    
								l.add(tab.getString("Options.RemoveJob.Usage"));
						 
						 
					}
				}
			}
			
		}      else if(args.length == 3) {
			
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
				if(args[1].equalsIgnoreCase(tab.getString("Options.Reload.Usage"))) {
					if(tab.getBoolean("Options.Reload.Enabled")) { 
						l.add("chat.yml");
						l.add("Command.yml");
						 
						l.add("Jobs.yml");
						l.add("Levels.yml");
						l.add("MainGUI.yml");
						l.add("Messages.yml");
						l.add("PlaceHolders.yml");
						l.add("RewardHandler.yml");
						l.add("Skills.yml");
						l.add("LevelGUI.yml");
						l.add("quests.yml");
						l.add("top.yml");
						l.add("sounds.yml");
					}
				}
			}
			
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
				if(args[1].equalsIgnoreCase(tab.getString("Options.SetExp.Usage"))
						|| args[1].equalsIgnoreCase(tab.getString("Options.SetLevel.Usage"))
						|| args[1].equalsIgnoreCase(tab.getString("Options.AddJob.Usage"))
						|| args[1].equalsIgnoreCase(tab.getString("Options.RemoveJob.Usage"))) {
					for(Player oo : b) {
						l.add(oo.getName());
					}
				}
				 
			}
			
		}     else if(args.length == 4) {
			if(args[0].equalsIgnoreCase(tab.getString("Options.Admin.Usage"))) {
				if(args[1].equalsIgnoreCase(tab.getString("Options.AddJob.Usage"))
						|| args[1].equalsIgnoreCase(tab.getString("Options.RemoveJob.Usage"))) {
					List<String> a = JobAPI.getJobsAsConfigID();
					for(String d : a) {
						l.add(d);
					}
				}
			}		
		}
		
		 
		return l;
	}

}















