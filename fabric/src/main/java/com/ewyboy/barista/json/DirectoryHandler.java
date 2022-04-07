package com.ewyboy.barista.json;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.util.ModLogger;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryHandler {

    public static final Path BASE_DIRECTORY_PATH = Paths.get(FabricLoader.getInstance().getConfigDir().toAbsolutePath().toString(), Barista.MOD_ID);
    public static final Path ICON_DIRECTORY_PATH = Paths.get(FabricLoader.getInstance().getConfigDir().toAbsolutePath() + "/" + Barista.MOD_ID, "icon");

    public static void setup() {
        createDirectories(BASE_DIRECTORY_PATH);
        createDirectories(ICON_DIRECTORY_PATH);
    }

    public static void createDirectories(Path directoryPath) {
        try {
            ModLogger.info("Creating Barista directory :: " + directoryPath);
            Files.createDirectory(directoryPath);
        } catch (FileAlreadyExistsException ignored) {
        } catch (IOException e) {
            ModLogger.error("Failed to create Barista directory :: " + directoryPath, e);
        }
    }

}
