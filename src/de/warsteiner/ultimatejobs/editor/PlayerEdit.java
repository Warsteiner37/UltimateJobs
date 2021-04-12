package de.warsteiner.ultimatejobs.editor;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class PlayerEdit {
	
	public static Inventory Load_Job_RemovePage(final Player p, String name) {
		 
/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * 3,
		"§bUltimateJobs §8: §cRemove §8: §4"+name);
 
Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {
 
	@Override
	public void run() {
 
		if(inv.getItem(15) == null) {
			ItemStack d1 = new ItemStack(Material.STICK,1);
			/* 42 */ ItemMeta dm1 = d1.getItemMeta();
			/* 43 */ dm1.setDisplayName("§8< §bAdd d §8>"); 
		 
			/* 44 */ d1.setItemMeta(dm1);
			/*     */
					inv.setItem(15, d1);
		}
 
		ItemStack d1 = new ItemStack(Material.BARRIER,1);
		/* 42 */ ItemMeta dm1 = d1.getItemMeta();
		/* 43 */ dm1.setDisplayName("§8< §cGo Back §8>");
		ArrayList<String> d1ore = new ArrayList<String>();
		d1ore.add("§8-> §bGo back to player list");
		dm1.setLore(d1ore);
 
		/* 44 */ d1.setItemMeta(dm1);
		/*     */
				inv.setItem(26, d1);
		
 
	}  
});
return inv;

}

	public static Inventory load(final Player p, String name, int page) {
		 
/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * 3,
		"§bUltimateJobs §8: §7Player §8: §4"+name);
 
Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {
 
	@Override
	public void run() {
 
		if(inv.getItem(15) == null) {
			ItemStack d1 = new ItemStack(Material.GREEN_DYE,1);
			/* 42 */ ItemMeta dm1 = d1.getItemMeta();
			/* 43 */ dm1.setDisplayName("§8< §bAdd Jobs §8>"); 
	 
			/* 44 */ d1.setItemMeta(dm1);
			/*     */
					inv.setItem(15, d1);
		}
		
		if(inv.getItem(11) == null) {
			ItemStack d1 = new ItemStack(Material.RED_DYE,1);
			/* 42 */ ItemMeta dm1 = d1.getItemMeta();
			/* 43 */ dm1.setDisplayName("§8< §bRemove Jobs §8>"); 
		 
			/* 44 */ d1.setItemMeta(dm1);
			/*     */
					inv.setItem(11, d1);
		}
		
		if(inv.getItem(13) == null) {
			ItemStack d1 = new ItemStack(Material.IRON_PICKAXE,1);
			/* 42 */ ItemMeta dm1 = d1.getItemMeta();
			/* 43 */ dm1.setDisplayName("§8< §bManage Jobs §8>"); 
		 
			/* 44 */ d1.setItemMeta(dm1);
			/*     */
					inv.setItem(13, d1);
		}
 
		ItemStack d1 = new ItemStack(Material.BARRIER,page);
		/* 42 */ ItemMeta dm1 = d1.getItemMeta();
		/* 43 */ dm1.setDisplayName("§8< §cGo Back §8>");
		ArrayList<String> d1ore = new ArrayList<String>();
		d1ore.add("§8-> §bGo back to player list");
		dm1.setLore(d1ore);
		 
		/* 44 */ d1.setItemMeta(dm1);
		/*     */
				inv.setItem(26, d1);
		
 
	}  
});
return inv;

}
	
}
