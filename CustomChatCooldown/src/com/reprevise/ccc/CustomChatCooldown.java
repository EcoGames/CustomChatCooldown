package com.reprevise.ccc;

import org.bukkit.plugin.java.JavaPlugin;

import com.reprevise.ccc.cmds.Commands;
import com.reprevise.ccc.events.ChatEvent;

public class CustomChatCooldown extends JavaPlugin {
	
	public static CustomChatCooldown instance;

	public void onEnable() {
		instance = this;
		this.saveDefaultConfig();
		this.getCommand("chat").setExecutor(new Commands());
		this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
	}
	
	public void onDisable() {
		// TODO probs save a config
	}

	public static CustomChatCooldown getInstance() {
		return instance;
	}
	
}
