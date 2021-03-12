package de.warsteiner.ultimatejobs.sounds;

import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.PlayerAlreadyOwnJob;
import de.warsteiner.ultimatejobs.custom.PlayerBuyJob;
import de.warsteiner.ultimatejobs.custom.PlayerJobChange;
import de.warsteiner.ultimatejobs.custom.PlayerLevelChangeEvent;
import de.warsteiner.ultimatejobs.custom.PlayerLevelExpChangeEvent;
import de.warsteiner.ultimatejobs.custom.PlayerOpenJobsGUI;
import de.warsteiner.ultimatejobs.custom.PlayerOpenLevelsGUI;
import de.warsteiner.ultimatejobs.custom.PlayerOpenQuestsGUI;
import de.warsteiner.ultimatejobs.custom.PlayerOpenSkillsGUI;

public class PlayerBasedSounds implements Listener {

	//List<String> list = getSoundAPI().getCustomConfig().getStringList("Sounds");
	
	@EventHandler
	public void onSound(PlayerOpenJobsGUI e) {
		PlayerSound(e.p, e.getEventName());
	}
	
	@EventHandler
	public void onSound(PlayerOpenSkillsGUI e) {
		PlayerSound(e.p, e.getEventName());
	}
	
	@EventHandler
	public void onSound(PlayerOpenLevelsGUI e) {
		PlayerSound(e.p, e.getEventName());
	}
	
	@EventHandler
	public void onSound(PlayerOpenQuestsGUI e) {
		PlayerSound(e.p, e.getEventName());
	}
	
	@EventHandler
	public void onSound(PlayerLevelChangeEvent e) {
		PlayerSound(e.p, e.getEventName());
	}
	
	@EventHandler
	public void onSound(PlayerLevelExpChangeEvent e) {
		PlayerSound(e.p, e.getEventName());
	}
	
	@EventHandler
	public void onSound(PlayerJobChange e) {
		PlayerSound(e.p, e.getEventName());
	}
	
	@EventHandler
	public void onSound(PlayerBuyJob e) {
		PlayerSound(e.p, e.getEventName());
	}
	

	@EventHandler
	public void onSound(PlayerAlreadyOwnJob e) {
		PlayerSound(e.p, e.getEventName());
	}
	
	public static boolean isEnabled(String event) {
		
		List<String> list = UltimateJobs.getSoundAPI().getCustomConfig().getStringList("Sounds");
		
		for(String l : list) {
			
			String[] b = l.split(":");
			
			String s = b[0].toUpperCase();
			
			if(s.equalsIgnoreCase(event.toUpperCase())) {
				return true;
			}
			
		}
		
		return false;
		
	}
	
	public static void PlayerSound(Player p, String event) {
		if(isEnabled(event)) {
			
			List<String> list = UltimateJobs.getSoundAPI().getCustomConfig().getStringList("Sounds");
			
			for(String l : list) {
				
				String[] b = l.split(":");
				
				String s = b[0].toUpperCase();
				
				if(event.toUpperCase().equalsIgnoreCase(s)) {
					
					//PlayerOpenJobsGUI:BLOCK_CHEST_OPEN:1:1
					
					if(Sound.valueOf(b[1]) == null) {
						System.out.println("§4$lERROR 3:  THERE IS A BAD SOUND ID, PLEASE CHECK");
						return;
					}
					
					Sound sound = Sound.valueOf(b[1]);
					int arg1 = Integer.valueOf(b[2]);
					int arg2 = Integer.valueOf(b[3]);
					
					p.playSound(p.getLocation(), sound, arg1, arg2);
					
					return;
					
				}
				
			}
			
		}
	}
	
}
