package sylaires.invasion.main;

import java.io.File;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/*
 * Copyright 2022, Sylaires. All rights reserved.
 */

public class FileManager {
	
	private File file;
	private FileConfiguration config;
	
	private FileManager(String filename) {
		file = new File(Main.getPlugin().getDataFolder(), filename + ".yml");
		
		if(!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch(Exception e) {
				Bukkit.getLogger().info("[Invasion] **ERROR**");
				Bukkit.getLogger().info("File could not be created.");
				Bukkit.getLogger().info("Class: FileManager - Method: Constructor");
				Bukkit.getLogger().info("[Invasion] **ERROR**");
			}
		}
		
		config = YamlConfiguration.loadConfiguration(file);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String path) {
		return (T) config.get(path);
	}
	
	public void set(String path, Object value) {
		config.set(path, value);
		save();
	}
	
	public boolean contains(String path) {
		return config.contains(path);
	}
	
	private void save() {
		try {
			config.save(file);
		} catch(Exception e) {
			Bukkit.getLogger().info("[Invasion/ERROR] SettingsManager: Method save() was unable to save a file!");
		}
	}
	
	public Set<String> getKeys() {
		return config.getKeys(false);
	}
	
	public ConfigurationSection createSection(String path) {
		ConfigurationSection section = config.createSection(path);
		save();
		return section;
	}
	
	public static FileManager getDataFile(String name) {
		FileManager data = new FileManager(name);
		return data;
	}

}
