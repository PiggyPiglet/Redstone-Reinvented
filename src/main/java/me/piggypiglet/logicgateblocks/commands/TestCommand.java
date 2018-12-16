package me.piggypiglet.logicgateblocks.commands;

import me.piggypiglet.logicgateblocks.core.framework.Command;
import org.bukkit.command.CommandSender;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class TestCommand extends Command {
    public TestCommand() {
        super("test");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        sender.sendMessage("test");
    }
}
