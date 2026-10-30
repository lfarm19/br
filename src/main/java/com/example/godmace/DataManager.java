package com.example.godmace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class DataManager {
    private GodMacePlugin plugin;
    private FileConfiguration config;
    private File file;

    public DataManager(GodMacePlugin plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "data.yml");
        if (!file.exists()) { plugin.saveResource("data.yml", false); }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public int getCraftCount(String key) { return config.getInt("crafts." + key, 0); }
    
    public void incrementCraftCount(String key) {
        config.set("crafts." + key, getCraftCount(key) + 1);
        try { config.save(file); } catch (Exception e) { e.printStackTrace(); }
    }
}
