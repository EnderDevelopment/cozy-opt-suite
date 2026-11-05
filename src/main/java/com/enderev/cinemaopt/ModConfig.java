package com.enderev.cinemaopt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * JSON backed configuration. Each module of the mod reads its settings
 * from the single {@link Config} instance. Loaded once at startup and
 * written back whenever the user changes a feature on the fly.
 */
public final class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Path configPath;
    private static Config instance = new Config();

    private ModConfig() {
    }

    /** Loads the configuration from disk, creating defaults on first run. */
    public static void load() {
        configPath = FabricLoader.getInstance().getConfigDir().resolve(ModIds.MOD_ID + ".json");
        if (Files.exists(configPath)) {
            try {
                instance = GSON.fromJson(Files.readString(configPath), Config.class);
            } catch (IOException e) {
                CinemaOpt.LOGGER.error("Failed to read config, using defaults.", e);
                instance = new Config();
            }
        }
        if (instance == null) {
            instance = new Config();
        }
        save();
    }

    /** Persists the current configuration to disk. */
    public static void save() {
        if (configPath == null) {
            return;
        }
        try {
            Files.writeString(configPath, GSON.toJson(instance));
        } catch (IOException e) {
            CinemaOpt.LOGGER.error("Failed to save config.", e);
        }
    }

    public static Config get() {
        return instance;
    }
}
