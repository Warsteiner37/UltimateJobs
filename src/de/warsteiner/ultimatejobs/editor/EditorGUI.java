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

public class EditorGUI {

	public static Inventory load(final Player p) {
		 
		/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * 3,
				"�bUltimateJobs �8: �cEdit");
		 
		Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
		 
		 
				ItemStack d = new ItemStack(Material.BARRIER,1);
				/* 42 */ ItemMeta dm = d.getItemMeta();
				/* 43 */ dm.setDisplayName("�8< �7Warning �8>");
				ArrayList<String> dore = new ArrayList<String>();
		 
				dore.add("�bThis feature is in work!");
				dm.setLore(dore);
				
				/* 44 */ d.setItemMeta(dm);
				/*     */
						inv.setItem(13, d);
				
					 
					/* 41 */ ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
					/* 42 */ ItemMeta meta = item.getItemMeta();
					/* 43 */ meta.setDisplayName("�c ");
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
 