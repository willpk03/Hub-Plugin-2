package com.willpk.mc;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class inventoryListener implements Listener {

    private final HubPlugin plugin;
    public inventoryListener(HubPlugin plugin) {
        this.plugin = plugin;
    }

    

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        e.setCancelled(true);
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
        playerHiderOnItemMeta.setDisplayName("§6§lHide Players");
        playerHiderOnItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "PlayerHiderOn");
        playerHiderOn.setItemMeta(playerHiderOnItemMeta);

        ItemStack Survival = new ItemStack(Material.OAK_WOOD);
        ItemMeta SurvivalItemMeta = Survival.getItemMeta();
        SurvivalItemMeta.setDisplayName("§6§lSurvival");
        ArrayList<String> Survivallore= new ArrayList<String>();
        Survivallore.add(" ");
        Survivallore.add("§BVanilla Survival with your friends!");
        Survivallore.add(" ");
        Survivallore.add("§BJoin Now");
        SurvivalItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "Survival");
        SurvivalItemMeta.setLore(Survivallore);

        Survival.setItemMeta(SurvivalItemMeta);

        ItemStack Skyblock = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta SkyblockItemMeta = Skyblock.getItemMeta();
        SkyblockItemMeta.setDisplayName("§6§lSkyblock");
        ArrayList<String> Skyblocklore = new ArrayList<String>();
        Skyblocklore.add(" ");
        Skyblocklore.add("§BStart on an Island in the sky!");
        Skyblocklore.add(" ");
        Skyblocklore.add("§BJoin Now");
        SkyblockItemMeta.setLore(Skyblocklore);
        SkyblockItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "Skyblock");
        Skyblock.setItemMeta(SkyblockItemMeta);

        ItemStack Prison = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta PrisonItemMeta = Skyblock.getItemMeta();
        PrisonItemMeta.setDisplayName("§6§lPrison");
        ArrayList<String> Prisonlore = new ArrayList<String>();
        Prisonlore.add(" ");
        Prisonlore.add("§BCan you escape the prison!");
        Prisonlore.add(" ");
        Prisonlore.add("§BJoin Now");
        PrisonItemMeta.setLore(Prisonlore);
        PrisonItemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "Prison");
        Prison.setItemMeta(PrisonItemMeta);

        if (MainHand.equals(gamemodes)) {
            Inventory gamemodesInv = Bukkit.getServer().createInventory(p, 27, "Select the Gamemode you want to join");
                gamemodesInv.setItem(11, Survival);
                gamemodesInv.setItem(13, Skyblock);
                gamemodesInv.setItem(15, Prison);
            p.openInventory(gamemodesInv);
        } else if (MainHand.equals(boost)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2), true);
            p.sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("playerVisibleOffMessage")));
        } else if (MainHand.equals(playerHiderOn)) {
            for(Player online : Bukkit.getOnlinePlayers()){
                p.showPlayer(online);
                p.sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("playerVisibleOffMessage")));
                p.getInventory().setItemInMainHand(playerHiderOn);
            }
        } else if (MainHand.equals(playerHiderOff)) {
            for(Player online : Bukkit.getOnlinePlayers()){
                p.hidePlayer(online);
                p.getInventory().setItemInMainHand(playerHiderOff);
                p.sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("playerVisibleOffMessage")));

            }
        } else {
            //throw new IllegalStateException("Unexpected value: " + MainHand);
            if(!p.hasPermission("")) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    private void inventoryClick(InventoryClickEvent e) {
        NamespacedKey key = new NamespacedKey(plugin, "InventoryIdentifier");
        ItemStack currentItem = e.getWhoClicked().getItemOnCursor();
        if (currentItem.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            String keyText = currentItem.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
            if (Objects.equals(keyText, "Survival")) {
                e.getWhoClicked().sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("tpSurvivalMessage")));
                e.getWhoClicked().closeInventory();
                e.setCancelled(true);
            } else if (Objects.equals(keyText, "Skyblock")) {
                e.getWhoClicked().sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("tpSkyblockMessage")));
                e.getWhoClicked().closeInventory();
                e.setCancelled(true);
            } else if (Objects.equals(keyText, "Prison")) {
                e.getWhoClicked().sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("tpPrisonMessage")));
                e.getWhoClicked().closeInventory();
                e.setCancelled(true);
            }
        }
    }

}
