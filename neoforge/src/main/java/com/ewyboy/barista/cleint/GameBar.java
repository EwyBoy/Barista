package com.ewyboy.barista.cleint;

import com.ewyboy.barista.json.JsonHandler;
import com.ewyboy.barista.json.objects.BarModule;
import com.ewyboy.barista.module.ModuleHandler;
import com.ewyboy.barista.util.Bartender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.util.Mth;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;

public class GameBar {

    private final Minecraft mc = Minecraft.getInstance();

    public void renderOverlay() {
        if (mc.isPaused()) return;
        if (mc.gui.screen() != null) return;

        Bartender.serve(mc, () -> buildBar(mc));
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGuiLayerEvent.Post event) {
        renderOverlay();
    }

    @SubscribeEvent
    public void onScreenDraw(ScreenEvent.Render.Post event) {
        if (event.getScreen() instanceof LevelLoadingScreen loadingScreen) {
            Bartender.serve(mc, () -> buildMainMenuBar(mc, "World Loading: " + Mth.floor(loadingScreen.loadTracker.serverProgress() * 100.0F) + "%"));
        } else if (!event.getScreen().getTitle().getString().isEmpty()) {
            Bartender.serve(mc, () -> buildMainMenuBar(mc, event.getScreen().getTitle().getString()));
        }
    }

    private String buildMainMenuBar(Minecraft mc, String where) {
        StringBuilder builder = new StringBuilder();
        for (BarModule module : JsonHandler.barConfig.getModuleList()) {
            if (module.isDisplay()) {
                switch (module.getName()) {
                    case "text" -> ModuleHandler.getText(builder, module.getContext());
                    case "mods" -> ModuleHandler.getMods(builder);
                    case "clock" -> ModuleHandler.getClock(builder, module.getContext());
                    case "session" -> ModuleHandler.getSession(builder);
                    case "fps" -> ModuleHandler.getFps(mc, builder);
                    case "memory" -> ModuleHandler.getMemory(builder);
                }
            }
        }
        ModuleHandler.getText(builder, where);
        return builder.toString();
    }

    private String buildBar(Minecraft mc) {
        StringBuilder builder = new StringBuilder();
        for (BarModule module : JsonHandler.barConfig.getModuleList()) {
            if (module.isDisplay()) {
                switch (module.getName()) {
                    case "text" -> ModuleHandler.getText(builder, module.getContext());
                    case "mods" -> ModuleHandler.getMods(builder);
                    case "clock" -> ModuleHandler.getClock(builder, module.getContext());
                    case "session" -> ModuleHandler.getSession(builder);
                    case "day" -> ModuleHandler.getDay(mc, builder);
                    case "time" -> ModuleHandler.getTime(mc, builder);
                    case "fps" -> ModuleHandler.getFps(mc, builder);
                    case "ping" -> ModuleHandler.getPing(mc, builder);
                    case "memory" -> ModuleHandler.getMemory(builder);
                    case "biome" -> ModuleHandler.getBiome(mc, builder);
                    case "weather" -> ModuleHandler.getWeather(mc, builder);
                    case "position" -> ModuleHandler.getPosition(mc, builder);
                    case "chunk" -> ModuleHandler.getChunk(mc, builder);
                    case "dimension" -> ModuleHandler.getDimension(mc, builder);
                    case "facing" -> ModuleHandler.getFacing(mc, builder);
                    case "looking_at" -> ModuleHandler.getLookingAt(mc, builder);
                    case "property" -> ModuleHandler.getProperty(mc, builder);
                    case "target" -> ModuleHandler.getTarget(mc, builder);
                    case "target_health" -> ModuleHandler.getTargetHealth(mc, builder);
                }
            }
        }
        return builder.toString();
    }

}
