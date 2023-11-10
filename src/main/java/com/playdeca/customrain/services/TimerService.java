package com.playdeca.customrain.services;

import com.playdeca.customrain.controllers.RainController;
import com.playdeca.customrain.objects.RainType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TimerService {
    BukkitTask countDown, rain;
    public BossBar timeBar = Bukkit.createBossBar("", BarColor.PURPLE, org.bukkit.boss.BarStyle.SOLID);
    JavaPlugin plugin;
    RainController rainController;
    public TimerService(JavaPlugin plugin, RainController rainController) {
        this.plugin = plugin;
        this.rainController = rainController;
    }

    public void CancelTimers(Player player){
        try {
            stopRainTimer();
            stopRain();
            timeBar.setProgress(0);
            timeBar.removePlayer(player);
        }catch (Exception e) {
            Bukkit.getLogger().info("Error on CancelTimers: " + e.getMessage());
            Bukkit.getLogger().warning("Error on CancelTimers: " + e.getLocalizedMessage());
        }
    }

    public void startRainTimer(World world, RainType rainType) {
            countDown = new BukkitRunnable() {
                int countDownLimit = 120;
                @Override
                public void run() {
                    try {
                        for (Player player : world.getPlayers()) {
                            if(!timeBar.getPlayers().contains(player)){
                                timeBar.addPlayer(player);
                            }

                            if (countDownLimit > 0){
                                var timeLeft = countDownLimit--;
                                timeBar.setTitle("Raining "+ rainType.name() +" for " + timeLeft + "s ...");
                                timeBar.setProgress((double) timeLeft / 120);
                                if(!rainController.isRaining(world)){
                                    CancelTimers(player);
                                    stopRain();
                                }
                            } else {
                                CancelTimers(player);
                                stopRain();
                            }
                        }

                    }catch (Exception e){
                        Bukkit.getLogger().info("Error on startCountdown: " + e.getMessage());
                        Bukkit.getLogger().warning("Error on startCountdown: " + e.getLocalizedMessage());
                    }
                }
            }.runTaskTimerAsynchronously(plugin,0L,20L);
    }

    public void startRain(World world, RainType rainType) {
        rain = new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    if (rainController.isRaining(world)) {
                        for (Player player : world.getPlayers()) {
                            if(rainController.isExposed(player)){
                                rainController.rainType(rainType, player);
                            }
                        }
                    }
                }catch (Exception e){
                    Bukkit.getLogger().info("Error on startRain: " + e.getMessage());
                    Bukkit.getLogger().warning("Error on startRain: " + e.getLocalizedMessage());
                }
            }
        }.runTaskTimer(plugin,0L,10L);
    }


    public void stopRainTimer() {
        try {
            if (countDown != null) {
                countDown.cancel();
            }
        }catch (Exception e){
            Bukkit.getLogger().info("Error on stopCountdown: " + e.getMessage());
            Bukkit.getLogger().warning("Error on stopCountdown: " + e.getLocalizedMessage());
        }
    }

    public void stopRain() {
        try {
            if (rain != null) {
                rain.cancel();
            }
        }catch (Exception e){
            Bukkit.getLogger().info("Error on stopRain: " + e.getMessage());
            Bukkit.getLogger().warning("Error on stopRain: " + e.getLocalizedMessage());
        }
    }

}
