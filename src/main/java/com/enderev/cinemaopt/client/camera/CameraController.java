package com.enderev.cinemaopt.client.camera;

import com.enderev.cinemaopt.Config;
import com.enderev.cinemaopt.ModConfig;
import com.enderev.cinemaopt.client.KeyBindings;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

/**
 * Handles the cinematic camera smoothing and the held-to-zoom feature.
 * Cinematic mode interpolates the player's view rotation toward the
 * mouse input each tick, producing a smooth, eased, filmed look.
 */
public final class CameraController {
    private static double targetYaw;
    private static double targetPitch;
    private static double smoothYaw;
    private static double smoothPitch;
    private static boolean active;

    private CameraController() {
    }

    /** Registers the tick-update behaviour. Called from client init. */
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> onClientTick());
    }

    /**
     * Called every client tick. When cinematic mode is enabled the view is
     * eased toward the current mouse direction; when zoom is held the FOV is
     * scaled down for a smooth magnified view.
     */
    public static void onClientTick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }

        Config cfg = ModConfig.get();
        boolean cinematic = cfg.cinematicCamera;

        if (cinematic && !active) {
            targetYaw = mc.player.getYRot();
            targetPitch = mc.player.getXRot();
            smoothYaw = targetYaw;
            smoothPitch = targetPitch;
            active = true;
        } else if (!cinematic) {
            active = false;
        }

        if (active) {
            // Update target to where the mouse is pointing.
            targetYaw = mc.player.getYRot();
            targetPitch = mc.player.getXRot();

            // Ease smoothly toward the target for a cinematic feel.
            double ease = 0.12;
            smoothYaw = lerpAngle(smoothYaw, targetYaw, ease);
            smoothPitch = smoothPitch + (targetPitch - smoothPitch) * ease;

            // Apply the smoothed rotation.
            mc.player.setYRot((float) smoothYaw);
            mc.player.setXRot((float) smoothPitch);
        }
    }

    /** True while the player holds the zoom key. */
    public static boolean isZooming() {
        return KeyBindings.zoom.isDown();
    }

    /** True when cinematic camera mode is enabled. */
    public static boolean isCinematic() {
        return ModConfig.get().cinematicCamera;
    }

    private static double lerpAngle(double from, double to, double t) {
        double delta = Math.toRadians(to - from);
        delta = Math.atan2(Math.sin(delta), Math.cos(delta));
        return from + Math.toDegrees(delta) * t;
    }
}
