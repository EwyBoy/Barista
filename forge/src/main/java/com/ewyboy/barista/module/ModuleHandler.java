package com.ewyboy.barista.module;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.util.RayTracer;
import com.ewyboy.barista.util.Translation;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.fml.ModList;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ModuleHandler {

    private static final String separator = " | ";

    public static void getText(StringBuilder builder, String ctx) {
        builder.append(ctx).append(separator);
    }

    public static void getIcon(Minecraft mc, String ctx) {
        InputStream formattedIcon = ModuleFormatter.formatIcon(ctx);
        if (formattedIcon != null) mc.getWindow().setIcon(formattedIcon, formattedIcon);
    }

    public static void getMods(StringBuilder builder) {
        builder.append("Mods: ").append(ModList.get().getMods().size()).append(separator);
    }

    public static void getFps(Minecraft mc, StringBuilder builder) {
        builder.append(ModuleFormatter.formatText(mc.fpsString.split("\\s+")[0], Translation.Display.FPS)).append(separator);
    }

    public static void getPing(Minecraft mc, StringBuilder builder) {
        NetworkPlayerInfo entry = Objects.requireNonNull(mc.player).connection.getPlayerInfo(mc.player.getUUID());
        if (entry != null) {
            if (entry.getLatency() != 0) {
                builder.append(ModuleFormatter.formatText(String.valueOf(entry.getLatency()), Translation.Display.PING)).append(separator);
            }
        }
    }

    public static void getMemory(StringBuilder builder) {
        int max = (int) (Runtime.getRuntime().maxMemory() / 1024 / 1024);
        int free = (int) (Runtime.getRuntime().freeMemory() / 1024 / 1024);
        int total = (int) (Runtime.getRuntime().totalMemory() / 1024 / 1024);

        int difference = total - free;

        builder.append(ModuleFormatter.formatText(
                String.valueOf(difference * 100 / max),
                String.valueOf(difference),
                String.valueOf(max),
                Translation.Display.MEMORY
        )).append(separator);
    }

    public static void getPosition(Minecraft mc, StringBuilder builder) {
        if (mc.player != null) {
            builder.append(ModuleFormatter.formatPosition(mc.player.blockPosition().toShortString())).append(separator);
        }
    }

    public static void getChunk(Minecraft mc, StringBuilder builder) {
        if (mc.level != null && mc.player != null) {
            builder.append("Chunk: ").append(mc.level.getChunk(mc.player.blockPosition()).getPos()).append(separator);
        }
    }

    public static void getBiome(Minecraft mc, StringBuilder builder) {
        if (mc.player != null) {
            builder.append("Biome: ").append(Objects.requireNonNull(mc.player.clientLevel.getBiome(mc.player.blockPosition()).getRegistryName()).getPath().toUpperCase(Locale.ROOT)).append(separator);
        }
    }

    public static void getDimension(Minecraft mc, StringBuilder builder) {
        if (mc.level != null) {
            builder.append("Dim: ").append(mc.level.dimension().location().getPath().toUpperCase()).append(separator);
        }
    }

    public static void getLookingAt(Minecraft mc, StringBuilder builder) {
        BlockState state = RayTracer.getStateFromRaytrace(mc);
        if (state != null) {
            builder.append(Objects.requireNonNull(state.getBlock().getName().getString())).append(separator);
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
                builder.append("Health: ").append(health).append(" / ").append(maxHealth).append(separator);
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
            builder.append("Day: ").append(mc.level.getDayTime() / 24000L).append(separator);
        }
    }

    public static void getFacing(Minecraft mc, StringBuilder builder) {
        if (mc.player != null) {
            builder.append("Facing: ").append(mc.player.getDirection().toString().toUpperCase()).append(separator);
        }
    }


}
