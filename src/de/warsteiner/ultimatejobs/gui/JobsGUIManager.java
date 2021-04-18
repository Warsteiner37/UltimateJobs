/*     */
package de.warsteiner.ultimatejobs.gui;
  
import java.util.ArrayList;
import java.util.List;

/*     */ import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.PlayerOpenJobsGUI;
import de.warsteiner.ultimatejobs.utils.JobAPI;
 
/*     */
/*     */
/*     */
/*     */ public class JobsGUIManager
 
/*     */ {
	
	public static void updateCustomItems(Player p, InventoryView inv) {

		FileConfiguration cfg = UltimateJobs.getMainGUIConfig().getCustomConfig();
		 
		List<String> list_own_items =cfg.getStringList("Design.Used_Items");  
		List<String> list_of_placeholders = cfg.getStringList("Design.PlaceHolders");

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
		
		String jobwithcolor = null;
		
		if(UltimateJobs.getJobAPI().getCurrentJob(p.getUniqueId()).equalsIgnoreCase("None")) {
			jobwithcolor = UltimateJobs.getPlugin().getConfig().getString("PlaceHolders.No_Job");
		} else {
			jobwithcolor =  UltimateJobs.getJobAPI().JobNameWithColor(UltimateJobs.getJobAPI().getCurrentJob(p.getUniqueId()));
		}
		
		 for (int i = 0; i < list_own_items.size(); i++) {
			 
			 String b = list_own_items.get(i);
		 
			 boolean use = cfg.getBoolean("Items."+b+".Enabled");
			 
			 if(use == true) {
				 
				 boolean only_job = cfg.getBoolean("Items."+b+".Show_Only_If_InJob");
				 
				 
					 
					 String mat = cfg.getString("Items."+b+".Material");
					 
					 String new_mat = null;
					 
					 if(Material.getMaterial(mat) == null) {
						 new_mat = mat.replaceAll("<player>", p.getName());
					 } else {
						 new_mat = mat;
					 }
					 
					 int slot = cfg.getInt("Items."+b+".Slot");
					 boolean lore = cfg.getBoolean("Items."+b+".Lore_Option");
					 boolean enchanted = cfg.getBoolean("Items."+b+".Enchanted");
					 
					 if(Material.getMaterial(mat) == null) {
						 
						 ItemStack item = generateSkull(new_mat);
						 
						 ItemMeta meta = item.getItemMeta();
						 
						 if(lore ) {
							 
							 List<String> a = cfg.getStringList("Items."+b+".Lore");
							 
							 ArrayList<String> l = new ArrayList<String>();
							 
							 for (int i2 = 0; i2 < a.size(); i2++) {
								 l.add(a.get(i2).replaceAll("<job>", jobwithcolor).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 }
						 
						 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
						 if(enchanted) {
							 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
							 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 }
						 
						 item.setItemMeta(meta);
						 
						 if(only_job == false) {
							 inv.setItem(slot, item);
						 }  else if(!UltimateJobs.getJobAPI().getCurrentJob(p.getUniqueId()).equalsIgnoreCase("None")) {
							 inv.setItem(slot, item);
						 }
						 
						  continue;
						 
					 } else {
						 
						 ItemStack item = new ItemStack(Material.valueOf(new_mat),1);
						 
						 ItemMeta meta = item.getItemMeta();
						 
						 if(lore ) {
							 
							 List<String> a = cfg.getStringList("Items."+b+".Lore");
							 
							 ArrayList<String> l = new ArrayList<String>();
							 
							 for (int i2 = 0; i2 < a.size(); i2++) {
								 l.add(a.get(i2).replaceAll("<job>", jobwithcolor).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 }
						 
						 if(enchanted) {
							 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
							 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 }
						 
						 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
						 item.setItemMeta(meta);
						 
						 if(only_job == false) {
							 inv.setItem(slot, item);
						 }  else if(!UltimateJobs.getJobAPI().getCurrentJob(p.getUniqueId()).equalsIgnoreCase("None")) {
							 inv.setItem(slot, item);
						 }
						 
						  continue;
						 
					 }
					  
				 }  
		 }
		 
	 
			 
			 
		 
	}
				 
	
	public static void updateJobs(Player p, InventoryView inventoryView) {
		 JobsGUIManager.updateCustomItems(p,inventoryView);
		FileConfiguration jobs = UltimateJobs.getJobsConfig().getCustomConfig();
		
		List<String> List_jobs = jobs.getStringList("Options.GUI");
	 
		
		 for (int i = 0; i < List_jobs.size(); i++) {
			 
			 String b = List_jobs.get(i);
			 
			 String mat = jobs.getString(b+".Icon");
			 
			 String new_mat = null;
			 
			 if(Material.getMaterial(mat) == null) {
				 new_mat = mat.replaceAll("<player>", p.getName());
			 } else {
				 new_mat = mat;
			 }
			 
			 int slot = jobs.getInt(b+".Slot");
			 int price = jobs.getInt(b+".Price");
			 String color = jobs.getString(b+".Color");
			 String display = jobs.getString(b+".Display");

			 List<String> desc = jobs.getStringList(b+".Description");
		 
			 String finaldis = color+display;
			 
			 if(Material.getMaterial(mat) == null) {
				 ItemStack item = generateSkull(new_mat);
				 
				 ItemMeta meta = item.getItemMeta();
				  
				 meta.setDisplayName(finaldis.replaceAll("<name>", p.getName()).replaceAll("&", "§"));
				 
				 String state = null;
				 
				 boolean ech = false;
				 
				 if(UltimateJobs.getData().getCurrentJob(p.getUniqueId()).equalsIgnoreCase(b)) {
					 state = jobs.getString("Options.States.In_Job");
					 ech = true;
				 } else if(UltimateJobs.getData().hasJob(p.getUniqueId(), b)) {
					 state = jobs.getString("Options.States.Own_Job");
				 } else {
					 state = jobs.getString("Options.States.Price").replaceAll("<price>", ""+price);
				 }
				 
				 ArrayList<String> l = new ArrayList<String>();
				 
				  for (int i2 = 0; i2 < desc.size(); i2++) {
						  l.add(desc.get(i2).replaceAll("<state>", state).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
				  }
					 
				  meta.setLore(l);
				  
				  List<String> flags = jobs.getStringList("Options.Item_Flags");
				 
				  for(String c : flags) {
					  meta.addItemFlags(ItemFlag.valueOf(c));
				  }
				  
				  if(ech) {
				  meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			   
				  }
				 
				 item.setItemMeta(meta);
				  
					 inventoryView.setItem(slot, item);
					 
					  continue;
				 
			 } else {
				 ItemStack item = new ItemStack(Material.valueOf(new_mat),1);
				 
				 ItemMeta meta = item.getItemMeta();
				  
				 meta.setDisplayName(finaldis.replaceAll("<name>", p.getName()).replaceAll("&", "§"));
				 
				 String state = null;
				 
				 boolean ech = false;
				 
				 if(UltimateJobs.getData().getCurrentJob(p.getUniqueId()).equalsIgnoreCase(b)) {
					 state = jobs.getString("Options.States.In_Job");
					 ech = true;
				 } else if(UltimateJobs.getData().hasJob(p.getUniqueId(), b)) {
					 state = jobs.getString("Options.States.Own_Job");
				 } else {
					 state = jobs.getString("Options.States.Price").replaceAll("<price>", ""+price);
				 }
				 
				 ArrayList<String> l = new ArrayList<String>();
				 
				  for (int i2 = 0; i2 < desc.size(); i2++) {
						  l.add(desc.get(i2).replaceAll("<state>", state).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
				  }
					 
				  meta.setLore(l);
				 
				  List<String> flags = jobs.getStringList("Options.Item_Flags");
					 
				  for(String c : flags) {
					  meta.addItemFlags(ItemFlag.valueOf(c));
				  }
				  
				  if(ech) {
				  meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			   
				  }
				 
				 item.setItemMeta(meta);
				  
					 inventoryView.setItem(slot, item);
					 
					  continue;
			 }
		 
			 
			   
			   
			 
}
		
	}
	
				public static void open(Player p) {
					p.openInventory(get(p));
					updateJobs(p, p.getOpenInventory());
					updateCustomItems(p, p.getOpenInventory());
				}
	
	/*     */ public static Inventory get(final Player p) {
		
				if(JobAPI.getCurrentJob(p.getUniqueId()) != null) {
				 new PlayerOpenJobsGUI(p);
		
		/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * UltimateJobs.getMainGUIConfig().getCustomConfig().getInt("Options.Size"),
				UltimateJobs.getMainGUIConfig().getCustomConfig().getString("Options.Name").replaceAll("&", "§"));
 
 
		
		return inv;
		
				} else {
					p.sendMessage("§4§lError by loading player data. Please rejoin the current service!");
				}
				return null;
		
		/* 160 */  
		/*     */ }
	
	public static ItemStack generateSkull(String owner) {
		/* 156 */     ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		/* 157 */     SkullMeta skullMeta = (SkullMeta)itemStack.getItemMeta();
		/* 158 */     skullMeta.setOwner(owner);
		/* 159 */     itemStack.setItemMeta((ItemMeta)skullMeta);
		/* 160 */     return itemStack;
		/*     */   }
	
}
 
 
 