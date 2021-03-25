package de.warsteiner.ultimatejobs.command;

import java.util.concurrent.TimeUnit;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_16_R3.block.impl.CraftSkullPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.quests.QuestGUI;

public class SellHeadCommand extends Command {
 
	  public SellHeadCommand(String cmd) { 
		  /*  23 */    super(cmd);
		  /*     */   }
	  
	 public static boolean isValid(ItemStack item) {
		 
		 if(item.getType() == Material.PLAYER_HEAD) {
			 
			 SkullMeta meta =  (SkullMeta)item.getItemMeta();
			 
			 if(meta.getOwner() == null) {
				 return false;
			 }
			 
			 String dis = meta.getDisplayName();
			 
			 FileConfiguration c = UltimateJobs.getHunterConfig().getCustomConfig();
			   
			   String disneed = c.getString("Skulls.Display")
					   .replaceAll("<skull>", meta.getOwner())
					   .replaceAll("&", "§");
			 
			   if(dis.equalsIgnoreCase(disneed)) {
				   return true;
			   }
			   
		 }
		 
		return false;
		 
	 }
 
	@Override
	public boolean execute(@NotNull CommandSender s, @NotNull String arg1, @NotNull String[] args) {
		Player p = (Player) s;
		
		FileConfiguration m = UltimateJobs.MessageHandler().getCustomConfig();
		
		if(args.length == 0) {
			
			if(p.getItemInHand() == null) {
				return true;
			}
			
			 ItemStack item = p.getItemInHand();
			
			if(isValid(item)) {
				
				int many = item.getAmount();
				
				p.sendMessage("§c§lThis feature will released in version v1.5.4!");
			} else {
				String d = m.getString("Prefix")+m.getString("No_Head");
				p.sendMessage(d.replaceAll("&", "§"));
			}
			
		} else {
			String d = m.getString("Prefix")+m.getString("Try_Head");
			p.sendMessage(d.replaceAll("&", "§"));
		}
		return false;
	}

	 
}












