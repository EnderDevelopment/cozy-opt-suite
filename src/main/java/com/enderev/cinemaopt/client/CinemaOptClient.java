package com.enderev.cinemaopt.client;

import com.enderev.cinemaopt.CinemaOpt;
import com.enderev.cinemaopt.client.camera.CameraController;
import com.enderev.cinemaopt.client.hud.CoordHudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

/**
 * Client-side entrypoint. Registers all key bindings, tick handlers and
 * HUD renderers that power the cinematic, optimisation and utility modules.
 */
public class CinemaOptClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // --- Key bindings + one-shot toggle handling ---
        KeyBindings.register();

        // --- Per-tick feature controllers ---
        CameraController.init();
        FullbrightManager.init();

        // --- HUD (coords, day counter, FPS) ---
        HudRenderCallback.EVENT.register(CoordHudRenderer::render);

        CinemaOpt.LOGGER.info("Cinema Opt client initialised.");
    }
}
