package com.enderev.cinemaopt.client;

import com.enderev.cinemaopt.Config;
import com.enderev.cinemaopt.ModConfig;
import com.enderev.cinemaopt.ModIds;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

/**
 * All key bindings offered by the mod. Each toggles or holds a single
 * feature, and the resulting configuration is persisted automatically.
 */
public final class KeyBindings {
    public static KeyMapping cinematicCamera;
    public static KeyMapping zoom;
    public static KeyMapping fullbright;
    public static KeyMapping toggleCoords;
    public static KeyMapping toggleFps;
    public static KeyMapping toggleSway;

    private KeyBindings() {
    }

    public static void register() {
        String category = ModIds.KEY_CATEGORY;

        cinematicCamera = register("cinematic", GLFW.GLFW_KEY_F7, category);
        zoom = register("zoom", GLFW.GLFW_KEY_C, category);
        fullbright = register("fullbright", GLFW.GLFW_KEY_G, category);
        toggleCoords = register("coords", GLFW.GLFW_KEY_K, category);
        toggleFps = register("fps", GLFW.GLFW_KEY_F8, category);
        toggleSway = register("sway", GLFW.GLFW_KEY_F9, category);

        // Handle one-shot toggles on tick.
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> handleToggles());
    }

    private static KeyMapping register(String id, int key, String category) {
        return KeyBindingHelper.registerKeyBinding(
                new KeyMapping(ModIds.key(id), InputConstants.Type.KEYSYM, key, category));
    }

    private static void handleToggles() {
        if (Minecraft.getInstance().player == null) {
            return;
        }
        Config cfg = ModConfig.get();
        boolean changed = false;

        if (cinematicCamera.consumeClick()) {
            cfg.cinematicCamera = !cfg.cinematicCamera;
            changed = true;
        }
        if (fullbright.consumeClick()) {
            cfg.fullbright = !cfg.fullbright;
            changed = true;
        }
        if (toggleCoords.consumeClick()) {
            cfg.showCoords = !cfg.showCoords;
            changed = true;
        }
        if (toggleFps.consumeClick()) {
            cfg.showFps = !cfg.showFps;
            changed = true;
        }
        if (toggleSway.consumeClick()) {
            cfg.swayAmount = cfg.swayAmount > 0 ? 0 : 0.6;
            changed = true;
        }
        if (changed) {
            ModConfig.save();
        }
    }
}
