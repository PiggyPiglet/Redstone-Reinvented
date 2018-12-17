package me.piggypiglet.logicgateblocks.commands;

import com.google.inject.Inject;
import me.piggypiglet.logicgateblocks.LogicGateBlocks;
import me.piggypiglet.logicgateblocks.core.framework.Command;
import me.piggypiglet.logicgateblocks.core.objects.enums.Registerables;
import org.bukkit.command.CommandSender;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class ReloadCommand extends Command {
    @Inject private LogicGateBlocks main;

    public ReloadCommand() {
        super("reload");
        options
                .setPermissions("logicgateblocks.commands.reload", "logicgateblocks.commands.admin")
                .setDescription("Admin command to reload the config and language file.");
    }

    @Override
    protected void execute(CommandSender sender, String[] args) {
        main.register(Registerables.FILES);
        sendMessage("commands.reload.success");
    }
}
