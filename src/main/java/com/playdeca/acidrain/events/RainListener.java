package com.playdeca.acidrain.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RainListener implements Listener {

    private final JavaPlugin plugin;

    public RainListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            // It's raining now
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (player.getWorld().hasStorm()) {
                    // Check if the player is exposed to the rain
                    if (!isPlayerUnderObstruction(player)) {
                        // Get the world-specific setting from config
                        String worldName = player.getWorld().getName();
                        boolean acidRainEnabled = plugin.getConfig().getBoolean("perWorldSettings." + worldName, false);

                        if (acidRainEnabled) {
                            // Replace rain particles with slime balls for acid rain
                            player.spawnParticle(org.bukkit.Particle.SLIME, player.getLocation(), 10);

                            // Deal damage to players in acid rain
                            double acidRainDamage = plugin.getConfig().getDouble("acidRainDamage", 2.0);
                            player.damage(acidRainDamage);
                        }
                    }
                }
            }
        } else {
            // Weather changed to non-rain
            // You can add any other cleanup or logic here
        }
    }

    // Check if the player is under an obstruction (not exposed to the sky) up to a specified height
    private boolean isPlayerUnderObstruction(Player player) {
        int playerY = player.getLocation().getBlockY();

        for (int y = playerY + 1; y <= 160; y++) {
            Material blockAbove = player.getWorld().getBlockAt(player.getLocation().getBlockX(), y, player.getLocation().getBlockZ()).getType();
            if (!blockAbove.isOccluding()) {
                return true; // Player is obstructed by a solid block within the specified height
            }
        }
        return false; // Player is not obstructed within the specified height
    }


}
