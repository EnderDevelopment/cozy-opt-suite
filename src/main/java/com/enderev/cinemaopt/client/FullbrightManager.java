package com.enderev.cinemaopt.client;

import com.enderev.cinemaopt.Config;
import com.enderev.cinemaopt.ModConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;

/**
 * Implements the fullbright convenience toggle. When enabled it smoothly
 * raises the brightness (gamma) option to maximum; when disabled it returns
 * the brightness to the value the player had configured before.
 */
public final class FullbrightManager {
    private static double originalGamma = -1.0;

    private FullbrightManager() {
    }

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> onTick());
    }

    private static void onTick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.options == null) {
            return;
        }
        Options opts = mc.options;
        Config cfg = ModConfig.get();

        if (cfg.fullbright) {
            if (originalGamma < 0) {
                originalGamma = opts.gamma().get();
            }
            double target = 1.0;
            double current = opts.gamma().get();
            double next = current + (target - current) * 0.25;
            opts.gamma().set(next);
        } else if (originalGamma >= 0) {
            opts.gamma().set(originalGamma);
            originalGamma = -1.0;
        }
    }
}
