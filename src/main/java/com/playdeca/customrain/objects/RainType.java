package com.playdeca.customrain.objects;

import org.bukkit.Particle;
import org.bukkit.potion.PotionEffectType;

public record RainType(String name, PotionEffectType potionEffectType, Particle particle){
    public static final RainType ACID = new RainType("Acid", PotionEffectType.POISON, Particle.SLIME);
    public static final RainType SLIME = new RainType("Slime", PotionEffectType.SLOW, Particle.SLIME);
    public static final RainType HEALING = new RainType("Healing", PotionEffectType.HEAL, Particle.HEART);
    public static final RainType REGENERATION = new RainType("Regeneration", PotionEffectType.REGENERATION, Particle.VILLAGER_HAPPY);
    public static final RainType SPEED = new RainType("Speed", PotionEffectType.SPEED, Particle.ELECTRIC_SPARK);
    public static final RainType JUMP = new RainType("Jump", PotionEffectType.JUMP, Particle.CLOUD);
    public static final RainType INVISIBILITY = new RainType("Invisibility", PotionEffectType.INVISIBILITY, Particle.VILLAGER_HAPPY);
    public static final RainType BLINDNESS = new RainType("Blindness", PotionEffectType.BLINDNESS, Particle.CLOUD);
    public static final RainType MINING_FATIGUE = new RainType("Mining Fatigue", PotionEffectType.SLOW_DIGGING, Particle.VILLAGER_HAPPY);
    public static final RainType LUCK = new RainType("Luck", PotionEffectType.LUCK, Particle.VILLAGER_HAPPY);
    public static final RainType BAD_LUCK = new RainType("Bad Luck", PotionEffectType.UNLUCK, Particle.VILLAGER_HAPPY);
    public static final RainType SLOW_FALLING = new RainType("Slow Falling", PotionEffectType.SLOW_FALLING, Particle.LANDING_HONEY);

}
