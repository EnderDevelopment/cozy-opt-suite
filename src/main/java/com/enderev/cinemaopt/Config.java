package com.enderev.cinemaopt;

/**
 * All configurable values of the mod. Kept in one serializable POJO so the
 * whole set can be saved and reloaded in a single JSON file.
 */
public class Config {
    // ---- Optimization ----
    /** Tracks FPS and shows the average on screen. */
    public boolean showFps = true;

    // ---- Cinematic camera ----
    /** Enables smooth cinematic-style camera smoothing. */
    public boolean cinematicCamera = false;
    /** Extra zoom level (multiplier) when the zoom key is held. */
    public double zoomAmount = 0.25;
    /** Whether the cinematic smoothing is eased for a buttery feel. */
    public boolean smoothZoom = true;

    // ---- Movement / utilities ----
    /** Renders a coordinate and direction HUD. */
    public boolean showCoords = false;
    /** Renders the in-game day counter. */
    public boolean showDay = false;
    /** Whether the F3 debug screen shows extra optimised info. */
    public boolean optimizeDebugInfo = true;

    // ---- Animation ----
    /** Intensity of the item/hand sway while moving (0 disables). */
    public double swayAmount = 0.6;
    /** Whether to gently bob held items while walking. */
    public boolean itemBob = true;
    /** Whether the offhand renders with a subtle 3D offset. */
    public boolean offhandOffset = true;

    // ---- Convenience ----
    /** Night vision style fullbright override. */
    public boolean fullbright = false;
}
