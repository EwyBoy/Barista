package com.ewyboy.barista.module;

import com.ewyboy.barista.util.Translation;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.IoSupplier;
import net.minecraft.world.level.Level;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ModuleFormatter {

    private static final Map<String, byte[]> STORAGE = new HashMap<>();
    private static final List<String> PNG_PATHS = new LinkedList<>();

    public static String formatPosition(String pos) {
        String[] string = pos.split(",");
        return
                "x " + string[0] + ", " +
                "y"  + string[1] + ", " +
                "z"  + string[2];
    }

    public static String formatTranslation(String translation) {
        Component textComponent = Component.translatable(translation);
        return textComponent.getString();
    }

    public static String formatTranslation(String text, String translation) {
        Component textComponent = Component.translatable(translation, text);
        return textComponent.getString();
    }

    public static String formatTranslation(String text1, String text2, String translation) {
        Component textComponent = Component.translatable(translation, text1, text2);
        return textComponent.getString();
    }

    public static String formatTranslation(String text1, String text2, String text3, String translation) {
        Component textComponent = Component.translatable(translation, text1, text2, text3);
        return textComponent.getString();
    }

    public static String formatBiome(String biome) {
        String name = biome.split(":")[1].replace('_', ' ');
        return WordUtils.capitalize(name);
    }

    public static String formatDimension(ResourceKey<Level> dimension) {
        String name = Objects.requireNonNull(dimension.location().getPath()).replace('_', ' ');
        return WordUtils.capitalize(name);
    }

    public static String formatWeather(ClientLevel level) {
        String weather = formatTranslation(Translation.Bar.WEATHER_CLEAR);
        if (level.isThundering()) {
            weather = formatTranslation(Translation.Bar.WEATHER_THUNDER);
        } else if (level.isRaining()) {
            weather = formatTranslation(Translation.Bar.WEATHER_RAIN);
        }
        return weather;
    }

    public static void formatIcon(String ctx, boolean isPng) {
        try {
            var file = Files.newInputStream(new File(FabricLoader.getInstance().getConfigDir() + "/barista/icon/" + ctx).toPath(), StandardOpenOption.READ);
            byte[] data = IOUtils.toByteArray(file);
            STORAGE.put(ctx, data);
            if (isPng) {
                PNG_PATHS.add(ctx);
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to load resource %s: %s", ctx, e));
        }
    }

    public static IoSupplier<InputStream> getResource(String path) {
        byte[] data = STORAGE.get(path);
        if (data == null) {
            throw new RuntimeException("Unexpected resource path " + path);
        } else {
            return () -> new ByteArrayInputStream(data);
        }
    }

    public static List<IoSupplier<InputStream>> getAllPngResources() {
        return PNG_PATHS.stream().map(ModuleFormatter :: getResource).toList();
    }

}
