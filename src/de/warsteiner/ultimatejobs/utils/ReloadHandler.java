package de.warsteiner.ultimatejobs.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class ReloadHandler {
	
	public static void reload(Player p, String file) {
		
		String f = file.toLowerCase().replaceAll(".yml", "");
		
		 
		//	m.createCustomConfig();
 
		//levels.createCustomConfig();
		//rconfig.createCustomConfig();
		//skills.createCustomConfig();
		
		String m = "§aYou reloaded the Config§8: §e"+file;
		p.sendMessage(m);
		if(f.equalsIgnoreCase("command")) {
			UltimateJobs.getCommandConfig().createCustomConfig();
			return;
		} else if(f.equalsIgnoreCase("maingui")) {
			UltimateJobs.getMainGUIConfig().createCustomConfig();
			return;
		} else if(f.equalsIgnoreCase("jobs")) {
			UltimateJobs.getJobsConfig().createCustomConfig();
			return;
		} else if(f.equalsIgnoreCase("placeholders")) {
			UltimateJobs.getPlaceHolderConfig().createCustomConfig();
			return;
		}else if(f.equalsIgnoreCase("levels")) {
			UltimateJobs.getLevelConfig().createCustomConfig();
			return;
		}else if(f.equalsIgnoreCase("messages")) {
			UltimateJobs.MessageHandler().createCustomConfig();
			return;
		}else if(f.equalsIgnoreCase("rewardhandler")) {
			UltimateJobs.getRewardConfig().createCustomConfig();
			return;
		} else if(f.equalsIgnoreCase("skills")) {
			UltimateJobs.getSkillsConfig().createCustomConfig();
			return;
		} else if(f.equalsIgnoreCase("levelgui")) {
			UltimateJobs.getLevelGUI().createCustomConfig();
			return;
		}
		
	}

}
