package me.piggypiglet.logicgateblocks.core.framework;

import lombok.Getter;
import lombok.Setter;
import me.piggypiglet.logicgateblocks.core.storage.Lang;
import me.piggypiglet.logicgateblocks.core.utils.chat.MessageUtils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public abstract class Command {
    @Getter private final List<String> commands = new ArrayList<>();
    @Getter private final List<String> permissions = new ArrayList<>();
    @Getter private String description = "null";
    @Getter private String usage = "<args> [optional args]";
    protected Options options = new Options();
    private CommandSender sender;

    protected Command(String... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }

    protected abstract void execute(CommandSender sender, String[] args);

    public void run(CommandSender sender, String[] args) {
        this.sender = sender;
        execute(sender, args);
    }

    protected void sendUsage() {
        sendMessage("commands.incorrect-usage", usage);
    }

    protected void sendMessage(String key, Object... variables) {
        MessageUtils.sendMessage(sender, key, variables);
    }

    protected final class Options {
        private Options() {}

        public Options setPermissions(String... permissions) {
            Command.this.permissions.addAll(Arrays.asList(permissions));
            return this;
        }

        public Options setUsage(String usage) {
            Command.this.usage = usage;
            return this;
        }

        public Options setDescription(String description) {
            Command.this.description = description;
            return this;
        }
    }
}