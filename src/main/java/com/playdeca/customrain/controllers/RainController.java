package com.playdeca.customrain.controllers;

import com.playdeca.customrain.objects.RainType;
import com.playdeca.customrain.services.TimerService;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import java.util.Objects;
import java.util.Random;

public class RainController {
    TimerService timer;
    JavaPlugin plugin;
    public RainController(JavaPlugin plugin) {
        this.plugin = plugin;
        this.timer = new TimerService(plugin, this);
    }

    public boolean isRaining(World world){
        boolean isRaining = world.hasStorm();
        boolean isThundering = world.isThundering();
        return isRaining || isThundering;
    }

    public boolean isExposed(Player player) {
        try {
            World playerWorld = Objects.requireNonNull(player.getPlayer()).getWorld();
            int playerY = player.getPlayer().getLocation().getBlockY();
            int highestBlockInLocationY = playerWorld.getHighestBlockAt(player.getPlayer().getLocation()).getLocation().getBlockY();
            // if Player is higher than the highest block in the location -> Player is exposed
            return playerY >= highestBlockInLocationY;

        } catch (Exception e) {
            plugin.getLogger().severe("CustomRain: An error occurred in isExposed:");
            plugin.getLogger().warning(e.getLocalizedMessage());
            return false;
        }
    }

    private RainType getRandomRainType() {
        try {
            RainType[] rainTypes = {RainType.ACID, RainType.SLIME, RainType.HEALING,
                    RainType.REGENERATION, RainType.SPEED, RainType.JUMP, RainType.INVISIBILITY,
                    RainType.BLINDNESS, RainType.MINING_FATIGUE, RainType.LUCK, RainType.BAD_LUCK,
                    RainType.SLOW_FALLING};

            Random random = new Random();
            return rainTypes[random.nextInt(rainTypes.length)];
        }catch (Exception e) {
            plugin.getLogger().severe("CustomRain: An error occurred in getRandomRainType:");
            plugin.getLogger().warning(e.getLocalizedMessage());
            return RainType.ACID;
        }
    }

    public void rainType(RainType rainType, Player player) {
        try {
            player.spawnParticle(rainType.particle(), player.getLocation(), 10);
            if(!player.hasPotionEffect(rainType.potionEffectType())) {
            player.addPotionEffect(new PotionEffect(rainType.potionEffectType(), 20 * 2, 1));
            }else{
                player.addPotionEffect(new PotionEffect(rainType.potionEffectType(), 20 * 2, 1));
            }
        } catch (Exception e) {
            plugin.getLogger().severe("An error occurred in rainType:");
            plugin.getLogger().warning(e.getLocalizedMessage());
        }
    }

    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        try {
            //if it was already raining do nothing...
            if (isRaining(event.getWorld())) {
                return;
            }
            //if it is not raining, but it is going to rain, start the timer
            if (event.toWeatherState()) {
                RainType rainType = getRandomRainType();
                timer.startRainTimer(event.getWorld(), rainType);
                timer.startRain(event.getWorld(), rainType);
            }
        } catch (Exception e) {
            plugin.getLogger().severe("CustomRain: An error occurred in onWeatherChangeEvent:");
            plugin.getLogger().warning(e.getLocalizedMessage());
        }
    }

}
