package com.enderev.cinemaopt;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main mod entrypoint. This mod is fully client-side, so the main
 * initializer only sets up the shared configuration and logging.
 */
public class CinemaOpt implements ModInitializer {
    public static final String MOD_ID = "cinema-opt";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModConfig.load();
        LOGGER.info("Cinema Optimized Experience loaded. Minecraft version 1.21.1, Fabric loader.");
    }
}
