package com.ewyboy.barista.module;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.util.Clockwork;
import com.ewyboy.barista.util.RayTracer;
import com.ewyboy.barista.util.Statinator;
import com.ewyboy.barista.util.Translation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ModuleHandler {

    private static final String x = ": ";
    private static final String separator = " | ";

    public static void getText(StringBuilder builder, String ctx) {
        builder.append(ctx).append(separator);
    }

    public static void getIcon(Minecraft mc, String ctx) {
        InputStream formattedIcon = ModuleFormatter.formatIcon(ctx);
        if (formattedIcon != null) mc.getWindow().setIcon(formattedIcon, formattedIcon);
    }

    public static void getMods(StringBuilder builder) {
        //builder.append(ModuleFormatter.formatTranslation(Translation.Bar.MODS)).append(x).append(ModList.get().getMods().size()).append(separator);
    }

    public static void getFps(Minecraft mc, StringBuilder builder) {
        builder.append(ModuleFormatter.formatTranslation(mc.fpsString.split("\\s+")[0], Translation.Bar.FPS)).append(separator);
    }

    public static void getPing(Minecraft mc, StringBuilder builder) {
        PlayerInfo entry = Objects.requireNonNull(mc.player).connection.getPlayerInfo(mc.player.getUUID());
        if (entry != null) {
            if (entry.getLatency() != 0) {
                builder.append(ModuleFormatter.formatTranslation(String.valueOf(entry.getLatency()), Translation.Bar.PING)).append(separator);
            }
        }
    }

    public static void getMemory(StringBuilder builder) {
        int max = (int) (Runtime.getRuntime().maxMemory() / 1024 / 1024);
        int free = (int) (Runtime.getRuntime().freeMemory() / 1024 / 1024);
        int total = (int) (Runtime.getRuntime().totalMemory() / 1024 / 1024);

        int difference = total - free;

        builder.append(ModuleFormatter.formatTranslation(
                String.valueOf(difference * 100 / max),
                String.valueOf(difference),
                String.valueOf(max),
                Translation.Bar.MEMORY
        )).append(separator);
    }

    public static void getPosition(Minecraft mc, StringBuilder builder) {
        if (mc.player != null) {
            builder.append(ModuleFormatter.formatPosition(mc.player.blockPosition().toShortString())).append(separator);
        }
    }

    public static void getChunk(Minecraft mc, StringBuilder builder) {
        if (mc.level != null && mc.player != null) {
            builder.append(ModuleFormatter.formatTranslation(Translation.Bar.CHUNK)).append(x).append(mc.level.getChunk(mc.player.blockPosition()).getPos()).append(separator);
        }
    }

    public static void getWeather(Minecraft mc, StringBuilder builder) {
        if (mc.level != null) {
            builder.append(ModuleFormatter.formatTranslation(Translation.Bar.WEATHER)).append(x).append(ModuleFormatter.formatWeather(mc.level)).append(separator);
        }
    }

    public static void getBiome(Minecraft mc, StringBuilder builder) {
        if (mc.player != null) {
            builder.append(ModuleFormatter.formatTranslation(Translation.Bar.BIOME)).append(x).append(ModuleFormatter.formatBiome(mc.player.clientLevel.getBiome(mc.player.blockPosition()))).append(separator);
        }
    }

    public static void getDimension(Minecraft mc, StringBuilder builder) {
        if (mc.level != null) {
            builder.append(ModuleFormatter.formatTranslation(Translation.Bar.DIMENSION)).append(x).append(ModuleFormatter.formatDimension(mc.level.dimension())).append(separator);
        }
    }

    public static void getLookingAt(Minecraft mc, StringBuilder builder) {
        BlockState state = RayTracer.getStateFromRaytrace(mc);
        if (state != null) {
            builder.append(Objects.requireNonNull(state.getBlock().getName().getString())).append(separator);
        }
    }

    public static void getProperty(Minecraft mc, StringBuilder builder) {
        BlockState state = RayTracer.getStateFromRaytrace(mc);
        if (state != null) {
            Statinator.handleStates(state, builder, separator);
        }
    }

    public static void getTarget(Minecraft mc, StringBuilder builder) {
        Entity entity = mc.crosshairPickEntity;
        if (entity != null) {
            builder.append(entity.getDisplayName().getString()).append(separator);
        }
    }

    public static void getTargetHealth(Minecraft mc, StringBuilder builder) {
        Entity entity = mc.crosshairPickEntity;
        if (entity != null) {
            if (entity instanceof LivingEntity) {
                float health = ((LivingEntity) entity).getHealth();
                float maxHealth = ((LivingEntity) entity).getMaxHealth();
                builder.append(ModuleFormatter.formatTranslation(Translation.Bar.HEALTH)).append(x).append(health).append(" / ").append(maxHealth).append(separator);
            }
        }
    }

    public static void getClock(StringBuilder builder, String ctx) {
        SimpleDateFormat formatter = new SimpleDateFormat(ctx);
        Date date = new Date();
        builder.append(formatter.format(date)).append(separator);
    }

    public static void getSession(StringBuilder builder) {
        builder.append(Barista.clockwork.getSessionLength()).append(separator);
    }

    public static void getDay(Minecraft mc, StringBuilder builder) {
        if (mc.level != null) {
            builder.append(ModuleFormatter.formatTranslation(Translation.Bar.DAY)).append(x).append(mc.level.getDayTime() / 24000L).append(separator);
        }
    }

    public static void getTime(Minecraft mc, StringBuilder builder) {
        if (mc.level != null) {
            builder.append(Clockwork.getTimeOfDay(mc.level.getDayTime())).append(separator);
        }
    }

    public static void getFacing(Minecraft mc, StringBuilder builder) {
        if (mc.player != null) {
            builder.append(ModuleFormatter.formatTranslation(Translation.Bar.FACING)).append(x).append(mc.player.getDirection().toString().toUpperCase()).append(separator);
        }
    }

}
