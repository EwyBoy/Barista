package com.ewyboy.barista.json;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.util.ModLogger;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InfoHandler {

    public static final File INFO_FILE = new File(FabricLoader.getInstance().getConfigDir() + "/" +  Barista.MOD_ID + "/Info.txt");

    public static void setup() {
        createInfoFile();
    }

    private static void createInfoFile() {
        try {
            if (InfoHandler.INFO_FILE.createNewFile()) {
                ModLogger.info("Creating Barista information file: " + InfoHandler.INFO_FILE.getName());
                FileWriter writer = new FileWriter(INFO_FILE);
                writeInfoFile(writer);
            }
        } catch (IOException e) {
            ModLogger.error("Failed to create information file: " + InfoHandler.INFO_FILE.getName());
            e.printStackTrace();
        }
    }

    private static void writeInfoFile(FileWriter writer) throws IOException {
        writer.write("Barista - Information");
        writer.write("\n");

        writer.write("Edit the spawn.json file to add or remove entries from the grass loot table.");
    }

}
