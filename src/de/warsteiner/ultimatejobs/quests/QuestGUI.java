package de.warsteiner.ultimatejobs.quests;

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
import de.warsteiner.ultimatejobs.command.PlayerJobCommand;
import de.warsteiner.ultimatejobs.custom.PlayerOpenQuestsGUI;
import de.warsteiner.ultimatejobs.custom.PlayerOpenSkillsGUI;
import de.warsteiner.ultimatejobs.utils.JobAPI;

public class QuestGUI {
	
	public static void UpdateItemsWithActionDisplayTIme(Player p) {
		new PlayerOpenQuestsGUI(p);
		FileConfiguration cfg = UltimateJobs.getQuestAPI().getCustomConfig();
		Bukkit.getScheduler().runTaskAsynchronously(UltimateJobs.getPlugin(), new Runnable() {

			@Override
			public void run() {
				List<String> list_of_custom_Items = cfg.getStringList("Design.Items");
				
				long dann = UltimateJobs.getData().getCool(""+p.getUniqueId());
				 
				 FileConfiguration cfg = UltimateJobs.getQuestAPI().getCustomConfig();
				long secondsLeft = ((dann/1000)+cfg.getLong("New_Quest")) - System.currentTimeMillis()/1000;
				String t2 = PlayerJobCommand.calculateTime(secondsLeft);
				
				  if(secondsLeft<0) {
					  UltimateJobs.getData().UpdateQuests(""+p.getUniqueId(),  p);
					  UpdateQuest(p,p.getOpenInventory());
					  if(cfg.getBoolean("Get_Message_If_New_Quest_by_live_Update.Enabled")) {
							p.sendMessage(cfg.getString("Get_Message_If_New_Quest_by_live_Update.Message").replaceAll("&", "§"));
						}
				  }
				
				 for (int i = 0; i < list_of_custom_Items.size(); i++) {
					 
					 String b = list_of_custom_Items.get(i);
					 
					 List<String> bc = cfg.getStringList("Items."+b+".Lore");
					 
					 for(String t : bc) {
						 if(t.contains("<new>")) {
							 int slot = Integer.valueOf(cfg.getInt("Items."+b+".Slot"));
							 
							 if(p.getOpenInventory().getItem(slot) == null) {
			return;
		}
 							  
							 boolean lore = cfg.getBoolean("Items."+b+".Lore_Option");
							 boolean enchanted = cfg.getBoolean("Items."+b+".Enchanted");
							 
							ItemStack item = p.getOpenInventory().getItem(slot);
							
							 
							
							 ItemMeta meta = item.getItemMeta();
							 
							 if(lore ) {
								 
								 List<String> a = cfg.getStringList("Items."+b+".Lore");
								 
								 ArrayList<String> l = new ArrayList<String>();
								 
								 for (int i2 = 0; i2 < a.size(); i2++) {
									 l.add(a.get(i2).replaceAll("<new>", t2).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
								 }
								 
								 meta.setLore(l);
								 
							 }
							 
							 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<new>", t2).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 
							 if(enchanted) {
								 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
								 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
							 }
			 
							item.setItemMeta(meta);
						 }
					 }
					 
				 }
				
			}
			
		});
	}
	
	public static void UpdateQuest(Player p, InventoryView inventoryView) {
		FileConfiguration cfg = UltimateJobs.getQuestAPI().getCustomConfig();
		List<String> slots = cfg.getStringList("Quests.Slots");
		
		 for (int i = 0; i < slots.size(); i++) {
			 	String id = UltimateJobs.getData().getQuestByID(""+p.getUniqueId(), i);
				/*     */
				String[] action = cfg.getString("Quests."+id+".Action").split(":");
				String dis = UltimateJobs.getQuestAPI().getCustomConfig().getString("Quests."+id+".ID");
				
				if(id != null) { 

					if(UltimateJobs.getData().getQuestsBoolean(""+p.getUniqueId(), i)) {
						
						/* 41 */ ItemStack item = new ItemStack(Material.valueOf(cfg.getString("Quests.Quest_Done.Material")), 1);
						/* 42 */ ItemMeta meta = item.getItemMeta();
						/* 43 */ meta.setDisplayName(cfg.getString("Quests.Quest_Done.Display")	 .replaceAll("<quest_id>", dis)	.replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(action[0]))
								.replaceAll("&", "§"));
						
						 List<String> a = cfg.getStringList("Quests.Quest_Done.Lore");
						 
						 ArrayList<String> l = new ArrayList<String>();
						 
						 for (int i2 = 0; i2 < a.size(); i2++) {
							 l.add(a.get(i2) .replaceAll("<quest_id>", dis).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 }
						 
						 meta.setLore(l);
						
						/* 44 */ item.setItemMeta(meta);
						
						 
						inventoryView.setItem(Integer.valueOf(slots.get(i)), item);
						
					} else {
						 
					 
						 
						 int has = UltimateJobs.getData().getQuestsInt(""+p.getUniqueId(), i);
						 int need = Integer.valueOf(action[1]);
						 
						 int eins = need / 100;
						 int all = has * eins;
						/* 41 */ ItemStack item = new ItemStack(Material.valueOf(cfg.getString("Quests."+id+".Material")), 1);
						/* 42 */ ItemMeta meta = item.getItemMeta();
						/* 43 */ meta.setDisplayName(cfg.getString("Quests."+id+".Display")
								.replaceAll("<complete>", ""+all)	.replaceAll("<job>", JobAPI.fromOriginalConfigIDToCustomDisplay(action[0]))
								.replaceAll("&", "§"));
						
								List<String> lore = cfg.getStringList("Quests."+id+".Lore");
								 
								 ArrayList<String> l = new ArrayList<String>();
							 
								 
								 for (int i2 = 0; i2 < lore.size(); i2++) {
									 l.add(lore.get(i2).replaceAll("<complete>", ""+all).replaceAll("<need>", ""+need).replaceAll("<has>", ""+has).replaceAll("<new>", "").replaceAll("<name>", p.getName()).replaceAll("&", "§"));
								 }
							 
								 meta.setLore(l);
						
						/* 44 */ item.setItemMeta(meta);
						
						 
						inventoryView.setItem(Integer.valueOf(slots.get(i)), item); 
					}
					
				} else {
					/* 41 */ ItemStack item = new ItemStack(Material.valueOf(cfg.getString("Quests.No_Quest_Found.Material")), 1);
					/* 42 */ ItemMeta meta = item.getItemMeta();
					/* 43 */ meta.setDisplayName(cfg.getString("Quests.No_Quest_Found.Display").replaceAll("&", "§"));
					
					 List<String> a = cfg.getStringList("Quests.No_Quest_Found.Lore");
					 
					 ArrayList<String> l = new ArrayList<String>();
					 
					 for (int i2 = 0; i2 < a.size(); i2++) {
						 l.add(a.get(i2).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
					 }
					 
					 meta.setLore(l);
					
					/* 44 */ item.setItemMeta(meta);
					
					 
					inventoryView.setItem(Integer.valueOf(slots.get(i)), item);
				}
				/*     */
			 
		 }
	}
	
	public static Inventory load(final Player p) {
		FileConfiguration cfg = UltimateJobs.getQuestAPI().getCustomConfig();
/* 24 */ final Inventory inv = Bukkit.createInventory(null, 9 * cfg.getInt("Design.Size"),
		cfg.getString("Design.Name").replaceAll("&", "§"));




 
 
//cooldown.put(p.getName(), System.currentTimeMillis()); //update




new PlayerOpenSkillsGUI(p);
Bukkit.getScheduler().runTaskAsynchronously((Plugin) UltimateJobs.getPlugin(), new Runnable() {

	@Override
	public void run() {
		
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
		
 
		 UpdateQuest(p, p.getOpenInventory());

long dann = UltimateJobs.getData().getCool(""+p.getUniqueId());
 
 FileConfiguration cfg = UltimateJobs.getQuestAPI().getCustomConfig();
long secondsLeft = ((dann/1000)+cfg.getLong("New_Quest")) - System.currentTimeMillis()/1000;
 
 
    	String t = PlayerJobCommand.calculateTime(secondsLeft);
		
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
								 l.add(a.get(i2).replaceAll("<new>", t).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 }
						 
						 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<new>", t).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
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
								 l.add(a.get(i2).replaceAll("<new>", t).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
							 }
							 
							 meta.setLore(l);
							 
						 } 
						 
						 if(enchanted) {
							 meta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
							 meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						 }
						 
						 meta.setDisplayName(cfg.getString("Items."+b+".Display").replaceAll("<new>", t).replaceAll("<name>", p.getName()).replaceAll("&", "§"));
						 
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
