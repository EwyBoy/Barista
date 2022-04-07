package com.ewyboy.barista;

import com.ewyboy.barista.json.JsonHandler;
import com.ewyboy.barista.module.ModuleHandler;
import com.ewyboy.barista.util.Clockwork;
import com.ewyboy.barista.util.ModLogger;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;

import java.util.Objects;

public class Barista implements ClientModInitializer {

    public static final String NAME = "Barista";
    public static final String MOD_ID = "barista";

    public static Clockwork clockwork;

    @Override
    public void onInitializeClient() {
        startClock();
        StringBuilder builder = new StringBuilder();

        JsonHandler.barConfig.getModuleList().forEach(module -> {
            /*if (Objects.equals(module.getName(), "icon")) {
                if (module.isDisplay()) {
                    ModuleHandler.getIcon(Minecraft.getInstance(), module.getContext());
                }
            }*/
            if (Objects.equals(module.getName(), "text")) {
                if (module.isDisplay()) {
                    ModuleHandler.getText(builder, module.getContext());
                    ModLogger.info("Text: " + module.getContext());
                }
            }
        });
    }

    private void startClock() {
        clockwork = new Clockwork();
        clockwork.start();
    }

}