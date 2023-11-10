package com.playdeca.customrain.commands;

import com.playdeca.customrain.CustomRainPlugin;
import com.playdeca.customrain.services.MessageService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class StartRain implements CommandExecutor {
    MessageService msg = new MessageService();
    public StartRain() {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        if (sender instanceof Player player) {
            startRain(player);
            player.sendMessage(msg.getRainStartMessage());
        } else {
            sender.sendMessage("Only players can use this command.");
        }
        return true;
    }

    public void startRain(Player player){
        player.getWorld().setStorm(true);
    }

}
