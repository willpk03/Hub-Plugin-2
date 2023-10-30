package com.willpk.mc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class friendCommand implements CommandExecutor {
    public friendCommand(HubPlugin hubPlugin) {
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if(player.isFlying()){
                player.sendMessage("You have turned off fly");
                player.setAllowFlight(false);
            }else {
                player.sendMessage("You have turned on fly");
                player.setAllowFlight(true);
            }

            return true;
        } else {
            sender.sendMessage("Only players can use this command!");
            return true;
        }
    }
}
