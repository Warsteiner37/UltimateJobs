package de.warsteiner.ultimatejobs.chat;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.utils.JobAPI;

public class PlayerJobChatEvent implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent  e) {
		FileConfiguration cfg = UltimateJobs.getChatConfig().getCustomConfig();
		
		String m = e.getMessage();
		
		Player p = e.getPlayer();
		
		if(cfg.getBoolean("Enable_Chat")) {
		
			String message = m.toLowerCase();
			
			String b = cfg.getString("Use").toLowerCase();
			
			if(message.startsWith(b)) { 
				e.setCancelled(true);
				if(JobAPI.getCurrentJob(p.getUniqueId()).equalsIgnoreCase("None")) {
					p.sendMessage(cfg.getString("No_Job").replaceAll("&", "§"));
					return;
				} else {
					if(message.length() >= 3) {
						String m2 = m.replaceAll(b, "b").replaceAll("b", "");
						 //Format: "&8[&b<level> &8- <job>&8] &7<player>&8: <message>"
						String format = cfg.getString("Format")
								.replaceAll("<level>", ""+UltimateJobs.getData().getLevel(""+p.getUniqueId(), JobAPI.getCurrentJob(p.getUniqueId())))
								.replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(JobAPI.getCurrentJob(p.getUniqueId())))
								.replaceAll("<player>", p.getName())
								.replaceAll("<message>", m2)
								.replaceAll("&", "§");
						
						for(Player p2 : Bukkit.getOnlinePlayers()) {
							if(JobAPI.getCurrentJob(p2.getUniqueId()).equalsIgnoreCase(JobAPI.getCurrentJob(p.getUniqueId()))) {
								p2.sendMessage(format);
							}
						}
						
						return;
					} else {
						p.sendMessage(cfg.getString("Short").replaceAll("&", "§"));
						return;
					}
				}
			}
		
		}
	}
	
}
