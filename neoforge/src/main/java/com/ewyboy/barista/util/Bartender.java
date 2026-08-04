package com.ewyboy.barista.util;

import net.minecraft.client.Minecraft;

import java.util.function.Supplier;
import java.util.regex.Pattern;

/**
 * Serves the bar to the window title, but only as fast as anyone can actually read it.
 *
 * The render hooks we listen on fire far more often than once per frame, so counting
 * invocations is not a usable throttle. On NeoForge RenderGuiLayerEvent.Post is posted
 * once per GUI layer, which is roughly 25 times a frame before other mods add their own.
 * Gate on the wall clock instead, and never hand GLFW a title it is already showing.
 */
public class Bartender {

    private static final long UPDATE_INTERVAL_MS = 100;

    /**
     * A window title is a single line, but the strings we paste into it are not always.
     * Screen titles in particular can carry newlines, which leaves the title bar rendering
     * across several lines. Precompiled because this runs on every update.
     */
    private static final Pattern WHITESPACE = Pattern.compile("\\s+");

    private static long lastUpdate = 0;
    private static String lastTitle = "";

    /**
     * Builds and sets the title at most once every {@link #UPDATE_INTERVAL_MS}.
     * The supplier is only invoked when an update is actually due, so a rate limited
     * call costs nothing beyond the clock read.
     */
    public static void serve(Minecraft mc, Supplier<String> bar) {
        long now = System.currentTimeMillis();

        if (now - lastUpdate < UPDATE_INTERVAL_MS) return;
        lastUpdate = now;

        String title = flatten(bar.get());

        if (title.equals(lastTitle)) return;
        lastTitle = title;

        mc.getWindow().setTitle(title);
    }

    /** Collapses every run of whitespace to a single space so the bar stays on one line. */
    private static String flatten(String title) {
        return WHITESPACE.matcher(title).replaceAll(" ").strip();
    }

}
