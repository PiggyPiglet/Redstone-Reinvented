package me.piggypiglet.logicgateblocks.core.objects.tasks;

import com.google.inject.Inject;
import me.piggypiglet.logicgateblocks.LogicGateBlocks;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class Task {
    @Inject private static LogicGateBlocks main;

    public static void async(Consumer<Runnable> task) {
        Bukkit.getScheduler().runTaskAsynchronously(main, new Runnable() {
            @Override
            public void run() {
                task.accept(this);
            }
        });
    }
}
