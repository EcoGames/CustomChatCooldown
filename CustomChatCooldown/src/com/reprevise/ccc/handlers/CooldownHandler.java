package com.reprevise.ccc.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownHandler {
	
	static Map<UUID, Integer> COOLDOWNS;

	public CooldownHandler() {
		COOLDOWNS = new HashMap<>();
	}

	public static Map<UUID, Integer> getCooldowns() {
		return COOLDOWNS;
	}
	
}
