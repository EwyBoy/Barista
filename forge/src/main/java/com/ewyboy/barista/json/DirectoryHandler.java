package com.ewyboy.barista.json;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.util.ModLogger;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class DirectoryHandler {

    public static final String ICON_PATH = "assets/barista/icon/icon.png";
    public static final Path BASE_DIRECTORY_PATH = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath().toString(), Barista.MOD_ID);
    public static final Path ICON_DIRECTORY_PATH = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath() + "/" + Barista.MOD_ID, "icon");
    public static final Path ICON_DIRECTORY_CONFIG_PATH = Paths.get(FMLPaths.CONFIGDIR.get().toAbsolutePath() + "/" + Barista.MOD_ID, "icon/icon.png");

    public static void setup() {
        createDirectories(BASE_DIRECTORY_PATH);
        createDirectories(ICON_DIRECTORY_PATH);

        if (!fileExists(ICON_DIRECTORY_CONFIG_PATH)) {
            copyResource(ICON_PATH, ICON_DIRECTORY_PATH + "/icon.png");
        }
    }

    public static boolean fileExists(Path path) {
        ModLogger.info("Checking if icon exists :: " + path);
        return Files.exists(path);
    }

    public static void copyResource(String resource, String destination) {
        try {
            Files.copy(Objects.requireNonNull(Barista.class.getClassLoader().getResourceAsStream(resource)), Paths.get(destination));
        } catch (IOException e) {
            ModLogger.error("Failed to copy resource: " + resource + " to: " + destination);
            e.printStackTrace();
        }
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
