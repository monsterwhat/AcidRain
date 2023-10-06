package com.playdeca.acidrain.commands;

import com.playdeca.acidrain.AcidRainPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DisableAcidRain implements CommandExecutor {

    private final AcidRainPlugin plugin;

    public DisableAcidRain(AcidRainPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        plugin.setAcidRainEnabled(false);
        sender.sendMessage("Acid rain is now disabled.");
        return true;
    }
}
