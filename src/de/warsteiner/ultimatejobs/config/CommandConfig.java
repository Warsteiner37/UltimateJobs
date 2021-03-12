package de.warsteiner.ultimatejobs.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.warsteiner.ultimatejobs.UltimateJobs;

public class CommandConfig {
	
	
	
    private File customConfigFile;
    private FileConfiguration customConfig;
 
    public FileConfiguration getCustomConfig() {
     
        return this.customConfig;
    }

    public void createCustomConfig() {
        customConfigFile = new File(UltimateJobs.getPlugin().getDataFolder(), "Command.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            UltimateJobs.getPlugin(). saveResource("Command.yml", false);
         }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
