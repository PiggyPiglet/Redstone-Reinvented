package me.piggypiglet.redstonereinvented.core.storage;

import com.google.inject.Inject;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class Lang {
    @Inject private static GFile gFile;

    public static String getPrefix(boolean console) {
        return gFile.getFileConfiguration("lang").getString((console ? "console" : "commands") + ".prefix");
    }

    public static String getString(String key, Object... variables) {
        return String.format(gFile.getFileConfiguration("lang").getString(key, "null"), variables);
    }
}
