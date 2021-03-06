package com.ewyboy.barista.client;

import com.ewyboy.barista.json.JsonHandler;
import com.ewyboy.barista.json.objects.BarModule;
import com.ewyboy.barista.module.ModuleHandler;
import net.minecraft.client.Minecraft;

public class GameBar {

    public static String buildMainMenuBar(Minecraft mc, String where) {
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

    public static String buildBar(Minecraft mc) {
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
