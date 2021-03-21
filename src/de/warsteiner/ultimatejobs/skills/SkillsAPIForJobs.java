package de.warsteiner.ultimatejobs.skills;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class SkillsAPIForJobs {

	public static String getNextLevelMulti(String name, String s, int current) {
		FileConfiguration cfg = UltimateJobs.getSkillsPerJob().getCustomConfig();
		 List<String> a = cfg.getStringList("SillJobs."+name+"."+s+".Levels");
		
		 for(String l : a) {
			 
			 String[] b = l.split(":");
			 
			 int level = Integer.valueOf(b[0]);
			 
			 if(current == level) {
				 return b[2];
			 }
			 
		}
		return "0";
		
	}
	
	public static String getNextLevelPrice(String name, String s, int current) {
		FileConfiguration cfg = UltimateJobs.getSkillsPerJob().getCustomConfig();
		 List<String> a = cfg.getStringList("SillJobs."+name+"."+s+".Levels");
		
		 for(String l : a) {
			 
			 String[] b = l.split(":");
			 
			 
			 int level = Integer.valueOf(b[0]);
			 
			 if(current == level) {
				 return b[1];
			 }
			 
		}
		return "0";
		
	}
	
 
	
}
