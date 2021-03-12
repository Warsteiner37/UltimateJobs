package de.warsteiner.ultimatejobs.ranking;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class GlobalRankingGUI {
	
	private static Map<String, Integer> test = Maps.newHashMap();
	
	public static Inventory load(final Player p) {
		FileConfiguration cfg = UltimateJobs.getTopConfig().getCustomConfig();
/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * cfg.getInt("Design_Global.Size"),
		cfg.getString("Design_Global.Name").replaceAll("&", "§"));

Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

	@Override
	public void run() {
		
		List<String> players = UltimateJobs.getData().getPlayerList();
		
		for(String p2 : players) {
			String ud = UltimateJobs.getData().getUUIDByName(p2);
			int points_job = UltimateJobs.getData().getGlobalPoints(ud);
			test.put(p2, points_job);
		}
		
		List<Entry<String, Integer>> sorted = MapSorting.sortedValues(test);
		List<String> ranked = Lists.newArrayList(MapSorting.keys(sorted));
		
	 
		List<String> list_of_placeholders = cfg.getStringList("Design_Global.PlaceHolders");
		List<String> list_of_custom_Items = cfg.getStringList("Design_Global.Items");
 
		/* 34 */ for (String pl : list_of_placeholders) {
			/* 35 */ String[] t = pl.split(":");
			/*     */
			/* 37 */ Material material = Material.valueOf(t[0]);
			/* 38 */ int slot = Integer.valueOf(t[1]).intValue();
			/* 39 */ String display = t[2];
			/*     */
			/* 41 */ ItemStack item = new ItemStack(material, 1);
			/* 42 */ ItemMeta meta = item.getItemMeta();
			/* 43 */ meta.setDisplayName(display.replaceAll("&", "§"));
			/* 44 */ item.setItemMeta(meta);
			/*     */
			/* 46 */ inv.setItem(slot, item);
			/*     */ }
		
 
		 for (int i = 0; i < list_of_custom_Items.size(); i++) {
			 
			 String b = list_of_custom_Items.get(i);
		 
			 boolean use = cfg.getBoolean("Items_Global."+b+".Enabled");
			 
			 if(use == true) {
				 
				 	String[] action =  cfg.getString("Items_Global."+b+".Action").split(":");
				 	
				 	if(action[1].equalsIgnoreCase("TOP")) {
				 		
				 		Integer place = Integer.valueOf(action[2]);
				 		
						int rechnung = ranked.size()-place;
						
						 String mat = cfg.getString("Items_Global."+b+".Material");
						 
						 String new_mat = null;
						 
					 
						 
						 int slot = cfg.getInt("Items_Global."+b+".Slot");
						 boolean lore = cfg.getBoolean("Items_Global."+b+".Lore_Option");
						 boolean enchanted = cfg.getBoolean("Items_Global."+b+".Enchanted");
						 
						 String dis = null;
						 String player = null;
						 int points = 0;
						 
						if(rechnung >= 0) {
							
							  player = ranked.get(ranked.size()-place);
							
							 dis = cfg.getString("Items_Global."+b+".Display").replaceAll("<points>", ""+points).replaceAll("<name>", player).replaceAll("&", "§");
							 
							 points = UltimateJobs.getData().getGlobalPoints(UltimateJobs.getData().getUUIDByName(player));
							 
						} else {
							
							 dis = cfg.getString("Items_Global."+b+".Display").replaceAll("<points>", ""+points).replaceAll("<name>", cfg.getString("Options_Global.None")).replaceAll("&", "§");
							 player = "NONE";
						}
						
						 if(Material.getMaterial(mat) == null) {
							 new_mat = mat.replaceAll("<player>", player);
						 } else {
							 new_mat = mat;
						 }
						
						 if(Material.getMaterial(mat) == null) {
							 
							 ItemStack item = generateSkull(new_mat);
							 
							 ItemMeta meta = item.getItemMeta();
							 
							 if(lore ) {
								 
								 List<String> a = cfg.getStringList("Items_Global."+b+".Lore");
								 
								 ArrayList<String> l = new ArrayList<String>();
								 
								 for (int i2 = 0; i2 < a.size(); i2++) {
									 l.add(a.get(i2).replaceAll("<points>", ""+points).replaceAll("<name>", player).replaceAll("&", "§"));
								 }
								 
								 meta.setLore(l);
								 
							 }
							 
							 meta.setDisplayName(dis);
							 
							 if(enchanted) {
								 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
								 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							 }
							 
							 item.setItemMeta(meta);
							 
						 
								 inv.setItem(slot, item);
						 
							  continue;
							 
						 } else {
							 
							 ItemStack item = new ItemStack(Material.valueOf(new_mat),1);
							 
							 ItemMeta meta = item.getItemMeta();
							 
							 if(lore ) {
								 
								 List<String> a = cfg.getStringList("Items_Global."+b+".Lore");
								 
								 ArrayList<String> l = new ArrayList<String>();
								 
								 for (int i2 = 0; i2 < a.size(); i2++) {
									 l.add(a.get(i2).replaceAll("<points>", ""+points).replaceAll("<name>", player).replaceAll("&", "§"));
								 }
								 
								 meta.setLore(l);
								 
							 } 
							 
							 if(enchanted) {
								 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
								 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							 }
							 
							 meta.setDisplayName(dis);
							 
							 item.setItemMeta(meta);
							 
						 
								 inv.setItem(slot, item);
							 
							  continue;
							 
						 }
				 	  
				 	 
				 	} else {
	 
					 String mat = cfg.getString("Items_Global."+b+".Material");
					 
					 String new_mat = null;
					 
					 if(Material.getMaterial(mat) == null) {
						 new_mat = mat.replaceAll("<player>", p.getName());
					 } else {
						 new_mat = mat;
					 }
					 
					 int slot = cfg.getInt("Items_Global."+b+".Slot");
					 boolean lore = cfg.getBoolean("Items_Global."+b+".Lore_Option");
					 boolean enchanted = cfg.getBoolean("Items_Global."+b+".Enchanted");
					 
					 if(Material.getMaterial(mat) == null) {
						 
						 ItemStack item = generateSkull(new_mat);
						 
						 ItemMeta meta = item.getItemMeta();
						 
						 if(lore ) {
							 
							 List<String> a = cfg.getStringList("Items_Global."+b+".Lore");
							 
							 ArrayList<String> l = new ArrayList<String>();
							 
							 for (int i2 = 0; i2 < a.size(); i2++) {
								 l.add(a.get(i2).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 }
						 
						 meta.setDisplayName(cfg.getString("Items_Global."+b+".Display").replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
						 if(enchanted) {
							 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
							 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 }
						 
						 item.setItemMeta(meta);
						 
					 
							 inv.setItem(slot, item);
					 
						  continue;
						 
					 } else {
						 
						 ItemStack item = new ItemStack(Material.valueOf(new_mat),1);
						 
						 ItemMeta meta = item.getItemMeta();
						 
						 if(lore ) {
							 
							 List<String> a = cfg.getStringList("Items_Global."+b+".Lore");
							 
							 ArrayList<String> l = new ArrayList<String>();
							 
							 for (int i2 = 0; i2 < a.size(); i2++) {
								 l.add(a.get(i2).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 } 
						 
						 if(enchanted) {
							 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
							 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 }
						 
						 meta.setDisplayName(cfg.getString("Items_Global."+b+".Display").replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
						 item.setItemMeta(meta);
						 
					 
							 inv.setItem(slot, item);
						 
						  continue;
						 
					 }
					 
					 
				 }  
				 
			 }
			

			
		}
		
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
