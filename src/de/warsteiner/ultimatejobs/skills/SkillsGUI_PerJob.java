package de.warsteiner.ultimatejobs.skills;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
 
import de.warsteiner.ultimatejobs.UltimateJobs;
import de.warsteiner.ultimatejobs.custom.PlayerOpenSkillsGUI;
import de.warsteiner.ultimatejobs.utils.JobAPI;

public class SkillsGUI_PerJob {
	
	public static void setSkillItems(Player p) {
		  InventoryView inv = p.getOpenInventory();
		String job = JobAPI.getCurrentJob(p.getUniqueId());
		FileConfiguration cfg = UltimateJobs.getSkillsPerJob().getCustomConfig();
		 
			
		Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {
		
			@Override
			public void run() {
				
				 int points = UltimateJobs.getData().getSkillPointsOfJob(""+p.getUniqueId(), job);
				 
				 List<String> list = cfg.getStringList("SillJobs."+job+".Use");
				 
				 for (int i = 0; i < list.size(); i++) {
					 
					 String b = list.get(i);
					 int i3 = i+1;
					 int level = UltimateJobs.getData().getSkilledLevelOfJob(""+p.getUniqueId(), job, i3);
						
					 String price = SkillsAPIForJobs.getNextLevelPrice(job, b, level);
					 String multi = SkillsAPIForJobs.getNextLevelMulti(job, b, level);
				 
					 if(cfg.getString("SillJobs."+job+"."+b+".Material") != null) {
						 
						 String raw = cfg.getString("SillJobs."+job+"."+b+".Material");
						 boolean Enchanted = cfg.getBoolean("SillJobs."+job+"."+b+".Enchanted");
						 int Slot = cfg.getInt("SillJobs."+job+"."+b+".Slot");
						 String dis = cfg.getString("SillJobs."+job+"."+b+".Display");
						   List<String> lore = cfg.getStringList("SillJobs."+job+"."+b+".Lore");
						   
						   ItemStack m = null;
						   
							 if(Material.getMaterial(raw) == null) {
								 m = generateSkull(raw);
							 } else {
								 m = new ItemStack(Material.valueOf(raw),1);
							 }
						 
								/* 41 */ ItemStack item = m;
								/* 42 */ ItemMeta meta = item.getItemMeta();
								/* 43 */ meta.setDisplayName(dis.replaceAll("&", "§"));
								
						 
								 ArrayList<String> l = new ArrayList<String>();
								 
								 for (int i2 = 0; i2 < lore.size(); i2++) {
									 l.add(lore.get(i2).replaceAll("<m>", ""+multi).replaceAll("<price>", ""+price).replaceAll("<level>", ""+level).replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)).replaceAll("<points>", ""+points).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
								 }
								 
								 meta.setLore(l);
								 meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
								 if(Enchanted) {
									 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
									 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
								 }
								
								/* 44 */ item.setItemMeta(meta);
								/*     */
								/* 46 */ inv.setItem(Slot, item);
						 
					 }
					 
				 }
				 
			}
		});
		 
	}
	
	public static void open(Player p) {
		p.openInventory(get(p));
		setSkillItems(p);
	}
	
	public static Inventory get(final Player p) {
		String job = JobAPI.getCurrentJob(p.getUniqueId());
		FileConfiguration cfg = UltimateJobs.getSkillsPerJob().getCustomConfig();
/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * cfg.getInt("Design.Size"),
		cfg.getString("Design.Name").replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)).replaceAll("&", "§"));
new PlayerOpenSkillsGUI(p);
Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

	@Override
	public void run() {
		
		 List<String> list = cfg.getStringList("SillJobs."+job+".Use");
	 
		List<String> list_of_placeholders = cfg.getStringList("Design.PlaceHolders");
		List<String> list_of_custom_Items = cfg.getStringList("Design.Items");
 
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
		
		 int points = UltimateJobs.getData().getSkillPointsOfJob(""+p.getUniqueId(), job);
		
		 for (int i = 0; i < list_of_custom_Items.size(); i++) {
			 
			 String b = list_of_custom_Items.get(i);
		 
			 boolean use = cfg.getBoolean("Items."+b+".Enabled");
			 
			 if(use == true) {
				 
	 
					 String mat = cfg.getString("Items."+b+".Material");
					 
					 ItemStack new_mat = null;
					 
					 if(Material.getMaterial(mat) == null) {
						 new_mat = generateSkull( mat.replaceAll("<player>", p.getName()));
					 } else {
						 new_mat = new ItemStack(Material.valueOf(mat),1);
					 }
					 
					 int slot = cfg.getInt("Items."+b+".Slot");
					 boolean lore = cfg.getBoolean("Items."+b+".Lore_Option");
					 boolean enchanted = cfg.getBoolean("Items."+b+".Enchanted");
					 
				 
						 
						 ItemStack item = new_mat;
						 
						 ItemMeta meta = item.getItemMeta();
						 
						 if(lore ) {
							 
							 List<String> a = cfg.getStringList("Items."+b+".Lore");
							 
							 ArrayList<String> l = new ArrayList<String>();
							 
							 for (int i2 = 0; i2 < a.size(); i2++) {
								 l.add(a.get(i2).replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)).replaceAll("<points>", ""+points).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 }
						 
						 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(job)).replaceAll("<points>", ""+points).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
						 if(enchanted) {
							 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
							 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 }
						 
						 item.setItemMeta(meta);
						 
					 
							 inv.setItem(slot, item);
					 
						  continue;
						 
					 
					 
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
