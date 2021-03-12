package de.warsteiner.ultimatejobs.events;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.config.LevelsConfig;
import de.warsteiner.ultimatejobs.custom.PlayerLevelExpChangeEvent;
import de.warsteiner.ultimatejobs.utils.JobAPI;
import de.warsteiner.ultimatejobs.utils.RandomNumberHandler;
import net.milkbowl.vault.economy.Economy;

public class PlayerLevelCheckEvent implements Listener {
	
	@EventHandler
	public void onExpChange(PlayerLevelExpChangeEvent e) {
		check(e.p);
	}
	
	 public   void check(Player p) {
	     
			Economy eco = UltimateJobs.getEconomy();
			/* 22 */     UUID uuid = p.getUniqueId();
						String job = JobAPI.getCurrentJob(uuid);/* 23 */    
			
			/* 24 */     int current_level = UltimateJobs.getData().getLevel(""+uuid, job);
			/* 25 */     double current_exp = UltimateJobs.getData().getExp(""+uuid, job);
 
						List<String> list = UltimateJobs.getJobsConfig().getCustomConfig().getStringList(job+".Levels");
	 
			/* 28 */     for (String a : list) {
			/* 29 */       String[] b = a.split(":");
			/*    */       //  - "1:&bLevel I:0:1.0:MONEY:0.20"
			
							String id = b[0];
			
							if(!id.equalsIgnoreCase("MAX")) {
						 
			/* 33 */      
							
							if(Integer.valueOf(id) == current_level+1) {
								  double need_exp = Double.valueOf(b[2]).intValue();
								  	int newp = Integer.valueOf(b[6]);
								/* 37 */       if ( 
										/* 38 */         current_exp >= need_exp) {
										/* 39 */          UltimateJobs.getData().setExp(""+uuid, job, need_exp);
										UltimateJobs.getData().setLevel(""+uuid, job, current_level + 1);
										UltimateJobs.getData().setExp(""+uuid, job, 0);
										int old = UltimateJobs.getData().getSkillPoints(""+uuid);
										UltimateJobs.getData().setSkillPoints(""+uuid, old+newp);
										
										FileConfiguration cfg = UltimateJobs.getLevelConfig().getCustomConfig();
										
										String newlevel = ""+UltimateJobs.getData().getLevel(""+uuid, job);
										
										  //  MAP_MINER:
										  //   - "give <player> diamond"
										  //  - "say hello" 
											     
										  //   - "4:&cLevel IV:300:1.19:COMMAND_MAP:MAP_MINER:1"
										  //   - "MAX:&4&lMax Level:0"
										
										String action = b[4];
						 
										if(action.equalsIgnoreCase("MONEY")) {
											String m = b[5];
											if(m.contains("%")) {
												
												String r = RandomNumberHandler.gen(m);
												
												double money = Double.valueOf(r);
												eco.withdrawPlayer(p, money);
												
											} else {
												double money = Double.valueOf(b[5]);
												eco.withdrawPlayer(p, money);
											}
										} else 				if(action.equalsIgnoreCase("EXP")) {
											int e = Integer.valueOf(b[5]);
											p.giveExp(e);
										} else 				if(action.equalsIgnoreCase("COMMAND")) {
											String cmd = b[5];
											if(cmd.contains("%")) {
												
												String[] s = cmd.split("%");
												
												String n = "%"+s[1]+"%";
												
												String n1 = RandomNumberHandler.gen(n);
												 
												/* 44 */           ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
												/*    */           
												/* 46 */           Bukkit.dispatchCommand((CommandSender)console, cmd.replaceAll(n, n1).replaceAll("<name>", p.getName()));
												 continue;
											} else {
												/* 44 */           ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
												/*    */           
												/* 46 */           Bukkit.dispatchCommand((CommandSender)console, cmd.replaceAll("<name>", p.getName()));
												 continue;
											}
										}  else 				if(action.equalsIgnoreCase("COMMAND_MAP")) {
											
											List<String> map = UltimateJobs.getJobsConfig().getCustomConfig().getStringList(job+"."+b[5]);
											for(String x : map) {
												if(x.contains("%")) {
													
													String[] s = x.split("%");
													
													String n = "%"+s[1]+"%";
													
													String n1 = RandomNumberHandler.gen(n);
													
													   ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
													  Bukkit.dispatchCommand((CommandSender)console, x.replaceAll(n, n1).replaceAll("<name>", p.getName()));
													  continue;
												} else {
													   ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
														  Bukkit.dispatchCommand((CommandSender)console, x.replaceAll("<name>", p.getName()));
														  continue;
												}
											}
										}
										  
										
										if(cfg.getBoolean("Options.Message")) {  
											String m = cfg.getString("Options.Message_Config.Type").replaceAll("<level>", newlevel).replaceAll("<prefix>", UltimateJobs.MessageHandler().getCustomConfig().getString("Prefix").replaceAll("&", "§"));
											
											p.sendMessage(m.replaceAll("&", "§"));
											
											//<prefix> &7You reached an new Level! &8[&4<level>&8]
										}
										
										if(cfg.getBoolean("Options.Sound")) {
											   
											String sound = cfg.getString("Options.Sound_Config.Type");
											
											p.playSound(p.getLocation(), Sound.valueOf(sound), 0, 0);
											
										}
										
										if(cfg.getBoolean("Options.Title")) {  
											
											String sec = cfg.getString("Options.Title_Config.Second").replaceAll("<level>", newlevel).replaceAll("<prefix>", UltimateJobs.MessageHandler().getCustomConfig().getString("Prefix").replaceAll("&", "§"));
											
											String first = cfg.getString("Options.Title_Config.First").replaceAll("<level>", newlevel).replaceAll("<prefix>", UltimateJobs.MessageHandler().getCustomConfig().getString("Prefix").replaceAll("&", "§"));
											
											p.sendTitle(first.replaceAll("&", "§"), sec.replaceAll("&", "§"));
											
										}
										
										/* 42 */         
														return;
										
										/*    */       } 
							}
							}
			/*    */     } 
			/*    */  
		}

}
