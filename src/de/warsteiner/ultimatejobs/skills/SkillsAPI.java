package de.warsteiner.ultimatejobs.skills;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class SkillsAPI {

	public double getMoneyMulti(String uuid) {
		if(UltimateJobs.getSkillsMainConfig().getCustomConfig().getBoolean("Enable_Skills")) {
			if(UltimateJobs.getPerPlayerSkillsConfig().getCustomConfig().getStringList("Design.Skills").contains("MONEY")) {
				return Double.valueOf(getNextLevelMulti("MONEY", UltimateJobs.getData().getSkillPointLevel(uuid, "MONEY")));
			}
		}
		return 0;
	}
	
	public double getExpMulti(String uuid) {
		if(UltimateJobs.getSkillsMainConfig().getCustomConfig().getBoolean("Enable_Skills")) {
			if(UltimateJobs.getPerPlayerSkillsConfig().getCustomConfig().getStringList("Design.Skills").contains("LEVELEXP")) {
				return Double.valueOf(getNextLevelMulti("LEVELEXP", UltimateJobs.getData().getSkillPointLevel(uuid, "LEVELEXP")));
			}
		}
		return 0;
	}
 
	public double getMultiByName(String name,  String uuid) {
		if(name.equalsIgnoreCase("LEVELEXP")) { return getExpMulti( uuid); }
		if(name.equalsIgnoreCase("MONEY")) { return getMoneyMulti( uuid); } 
		return 0;
	}//1:0:0.00
	
	public static String getNextLevelMulti(String name, int la) {
		FileConfiguration cfg = UltimateJobs.getPerPlayerSkillsConfig().getCustomConfig();
		 List<String> a = cfg.getStringList("Items."+name+".SkillLevels");
		
		 for(String l : a) {
			 
			 String[] b = l.split(":");
			 
			 int level = Integer.valueOf(b[0]);
			 
			 if(la == level) {
				 return b[2];
			 }
			 
		}
		return "0";
		
	}
	
	public static String getNextLevelPrice(String name, int la) {
		FileConfiguration cfg = UltimateJobs.getPerPlayerSkillsConfig().getCustomConfig();
		 List<String> a = cfg.getStringList("Items."+name+".SkillLevels");
		
		 for(String l : a) {
			 
			 String[] b = l.split(":");
			 
			 int level = Integer.valueOf(b[0]);
			 
			 if(la == level) {
				 return b[1];
			 }
			 
		}
		return "0";
		
	}
	
	
}
