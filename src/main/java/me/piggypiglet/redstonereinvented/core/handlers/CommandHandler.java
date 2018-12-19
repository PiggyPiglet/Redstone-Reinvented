package me.piggypiglet.redstonereinvented.core.handlers;

import com.google.inject.Singleton;
import lombok.Getter;
import me.piggypiglet.redstonereinvented.core.framework.Command;
import me.piggypiglet.redstonereinvented.core.utils.chat.MessageUtils;
import me.piggypiglet.redstonereinvented.core.utils.string.StringUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class CommandHandler implements CommandExecutor {
    @Getter private final List<Command> commands = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] badArgs) {
        if (badArgs.length >= 1) {
            String text = String.join(" ", badArgs);

            for (Command cmd : commands) {
                if (StringUtils.startsWith(text, cmd.getCommands())) {
                    if (cmd.getPermissions().stream().anyMatch(sender::hasPermission) || cmd.getPermissions().isEmpty()) {
                        String[] args = text.trim().split("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                        args = Arrays.copyOfRange(args, 1, args.length);
                        args = args.length == 0 ? new String[]{} : args;

                        cmd.run(sender, args);
                    } else {
                        MessageUtils.sendMessage(sender, "commands.no-permission");
                    }

                    return true;
                }
            }

            MessageUtils.sendMessage(sender, "commands.unknown-command");
            return true;
        }

        return true;
    }
}
