package com.enderev.cinemaopt.mixin;

import com.enderev.cinemaopt.Config;
import com.enderev.cinemaopt.ModConfig;
import com.enderev.cinemaopt.client.camera.CameraController;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/**
 * Applies a smooth zoom FOV reduction while the zoom key is held, and a
 * slight cinematic FOV compensation when cinematic mode is active.
 */
@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @ModifyVariable(
            method = "getFov",
            at = @At("RETURN"),
            ordinal = 0
    )
    private double cinemaopt_modifyFov(double fov) {
        Config cfg = ModConfig.get();
        if (CameraController.isZooming()) {
            fov *= cfg.zoomAmount;
        }
        if (CameraController.isCinematic()) {
            // Slightly widen FOV in cinematic mode for a more filmic frame.
            fov *= 1.0 + 0.04;
        }
        return fov;
    }
}
