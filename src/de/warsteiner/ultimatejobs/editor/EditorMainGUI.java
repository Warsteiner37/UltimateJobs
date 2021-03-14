package de.warsteiner.ultimatejobs.editor;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.PlayerOpenSkillsGUI;

public class EditorMainGUI {
	
	public static Inventory load(final Player p) {
 
/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * 5,
		"§bUltimateJobs §8: §c"+UltimateJobs.getPlugin().getDescription().getVersion());
 
Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

	@Override
	public void run() {
 
 
 
 
			/* 41 */ ItemStack item = new ItemStack(Material.ACACIA_BOAT, 1);
			/* 42 */ ItemMeta meta = item.getItemMeta();
			/* 43 */ meta.setDisplayName("§ctest");
			/* 44 */ item.setItemMeta(meta);
			/*     */
					inv.setItem(1, item);
  
		
	}
});
return inv;
	}

}
