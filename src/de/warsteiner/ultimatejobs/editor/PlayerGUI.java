package de.warsteiner.ultimatejobs.editor;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
 
import de.warsteiner.ultimatejobs.UltimateJobs;

public class PlayerGUI {
	
	public static ItemStack generateSkull(String owner) {
		/* 156 */     ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		/* 157 */     SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
		/* 158 */     skullMeta.setOwner(owner);
		/* 159 */     itemStack.setItemMeta((ItemMeta)skullMeta);
		/* 160 */     return itemStack;
		/*     */   }
	
	public static Inventory load(final Player p, int from, int max, int page) {
		 
		/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * 6,
				"§bUltimateJobs §8: §cPlayers §8: §a#"+page);
		 
		Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				  Collection<String> players = UltimateJobs.getData().getPlayerList();
				  
				  ArrayList<Integer> list = new ArrayList<Integer>();
				  
				  for (int i = from; i < max; i++) {
					  list.add(i);
				  }
				  
					 for (int i = 0; i < players.size(); i++) {
					  
						 String pl = UltimateJobs.getData().getPlayerList().get(i);
						 
						if(UltimateJobs.getData().getUUIDByName(pl) != null) {
							String uuid = UltimateJobs.getData().getUUIDByName(pl);
							ItemStack d = generateSkull(pl);
							/* 42 */ ItemMeta dm = d.getItemMeta();
							/* 43 */ dm.setDisplayName("§8< §8(§7"+pl+"§8) §8>");
							ArrayList<String> dore = new ArrayList<String>();
							 
								dore.add("§7Name§8: §a"+pl);
								dore.add("§7UUID§8: §a"+uuid);
								dore.add("§7");
								dore.add("§8-> §7Click to manage this player");
					 
							dm.setLore(dore);
							
							/* 44 */ d.setItemMeta(dm);
							/*     */
									inv.setItem(list.get(i), d);
						}
					  
				  }
		 
				ItemStack d = new ItemStack(Material.GREEN_DYE,1);
				/* 42 */ ItemMeta dm = d.getItemMeta();
				/* 43 */ dm.setDisplayName("§8< §7Next Page §8>");
				ArrayList<String> dore = new ArrayList<String>();
					int n = page+1;
				if(players.size() >= 35) {
					dore.add("§bGo to next Page: #"+n);
				} else {
					dore.add("§cYou cant go to a other page!");
				}
				dm.setLore(dore);
				
				/* 44 */ d.setItemMeta(dm);
				/*     */
						inv.setItem(50, d);
 
 
						ItemStack rl = new ItemStack(Material.RED_DYE,1);
						/* 42 */ ItemMeta rlm = rl.getItemMeta();
						/* 43 */ rlm.setDisplayName("§8< §7Go Back §8>");
						ArrayList<String> rllore = new ArrayList<String>();
						if(page == 1) {
							rllore.add("§cYou cant go back!");
						} else {
							int m = page-1;
							rllore.add("§bGo back to Page: #"+m);
						}
						rlm.setLore(rllore);
						
						/* 44 */ rl.setItemMeta(rlm);
						/*     */
								inv.setItem(48, rl);
		 
					/* 41 */ ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
					/* 42 */ ItemMeta meta = item.getItemMeta();
					/* 43 */ meta.setDisplayName("§c ");
					/* 44 */ item.setItemMeta(meta);
					/*     */
 
							inv.setItem(36, item);
							inv.setItem(37, item);
							inv.setItem(38, item);
							inv.setItem(39, item);
							inv.setItem(40, item);
							inv.setItem(41, item);
							inv.setItem(42, item);
							inv.setItem(43, item);
							inv.setItem(44, item);
			}    
		});
		return inv;

		}
	 


}
