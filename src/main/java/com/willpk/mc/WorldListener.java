package com.willpk.mc;

import io.papermc.paper.event.player.PlayerPickItemEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class WorldListener implements Listener {
    private final HubPlugin plugin;
    public WorldListener(HubPlugin plugin) {
        this.plugin = plugin;
    }
    //Handles Fall Damage, Void Deaths
    @EventHandler
    public void onDamageEvent(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player p) {
            e.setCancelled(true);
            if(e.getCause().equals(EntityDamageEvent.DamageCause.VOID) ) {
                p.sendMessage("You've been teleported back to spawn");
                Location loc = Bukkit.getWorld("world").getSpawnLocation();
                p.setGameMode(GameMode.ADVENTURE);
                p.teleport(loc);
            }
        }
    }
    //Hunger Loss
    @EventHandler
    public void onHungerDecrease(FoodLevelChangeEvent e) {
        e.setCancelled(true);

    }
    //Item Drop/Pickup
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
        e.getPlayer().sendMessage("You can not drop items");
    }

    @EventHandler
    public void onItemPickup(PlayerPickItemEvent e) {
        e.setCancelled(true);
        e.getPlayer().sendMessage("You can not pick up items");
    }

    //Fire spread
    @EventHandler
    public void onFireSpread(BlockSpreadEvent e) {
        e.setCancelled(true);
    }
    //Block burning
    @EventHandler
    public void onBlockBurn(BlockBurnEvent e) {
        e.setCancelled(true);
    }
    //Death messages

    //Leaf decay

}
