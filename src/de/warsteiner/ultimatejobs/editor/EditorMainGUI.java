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
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.PlayerOpenSkillsGUI;

public class EditorMainGUI {
	
	public static Inventory load(final Player p) {
 
/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * 5,
		"?bUltimateJobs ?8: ?c"+UltimateJobs.getPlugin().getDescription().getVersion());
 
Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
 
 
 
 
			/* 41 */ ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
			/* 42 */ ItemMeta meta = item.getItemMeta();
			/* 43 */ meta.setDisplayName("?c ");
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
					inv.setItem(26, item);
					inv.setItem(27, item);
					inv.setItem(35, item);
					inv.setItem(36, item);
					inv.setItem(37, item);
					inv.setItem(38, item);
					inv.setItem(39, item);
					inv.setItem(40, item);
					inv.setItem(41, item);
					inv.setItem(42, item);
					inv.setItem(43, item);
					inv.setItem(44, item);
					
 
								if(Bukkit.getPluginManager().getPlugin("PlugMan") != null) {
									ItemStack rl = new ItemStack(Material.REDSTONE_TORCH,1);
									/* 42 */ ItemMeta rlm = rl.getItemMeta();
									/* 43 */ rlm.setDisplayName("?8< ?7Plugin Reload ?8>");
									ArrayList<String> rllore = new ArrayList<String>();
									rllore.add("?8-> ?bReload the plugin");
									rlm.setLore(rllore);
									
									/* 44 */ rl.setItemMeta(rlm);
									/*     */
											inv.setItem(23, rl);
								} else {
									ItemStack rl = new ItemStack(Material.BARRIER,1);
									/* 42 */ ItemMeta rlm = rl.getItemMeta();
									/* 43 */ rlm.setDisplayName("?8< ?cPlugin Reload ?8>");
									ArrayList<String> rllore = new ArrayList<String>();
									rllore.add("?8-> ?7You cant reload the plugin! Please install 'PlugMan - Improved'");
									rlm.setLore(rllore);
									
									/* 44 */ rl.setItemMeta(rlm);
									/*     */
											inv.setItem(23, rl);
								}
			 
					/* 41 */ ItemStack dc = generateSkull("Mhf_Discord");
					/* 42 */ ItemMeta mdc = dc.getItemMeta();
					/* 43 */ mdc.setDisplayName("?8< ?7Join our Discord ?8>");
					
							ArrayList<String> dclore = new ArrayList<String>();
							dclore.add("?8-> ?9https://discord.ultimate-plugins.de");
							mdc.setLore(dclore);
					
					/* 44 */ dc.setItemMeta(mdc);
					/*     */
							inv.setItem(39, dc);
  
							ItemStack dw = generateSkull("Earth");
							/* 42 */ ItemMeta mdw = dw.getItemMeta();
							/* 43 */ mdw.setDisplayName("?8< ?7Visit the Download Page ?8>");
							ArrayList<String> dwlore = new ArrayList<String>();
							dwlore.add("?8-> ?6https://github.com/Warsteiner37/UltimateJobs");
							mdw.setLore(dwlore);
							
							/* 44 */ dw.setItemMeta(mdw);
							/*     */
									inv.setItem(41, dw);
									
									ItemStack ed = new ItemStack(Material.REDSTONE,1);
									/* 42 */ ItemMeta med = ed.getItemMeta();
									/* 43 */ med.setDisplayName("?8< ?7Open Settings ?8>");
									ArrayList<String> edlore = new ArrayList<String>();
									edlore.add("?8-> ?bChange ingame settings");
									med.setLore(edlore);
									
									/* 44 */ ed.setItemMeta(med);
									/*     */
											inv.setItem(21, ed);
							
		
	}
});
return inv;
	}

	public static ItemStack generateSkull(String owner) {
		/* 156 */     ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		/* 157 */     SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
		/* 158 */     skullMeta.setOwner(owner);
		/* 159 */     itemStack.setItemMeta((ItemMeta)skullMeta);
		/* 160 */     return itemStack;
		/*     */   }
	
}














