package me.piggypiglet.logicgateblocks.core.storage;

import com.google.inject.Singleton;
import me.piggypiglet.logicgateblocks.LogicGateBlocks;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

// ------------------------------
// Copyright (c) PiggyPiglet 2018
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class GFile {
    private final Map<String, Map<String, Object>> itemMaps = new HashMap<>();
    private Logger logger = Bukkit.getLogger();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void make(String name, String externalPath, String internalPath) {
        File file = new File(externalPath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();

                if (file.createNewFile()) {
                    if (exportResource(LogicGateBlocks.class.getResourceAsStream(internalPath), externalPath)) {
                        insertIntoMap(name, file);

                        logger.info(name + " successfully created & loaded.");
                    } else {
                        logger.severe(name + " creation failed.");
                    }
                } else {
                    logger.severe(name + " creation failed.");
                }
            } else {
                insertIntoMap(name, file);

                logger.info(name + " successfully loaded.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isYaml(File file) {
        return file.getPath().endsWith(".yml");
    }

    private void insertIntoMap(String name, File file) {
        itemMaps.put(name, new HashMap<>());

        try {
            String fileContent = FileUtils.readFileToString(file, "UTF-8");

            if (isYaml(file)) {
                FileConfiguration config = new YamlConfiguration();
                config.load(file);

                itemMaps.get(name).put("file-configuration", config);
            } else {
                itemMaps.get(name).put("file-content", fileContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemMaps.get(name).put("file", file);
    }

    public FileConfiguration getFileConfiguration(String name) {
        Object item = itemMaps.get(name).get("file-configuration");

        if (item instanceof FileConfiguration) {
            return (FileConfiguration) item;
        }

        return new YamlConfiguration();
    }

    public void clear() {
        itemMaps.clear();
    }

    private static boolean exportResource(InputStream source, String destination) {
        boolean success = true;

        try {
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }
}
