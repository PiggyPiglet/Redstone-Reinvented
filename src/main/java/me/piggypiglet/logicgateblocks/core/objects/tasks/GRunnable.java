package me.piggypiglet.logicgateblocks.core.objects.tasks;

import org.bukkit.scheduler.BukkitRunnable;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public abstract class GRunnable implements Runnable {
    public void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
