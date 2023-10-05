package com.playdeca.acidrain.commands;

import com.playdeca.acidrain.AcidRainPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class EnableAcidRainCommand implements CommandExecutor {

    private final AcidRainPlugin plugin;

    public EnableAcidRainCommand(AcidRainPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        plugin.setAcidRainEnabled(true);
        sender.sendMessage("Acid rain is now enabled.");
        return true;
    }
}
