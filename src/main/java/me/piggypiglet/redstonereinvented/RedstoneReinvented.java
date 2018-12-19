package me.piggypiglet.redstonereinvented;

import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.Getter;
import me.piggypiglet.redstonereinvented.core.framework.BinderModule;
import me.piggypiglet.redstonereinvented.core.framework.Command;
import me.piggypiglet.redstonereinvented.core.framework.dependencies.DependencyLoader;
import me.piggypiglet.redstonereinvented.core.framework.dependencies.MavenLibraries;
import me.piggypiglet.redstonereinvented.core.framework.dependencies.MavenLibrary;
import me.piggypiglet.redstonereinvented.core.handlers.CommandHandler;
import me.piggypiglet.redstonereinvented.core.objects.enums.Registerables;
import me.piggypiglet.redstonereinvented.core.storage.GFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.stream.Stream;

import static me.piggypiglet.redstonereinvented.core.objects.enums.Registerables.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
@MavenLibraries(
        value = {
                @MavenLibrary(groupId = "org.reflections", artifactId = "reflections", version = "0.9.11"),
                @MavenLibrary(groupId = "javassist", artifactId = "javassist", version = "3.12.1.GA"),
                @MavenLibrary(groupId = "com.google.inject", artifactId = "guice", version = "4.2.2"),
                @MavenLibrary(groupId = "javax.inject", artifactId = "javax.inject", version = "1"),
                @MavenLibrary(groupId = "aopalliance", artifactId = "aopalliance", version = "1.0"),
                @MavenLibrary(groupId = "org.apache.commons", artifactId = "commons-lang3", version = "3.8.1"),
                @MavenLibrary(groupId = "commons-io", artifactId = "commons-io", version = "2.6")
        }
)
public final class RedstoneReinvented extends JavaPlugin {
    @Inject private GFile gFile;

    @Inject private CommandHandler commandHandler;

    @Getter private Injector injector;
    @Getter private Reflections reflections;

    @Override
    public void onEnable() {
        Stream.of(
                DEPENDENCIES, GUICE, REFLECTIONS, FILES, COMMANDS, EVENTS
        ).forEach(this::register);
    }

    public void register(Registerables registerable) {
        switch (registerable) {
            case DEPENDENCIES:
                DependencyLoader.loadAll(getClass());
                break;

            case GUICE:
                injector = new BinderModule(this).createInjector();
                injector.injectMembers(this);
                break;

            case REFLECTIONS:
                reflections = new Reflections("me.piggypiglet.logicgateblocks");
                break;

            case FILES:
                gFile.clear();

                Stream.of(
                        "config.yml", "lang.yml"
                ).forEach(i -> gFile.make(i.substring(0, i.lastIndexOf('.')), getDataFolder() + "/" + i, "/" + i));
                break;

            case COMMANDS:
                getCommand("lgb").setExecutor(commandHandler);
                reflections.getSubTypesOf(Command.class).stream().map(injector::getInstance).forEach(commandHandler.getCommands()::add);
                break;

            case EVENTS:
                break;
        }
    }
}
