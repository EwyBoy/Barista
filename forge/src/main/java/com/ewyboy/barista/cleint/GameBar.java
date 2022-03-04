package com.ewyboy.barista.cleint;

import com.ewyboy.barista.json.JsonHandler;
import com.ewyboy.barista.json.objects.BarModule;
import com.ewyboy.barista.module.ModuleHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.*;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GameBar {

    private static boolean isGui = false;
    private final Minecraft mc = Minecraft.getInstance();

    public void renderOverlay() {
        if(mc.isPaused()) return;
        mc.getWindow().setTitle(buildBar(mc));
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            renderOverlay();
        }
    }

    @SubscribeEvent
    public void onScreenDraw(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (event.getGui() instanceof WorldLoadProgressScreen) {
            WorldLoadProgressScreen worldLoadProgressScreen = (WorldLoadProgressScreen) event.getGui();
            mc.getWindow().setTitle(buildMainMenuBar(mc, "World Loading: " + worldLoadProgressScreen.progressListener.getProgress() + "%"));
        } else {
            if (!event.getGui().getTitle().getString().isEmpty()) {
                mc.getWindow().setTitle(buildMainMenuBar(mc, event.getGui().getTitle().getString()));
            }
        }
    }

    private String buildMainMenuBar(Minecraft mc, String where) {
        StringBuilder builder = new StringBuilder();
        for (BarModule module : JsonHandler.barConfig.getModuleList()) {
            if (module.isDisplay()) {
                switch (module.getName()) {
                    case "text": ModuleHandler.getText(builder, module.getContext()); break;
                    case "mods": ModuleHandler.getMods(builder); break;
                    case "clock": ModuleHandler.getClock(builder, module.getContext()); break;
                    case "session": ModuleHandler.getSession(builder); break;
                    case "fps": ModuleHandler.getFps(mc, builder); break;
                    case "memory": ModuleHandler.getMemory(builder); break;
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
                    case "text": ModuleHandler.getText(builder, module.getContext()); break;
                    case "mods": ModuleHandler.getMods(builder); break;
                    case "clock": ModuleHandler.getClock(builder, module.getContext()); break;
                    case "session": ModuleHandler.getSession(builder); break;
                    case "day": ModuleHandler.getDay(mc, builder); break;
                    case "time": ModuleHandler.getTime(mc, builder); break;
                    case "fps": ModuleHandler.getFps(mc, builder); break;
                    case "ping": ModuleHandler.getPing(mc, builder); break;
                    case "memory": ModuleHandler.getMemory(builder); break;
                    case "biome": ModuleHandler.getBiome(mc, builder); break;
                    case "weather": ModuleHandler.getWeather(mc, builder); break;
                    case "position": ModuleHandler.getPosition(mc, builder); break;
                    case "chunk": ModuleHandler.getChunk(mc, builder); break;
                    case "dimension": ModuleHandler.getDimension(mc, builder); break;
                    case "facing": ModuleHandler.getFacing(mc, builder); break;
                    case "looking_at": ModuleHandler.getLookingAt(mc, builder); break;
                    case "property": ModuleHandler.getProperty(mc, builder); break;
                    case "target": ModuleHandler.getTarget(mc, builder); break;
                    case "target_health": ModuleHandler.getTargetHealth(mc, builder); break;
                }
            }
        }
        return builder.toString();
    }

}
