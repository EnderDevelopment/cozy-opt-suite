package com.enderev.cinemaopt.client;

import net.minecraft.client.Minecraft;

/**
 * Tracks the live frame rate for the on-screen FPS HUD using Minecraft's own
 * FPS counting. Wraps the current running average reported by the game.
 */
public final class ModClient {
    private static int lastFps;

    private ModClient() {
    }

    /** Returns the currently tracked FPS (refreshed each frame). */
    public static int activeFps() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.getFps() > 0) {
            lastFps = mc.getFps();
        }
        return lastFps;
    }
}
