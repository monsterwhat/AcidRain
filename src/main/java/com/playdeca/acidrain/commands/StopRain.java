package com.playdeca.acidrain.commands;

import com.playdeca.acidrain.AcidRainPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StopRain implements CommandExecutor {

    private final AcidRainPlugin plugin;

    public StopRain(AcidRainPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            // Implement code to stop acid rain around the player
            // You can use player.getWorld().setStorm(false) for this
            player.sendMessage("Acid rain has stopped around you.");
        } else {
            sender.sendMessage("Only players can use this command.");
        }
        return true;
    }
}
