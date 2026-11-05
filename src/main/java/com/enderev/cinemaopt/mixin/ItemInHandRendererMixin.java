package com.enderev.cinemaopt.mixin;

import com.enderev.cinemaopt.Config;
import com.enderev.cinemaopt.ModConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.InteractionHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.mojang.math.Axis;

/**
 * Adds gentle, swaying animation to held items and a slight 3D tilt to the
 * off-hand. All motion is purely cosmetic and client-side.
 */
@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Inject(
            method = "applyItemArmTransform",
            at = @At("HEAD")
    )
    private void cinemaopt_addSway(PoseStack poseStack, InteractionHand hand, float equipProgress, CallbackInfo ci) {
        Config cfg = ModConfig.get();

        // Gentle vertical-sway and roll that plays as the player moves.
        if (cfg.swayAmount > 0) {
            double intensity = cfg.swayAmount * 0.05;
            float f = 0.5F + equipProgress;
            poseStack.mulPose(Axis.XP.rotationDegrees((float) Math.sin(f * 2.0) * (float) intensity * 40.0F));
            poseStack.mulPose(Axis.ZP.rotationDegrees((float) Math.cos(f * 2.5) * (float) intensity * 30.0F));
        }

        // Off-hand gets a subtle 3D offset for depth, when enabled.
        if (hand == InteractionHand.OFF_HAND && cfg.offhandOffset) {
            poseStack.translate(-0.02F, 0.02F, -0.05F);
        }
    }
}
