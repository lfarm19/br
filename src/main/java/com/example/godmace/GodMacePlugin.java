package com.example.godmace;
import org.bukkit.plugin.java.JavaPlugin;
import com.example.godmace.commands.*;
import com.example.godmace.listeners.ItemListener;
import com.example.godmace.tasks.BuffTask;

public class GodMacePlugin extends JavaPlugin {
    private DataManager dataManager;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        dataManager = new DataManager(this);
        CustomItems.init(this);
        
        getCommand("godmace").setExecutor(new GodMaceCommand());
        getCommand("godmace").setTabCompleter(new GodMaceCommand());
        getCommand("endfight").setExecutor(new EndFightCommand());
        getCommand("endfight").setTabCompleter(new EndFightCommand());
        
        getServer().getPluginManager().registerEvents(new ItemListener(this), this);
        getServer().getScheduler().runTaskTimer(this, new BuffTask(), 0L, 20L); // Runs every 1 second
        
        getLogger().info("GodMacePlugin enabled with global crafting limits, lore, and active buffs!");
    }
    public DataManager getDataManager() { return dataManager; }
}
