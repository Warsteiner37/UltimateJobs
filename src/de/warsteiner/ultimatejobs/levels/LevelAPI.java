package de.warsteiner.ultimatejobs.levels;

import java.text.DecimalFormat;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.warsteiner.ultimatejobs.UltimateJobs; 
import de.warsteiner.ultimatejobs.utils.JobAPI;
 
public class LevelAPI {

	public String getFormatedExp(String job, String uuid) {
		
		   DecimalFormat t = new DecimalFormat(UltimateJobs.getPlugin().getConfig().getString("Plugin.Format_Exp"));
			 
		   Double exp = UltimateJobs.getData().getExp(uuid, job);
		   
		 String b = t.format(exp).replaceAll(",", ".");
		
		 return b;
		 
	}
	
	public String getFormatedMoney(double money) {
		
		   DecimalFormat t = new DecimalFormat(UltimateJobs.getPlugin().getConfig().getString("Plugin.Format_Money"));
			 
		 
		   
		 String b = t.format(money).replaceAll(",", ".");
		
		 return b;
		 
	}
	
	public double getMoneyMulti(String job, String uuid, String level) {
		if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
			System.out.println("§fValue for Money Double§8: §aAs Job: "+job);
			System.out.println("§fValue for Money Double§8: §aAs UUID: "+uuid);
			System.out.println("§fValue for Money Double§8: §aAs Level: "+level);
			
		}
 
			List<String> list = UltimateJobs.getJobsConfig().getCustomConfig().getStringList(job +".Levels");
			if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
				System.out.println("§fValue for Money Double§8: §6As List: "+list);
			}
			if(UltimateJobs.getLevelConfig().getCustomConfig().getBoolean("Use_Levels") == false) {
				if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
					System.out.println("§fValue for Money Double§8: §4§lLevels Are Disabled");
				}
				return 0;
			}
			/* 11 */     for (String a : list) {
				
				/* 12 */       String[] b = a.split(":");
				/*    */       
				/* 14 */       String id = String.valueOf(b[0]);
				if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
					System.out.println("§fValue for Money Double§8: §cLevelMulti As Double: "+Double.valueOf(b[3]));
				}
				/* 16 */       if (level.equalsIgnoreCase(id)) {
					if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
						System.out.println("§fValue for Money Double§8: §4"+ Double.valueOf(b[3]).doubleValue()+" §fFor Job: §b"+job);
					} //- "1:&bLevel I:0:1.0:MONEY:0.20:0"
				/* 17 */         return Double.valueOf(b[3]);
				/*    */       }
				/*    */     } 
				/*    */
			return 0;      
	 
	}
	
	
	
	public static boolean  PlayerIsMaxLevel(String uuid, String job) {
		 
	  //  - "4:&cLevel IV:300:1.19:COMMAND_MAP:MAP_MINER:1"
	  //  - "MAX:&4&lMax Level:0"
		
		List<String> list = UltimateJobs.getJobsConfig().getCustomConfig().getStringList(job +".Levels");
		
		 //  for (int i = -1; i < list.size(); i++) {
		
		int level = UltimateJobs.getData().getLevel(uuid, job);
			
		String b = list.get(level);
		
		if(b.contains("MAX")) { 
			return true;
		}
 
		return false;
		
	}
	
	public double getNeed(String j, UUID s) {
		 
		String job = JobAPI.getCurrentJob(s);/* 23 */    

/* 24 */     int current_level = UltimateJobs.getData().getLevel(""+s, job);
 
		List<String> list = UltimateJobs.getJobsConfig().getCustomConfig().getStringList(job+".Levels");
		 
/* 28 */     for (String a : list) {
/* 29 */       String[] b = a.split(":");
/*    */       //  - "1:&bLevel I:0:1.0:MONEY:0.20"

			String id = b[0];

			if(!id.equalsIgnoreCase("MAX")) {
		 
/* 33 */      
			
			if(Integer.valueOf(id) == current_level+1) {
				  double need_exp = Double.valueOf(b[2]).intValue();
				  
				  return need_exp;
				  
			}
	}
}
return 0;
	}
	
	
}
