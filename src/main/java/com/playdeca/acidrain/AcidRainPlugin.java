package com.playdeca.acidrain;

import com.playdeca.acidrain.commands.*;
import com.playdeca.acidrain.events.RainListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AcidRainPlugin extends JavaPlugin implements CommandExecutor {
    private boolean acidRainEnabled = false;
    private int rainDuration = 60; // Default duration is 60 seconds
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("AcidRainPlugin has been enabled!");

        // Register the RainListener with the plugin instance
        getServer().getPluginManager().registerEvents(new RainListener(this), this);

        // Register commands
        registerCommands();

        // Load configuration
        saveDefaultConfig();
        reloadConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("AcidRainPlugin has been disabled!");
    }

    public void registerCommands(){
        registerCommand("enableacidrain", new EnableAcidRainCommand(this));
        registerCommand("disableacidrain", new DisableAcidRainCommand(this));
        registerCommand("startrain", new StartRainCommand(this));
        registerCommand("stoprain", new StopRainCommand(this));
        registerCommand("setrainduration", new SetRainDurationCommand(this));
    }

    public boolean isAcidRainEnabled() {
        return acidRainEnabled;
    }

    public void setAcidRainEnabled(boolean enabled) {
        acidRainEnabled = enabled;
    }

    public int getRainDuration() {
        return rainDuration;
    }

    public void setRainDuration(int duration) {
        rainDuration = duration;
    }

    private void registerCommand(String commandName, CommandExecutor executor) {
        getCommand(commandName).setExecutor(executor);
    }

}
