package com.willpk.mc;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class vanishCommand implements CommandExecutor {
    public vanishCommand(HubPlugin hubPlugin) {

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(player.hasPermission("hubplugin.admin.*") || player.hasPermission("hubplugin.admin.vanish")) {
                if (player.isInvisible()) {
                    player.setInvisible(false);
                    player.sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("VanishOffMessage")));
                } else {
                    player.setInvisible(true);
                    player.sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("VanishOnMessage")));
                }

                return false;
            }else {
                player.sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("noPermissionmessage")));
            }
        } else{
            sender.sendMessage("Only players can use this command!");
        }

        return false;
    }
}
