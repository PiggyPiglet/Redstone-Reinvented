package me.piggypiglet.redstonereinvented.core.framework.dependencies;

import javax.annotation.Nonnull;
import java.lang.annotation.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// Credit to lucko, this dependency management system wouldn't be possible without his helper project to guide me
// https://lucko.me luck@lucko.me
// ------------------------------
@Repeatable(MavenLibraries.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MavenLibrary {
    @Nonnull
    String groupId();

    @Nonnull
    String artifactId();

    @Nonnull
    String version();

    @Nonnull
    String repo() default "https://repo1.maven.org/maven2";
}
