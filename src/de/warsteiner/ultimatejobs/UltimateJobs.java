/*     */ package de.warsteiner.ultimatejobs;
/*     */ 
/*     */ import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import de.warsteiner.ultimatejobs.chat.PlayerJobChatEvent;
import de.warsteiner.ultimatejobs.command.PlayerJobCommand;
import de.warsteiner.ultimatejobs.command.PlayerJobTabComplete;
import de.warsteiner.ultimatejobs.command.SellHeadCommand;
import de.warsteiner.ultimatejobs.config.ChatConfig;
import de.warsteiner.ultimatejobs.config.CommandConfig;
import de.warsteiner.ultimatejobs.config.HeadHunterConfig;
import de.warsteiner.ultimatejobs.config.JobConfig;
import de.warsteiner.ultimatejobs.config.LevelGUIConfig;
 
import de.warsteiner.ultimatejobs.config.MainGUIConfig;
import de.warsteiner.ultimatejobs.config.MessageConfig;
import de.warsteiner.ultimatejobs.config.PerJobSkills;
import de.warsteiner.ultimatejobs.config.PerPlayerSkills;
import de.warsteiner.ultimatejobs.config.QuestsConfig;
import de.warsteiner.ultimatejobs.config.SkillsMainConfug;
import de.warsteiner.ultimatejobs.config.SoundConfig;
import de.warsteiner.ultimatejobs.config.TopGUI;
import de.warsteiner.ultimatejobs.config.UtilConfig;
import de.warsteiner.ultimatejobs.events.EditorMainPageEvent;
import de.warsteiner.ultimatejobs.events.PlayerClickAtHeadGui; 
import de.warsteiner.ultimatejobs.events.PlayerClickEventAtMainGUI;
import de.warsteiner.ultimatejobs.events.PlayerEixstEvent;
import de.warsteiner.ultimatejobs.events.PlayerLevelCheckEvent;
import de.warsteiner.ultimatejobs.jobs.Job_Baker;
import de.warsteiner.ultimatejobs.jobs.Job_BeeKeeper;
 
import de.warsteiner.ultimatejobs.jobs.Job_Builder;
import de.warsteiner.ultimatejobs.jobs.Job_CookingMaster;
import de.warsteiner.ultimatejobs.jobs.Job_Crafter;
import de.warsteiner.ultimatejobs.jobs.Job_Digger;
import de.warsteiner.ultimatejobs.jobs.Job_Farmer;
import de.warsteiner.ultimatejobs.jobs.Job_Fisherman;
import de.warsteiner.ultimatejobs.jobs.Job_FoodEater;
import de.warsteiner.ultimatejobs.jobs.Job_HeadHunter;
import de.warsteiner.ultimatejobs.jobs.Job_Hunter;
import de.warsteiner.ultimatejobs.jobs.Job_Lumberjack;
import de.warsteiner.ultimatejobs.jobs.Job_MilkMan;
import de.warsteiner.ultimatejobs.jobs.Job_Miner;
import de.warsteiner.ultimatejobs.jobs.Job_Shepherd;
import de.warsteiner.ultimatejobs.jobs.PlayerBlockPlaceEvent;
import de.warsteiner.ultimatejobs.levels.LevelAPI;
import de.warsteiner.ultimatejobs.levels.levelgui.LevelClickEvent;
import de.warsteiner.ultimatejobs.quests.QuestClickEvent;
import de.warsteiner.ultimatejobs.quests.QuestEvent;
import de.warsteiner.ultimatejobs.quests.QuestGUI;
import de.warsteiner.ultimatejobs.ranking.TopClickEvent_GlobalRanking;
import de.warsteiner.ultimatejobs.ranking.TopClickEvent_JobRanking;
import de.warsteiner.ultimatejobs.reward.RewardHandler;
import de.warsteiner.ultimatejobs.skills.PlayerClickAtSkillsPerJob; 
import de.warsteiner.ultimatejobs.sounds.PlayerBasedSounds;
import de.warsteiner.ultimatejobs.utils.BossBarHandler;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.Metrics;
import de.warsteiner.ultimatejobs.utils.PlaceHolder;
import de.warsteiner.ultimatejobs.utils.PlayerData;
import de.warsteiner.ultimatejobs.utils.WG;
 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
import java.util.List;
 
/*     */ import net.milkbowl.vault.economy.Economy;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
/*     */ import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UltimateJobs
/*     */   extends JavaPlugin
/*     */ {
/*     */   private static UltimateJobs plugin;
/*     */   private static Economy econ;
/*     */   private static PlayerData data;
 
			public static YamlConfiguration file;

			private static JobAPI api;
 
/*  64 */   private static ArrayList<String> flags = new ArrayList<>();
			private static ArrayList<String> enabled_jobs = new ArrayList<>();
/*     */ 
/*     */  private static JobConfig jobs;
			private static MainGUIConfig maingui;
			private static MessageConfig m;
	 
			private static CommandConfig cmd;
			 
			private static RewardHandler rewards;
 
			private static LevelAPI levelapi;
		 
			private static BossBarHandler boss;
		 
			private static LevelGUIConfig lgui;
			private static TopGUI top;
			private static SoundConfig sounds;
			private static ChatConfig chat;
			private static QuestsConfig quests;
			private static PerPlayerSkills psk;
			private static SkillsMainConfug sk;
			private static PerJobSkills sjob;
			private static UtilConfig util;
			private static HeadHunterConfig head;
			@Override
/*     */   public void onLoad() {
	 
			  
  
/*  69 */     flags.add("can-work-miner"); 
			  flags.add("can-work-lumberjack"); 
			  flags.add("can-work-builder"); 
			  flags.add("can-work-hunter"); 
			  flags.add("can-work-fisherman"); 
			  flags.add("can-work-crafter"); 
			  flags.add("can-work-digger"); 
			  flags.add("can-work-shepherd"); 
			  flags.add("can-work-farmer"); 
			  flags.add("can-work-milkman"); 
			  flags.add("can-work-baker"); 
			  flags.add("can-work-banker"); 
			  flags.add("can-work-headhunter"); 
			  flags.add("can-work-BeeKeeper"); 
			  flags.add("can-work-Brewer"); 
			  flags.add("can-work-Trader"); 
			  flags.add("can-work-FoodEater"); 
			  flags.add("can-work-CookingMaster");  
			  flags.add("can-work-Artist"); 
			  flags.add("can-open-skills");
/*  81 */     File file = new File("plugins//WorldGuard.jar");
/*  82 */     if (file.exists()) {
/*  83 */       WG.load();
/*  84 */       System.out.println("?aLoading UltimateJobs with API of WorldGuard with ?e"+flags.size()+" ?aFlags");
/*     */     } 
/*     */   }
 
@Override
			public void onDisable() {
	data.save();
}
@Override
/*     */   public void onEnable() {
 
/*  95 */     plugin = this;
/*     */     	loadConfig();
 
/*  97 */     api = new JobAPI();
             jobs = new JobConfig();
             maingui = new MainGUIConfig();
             m = new MessageConfig();
             head = new HeadHunterConfig();
             cmd = new CommandConfig();
             sjob = new PerJobSkills();
/*     */ 	rewards = new RewardHandler();
 
			levelapi = new LevelAPI();
			psk = new PerPlayerSkills();
			util = new UtilConfig();
			lgui = new LevelGUIConfig();
			top = new TopGUI();
			boss = new BossBarHandler(getPlugin());
			sounds = new SoundConfig();
			chat = new ChatConfig();
			quests = new QuestsConfig();
			sk = new SkillsMainConfug();
 
 
 
/* 112 */     if (!CheckVault()) {
/* 113 */  	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
    			 Bukkit.getConsoleSender().sendMessage("?4?lWarning?8: ?7There is an Error. #1");
}
/*     */     } else {
 
/* 115 */       setupEconomy();
/*     */     } 
/*     */     
/* 118 */     
/* 120 */     if (setUpPapi()) {
	  System.out.println("?aLoaded PaPi Support for UltimateJobs!");
              new PlaceHolder() .register();
/*     */     } 
 
/* 134 */     
/*     */ 		head.createCustomConfig();
				jobs.createCustomConfig();
				maingui.createCustomConfig();
				sjob.createCustomConfig();
				m.createCustomConfig();
				cmd.createCustomConfig();
				 sk.createCustomConfig();
				 psk.createCustomConfig();
				top.createCustomConfig();
				lgui.createCustomConfig();
				sounds.createCustomConfig();
				chat.createCustomConfig();
				quests.createCustomConfig();
/*     */ 		util.createCustomConfig();
/*     */     
/* 138 */     Bukkit.getPluginManager().registerEvents((Listener)new PlayerEixstEvent(), (Plugin)this);
				Bukkit.getPluginManager().registerEvents((Listener)new PlayerClickEventAtMainGUI(), (Plugin)this);
				Bukkit.getPluginManager().registerEvents((Listener)new EditorMainPageEvent(), (Plugin)this); 
				List<String> jb = jobs.getCustomConfig().getStringList("Options.GUI");
/*     */     
				if(jb.contains("Miner")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Miner(), (Plugin)this);
				}
				if(jb.contains("Lumberjack")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Lumberjack(), (Plugin)this);
				}
				if(jb.contains("Builder")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Builder(),this);
				}
				if(jb.contains("Digger")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Digger(),this);
				}
				if(jb.contains("Milkman")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_MilkMan(),this);
				}
				if(jb.contains("Crafter")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Crafter(), this);
				}  
				if(jb.contains("Fisherman")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Fisherman(), this);
				}  
				if(jb.contains("Shepherd")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Shepherd(), this);
				}  
				if(jb.contains("CookingMaster")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_CookingMaster(), this);
				}  
				if(jb.contains("FoodEater")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_FoodEater(), this);
				}  
				if(jb.contains("Hunter")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Hunter(), this);
				} 
				if(jb.contains("Farmer")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Farmer(), this);
				} 
				if(jb.contains("Baker")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_Baker(), this);
				} 
				if(jb.contains("BeeKeeper")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_BeeKeeper(), this);
				} 
				if(jb.contains("HeadHunter")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new Job_HeadHunter(),this);
					 FileConfiguration cfg = head.getCustomConfig();
					 if(jobs.getCustomConfig().getBoolean("HeadHunter.Advanced_Heads")) {
						 List<String> cmds = cfg.getStringList("Command.Command");
						 Bukkit.getPluginManager().registerEvents((Listener)new PlayerClickAtHeadGui(), this);
						 if(cfg.getBoolean("Command.Use")) {
							 for (int i = 0; i < cmds.size(); i++) {
								 registerCommand(new SellHeadCommand(cmds.get(i)));
							 }
						 }
					 }
					 
				}
				 
				 Bukkit.getPluginManager().registerEvents((Listener)new PlayerBlockPlaceEvent(), (Plugin)this);
				
				 if(getConfig().getBoolean("Use_Levels")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new PlayerLevelCheckEvent(), (Plugin)this);
				 } 
				  
/* 165 */     data = new PlayerData();
/*     */     
/* 167 */     if (data.getfile() == null) {
/* 168 */       data.create();
/*     */     } else {
/* 170 */       data.load();
/*     */     }   
if(getConfig().getBoolean("Advanced.BStats")) {
	new Metrics(getPlugin(), 8753);
}
if(UltimateJobs.getSkillsMainConfig().getCustomConfig().getBoolean("Enable_Skills")) {
	  if(UltimateJobs.getSkillsMainConfig().getCustomConfig().getString("Mode").toUpperCase().equalsIgnoreCase("PER_JOB")) {
		 Bukkit.getPluginManager().registerEvents((Listener)new  PlayerClickAtSkillsPerJob(), (Plugin)this);
	 }
}
if(UltimateJobs.getLevelGUI().getCustomConfig().getBoolean("Enable_LevelGUI")) {
	 Bukkit.getPluginManager().registerEvents((Listener)new  LevelClickEvent(), (Plugin)this);
}
if(UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Enable_Top")) {
	if(UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Top_Global")) {
		 Bukkit.getPluginManager().registerEvents((Listener)new  TopClickEvent_GlobalRanking(), (Plugin)this);
	}
	if(UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Top_per_Job")) {
		 Bukkit.getPluginManager().registerEvents((Listener)new  TopClickEvent_JobRanking(), (Plugin)this);
	}
}
if(UltimateJobs.getChatConfig().getCustomConfig().getBoolean("Enable_Chat")) {
	 Bukkit.getPluginManager().registerEvents((Listener)new  PlayerJobChatEvent(), (Plugin)this);
}
 if(UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests") ) {
	 Bukkit.getPluginManager().registerEvents((Listener)new QuestEvent(), this);
	 Bukkit.getPluginManager().registerEvents((Listener)new QuestClickEvent(), this);
 
			 
			new BukkitRunnable(){
		 

		@Override
		public void run() {
			
			Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), new Runnable() {
				
				@Override
				public void run() {
			
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getOpenInventory().getTitle().equalsIgnoreCase(getQuestAPI().getCustomConfig().getString("Design.Name").replaceAll("&", "?"))) {
							QuestGUI.UpdateItemsWithActionDisplayTIme(p);
						}
					}
				}
			});
			
		}
			 
	 }  .runTaskTimer(getPlugin(), 0, 20);
		}
	 
  
 
/*     */                         Bukkit.getConsoleSender().sendMessage("?8+------------------------------------+");
/*     */                         Bukkit.getConsoleSender().sendMessage("            ?3UltimateJobs");
/*     */                         Bukkit.getConsoleSender().sendMessage("           ?8- ?7Jobs Plugin ?8-");
/*     */                         Bukkit.getConsoleSender().sendMessage("?8");
/*     */                              Bukkit.getConsoleSender().sendMessage("?f-> Loaded Version ?a" + getPlugin().getDescription().getVersion());
/*     */                              Bukkit.getConsoleSender().sendMessage("?f-> API Version ?c" + getPlugin().getDescription().getAPIVersion());  
								Bukkit.getConsoleSender().sendMessage("?f-> PlaceHolderAPI ?9" + setUpPapi()); 
								Bukkit.getConsoleSender().sendMessage("?f-> Use Levels ?6" + getConfig().getBoolean("Use_Levels")); 
/*     */                         Bukkit.getConsoleSender().sendMessage("?8");
									Bukkit.getConsoleSender().sendMessage("?f-> Addon: Skills ?e" + UltimateJobs.getSkillsMainConfig().getCustomConfig().getBoolean("Enable_Skills")+" ?8(?c"+UltimateJobs.getSkillsMainConfig().getCustomConfig().getString("Mode")+"?8)"); 
									Bukkit.getConsoleSender().sendMessage("?f-> Addon: LevelGUI ?e" + UltimateJobs.getLevelGUI().getCustomConfig().getBoolean("Enable_LevelGUI")); 
									Bukkit.getConsoleSender().sendMessage("?f-> Addon: TopGUI ?e" + UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Enable_Top")); 
									Bukkit.getConsoleSender().sendMessage("?f-> Addon: JobChat ?e" + UltimateJobs.getChatConfig().getCustomConfig().getBoolean("Enable_Chat")); 
									Bukkit.getConsoleSender().sendMessage("?f-> Addon: Quests ?e" + UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests")); 
									Bukkit.getConsoleSender().sendMessage("?8");
									Bukkit.getConsoleSender().sendMessage("?f-> Loaded ?e" +  jobs.getCustomConfig().getStringList("Options.GUI").size() + " ?fJobs"); 
								    Bukkit.getConsoleSender().sendMessage("?8");
/*     */                         Bukkit.getConsoleSender().sendMessage("?8+------------------------------------+");
 
     getCommand("jobs").setExecutor((CommandExecutor)new PlayerJobCommand());
     
     if(getCommandConfig().getCustomConfig().getBoolean("TabComplete")) {
    	  getCommand("jobs").setTabCompleter((TabCompleter)new PlayerJobTabComplete());
    	  
    	  if(getSoundAPI().getCustomConfig().getBoolean("Enabled")) {
    		  setUpSounds();
    	  }
     }
 
/*     */      
/*     */   }
/*     */ 
 
			private static void setUpSounds() {
				 
				 Bukkit.getPluginManager().registerEvents((Listener)new  PlayerBasedSounds(), (Plugin)getPlugin());
			}

/*     */   
/*     */   public static ArrayList<String> getFlags() {
/* 178 */     return flags;
/*     */   }
 
public static ChatConfig getChatConfig() {
	return chat;
}

			public static JobAPI getJobAPI() {
				return api;
			}
			
			public static PlayerData getData() {
				return data;
			}
			
			public static UtilConfig getUtil() {
				return util;
			}
			
			public static PerJobSkills getSkillsPerJob() {
				return sjob;
			}
			
			public static HeadHunterConfig getHunterConfig() {
				return head;
			}
			
			public static QuestsConfig getQuestAPI() {
				return quests;
			}
			
			public static JobConfig getJobsConfig() {
				return jobs;
			}
			
			public static SoundConfig getSoundAPI() {
				return sounds;
			}
			
			public static TopGUI getTopConfig() {
				return top;
			}
			
			public static MainGUIConfig getMainGUIConfig() {
				return maingui;
			}

			public static LevelGUIConfig getLevelGUI() {
				return lgui;
			}
	 
			
			public static MessageConfig MessageHandler() {
				return m;
			}
 
			public static RewardHandler getRewardHandler() {
				return rewards;
			}
 
			public static LevelAPI getLevelAPI() {
				return levelapi;
			}
	 
			public static BossBarHandler getBossBarHandler() {
				return boss;
			}
			
			public static CommandConfig getCommandConfig() {
				return cmd;
			}
			
			public static PerPlayerSkills getPerPlayerSkillsConfig() {
				return psk;
			}
			
			public static SkillsMainConfug getSkillsMainConfig() {
				return sk;
			}
			
			
/*     */   public static boolean checkFlag(Location location, String flag, Player p) {
/* 193 */     File file = new File("plugins//WorldGuard.jar");
/* 194 */     if (file.exists()) {
/* 195 */       return WG.checkFlag(location, flag, p);
/*     */     }
/* 197 */     return true;
/*     */   }
/*     */ 
 
/*     */   
/*     */   public static UltimateJobs getPlugin() {
/* 209 */     return plugin;
/*     */   }
/*     */ 
 
/*     */   

private void registerCommand(Command command) {
/*  99 */     CommandMap cmap = null;
/*     */     try {
/* 101 */       Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
/* 102 */       field.setAccessible(true);
/* 103 */       cmap = (CommandMap)field.get(Bukkit.getServer());
/* 104 */       cmap.register("", command);
/* 105 */     } catch (Exception e) {
/* 106 */       e.printStackTrace();
/*     */     } 
/*     */   }

/*     */   private void loadConfig() {
	
 
	
/* 214 */     File d = new File("plugins//" + getDescription().getName() + "//config.yml");
			 
/* 215 */     if (!d.exists()) {
/* 216 */  	     saveDefaultConfig();
file = YamlConfiguration.loadConfiguration(d);
/*     */     } else {
	 
	 
     file = YamlConfiguration.loadConfiguration(d);
			}
/*     */   }
/*     */   
/*     */   private boolean setupEconomy() {
/* 226 */     RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(Economy.class);
/* 227 */     if (economyProvider != null) {
/* 228 */       econ = (Economy)economyProvider.getProvider();
/*     */     }
/*     */     
/* 231 */     return (econ != null);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean setUpPapi() {
/* 236 */     if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
/* 237 */       return true;
/*     */     }
/*     */     
/* 240 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private WorldGuardPlugin getWorldGuard() {
/* 247 */     Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");
/* 248 */     if (plugin != null)  
/* 251 */     return (WorldGuardPlugin)plugin;
/*     */
	return null;
   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean CheckVault() {
/* 257 */     if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
/* 258 */       return true;
/*     */     }
/*     */     
/* 261 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Economy getEconomy() {
/* 266 */     return econ;
/*     */   }
/*     */ }

 