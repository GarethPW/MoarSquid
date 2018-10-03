package net.garethpw.MoarSquid;

import java.util.Random;

import org.bukkit.entity.Dolphin;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fish;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WaterMob;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Location;

public final class MoarSquidPlugin extends JavaPlugin implements Listener {

  private static Random random = new Random();

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onCreatureSpawn(CreatureSpawnEvent event) {
    if (!event.isCancelled() && event.getSpawnReason() == SpawnReason.NATURAL) {
      LivingEntity originalEntity = event.getEntity();

      if (originalEntity instanceof WaterMob) {
        float chance = 0f;

        if (originalEntity instanceof Fish) { chance = 0.10f; }
        else if (originalEntity instanceof Dolphin) { chance = 0.25f; }

        if (chance > 0f && random.nextFloat() < chance) {
          Location location = originalEntity.getLocation();

          event.setCancelled(true);
          location.getWorld().spawnEntity(location, EntityType.SQUID);
        }
      }
    }
  }

  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
  }

}
