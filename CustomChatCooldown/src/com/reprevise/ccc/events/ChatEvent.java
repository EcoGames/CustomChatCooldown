package com.reprevise.ccc.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.reprevise.ccc.CustomChatCooldown;
import com.reprevise.ccc.handlers.CooldownHandler;

public class ChatEvent implements Listener {
	
	private int COOLDOWN_LEVEL;
	
	private int n = 0;
	private String prefix;
	private String chattime;
	private String cooldowntimeline;
	int timeLeft;
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player plr = event.getPlayer();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				n++;
				if (plr.hasPermission("cc.cooldown." + n)) {
					COOLDOWN_LEVEL = n;
					n=0;
					this.cancel();
				} else if (plr.isOp()) {
					n=0;
					this.cancel();
				}
				
			}
		}.runTaskTimer(CustomChatCooldown.getInstance(), 20, 20);
		
		if (CooldownHandler.getCooldowns().containsKey(plr.getUniqueId()) && !plr.isOp()) {
			
			prefix = ChatColor.translateAlternateColorCodes('&', CustomChatCooldown.getInstance().getConfig().getString("prefix"));
			chattime = ChatColor.translateAlternateColorCodes('&', CustomChatCooldown.getInstance().getConfig().getString("cooldownwaittime"));
			cooldowntimeline = ChatColor.translateAlternateColorCodes('&', CustomChatCooldown.getInstance().getConfig().getString("cooldownwaittimeline")); 
			
			String new_chattime = chattime.replace("%seconds%", Integer.toString(timeLeft));
			
			event.setCancelled(true);
			plr.sendMessage(prefix + " " + new_chattime);
			plr.sendMessage(prefix + " " + cooldowntimeline);
			
		} else if (!plr.isOp()) {
            CooldownHandler.getCooldowns().put(plr.getUniqueId(), COOLDOWN_LEVEL);
            System.out.println("put user in hashmap");
			new BukkitRunnable() {
                @Override
                public void run() {
                    timeLeft = CooldownHandler.getCooldowns().get(plr.getUniqueId());
                    CooldownHandler.getCooldowns().put(plr.getUniqueId(), --timeLeft);
                    System.out.println(timeLeft);
                    if(timeLeft <= 0){
                        this.cancel();
                        CooldownHandler.getCooldowns().remove(plr.getUniqueId());
                        return;
                    }
                }
            }.runTaskTimer(CustomChatCooldown.getInstance(), 20, 20);
		}
		
	}

	public int getCOOLDOWN_LEVEL() {
		return COOLDOWN_LEVEL;
	}

}
