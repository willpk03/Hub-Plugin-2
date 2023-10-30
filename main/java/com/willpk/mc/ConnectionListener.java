package com.willpk.mc;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ConnectionListener implements Listener {

    private final HubPlugin plugin;
    public ConnectionListener(HubPlugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        long lastSeen = Bukkit.getOfflinePlayer(e.getPlayer().getUniqueId()).getLastSeen();

        if(lastSeen == 0) {
            Bukkit.broadcastMessage("Please Welcome "+ e.getPlayer().getName());

        }

        Player p = Bukkit.getPlayer(Objects.requireNonNull(e.getPlayer().getUniqueId()));
        Inventory spawnInventory = Bukkit.getServer().createInventory(p, 9, "spawnInventory");
        p.sendMessage("Hello you have just joined the server");
        p.getInventory().clear();
        NamespacedKey key = new NamespacedKey(plugin, "InventoryIdentifier");
        ItemStack MainHand = p.getItemInHand();
        ItemStack gamemodes = new ItemStack(Material.COMPASS);
        ItemMeta gamemodesItemMeta = gamemodes.getItemMeta();
        gamemodesItemMeta.setDisplayName("Gamemode Selector");
        gamemodesItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "gamemodes");
        gamemodes.setItemMeta(gamemodesItemMeta);

        ItemStack boost = new ItemStack(Material.FEATHER);
        ItemMeta boostItemMeta = boost.getItemMeta();
        boostItemMeta.setDisplayName("§6§lBoost Forward");
        boostItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "boost");
        boost.setItemMeta(boostItemMeta);

        ItemStack playerHiderOff = new ItemStack(Material.LIME_DYE);
        ItemMeta playerHiderItemMeta = playerHiderOff.getItemMeta();
        playerHiderItemMeta.setDisplayName("§6§lHide Players");
        playerHiderItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "playerHiderOff");
        playerHiderOff.setItemMeta(playerHiderItemMeta);

        ItemStack playerHiderOn = new ItemStack(Material.LIME_DYE);
        ItemMeta playerHiderOnItemMeta = playerHiderOn.getItemMeta();
        playerHiderOnItemMeta.setDisplayName("§6§lShow Players");
        playerHiderOnItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "PlayerHiderOn");
        playerHiderOn.setItemMeta(playerHiderOnItemMeta);

        spawnInventory.setItem(2, gamemodes);
        spawnInventory.setItem(3, boost);
        spawnInventory.setItem(4, playerHiderOn);

        p.getInventory().setItem(2, gamemodes);
        p.getInventory().setItem(3, boost);
        p.getInventory().setItem(4, playerHiderOn);



    }
    @EventHandler
    public void onPlayerLeave(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(!p.hasPermission("Hubplugin.BlockBreak")){
            e.setCancelled(true);
            p.sendMessage("You are not allowed to break blocks here");
        }
    }

}
