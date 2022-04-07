package com.ewyboy.barista;

import com.ewyboy.barista.json.JsonHandler;
import com.ewyboy.barista.module.ModuleHandler;
import com.ewyboy.barista.util.Clockwork;
import com.ewyboy.barista.util.ModLogger;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;

import java.util.Objects;

public class Barista implements ClientModInitializer {

    public static final String MOD_ID = "barista";
    public static Clockwork clockwork;

    private void startClock() {
        clockwork = new Clockwork();
        clockwork.start();
    }

    @Override
    public void onInitializeClient() {
        startClock();
        StringBuilder builder = new StringBuilder();

        Minecraft.getInstance().execute(() -> {
            Minecraft mc = Minecraft.getInstance();
            JsonHandler.barConfig.getModuleList().forEach(module -> {
                if (Objects.equals(module.getName(), "icon") && module.isDisplay())
                    ModuleHandler.getIcon(mc, module.getContext());
                if (Objects.equals(module.getName(), "text") && module.isDisplay())
                    ModuleHandler.getText(builder, module.getContext());
            });
            builder.append("Starting up..");
            mc.getWindow().setTitle(builder.toString());
        });
    }
}