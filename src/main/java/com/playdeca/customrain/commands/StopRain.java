package com.playdeca.customrain.commands;

import com.playdeca.customrain.CustomRainPlugin;
import com.playdeca.customrain.services.MessageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StopRain implements CommandExecutor {
    MessageService msg = new MessageService();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            stopRain(player);
            player.sendMessage(msg.getRainStopMessage());
        } else {
            sender.sendMessage("Only players can use this command.");
        }
        return true;
    }

    public void stopRain(Player player){
        player.getWorld().setStorm(false);
    }
}
