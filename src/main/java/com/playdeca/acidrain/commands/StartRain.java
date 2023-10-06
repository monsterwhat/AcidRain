package com.playdeca.acidrain.commands;

import com.playdeca.acidrain.AcidRainPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StartRain implements CommandExecutor {

    private final AcidRainPlugin plugin;

    public StartRain(AcidRainPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            // Implement code to start acid rain around the player
            // You can use player.getWorld().setStorm(true) for this
            // Consider using a scheduler to stop the rain after a specified duration
            player.sendMessage("Acid rain has started around you.");
        } else {
            sender.sendMessage("Only players can use this command.");
        }
        return true;
    }
}
