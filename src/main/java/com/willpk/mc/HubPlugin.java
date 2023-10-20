package com.willpk.mc;

import org.bukkit.plugin.java.JavaPlugin;

public final class HubPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerListeners();
        registerCommands();
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

}
