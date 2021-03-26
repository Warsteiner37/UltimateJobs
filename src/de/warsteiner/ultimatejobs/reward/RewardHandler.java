package de.warsteiner.ultimatejobs.reward;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.PlayerLevelExpChangeEvent;
import de.warsteiner.ultimatejobs.skills.SkillsAPIForJobs;
import de.warsteiner.ultimatejobs.utils.ActionBar;
import de.warsteiner.ultimatejobs.utils.BossBarHandler; 
import de.warsteiner.ultimatejobs.utils.JobAPI;
import net.milkbowl.vault.economy.Economy;

public class RewardHandler {
	
	public void sendRewardMessage(Player p, String mat, String levelexp, String vanilla, String points, String mode, String money) {
		new PlayerLevelExpChangeEvent(p);
		Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {
 
			@Override
			public void run() {
				UUID uuid = p.getUniqueId();
				
				 Economy eco = UltimateJobs.getEconomy();
				
				String job = JobAPI.getCurrentJob(uuid);
				
				Bukkit.broadcastMessage("mat: "+mat);
				Bukkit.broadcastMessage("levelexp: "+levelexp);
				Bukkit.broadcastMessage("vanilla: "+vanilla);
				Bukkit.broadcastMessage("points: "+points);
				Bukkit.broadcastMessage("mode: "+mode);
				Bukkit.broadcastMessage("money: "+money); 
				 
				  //FORMAT: MATERIAL:CHANCE:LEVEL_EXP:VANILLA_EXP:POINTS:REWARD_MODE:REWARD 
				 //  - "STONE:70:1.20:2:1:VAULT:2" 
				 //  - "IRON_ORE:80:1.20:2:1:COMMAND:say hello <player>" 
				
			}
			
		});
		
		 
		
	}

}
