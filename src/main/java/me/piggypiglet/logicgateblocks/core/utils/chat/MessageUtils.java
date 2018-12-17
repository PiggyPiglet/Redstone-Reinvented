package me.piggypiglet.logicgateblocks.core.utils.chat;

import me.piggypiglet.logicgateblocks.core.storage.Lang;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class MessageUtils {
    public static void sendMessage(CommandSender user, String key, Object... variables) {
        user.sendMessage(cc(Lang.getPrefix(false) + " " + Lang.getString(key, variables)));
    }

    private static String cc(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
