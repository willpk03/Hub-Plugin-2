package com.willpk.mc;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ResourceBundle;

public final class HubPlugin extends JavaPlugin {
    File Config;
   private FileConfiguration newConfig;
   private static HubPlugin instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerListeners();
        registerCommands();
        //HubPlugin hubPlugin = new HubPlugin();
        //hubPlugin.saveDefaultConfig();
        instance = this;
        loadConfig();




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners() {
        //Handles Players joining, Players breaking blocks, Players Leaving
        getServer().getPluginManager().registerEvents(new ConnectionListener(this), this);

        //Handles Right clicking items in hand, Interactions inside inventories,
        getServer().getPluginManager().registerEvents(new inventoryListener(this), this);

        //Handles Fall Damage, Void Deaths, Item Drop/Pickup, Hunger Loss, fire spread, block burning, death messages, leaf decay
        getServer().getPluginManager().registerEvents(new WorldListener(this), this);
    }

    private void registerCommands() {
        getCommand("spawn").setExecutor(new spawnCommand(this));
        getCommand("fly").setExecutor(new flyCommand(this));
        getCommand("vanish").setExecutor(new vanishCommand(this));
    }

    private void loadConfig() {


        Config = new File(getDataFolder(), "config.yml");

        if(!Config.exists()) {
            saveResource("config.yml", false);
        }

        newConfig = YamlConfiguration.loadConfiguration(Config);

    }

    public @NotNull FileConfiguration getConfig() {
        return newConfig;
    }

    public static HubPlugin getInstance(){
        return instance;
    }
}
