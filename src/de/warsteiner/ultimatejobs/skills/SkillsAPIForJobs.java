package de.warsteiner.ultimatejobs.skills;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;

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
	
	
	
	public static boolean isMaxLevel(String name, String s, int current) {
		FileConfiguration cfg = UltimateJobs.getSkillsPerJob().getCustomConfig();
		 List<String> a = cfg.getStringList("SillJobs."+name+"."+s+".Levels");
		 
	 
			 String l =a.get(a.size()-1);
		 
			 String[] b = l.split(":");
			 
			 int level = Integer.valueOf(b[0]);
	 
			 if(level == current) {
				 return true;
			 }
		 
		return false;
	}
	
	public static String getItemStackForDupe(String mat) {
		
		FileConfiguration cfg = UltimateJobs.getUtil().getCustomConfig();
		 List<String> a = cfg.getStringList("ItemListForDoubler");
		
		 for(String g : a) {
			 String[] b = g.split(":");
			 
			 String org = b[0].toUpperCase();
			 
			 if(org.equalsIgnoreCase(mat.toUpperCase())) {
				 return b[1];
			 }
			 
		 }
		return "NONE";
	}
	
	public static String getItemStackForSkulls(String mat) {
		
		FileConfiguration cfg = UltimateJobs.getUtil().getCustomConfig();
		 List<String> a = cfg.getStringList("Skulls");
		
		 for(String g : a) {
			 String[] b = g.split(":");
			 
			 String org = b[0].toUpperCase();
			 
			 if(org.equalsIgnoreCase(mat.toUpperCase())) {
				 return b[1];
			 }
			 
		 }
		return "NONE";
	}
	
public static String getItemStackForReplant(String mat) {
		
		FileConfiguration cfg = UltimateJobs.getUtil().getCustomConfig();
		 List<String> a = cfg.getStringList("ItemListForAutoReplant");
		
		 for(String g : a) {
			 String[] b = g.split(":");
			 
			 String org = b[0].toUpperCase();
			 
			 if(org.equalsIgnoreCase(mat.toUpperCase())) {
				 return b[1];
			 }
			 
		 }
		return "NONE";
	}
	
	
	public static String getFoodLevel(int level, String job, String t) {
	    List<String> b2 = UltimateJobs.getSkillsPerJob().getCustomConfig().getStringList("SillJobs."+job+"."+t+".Levels");
		   
		   for(String c : b2) {
			   String[] d = c.split(":");
			   
			   if(Integer.valueOf(d[0]) == level) {
				   int time = Integer.valueOf(d[3]);
				   
				   return ""+time;
				 
			   }
			   
		   }
		return "0";
	}
	
	public static String getRadius(int level, String job) {
	    List<String> b2 = UltimateJobs.getSkillsPerJob().getCustomConfig().getStringList("SillJobs."+job+"."+"CSpeed"+".Levels");
		   
		   for(String c : b2) {
			   String[] d = c.split(":");
			   
			   if(Integer.valueOf(d[0]) == level) {
				   int time = Integer.valueOf(d[3]);
				   
				   return ""+time;
				 
			   }
			   
		   }
		return "0";
	}
	
	public static String getTime(int level, String job, String type) {
	    List<String> b2 = UltimateJobs.getSkillsPerJob().getCustomConfig().getStringList("SillJobs."+job+"."+type+".Levels");
		   
		   for(String c : b2) {
			   String[] d = c.split(":");
			   
			   if(Integer.valueOf(d[0]) == level) {
				   int time = Integer.valueOf(d[5]);
				   
				   return ""+time;
				 
			   }
			   
		   }
		return "0";
	}
	
	public static String getStr(int level, String job, String type) {
	    List<String> b2 = UltimateJobs.getSkillsPerJob().getCustomConfig().getStringList("SillJobs."+job+"."+type+".Levels");
		   
		   for(String c : b2) {
			   String[] d = c.split(":");
			   
			   if(Integer.valueOf(d[0]) == level) {
				 
				   int st = Integer.valueOf(d[4])+1;
				   return ""+st;
				 
			   }
			   
		   }
		return "0";
	}
	
	public static int getPosOfSkillInList(String name, String s) {
		FileConfiguration cfg = UltimateJobs.getSkillsPerJob().getCustomConfig();
		 List<String> a = cfg.getStringList("SillJobs."+name+".Use");
		
		 for (int i = 0; i < a.size(); i++) {
			 
			 String b = a.get(i);
			 
			 
			 if(b.toUpperCase().equalsIgnoreCase(s.toUpperCase())) {
				 return i;
			 }
			 
		}
		return 0;
		
	}
	
	public static String getChance(String name, String s, int current) {
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
	
	public static boolean isEnabled() {
		if(UltimateJobs.getSkillsMainConfig().getCustomConfig().getBoolean("Enable_Skills")) {
			  if(UltimateJobs.getSkillsMainConfig().getCustomConfig().getString("Mode").toUpperCase().equalsIgnoreCase("PER_JOB")) {
				return true;
		}
	}
		return false;
	}
	
	public static boolean isSkillEnabled(String skill, String job) {
		
		 List<String> list = UltimateJobs.getSkillsPerJob().getCustomConfig().getStringList("SillJobs."+job+".Use");
		 
		 for(String b : list) {
			 if(b.toUpperCase().equalsIgnoreCase(skill.toUpperCase())) {
				 return true;
			 }
		 }
		
		return false;
		
	}
	
 
	
}
