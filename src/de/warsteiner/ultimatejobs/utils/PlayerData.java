/*     */ package de.warsteiner.ultimatejobs.utils;
/*     */  
/*     */ import java.io.File;
import java.util.ArrayList;
/*     */ import java.util.List;
import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.QuestDataChangeEvent;
/*     */ 
/*     */ 
/*     */ public class PlayerData
/*     */ {
/*     */   public File cfile;
/*     */   public FileConfiguration config;
/*     */   
/*     */   public void createPlayer(UUID uuid) {
/*  19 */     get().set("Job." + uuid + ".Current", "None");
			get().set("Job." + uuid + ".SkillPoints", 1);
			get().set("Job." + uuid + ".LEVELEXP.Level", 1);
			get().set("Job." + uuid + ".MONEY.Level", 1);
			get().set("Job." + uuid + ".VANILLAEXP.Level", 1); 
/*  22 */     save();
/*     */   }

/*     */   public void setPage(String page, String uuid) {
/*  26 */     get().set("Player."+JobAPI.getCurrentJob(UUID.fromString(uuid.toString()))+".Job."+uuid+".Page", page);
/*     */     
/*  28 */     save();
/*     */   }
/*     */   
/*     */   public String getPage(String uuid) {
/*  32 */     return get().getString("Player."+JobAPI.getCurrentJob(UUID.fromString(uuid.toString()))+".Job."+uuid+".Page");
/*     */   }

			public boolean existPlayerPageData(String uuid) {
				if(get().contains("Player."+JobAPI.getCurrentJob(UUID.fromString(uuid.toString()))+".Job."+uuid+".Page")) {
					return true;
				}
				return false;
			}
/*     */   
/*     */   public void setUUID(String name, String uuid) {
/*  26 */     get().set("Job.Fetcher.B." + name + ".C", uuid);
/*     */     
/*  28 */     save();
/*     */   }

public int getGlobalPoints(String uuid) { 
/*  63 */     return get().getInt("Job." + uuid + ".Global"+".Points");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGlobalPoints(String uuid,int a) {
/*  68 */     get().set("Job." + uuid + ".Global"+".Points", a);
/*     */     
/*  70 */     save();
/*     */   }

public int getPointsByJob(String uuid, String job) { 
/*  63 */     return get().getInt("Job." + uuid + "."+job+".Points");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPointsByJob(String uuid,String job, int a) {
/*  68 */     get().set("Job." + uuid + "."+job+".Points", a);
/*     */     
/*  70 */     save();
/*     */   }


			public int getSkillPointLevel(String uuid, String skill) { 
/*  63 */     return get().getInt("Job." + uuid + "."+skill+".Level");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillPointLevel(String uuid,String skill, int a) {
/*  68 */     get().set("Job." + uuid + "."+skill+".Level", a);
/*     */     
/*  70 */     save();
/*     */   }

/*     */   public int getSkillPoints(String uuid) { 
/*  63 */     return get().getInt("Job." + uuid + ".SkillPoints");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkillPoints(String uuid, int a) {
/*  68 */     get().set("Job." + uuid + ".SkillPoints", a);
/*     */     
/*  70 */     save();
/*     */   }
/*     */   
/*     */   public String getUUIDByName(String name) {
/*  32 */     return get().getString("Job.Fetcher.B." + name + ".C");
/*     */   }
/*     */   
/*     */   public String getNameByUUID(String name) {
/*  36 */     return get().getString("Job.Fetcher.A." + name + ".S");
/*     */   }
/*     */   
/*     */   public void setName(String name, String uuid) {
/*  40 */     get().set("Job.Fetcher.A." + uuid + ".S", name);
/*     */     
/*  42 */     save();
/*     */   }
/*     */   
/*     */   public int getLevel(String uuid, String job) {
/*  46 * 
/*  49 */     return get().getInt(job+"." + uuid + ".Level");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLevel(String uuid, String job, int level) {
/*  54 */     get().set(job+"." + uuid + ".Level", Integer.valueOf(level));
/*     */     
/*  56 */     save();
/*     */   }
/*     */   
/*     */   public double getExp(String uuid, String job) {
/*  60 */     if (get().getDouble(job+"." + uuid + ".Exp") == 0) {
/*  61 */       return 0;
/*     */     }
/*  63 */     return get().getDouble(job+"." + uuid + ".Exp");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setExp(String uuid, String job, double d) {
/*  68 */     get().set(job+"." + uuid + ".Exp", Double.valueOf(d));
/*     */     
/*  70 */     save();
/*     */   }
/*     */   
/*     */   public boolean existplayer(UUID uuid) {
/*  74 */     if (get().getString("Job." + uuid + ".Current") != null) {
/*  75 */       return true;
/*     */     }
/*  77 */     return false;
/*     */   }
/*     */   
/*     */   public boolean existplayer(String uuid) {
/*  81 */     if (get().getString("Job." + uuid + ".Current") != null) {
/*  82 */       return true;
/*     */     }
/*  84 */     return false;
/*     */   }
/*     */   
/*     */   public boolean hasJob(UUID uuid, String job) {
/*  88 */     List<String> list = get().getStringList("Job." + uuid + ".Has.Name");
/*  89 */     if (list.contains(job)) {
/*  90 */       return true;
/*     */     }
/*  92 */     return false;
/*     */   }

		public long getCool(String uuid) {
			return get().getLong("Quests."+uuid+".Update");
		}

		public String getQuestByID(String uuid, int id) {
			return get().getString("Quests."+uuid+".ID."+id+".Current");
		}
		
		public int getQuestsInt(String uuid, int id) {
			return get().getInt("Quests."+uuid+".ID."+id+".Int");
		}
		
		public boolean getQuestsBoolean(String uuid, int id) {
			return get().getBoolean("Quests."+uuid+".ID."+id+".Done");
		}
		
		  public void setQuestInt(String uuid, int id, int ints) {
			  /*  68 */     get().set("Quests."+uuid+".ID."+id+".Int", ints);
			  /*     */     
			  /*  70 */     save();
			  /*     */   }
		  
		  public void setQuestBoolean(String uuid, int id, boolean ints) {
			  /*  68 */     get().set("Quests."+uuid+".ID."+id+".Done", ints);
			  /*     */     
			  /*  70 */     save();
			  /*     */   }
			  /*     */   
		
			public void QuestActionCount(Player p,  String idofitem) {
				FileConfiguration cfg = UltimateJobs.getQuestAPI().getCustomConfig();
				List<String> slots = cfg.getStringList("Quests.Slots");
				 
				 for (int i = 0; i < slots.size(); i++) {
					 	String id = UltimateJobs.getData().getQuestByID(""+p.getUniqueId(), i);
						/*     */
						if(id != null) { 
							
							String[] action = cfg.getString("Quests."+id+".Action").split(":");
							
							String job = action[0];
							
							if(job.equalsIgnoreCase(JobAPI.getCurrentJob(p.getUniqueId()))) {
								if(action[2].equalsIgnoreCase(idofitem)) {
									int old = getQuestsInt(""+p.getUniqueId(), i);
									setQuestInt(""+p.getUniqueId(), i, old+1);
								 
									new QuestDataChangeEvent(p, job, i, id);
									save();
								}
							}
							
						}
				 }
			}
	 
		
			public void UpdateQuests(String uuid, Player p) {
				FileConfiguration pd = get();
				FileConfiguration cfg = UltimateJobs.getQuestAPI().getCustomConfig();
				pd.set("Quests."+uuid+".Update",System.currentTimeMillis());
				
				ArrayList<String> newq = new ArrayList<String>();
				
				List<String> quests = cfg.getStringList("Quests.Used");
				List<String> slots = cfg.getStringList("Quests.Slots");
				
				 for (int i = 0; i < slots.size(); i++) {
					 
					  Random ran = new Random();
					 
					 int random = ran.nextInt(quests.size());
					 
					 int rly = random;
					 
 
					 newq.add(quests.get(rly));
					 pd.set("Quests."+uuid+".ID."+i+".Current", quests.get(rly));
					 pd.set("Quests."+uuid+".ID."+i+".Int", 0);
					 pd.set("Quests."+uuid+".ID."+i+".Done", false);
					 quests.remove(quests.get(rly));
 
				 
					 save();
				 }
				
				//LongSlots
				
				save();
				
			}
			
			
 
/*     */   
 
/*     */   public String currentjob(UUID uuid) {
/* 100 */     return get().getString("Job." + uuid + ".Current");
/*     */   }
/*     */ 
/*     */   
/*     */   public void SetEarnedMoneyWithID(String uuid, int jobid, double points) {
/* 105 */     get().set("Job." + uuid + "." + jobid + ".Money", Double.valueOf(points));
/* 106 */     save();
/*     */   }
/*     */   
/*     */   public double GetEarnedWithIDD(String uuid, int jobid) {
/* 110 */     return get().getDouble("Job." + uuid + "." + jobid + ".Money");
/*     */   }
/*     */   
/*     */   public String getname(String uuid) {
/* 114 */     return get().getString("Job." + uuid + ".PlayerName");
/*     */   }

/*     */   
/*     */   public double getCountOne(String uuid, String job) { 
/*  63 */     return get().getDouble(job+"." + uuid + ".Stats.Count1");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCountOne(String uuid, String job, double d) {
/*  68 */     get().set(job+"." + uuid + ".Stats.Count1", Double.valueOf(d));
/*     */     
/*  70 */     save();
/*     */   }

/*     */   public int getCountTwo(String uuid, String job) { 
/*  63 */     return get().getInt(job+"." + uuid + ".Stats.Count2");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCountTwo(String uuid, String job, int d) {
/*  68 */     get().set(job+"." + uuid + ".Stats.Count2", d);
/*     */     
/*  70 */     save();
/*     */   }
/*     */ 

/*     */   
/*     */   public void addJobToBought(UUID uuid, String job) {
/* 119 */     List<String> list = get().getStringList("Job." + uuid + ".Has.Name"); 
/* 121 */     list.add(job);
/* 122 */     get().set("Job." + uuid + ".Has.Name", list);
/* 123 */     get().set(job+"." + uuid + ".Level", 1);
			get().set(job+"." + uuid + ".Exp", 0);
			/* 123 */     get().set(job+"." + uuid + ".Stats.Count1", 0);
			get().set(job+"." + uuid + ".Stats.Count2", 0);
/* 124 */     save();
/*     */   }

/*     */   public void remoBought(UUID uuid, String job) {
/* 119 */     List<String> list = get().getStringList("Job." + uuid + ".Has.Name"); 
/* 121 */     list.remove(job);
/* 122 */     get().set("Job." + uuid + ".Has.Name", list);
/* 123 */     get().set(job+"." + uuid + ".Level", 1);
			get().set(job+"." + uuid + ".Exp", 0);
			/* 123 */     get().set(job+"." + uuid + ".Stats.Count1", 0);
			get().set(job+"." + uuid + ".Stats.Count2", 0);
/* 124 */     save();
/*     */   }
/*     */   
/*     */   public void setJobIn(UUID uuid, String data) {
/* 128 */     get().set("Job." + uuid + ".Current", data);
/* 129 */     save();
/*     */   }
/*     */   

/*     */   public List<String> getPlayerList() {
	/*  88 */     List<String> list = get().getStringList("Plugin.Players");
	/*  89 * 
	/*  92 */     return list;
	/*     */   }
 
/*     */   public boolean PlayerIsInPlayerList(String name) {
	/*  88 */     List<String> list = get().getStringList("Plugin.Players");
	/*  89 */     if (list.contains(name)) {
	/*  90 */       return true;
	/*     */     }
	/*  92 */     return false;
	/*     */   }
/*     */   public void addPlayerToPlayerList(String name) {
	/* 119 */     List<String> list = get().getStringList("Plugin.Players"); 
	/* 121 */     list.add(name);
	/* 122 */     get().set("Plugin.Players", list); 
	/* 124 */     save();
	/*     */   }
/*     */ 
/*     */   
/* 141 */   public String location = UltimateJobs.getPlugin().getConfig().getString("Plugin.Save_In");
/* 142 */   public UltimateJobs plugin = UltimateJobs.getPlugin();
/*     */   public void create() {
/* 144 */     if (getfile() != null && 
/* 145 */       !this.plugin.getDataFolder().exists())
/* 146 */       this.plugin.getDataFolder().mkdir(); 
/* 147 */     if (!getfile().exists()) {
/*     */       try {
/* 149 */         getfile().createNewFile();
/* 150 */       } catch (Exception e) {
	   if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
/* 151 */         Bukkit.getConsoleSender().sendMessage(
/* 152 */             ChatColor.RED + "Error 8: creating " + getfile().getName() + "!  - " + e.getMessage());
	   }
/*     */       } 
/*     */     }
/* 155 */     this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.cfile);
/*     */   }
/*     */   
/*     */   public File getfile() {
/* 159 */     this.cfile = new File(this.location, "data."+UltimateJobs.getPlugin().getConfig().getString("Advanced.SavingFile"));
/* 160 */     if (this.cfile != null) {
/* 161 */       return this.cfile;
/*     */     }
/* 163 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void load() {
/* 169 */     if (getfile() != null) {
/* 170 */       this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(this.cfile);
/*     */     } else {
	   if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
/* 172 */       Bukkit.broadcastMessage(ChatColor.RED + "Error 9: File does not exsist.  Cannot load file.");
	   }
/*     */     } 
/*     */   }
/*     */   
/*     */   public FileConfiguration get() {
/* 177 */     return this.config;
/*     */   }
/*     */   
/*     */   public void save() {
/*     */     try {
/* 182 */       this.config.save(getfile());
/* 183 */     } catch (Exception e) {
	   if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
/* 184 */       Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error 10: Error saving " + getfile().getName() + "!");
	   }
/*     */     } 
/*     */   }
/*     */ }

 