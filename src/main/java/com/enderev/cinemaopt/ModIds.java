package com.enderev.cinemaopt;

/**
 * Central place for the mod's identifier paths and shared constants.
 */
public final class ModIds {
    private ModIds() {
    }

    public static final String MOD_ID = "cinema-opt";

    /** Key binding category translation key. */
    public static final String KEY_CATEGORY = "category.cinemaopt.main";

    /** Returns a translation key in the mod's namespace. */
    public static String key(String path) {
        return "key." + MOD_ID + "." + path;
    }
}
