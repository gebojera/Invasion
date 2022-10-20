package sylaires.invasion.util;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class TitleUtil {
	
	public static void setForPlayer(Player p, String header, String footer){
	       
        CraftPlayer craftplayer = (CraftPlayer)p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;
        IChatBaseComponent top = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        IChatBaseComponent bottom = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try
        {
          Field headerField = packet.getClass().getDeclaredField("a");
          headerField.setAccessible(true);
          headerField.set(packet, top);
          headerField.setAccessible(!headerField.isAccessible());

          Field footerField = packet.getClass().getDeclaredField("b");
          footerField.setAccessible(true);
          footerField.set(packet, bottom);
          footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception ev) {
          ev.printStackTrace();
        }

        connection.sendPacket(packet);
    }
	
	@SuppressWarnings("deprecation")
	public static void createScoreboard(Player p, String title, String[] text){
        //Create new Scoreboard
        
        ScoreboardManager mgr = Bukkit.getScoreboardManager();
        Scoreboard s = mgr.getNewScoreboard();
       
        //Create objective


        Objective obj = s.registerNewObjective("sb", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName(title);
       
       
        //Add lines
       
        int value = text.length;
        for(String x : text){
            String pre = "";
            for(char c : (value+"").toCharArray()){
                pre+="�"+c;
            }
            pre+="�f";
            x=pre+x;
            if(x.length()<=16){
                obj.getScore(x).setScore(value);
            }else{
                Team team = s.registerNewTeam("line"+value);
                String prefix = x.substring(0, 16);
                String name = x.substring(16, x.length());
                String suffix = "";
                if(name.length()>16){
                    name = name.substring(0, 16);
                    suffix = x.substring(32, x.length());
                    if(suffix.length()>16)suffix = suffix.substring(0, 16);
                }
                team.setPrefix(prefix);
                team.setSuffix(suffix);
                OfflinePlayer op = Bukkit.getOfflinePlayer(name);
                team.addPlayer(op);
                obj.getScore(op).setScore(value);
            }
           
           
            value--;
        }
       
        //Set the Scoreboard for the Player
       
        p.setScoreboard(s);
    }
	
	public static void sendTitleToPlayer(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        PlayerConnection con = ((CraftPlayer) player).getHandle().playerConnection;
        con.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut));

        if (subtitle != null) {
            con.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}")));
        }

        if (title != null) {
            con.sendPacket(new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}")));
        }
    }
	
	public static void sendText(String text, Player[] player){
        String json = "{text:\""+text+"\"}";
        sendRaw(json, player);
    }
    public static void sendRaw(String json, Player[] player){
        PacketPlayOutChat chat = new PacketPlayOutChat(new ChatComponentText(json), (byte)2);
        for(Player p : player)sendPacket(chat, p);
    }
    @SuppressWarnings("rawtypes")
	private static void sendPacket(Packet p, Player p1){
        ((CraftPlayer)p1).getHandle().playerConnection.sendPacket(p);
    }

}
