package com.ewyboy.barista.module;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class ModuleFormatter {

    public static String formatPosition(String pos) {
        String[] string = pos.split(",");
        return
                "x " + string[0] + ", " +
                "y"  + string[1] + ", " +
                "z"  + string[2];
    }

    public static String formatText(String text, String translation) {
        ITextComponent fpsString = new TranslationTextComponent(translation, text);
        return fpsString.getString();
    }

    public static String formatText(String text1, String text2, String text3, String translation) {
        ITextComponent fpsString = new TranslationTextComponent(translation, text1, text2, text3);
        return fpsString.getString();
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
