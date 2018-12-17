package me.piggypiglet.logicgateblocks.core.utils.string;

import java.util.List;
import java.util.stream.Stream;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class StringUtils {
    public static boolean startsWith(String str, List<String> elements) {
        return lowercaseStream(elements).anyMatch(str.toLowerCase()::startsWith);
    }

    private static Stream<String> lowercaseStream(List<String> list) {
        return list.stream().map(String::toLowerCase);
    }
}
