package com.ewyboy.barista.module;

import com.ewyboy.barista.util.Translation;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.loading.FMLPaths;
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
        ITextComponent textComponent = new TranslationTextComponent(translation);
        return textComponent.getString();
    }

    public static String formatTranslation(String text, String translation) {
        ITextComponent textComponent = new TranslationTextComponent(translation, text);
        return textComponent.getString();
    }

    public static String formatTranslation(String text1, String text2, String translation) {
        ITextComponent textComponent = new TranslationTextComponent(translation, text1, text2);
        return textComponent.getString();
    }

    public static String formatTranslation(String text1, String text2, String text3, String translation) {
        ITextComponent textComponent = new TranslationTextComponent(translation, text1, text2, text3);
        return textComponent.getString();
    }

    public static String formatBiome(Biome biome) {
        String name = Objects.requireNonNull(biome.getRegistryName()).getPath().replace('_', ' ');
        return WordUtils.capitalize(name);
    }

    public static String formatDimension(RegistryKey<World> dimension) {
        String name = Objects.requireNonNull(dimension.location().getPath()).replace('_', ' ');
        return WordUtils.capitalize(name);
    }

    public static String formatWeather(ClientWorld level) {
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
            return Files.newInputStream(new File(FMLPaths.CONFIGDIR.get() + "/barista/icon/" + ctx).toPath(), StandardOpenOption.READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
