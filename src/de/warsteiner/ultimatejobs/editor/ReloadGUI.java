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

public class ReloadGUI {
	
	public static Inventory load(final Player p) {
		 
/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * 3,
		"§bUltimateJobs §8: §cPlugin Reload");
 
Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
 
 
		ItemStack d = new ItemStack(Material.PAPER,1);
		/* 42 */ ItemMeta dm = d.getItemMeta();
		/* 43 */ dm.setDisplayName("§8< §7Are you sure? §8>");
		ArrayList<String> dore = new ArrayList<String>();
 
		dore.add("§bYou want to reload the plugin?");
		dm.setLore(dore);
		
		/* 44 */ d.setItemMeta(dm);
		/*     */
				inv.setItem(13, d);
		
				
				ItemStack rl = new ItemStack(Material.GREEN_DYE,1);
				/* 42 */ ItemMeta rlm = rl.getItemMeta();
				/* 43 */ rlm.setDisplayName("§8< §aYes §8>");
				ArrayList<String> rllore = new ArrayList<String>();
				rllore.add("§8-> §bReload the plugin");
				rlm.setLore(rllore);
				
				/* 44 */ rl.setItemMeta(rlm);
				/*     */
						inv.setItem(11, rl);
						
						/* 41 */ ItemStack dc = new ItemStack(Material.RED_DYE,1);
						/* 42 */ ItemMeta mdc = dc.getItemMeta();
						/* 43 */ mdc.setDisplayName("§8< §cNo §8>");
						
								ArrayList<String> dclore = new ArrayList<String>();
								dclore.add("§8-> §bCancel and go back");
								mdc.setLore(dclore);
						
						/* 44 */ dc.setItemMeta(mdc);
						/*     */
								inv.setItem(15, dc);
 
 
			/* 41 */ ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
			/* 42 */ ItemMeta meta = item.getItemMeta();
			/* 43 */ meta.setDisplayName("§c ");
			/* 44 */ item.setItemMeta(meta);
			/*     */
					inv.setItem(0, item);
					inv.setItem(1, item);
					inv.setItem(2, item);
					inv.setItem(3, item);
					inv.setItem(4, item);
					inv.setItem(5, item);
					inv.setItem(6, item);
					inv.setItem(7, item);
					inv.setItem(8, item);
					inv.setItem(9, item);
					inv.setItem(17, item);
					inv.setItem(18, item);
					inv.setItem(19, item);
					inv.setItem(20, item);
					inv.setItem(21, item);
					inv.setItem(22, item);
					inv.setItem(23, item);
					inv.setItem(24, item);
					inv.setItem(25, item);
					inv.setItem(26, item);
	}  
});
return inv;

}
}
