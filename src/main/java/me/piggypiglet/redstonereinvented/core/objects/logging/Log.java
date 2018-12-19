package me.piggypiglet.redstonereinvented.core.objects.logging;

import me.piggypiglet.redstonereinvented.core.storage.Lang;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class Log {
    private static final Logger LOGGER = Bukkit.getLogger();

    public static void info(String key, Object... variables) {
        LOGGER.info(format(key, variables));
    }

    public static void error(String key, Object... variables) {
        LOGGER.severe(format(key, variables));
    }

    private static String format(String key, Object... variables) {
        return Lang.getPrefix(true) + Lang.getString(key, variables);
    }
}
