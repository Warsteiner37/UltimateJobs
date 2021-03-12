/*    */ package de.warsteiner.ultimatejobs.utils;
/*    */ 
 
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
/*    */ import org.bukkit.entity.Player;

import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle;
/*    */ 
/*    */ 
/*    */ public class ActionBar
/*    */ {
/*    */   public static void sendActionbar(Player player, String actionbar) {
/* 13 */     CraftPlayer p = (CraftPlayer)player;
/* 14 */     PacketPlayOutTitle sendactionbar = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, 
/* 15 */         (IChatBaseComponent)IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + actionbar + "\"}"));
/* 16 */     (p.getHandle()).playerConnection.sendPacket((Packet)sendactionbar);
/*    */   }
/*    */ }


/* Location:              C:\Users\hypas\Desktop\UltimateJobs.jar!\de\warsteine\\ultimatejob\\utils\ActionBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */