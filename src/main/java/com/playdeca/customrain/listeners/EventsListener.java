package com.playdeca.customrain.listeners;

import com.playdeca.customrain.controllers.RainController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventsListener implements Listener {
    RainController rainController;
    public EventsListener(JavaPlugin plugin) {
        rainController = new RainController(plugin);
    }
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        rainController.onWeatherChangeEvent(event);
    }

}
