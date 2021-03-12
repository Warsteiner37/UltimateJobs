package de.warsteiner.ultimatejobs.quests;

import java.util.List;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.QuestDataChangeEvent;
import de.warsteiner.ultimatejobs.utils.ActionBar;

public class QuestEvent implements Listener {
	
	@EventHandler
	public void onUpdate(QuestDataChangeEvent e) {
		Player p = e.p;
		String job = e.job;
		String id = e.questname;
		int i = e.quest;
		if(job != null) {
			
			FileConfiguration cfg = UltimateJobs.getQuestAPI().getCustomConfig();
 
			String[] action = cfg.getString("Quests."+id+".Action").split(":");
			 
			 int has = UltimateJobs.getData().getQuestsInt(""+p.getUniqueId(), i);
			 int need = Integer.valueOf(action[1]);
			 
			 if(has >= need) { 
				 UltimateJobs.getData().setQuestInt(""+p.getUniqueId(), i, 0);
					UltimateJobs.getData().setQuestBoolean (""+p.getUniqueId(), i, true);
					String dis = UltimateJobs.getQuestAPI().getCustomConfig().getString("Quests."+id+".ID");
				 String t = cfg.getString("Rewards.Reward_Type");
					if(t.equalsIgnoreCase("MESSAGE")) {
						p.sendMessage(UltimateJobs.getQuestAPI().getCustomConfig().getString("Rewards.Message.Message")
					.replaceAll("<quest_id>", dis) .replaceAll("&", "§"));
						return;
					}  else if(t.equalsIgnoreCase("TITLE")) {
						
						String a1 = UltimateJobs.getQuestAPI().getCustomConfig().getString("Rewards.Title.First")
								.replaceAll("<quest_id>", dis)	 .replaceAll("&", "§");
						
						String a2 = UltimateJobs.getQuestAPI().getCustomConfig().getString("Rewards.Title.Second")
								.replaceAll("<quest_id>", dis) .replaceAll("&", "§");
						
						p.sendTitle(a1, a2);
					}
				 
				 return;
			 }
			
		}
	}

}
