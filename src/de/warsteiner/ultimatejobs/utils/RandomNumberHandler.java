package de.warsteiner.ultimatejobs.utils;

import java.util.Random;

import org.bukkit.Bukkit;

public class RandomNumberHandler {
	
	public static String gen(String m) {
		
		String gen = m;
		String[] g = gen.split("%");
		if(gen.contains("%") && gen.contains("%")) {
		   
			if(g[1].contains("ri")) {
				
				String[] g2 = g[1].split("ri");
				
				int min = Integer.valueOf(g2[0]);
				int max = Integer.valueOf(g2[1]);
				 
			  int n = getRandomInt(max,min);
				 return ""+n;
			}
		}
		return "";
	}
	
	public static Integer getRandomInt(Integer max, Integer min) {
        Random ran = new Random();
        return  ran.nextInt(max - min) + min;
 
      }

}
