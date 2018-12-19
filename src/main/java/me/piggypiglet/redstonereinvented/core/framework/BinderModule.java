package me.piggypiglet.redstonereinvented.core.framework;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import me.piggypiglet.redstonereinvented.RedstoneReinvented;
import me.piggypiglet.redstonereinvented.core.objects.tasks.Task;
import me.piggypiglet.redstonereinvented.core.storage.Lang;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
public final class BinderModule extends AbstractModule {
    private final RedstoneReinvented main;

    public BinderModule(RedstoneReinvented main) {
        this.main = main;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Override
    public void configure() {
        bind(RedstoneReinvented.class).toInstance(main);
        requestStaticInjection(Task.class, Lang.class);
    }
}
