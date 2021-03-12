package de.warsteiner.ultimatejobs.utils;

import java.util.UUID;

import org.bukkit.configuration.file.FileConfiguration;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class PlaceUtils {

	public static String getPlayerJob(UUID uuid) {
		
		FileConfiguration l = UltimateJobs.getPlaceHolderConfig().getCustomConfig();
		
		if(JobAPI.getCurrentJob(uuid) == null) {
			return l.getString("No_Job");
		} else {
		
		String j = JobAPI.getCurrentJob(uuid);
		
		if(j.equalsIgnoreCase("None")) {
			return l.getString("No_Job");
		} else {
			return JobAPI.JobNameWithColor(j);
		}
		}
	}
	
		public static String getPlayerJobWithoutColor(UUID uuid) {
		
		FileConfiguration l = UltimateJobs.getPlaceHolderConfig().getCustomConfig();
		
		if(JobAPI.getCurrentJob(uuid) == null) {
			return l.getString("No_Job");
		} else {
		
		String j = JobAPI.getCurrentJob(uuid);
		
		if(j.equalsIgnoreCase("None")) {
			return l.getString("No_Job");
		} else {
			return j;
		}}
		
	}
		
		public static String getPlayerLevelInCurrentJob(UUID uuid) {
			
			FileConfiguration l = UltimateJobs.getPlaceHolderConfig().getCustomConfig();
			
			if(JobAPI.getCurrentJob(uuid) == null) {
				return l.getString("No_Level");
			} else {
			
			String j = JobAPI.getCurrentJob(uuid);
			if(j.equalsIgnoreCase("None")) {
				return l.getString("No_Level");
			} else {
				return ""+UltimateJobs.getData().getLevel(""+uuid, j);
			}
			}
		}
		
public static String getPlayerExpinCurrentJob(UUID uuid) {
			
			FileConfiguration l = UltimateJobs.getPlaceHolderConfig().getCustomConfig();
			
			if(JobAPI.getCurrentJob(uuid) == null) {
				return l.getString("No_Exp");
			} else {
			
			String j = JobAPI.getCurrentJob(uuid);
			if(j.equalsIgnoreCase("None")) {
				return l.getString("No_Exp");
			} else {
				return ""+UltimateJobs.getData().getExp(""+uuid, j);
			}
			}
		}
		
		public static String SkillPointsPerMode(UUID uuid) {
			FileConfiguration l = UltimateJobs.getPlaceHolderConfig().getCustomConfig();
			
			if(UltimateJobs.getSkillsConfig().getCustomConfig().getString("Mode").equalsIgnoreCase("PER_PLAYER")) {
				return ""+UltimateJobs.getData().getSkillPoints(""+uuid);
			}
			
			return l.getString("No_Points");
			
		}
 
	
}
