/*     */ package de.warsteiner.ultimatejobs.utils;
/*     */ 
/*     */ import de.warsteiner.ultimatejobs.UltimateJobs;

import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JobAPI
/*     */ {
 
/*     */ 
			
			public static boolean IsSupported(String job, String s, boolean check) {
				if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
					System.out.println("§fChecking Block§8: §4"+s+" §fFor Job: §b"+job);
				}
				List<String> list = UltimateJobs.getJobsConfig().getCustomConfig().getStringList(job+".IDS");
				
				for(String a : list) {
					
					String[] c = a.split(":");
					
					String mat = c[0];
					
					if(check) {
						if(Material.getMaterial(mat) == null) {
							/* 113 */      if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
				                 Bukkit.getConsoleSender().sendMessage("§4§lWarning§8: §7There is an Error. #2");
				}
							return false;
						}
					}
					
					if(s.equalsIgnoreCase(mat)) {
						/* 113 */      if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
			                 Bukkit.getConsoleSender().sendMessage("§4§lWarning§8: §7This is a supported id: "+s);
			}
						return true;
					}
					
				}
 
					/* 113 */      if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
		                 Bukkit.getConsoleSender().sendMessage("§4§lWarning§8: §7There is an Error. #3");
		}
		 
				return false;
				
				
				
				
			}
			
			public static List<String> getJobsAsCustomDisplay() {
				FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();
				
				List<String> List_jobs = jobs.getStringList("Options.GUI");
				
				ArrayList<String> nl  = new ArrayList<String>();
				
				for(String b : List_jobs) {
					nl.add(jobs.getString(b+".Display"));
				}
				
				return nl;
				
			}
			
			public static List<String> getJobsAsConfigID() {
				FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();
				
				List<String> List_jobs = jobs.getStringList("Options.GUI");
			 
				return List_jobs;
				
			} 
			
			public static List<String> getSupportedList(String job) {
				return UltimateJobs.getJobsConfig().getCustomConfig().getStringList(job+".IDS");
			}
	
	
	
			public static String FromDisPlaynameToJobID(String dis) {
				FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();

				List<String> list_of_jobs = jobs.getStringList("Options.GUI");
				
				for(String j : list_of_jobs) {
					 String color = jobs.getString(j+".Color");
					 String display = jobs.getString(j+".Display");
					 
					 String finaldis = color+display;
					 
					 if(finaldis.replaceAll("&", "§").equalsIgnoreCase(dis)) {
						 return j;
					 }
					 
				}
 
				return "NONE_FOUND";
				
			}
			
			public static String getJobsDisplays(String dis) {
				FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();

				List<String> list_of_jobs = jobs.getStringList("Options.GUI");
				
				for(String j : list_of_jobs) {
					 String color = jobs.getString(j+".Color");
					 String display = jobs.getString(j+".Display");
					 
					 String finaldis = color+display;
					 
					 if(finaldis.replaceAll("&", "§").equalsIgnoreCase(dis)) {
						 return j;
					 }
					 
				}
 
				return "NONE_FOUND";
				
			}
			
			public static String fromDisplayWithOutColorToOrginalOD(String id) {
				FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();

				List<String> list_of_jobs = jobs.getStringList("Options.GUI");
				
				for(String j : list_of_jobs) {
			 
					 String display = jobs.getString(j+".Display");
					  
					 if(id.replaceAll("&", "§").equalsIgnoreCase(display)) {
						 return j;
					 }
					 
				}
 
				return "NONE_FOUND";
			}
			
			public static String fromOriginalConfigIDToCustomDisplay(String id) {
				FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();

				List<String> list_of_jobs = jobs.getStringList("Options.GUI");
				
				for(String j : list_of_jobs) {
			  
					  
					 if(j.equalsIgnoreCase(id)) {
						 

						 String color = jobs.getString(j+".Color");
						 String display = jobs.getString(j+".Display");
						 
						 String finaldis = color+display;
						 
						 return finaldis;
					 }
					 
				}
 
				return "NONE_FOUND";
			}
			
			public static String JobNameWithColor(String dis) {
				FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();
 
					 String color = jobs.getString(dis+".Color");
					 String display = jobs.getString(dis+".Display");
					 
					 String finaldis = color+display;
					  
						 return finaldis;
					  
			 
			}
			
			public static boolean IsJob(String or_id) {
				
				FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();

				List<String> list_of_jobs = jobs.getStringList("Options.GUI");
				
				if(list_of_jobs.contains(or_id)) {
					return true;
				}
 
				return false;
				
			}
	
 
/*     */   public static void setJobActive(String job, Player p, UUID uuid) {
/*  76 */     UltimateJobs.getData().setJobIn(uuid, job); 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setallJobsNotActive(Player p, UUID uuid) {
/*  83 */     UltimateJobs.getData().setJobIn(uuid, "None"); 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setJobBought(String job, Player p, UUID uuid) {
/*  90 */     UltimateJobs.getData().addJobToBought(uuid, job);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getJobBought(UUID id, String job) {
/*  96 */     if (UltimateJobs.getData().hasJob(id, job)) {
/*  97 */       return "YES";
/*     */     }
/*  99 */     return "ERROR";
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInJob(UUID uuid, String name) {
/* 104 */     if (getCurrentJob(uuid).equalsIgnoreCase(name)) {
/* 105 */       return true;
/*     */     }
/*     */     
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getCurrentJob(UUID id) {
/* 113 */     return UltimateJobs.getData().getCurrentJob(id);
/*     */   }
 
/*     */   public static String getJobActive(UUID id, String job) {
/* 126 */     if (!UltimateJobs.getData().getCurrentJob(id).equalsIgnoreCase("None") && 
/* 127 */       UltimateJobs.getData().getCurrentJob(id).equalsIgnoreCase(job)) {
/* 128 */       return "YES";
/*     */     }
 
/*     */     
/* 132 */     return "ERROR";
/*     */   }
/*     */ }

 