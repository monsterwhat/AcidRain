package com.playdeca.acidrain.commands;

import com.playdeca.acidrain.AcidRainPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SetRainDuration implements CommandExecutor {

    private final AcidRainPlugin plugin;

    public SetRainDuration(AcidRainPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        if (args.length == 1) {
            try {
                int duration = Integer.parseInt(args[0]);
                if (duration >= 0) {
                    plugin.setRainDuration(duration);
                    sender.sendMessage("Rain duration set to " + duration + " seconds.");
                } else {
                    sender.sendMessage("Duration must be a non-negative number.");
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("Invalid duration. Please provide a valid number.");
            }
        } else {
            sender.sendMessage("Usage: /setrainduration <duration>");
        }
        return true;
    }
}
