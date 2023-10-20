package com.willpk.mc;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.bukkit.Bukkit;

public class spawnCommand implements CommandExecutor {

    public spawnCommand(HubPlugin hubPlugin) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            Location loc = Bukkit.getWorld("world").getSpawnLocation();
            player.setGameMode(GameMode.ADVENTURE);
            player.teleport(loc);
            player.sendMessage("Teleporting back to spawn");
            return false;
        } else{
            sender.sendMessage("Only players can use this command!");
        }

        return false;
    }
}
