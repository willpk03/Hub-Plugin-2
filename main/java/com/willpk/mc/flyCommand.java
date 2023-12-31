package com.willpk.mc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class flyCommand implements CommandExecutor {
    public flyCommand(HubPlugin hubPlugin) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if(player.hasPermission("hubplugin.admin.*") || player.hasPermission("hubplugin.admin.fly")) {
                if (player.getAllowFlight()) {
                    player.sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("flyOnMessage")));
                    player.setAllowFlight(false);
                } else {
                    player.sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("flyOffMessage")));
                    player.setAllowFlight(true);
                }
            }else {
                player.sendMessage(Objects.requireNonNull(HubPlugin.getInstance().getConfig().getString("noPermissionmesage")));
            }

            return true;
        } else {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
    }
}
