package com.enderev.cinemaopt.client.hud;

import com.enderev.cinemaopt.Config;
import com.enderev.cinemaopt.ModConfig;
import com.enderev.cinemaopt.client.ModClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

/**
 * Renders the optional QoL HUD: coordinates, in-game day counter and a live
 * FPS readout. Each item can be toggled from its key binding or config.
 */
public final class CoordHudRenderer {

    private CoordHudRenderer() {
    }

    public static void render(GuiGraphics graphics) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) {
            return;
        }
        Config cfg = ModConfig.get();

        int x = 4;
        int y = 4;
        int lineHeight = 10;
        int color = 0xFFFFFF;

        if (cfg.showCoords) {
            Player p = mc.player;
            String dir = direction(p.getYRot());
            String line = String.format(
                    "X %d  Y %d  Z %d  (%s)",
                    (int) Math.floor(p.getX()),
                    (int) Math.floor(p.getY()),
                    (int) Math.floor(p.getZ()),
                    dir);
            graphics.drawString(mc.font, Component.literal(line), x, y, color, true);
            y += lineHeight;
        }

        if (cfg.showDay) {
            long day = mc.level == null ? 0 : (mc.level.getDayTime() / 24000L) + 1;
            graphics.drawString(mc.font, Component.literal("Day " + day), x, y, color, true);
            y += lineHeight;
        }

        if (cfg.showFps) {
            int fps = ModClient.activeFps();
            String fpsColor = fps >= 60 ? "§a" : (fps >= 30 ? "§e" : "§c");
            graphics.drawString(mc.font, Component.literal(fpsColor + fps + " FPS"), x, y, color, true);
        }
    }

    private static String direction(float yaw) {
        // Normalise yaw to [0, 360).
        float d = ((yaw % 360) + 360) % 360;
        if (d >= 315 || d < 45) {
            return "South";
        } else if (d < 135) {
            return "West";
        } else if (d < 225) {
            return "North";
        }
        return "East";
    }
}
