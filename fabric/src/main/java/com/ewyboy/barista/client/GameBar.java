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

    public static String buildBar(Minecraft mc) {
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
