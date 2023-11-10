package com.playdeca.customrain;

import com.playdeca.customrain.commands.*;
import com.playdeca.customrain.listeners.EventsListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomRainPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        try {
            // Plugin startup logic
            // Register the EventsListener with the plugin instance
            getServer().getPluginManager().registerEvents(new EventsListener(this), this);
            // Register commands
            registerCommands();
            // Load configuration
            loadConfig();

            getLogger().info("CustomRainPlugin has been enabled!");
        }catch (Exception e) {
            getLogger().severe("CustomRain: An error occurred in onEnable:");
            getLogger().warning(e.getLocalizedMessage());
            onDisable();
        }
    }

    public void loadConfig(){
        try {
            saveDefaultConfig();
            reloadConfig();
        }catch (Exception e) {
            getLogger().severe("CustomRain: An error occurred in loadConfig:");
            getLogger().warning(e.getLocalizedMessage());
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("CustomRainPlugin has been disabled!");
        getServer().getPluginManager().disablePlugin(this);
    }

    public void registerCommands(){
        registerCommand("startrain", new StartRain());
        registerCommand("stoprain", new StopRain());
    }

    private void registerCommand(String commandName, CommandExecutor executor) {
        PluginCommand command = getCommand(commandName);
        if (command != null) {
            command.setExecutor(executor);
        } else {
            getLogger().warning("Failed to register command: " + commandName);
        }
    }


}
