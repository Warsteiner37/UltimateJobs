/*     */ package de.warsteiner.ultimatejobs;
/*     */ 
/*     */ import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import de.warsteiner.ultimatejobs.chat.PlayerJobChatEvent;
import de.warsteiner.ultimatejobs.command.PlayerJobCommand;
import de.warsteiner.ultimatejobs.command.PlayerJobTabComplete;
import de.warsteiner.ultimatejobs.config.ChatConfig;
import de.warsteiner.ultimatejobs.config.CommandConfig;
import de.warsteiner.ultimatejobs.config.JobConfig;
import de.warsteiner.ultimatejobs.config.LevelGUIConfig;
import de.warsteiner.ultimatejobs.config.LevelsConfig;
import de.warsteiner.ultimatejobs.config.MainGUIConfig;
import de.warsteiner.ultimatejobs.config.MessageConfig;
import de.warsteiner.ultimatejobs.config.PlaceHolderConfig;
import de.warsteiner.ultimatejobs.config.QuestsConfig;
import de.warsteiner.ultimatejobs.config.RewardConfig;
import de.warsteiner.ultimatejobs.config.SkillsConfug;
import de.warsteiner.ultimatejobs.config.SoundConfig;
import de.warsteiner.ultimatejobs.config.TopGUI;
import de.warsteiner.ultimatejobs.events.PlayerClickEventAtMainGUI;
import de.warsteiner.ultimatejobs.events.PlayerEixstEvent;
import de.warsteiner.ultimatejobs.events.PlayerLevelCheckEvent;
import de.warsteiner.ultimatejobs.jobs.Job_Baker;
import de.warsteiner.ultimatejobs.jobs.Job_Builder;
import de.warsteiner.ultimatejobs.jobs.Job_CookingMaster;
import de.warsteiner.ultimatejobs.jobs.Job_Crafter;
import de.warsteiner.ultimatejobs.jobs.Job_Digger;
import de.warsteiner.ultimatejobs.jobs.Job_Farmer;
import de.warsteiner.ultimatejobs.jobs.Job_Fisherman;
import de.warsteiner.ultimatejobs.jobs.Job_FoodEater;
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
import de.warsteiner.ultimatejobs.skills.PlayerClickAtSkills;
import de.warsteiner.ultimatejobs.skills.SkillsAPI;
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
			private static PlaceHolderConfig placeholders;
			private static CommandConfig cmd;
			private static LevelsConfig levels;
			private static RewardHandler rewards;
			private static SkillsAPI skillsapi;
			private static LevelAPI levelapi;
			private static RewardConfig rconfig;
			private static BossBarHandler boss;
			private static SkillsConfug skills;
			private static LevelGUIConfig lgui;
			private static TopGUI top;
			private static SoundConfig sounds;
			private static ChatConfig chat;
			private static QuestsConfig quests;
 
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
	 
/*  81 */     File file = new File("plugins//WorldGuard.jar");
/*  82 */     if (file.exists()) {
/*  83 */       WG.load();
/*  84 */       System.out.println("§aLoading UltimateJobs with API of WorldGuard with §e"+flags.size()+" §aFlags");
/*     */     } 
/*     */   }
 
/*     */   public void onEnable() {
 
/*  95 */     plugin = this;
/*     */     	loadConfig();
 
/*  97 */     api = new JobAPI();
             jobs = new JobConfig();
             maingui = new MainGUIConfig();
             m = new MessageConfig();
             placeholders = new PlaceHolderConfig();
             cmd = new CommandConfig();
             levels = new LevelsConfig();
/*     */ 	rewards = new RewardHandler();
			skillsapi = new SkillsAPI();
			levelapi = new LevelAPI();
			rconfig = new RewardConfig();
			skills = new SkillsConfug();
			lgui = new LevelGUIConfig();
			top = new TopGUI();
			boss = new BossBarHandler(getPlugin());
			sounds = new SoundConfig();
			chat = new ChatConfig();
			quests = new QuestsConfig();
 
/* 103 */ //    (new UpdateChecker(this, 81914)).getVersion(version -> {
/*     */        //   if (getDescription().getVersion().equalsIgnoreCase(version)) {
/*     */          //   System.out.println("Â§cNo Update was found!");
/*     */          // } else {
/*     */          //   System.out.println("Â§aThere is some new version of UltimateJobs on SpigotMC!");
/*     */           //} 
/*     */         //});
 
/* 112 */     if (!CheckVault()) {
/* 113 */  	if(UltimateJobs.getPlugin().getConfig().getBoolean("Advanced.Console_Logs")) {
    			 Bukkit.getConsoleSender().sendMessage("§4§lWarning§8: §7There is an Error. #1");
}
/*     */     } else {
 
/* 115 */       setupEconomy();
/*     */     } 
/*     */     
/* 118 */     
/* 120 */     if (setUpPapi()) {
	  System.out.println("§aLoaded PaPi Support for UltimateJobs!");
              new PlaceHolder() .register();
/*     */     } 
 
/* 134 */     
/*     */ 
				jobs.createCustomConfig();
				maingui.createCustomConfig();
				placeholders.createCustomConfig();
				m.createCustomConfig();
				cmd.createCustomConfig();
				levels.createCustomConfig();
				rconfig.createCustomConfig();
				skills.createCustomConfig();
				top.createCustomConfig();
				lgui.createCustomConfig();
				sounds.createCustomConfig();
				chat.createCustomConfig();
				quests.createCustomConfig();
/*     */ 
/*     */     
/* 138 */     Bukkit.getPluginManager().registerEvents((Listener)new PlayerEixstEvent(), (Plugin)this);
				Bukkit.getPluginManager().registerEvents((Listener)new PlayerClickEventAtMainGUI(), (Plugin)this);
			 
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
				 
				 Bukkit.getPluginManager().registerEvents((Listener)new PlayerBlockPlaceEvent(), (Plugin)this);
				
				 if(levels.getCustomConfig().getBoolean("Use_Levels")) {
					 Bukkit.getPluginManager().registerEvents((Listener)new PlayerLevelCheckEvent(), (Plugin)this);
				 } 
				  
/* 165 */     data = new PlayerData();
/*     */     
/* 167 */     if (data.getfile() == null) {
/* 168 */       data.create();
/*     */     } else {
/* 170 */       data.load();
/*     */     }   

new Metrics(getPlugin(), 8753);
 
if(UltimateJobs.getSkillsConfig().getCustomConfig().getBoolean("Enable_Skills")) {
	 Bukkit.getPluginManager().registerEvents((Listener)new  PlayerClickAtSkills(), (Plugin)this);
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
		Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), new Runnable() {
			 
			@Override
			public void run() {
			new BukkitRunnable(){
		 

		@Override
		public void run() {
			
			Bukkit.getScheduler().runTaskAsynchronously(getPlugin(), new Runnable() {
				
				@Override
				public void run() {
			
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getOpenInventory().getTitle().equalsIgnoreCase(getQuestAPI().getCustomConfig().getString("Design.Name").replaceAll("&", "§"))) {
							QuestGUI.UpdateItemsWithActionDisplayTIme(p);
						}
					}
				}
			});
			
		}
			 
	 }  .runTaskTimer(getPlugin(), 0, 20);
		}
		});
 
 }
 
/*     */                         Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
/*     */                         Bukkit.getConsoleSender().sendMessage("            §3UltimateJobs");
/*     */                         Bukkit.getConsoleSender().sendMessage("           §8- §7Jobs Plugin §8-");
/*     */                         Bukkit.getConsoleSender().sendMessage("§8");
/*     */                              Bukkit.getConsoleSender().sendMessage("§f-> Loaded Version §a" + getPlugin().getDescription().getVersion());
/*     */                              Bukkit.getConsoleSender().sendMessage("§f-> API Version §c" + getPlugin().getDescription().getAPIVersion());  
								Bukkit.getConsoleSender().sendMessage("§f-> PlaceHolderAPI §9" + setUpPapi()); 
								Bukkit.getConsoleSender().sendMessage("§f-> Use Levels §6" + UltimateJobs.getLevelConfig().getCustomConfig().getBoolean("Use_Levels")); 
/*     */                         Bukkit.getConsoleSender().sendMessage("§8");
									Bukkit.getConsoleSender().sendMessage("§f-> Addon: Skills §e" + UltimateJobs.getSkillsConfig().getCustomConfig().getBoolean("Enable_Skills")); 
									Bukkit.getConsoleSender().sendMessage("§f-> Addon: LevelGUI §e" + UltimateJobs.getLevelGUI().getCustomConfig().getBoolean("Enable_LevelGUI")); 
									Bukkit.getConsoleSender().sendMessage("§f-> Addon: TopGUI §e" + UltimateJobs.getTopConfig().getCustomConfig().getBoolean("Enable_Top")); 
									Bukkit.getConsoleSender().sendMessage("§f-> Addon: JobChat §e" + UltimateJobs.getChatConfig().getCustomConfig().getBoolean("Enable_Chat")); 
									Bukkit.getConsoleSender().sendMessage("§f-> Addon: Quests §e" + UltimateJobs.getQuestAPI().getCustomConfig().getBoolean("Enable_Quests")); 
									Bukkit.getConsoleSender().sendMessage("§8");
									Bukkit.getConsoleSender().sendMessage("§f-> Loaded §e" +  jobs.getCustomConfig().getStringList("Options.GUI").size() + " §fJobs"); 
								    Bukkit.getConsoleSender().sendMessage("§8");
/*     */                         Bukkit.getConsoleSender().sendMessage("§8+------------------------------------+");
 
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
			
			public static PlaceHolderConfig getPlaceHolderConfig() {
				return placeholders;
			}
			
			public static MessageConfig MessageHandler() {
				return m;
			}
			
			public static LevelsConfig getLevelConfig() {
				return levels;
			}
			
			public static RewardHandler getRewardHandler() {
				return rewards;
			}
			
			public static SkillsAPI getSkillAPI() {
				return skillsapi;
			}
			
			public static LevelAPI getLevelAPI() {
				return levelapi;
			}
			
			public static RewardConfig getRewardConfig() {
				return rconfig;
			}
			
			public static BossBarHandler getBossBarHandler() {
				return boss;
			}
			
			public static CommandConfig getCommandConfig() {
				return cmd;
			}
			
			public static SkillsConfug getSkillsConfig() {
				return skills;
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
/*     */   public void onDisable() {
/* 202 */   //  if (data != null) {
/* 203 */     	//  data.save();
/*     */  //   }
/*     */   }
/*     */ 
/*     */   
/*     */   public static UltimateJobs getPlugin() {
/* 209 */     return plugin;
/*     */   }
/*     */ 

public    void reloadValues() {
  
     reloadConfig();
     
}
/*     */   
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

 