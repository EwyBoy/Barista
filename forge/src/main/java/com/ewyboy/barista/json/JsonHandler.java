package com.ewyboy.barista.json;

import com.ewyboy.barista.json.objects.BarConfig;
import com.ewyboy.barista.json.objects.BarModule;
import com.ewyboy.barista.util.ModLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.util.SharedConstants;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {

    private static final Gson gson = new Gson();
    public static final File JSON_FILE = new File(FMLPaths.CONFIGDIR.get() + "/barista/bar.json");

    private static final List<BarModule> modules = new ArrayList<>();

    static {
        modules.add(new BarModule("icon", true, "icon.png"));
        modules.add(new BarModule("text", true, "Minecraft " + SharedConstants.getCurrentVersion().getName()));
        modules.add(new BarModule("mods", true));

        modules.add(new BarModule("clock", true, "HH:mm:ss"));
        modules.add(new BarModule("session", true));
        modules.add(new BarModule("day", true));

        modules.add(new BarModule("fps", true));
        modules.add(new BarModule("ping", true));
        modules.add(new BarModule("memory", true));

        modules.add(new BarModule("biome", true));
        modules.add(new BarModule("position", true));
        modules.add(new BarModule("chunk", true));
        modules.add(new BarModule("dimension", true));

        modules.add(new BarModule("facing", true));
        modules.add(new BarModule("looking_at", true));
        modules.add(new BarModule("target", true));
        modules.add(new BarModule("target_health", true));
    }

    public static BarConfig barConfig = new BarConfig(modules);

    public static void setup() {
        DirectoryHandler.setup();
        if(!JSON_FILE.exists()) {
            ModLogger.info("Creating Barista bar JSON file");
            writeJson(JSON_FILE);
        }
        ModLogger.info("Reading Barista bar config JSON file");
        readJson(JSON_FILE);
    }

    public static void reload() {
        writeJson(JSON_FILE);
        readJson(JSON_FILE);
    }

    public static boolean hasModule(BarModule barModule) {
        return barConfig.getModuleList().contains(barModule);
    }

    public static boolean addModule(BarModule module) {
        if (!hasModule(module)) {
            barConfig.getModuleList().add(module);
            reload();
            return true;
        }
        return false;
    }

    public static boolean removeModule(BarModule barModule) {
        if (hasModule(barModule)) {
            barConfig.getModuleList().removeIf(module -> module.equals(barModule));
            reload();
            return true;
        }
        return false;
    }

    public static void writeJson(File jsonFile) {
        try(Writer writer = new FileWriter(jsonFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(barConfig, writer);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void readJson(File jsonFile) {
        try(Reader reader = new FileReader(jsonFile)) {
            barConfig = gson.fromJson(reader, BarConfig.class);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}