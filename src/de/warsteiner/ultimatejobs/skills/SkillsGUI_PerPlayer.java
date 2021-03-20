package de.warsteiner.ultimatejobs.skills;

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
import de.warsteiner.ultimatejobs.utils.JobAPI;

public class SkillsGUI_PerPlayer {
	
	public static Inventory load(final Player p) {
		FileConfiguration cfg = UltimateJobs.getPerPlayerSkillsConfig().getCustomConfig();
/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * cfg.getInt("Design.Size"),
		cfg.getString("Design.Name").replaceAll("&", "§"));
new PlayerOpenSkillsGUI(p);
Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

	@Override
	public void run() {
		
	 
	 
		List<String> list_of_placeholders = cfg.getStringList("Design.PlaceHolders");
		List<String> list_of_custom_Items = cfg.getStringList("Design.Items");
		List<String> list_of_skills = cfg.getStringList("Design.Skills");
		
		
		 for (int i = 0; i < list_of_skills.size(); i++) {
			 
			 String b = list_of_skills.get(i);
			 
			 String[] action = cfg.getString("Items."+b+".Action").split(":");
			 
			 int current_level = UltimateJobs.getData().getSkillPointLevel(""+p.getUniqueId(), action[1]);
			 
			double multi = UltimateJobs.getSkillAPI().getMultiByName(action[1], ""+p.getUniqueId());
			
			String price =  UltimateJobs.getSkillAPI().getNextLevelPrice(b, current_level+1);
			String next =  UltimateJobs.getSkillAPI().getNextLevelMulti(b, current_level+1);
		 
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
								 l.add(a.get(i2).replaceAll("<name>", p.getName()).replaceAll(">n>", ""+next).replaceAll("<price>", ""+price).replaceAll("<m>", ""+multi).replaceAll("<level>", ""+current_level).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 }
						 
						 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<n>", ""+next).replaceAll("<price>", ""+price).replaceAll("<m>", ""+multi).replaceAll("<level>", ""+current_level).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
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
							 
							 List<String> a = cfg.getStringList("Items."+b+".Lore");
							 
							 ArrayList<String> l = new ArrayList<String>();
							 
							 for (int i2 = 0; i2 < a.size(); i2++) {
								 l.add(a.get(i2).replaceAll("<name>", p.getName()).replaceAll("<n>", ""+next).replaceAll("<price>", ""+price).replaceAll("<m>", ""+multi).replaceAll("<level>", ""+current_level).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 } 
						 
						 if(enchanted) {
							 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
							 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 }
						 
						 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<n>", ""+next).replaceAll("<price>", ""+price).replaceAll("<m>", ""+multi).replaceAll("<level>", ""+current_level).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
						 item.setItemMeta(meta);
						 
					 
							 inv.setItem(slot, item);
						 
						  continue;
						 
					 }
			 

			
		}
 
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
		
		 int points = UltimateJobs.getData().getSkillPoints(""+p.getUniqueId());
		
		 for (int i = 0; i < list_of_custom_Items.size(); i++) {
			 
			 String b = list_of_custom_Items.get(i);
		 
			 boolean use = cfg.getBoolean("Items."+b+".Enabled");
			 
			 if(use == true) {
				 
	 
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
								 l.add(a.get(i2).replaceAll("<points>", ""+points).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 }
						 
						 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<points>", ""+points).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
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
							 
							 List<String> a = cfg.getStringList("Items."+b+".Lore");
							 
							 ArrayList<String> l = new ArrayList<String>();
							 
							 for (int i2 = 0; i2 < a.size(); i2++) {
								 l.add(a.get(i2).replaceAll("<points>", ""+points).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 } 
						 
						 if(enchanted) {
							 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
							 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 }
						 
						 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<points>", ""+points).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
						 item.setItemMeta(meta);
						 
					 
							 inv.setItem(slot, item);
						 
						  continue;
						 
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
