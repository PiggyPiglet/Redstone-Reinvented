package me.piggypiglet.logicgateblocks.core.framework;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.piggypiglet.logicgateblocks.LogicGateBlocks;
import me.piggypiglet.logicgateblocks.core.objects.tasks.Task;
import me.piggypiglet.logicgateblocks.core.storage.GFile;
import me.piggypiglet.logicgateblocks.core.storage.Lang;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class BinderModule extends AbstractModule {
    private final LogicGateBlocks main;

    public BinderModule(LogicGateBlocks main) {
        this.main = main;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    public void configure() {
        bind(LogicGateBlocks.class).toInstance(main);
        requestStaticInjection(Task.class, Lang.class);
    }
}
