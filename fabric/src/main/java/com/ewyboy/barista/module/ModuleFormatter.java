package com.ewyboy.barista.module;

import com.ewyboy.barista.util.Translation;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import org.apache.commons.lang3.text.WordUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class ModuleFormatter {

    public static String formatPosition(String pos) {
        String[] string = pos.split(",");
        return
                "x " + string[0] + ", " +
                "y"  + string[1] + ", " +
                "z"  + string[2];
    }

    public static String formatTranslation(String translation) {
        TranslatableComponent textComponent = new TranslatableComponent(translation);
        return textComponent.getString();
    }

    public static String formatTranslation(String text, String translation) {
        TranslatableComponent textComponent = new TranslatableComponent(translation, text);
        return textComponent.getString();
    }

    public static String formatTranslation(String text1, String text2, String translation) {
        TranslatableComponent textComponent = new TranslatableComponent(translation, text1, text2);
        return textComponent.getString();
    }

    public static String formatTranslation(String text1, String text2, String text3, String translation) {
        TranslatableComponent textComponent = new TranslatableComponent(translation, text1, text2, text3);
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

    public static InputStream formatIcon(String ctx) {
        try {
            return Files.newInputStream(new File(FabricLoader.getInstance().getConfigDir() + "/barista/icon/" + ctx).toPath(), StandardOpenOption.READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
