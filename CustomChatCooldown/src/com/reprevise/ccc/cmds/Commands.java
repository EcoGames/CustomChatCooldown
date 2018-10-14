package com.reprevise.ccc.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.entity.Player;

import com.reprevise.ccc.CustomChatCooldown;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (label.equalsIgnoreCase("chat")) {

				if (args[0].equalsIgnoreCase("reload")) {

					if (player.hasPermission("cc.admin")) {
						
						Log.info("The config for CustomChatCooldown has been saved and reloaded by " + player.getName() + "!");
						player.sendMessage(ChatColor.GREEN + "You have reloaded and saved the CustomChatCooldown config!");
						CustomChatCooldown.getInstance().reloadConfig();

					} else {
						player.sendMessage(ChatColor.RED + "You require the " + ChatColor.YELLOW + ChatColor.BOLD
								+ "cc.admin " + ChatColor.RED + "permission to use that command.");
						return true;
					}

				}

			}

		} else {
			Log.warn("You must be a player to use the /chat reload command!");
		}
		return true;
	}

}
