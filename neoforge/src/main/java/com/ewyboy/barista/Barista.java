package com.ewyboy.barista;

import com.ewyboy.barista.cleint.GameBar;
import com.ewyboy.barista.cleint.Keybindings;
import com.ewyboy.barista.json.JsonHandler;
import com.ewyboy.barista.module.ModuleHandler;
import com.ewyboy.barista.util.Clockwork;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.Objects;

import static com.ewyboy.barista.Barista.MOD_ID;

@Mod(MOD_ID)
public class Barista {

    public static final String NAME = "Barista";
    public static final String MOD_ID = "barista";

    public static Clockwork clockwork;

    public Barista(IEventBus eventBus) {
        startClock();
        eventBus.register(this);
        eventBus.addListener(Keybindings::onRegisterKeyBinds);
    }

    private void startClock() {
        clockwork = new Clockwork();
        clockwork.start();
    }

    @SubscribeEvent
    public void clientRegister(FMLClientSetupEvent event) {
        StringBuilder builder = new StringBuilder();

        JsonHandler.barConfig.getModuleList().forEach(module -> {
            if (Objects.equals(module.getName(), "text") && module.isDisplay())
                ModuleHandler.getText(builder, module.getContext());
        });

        event.enqueueWork(() -> null).thenRun(() -> {
            Minecraft mc = Minecraft.getInstance();
            builder.append("Starting up..");
            mc.getWindow().setTitle(builder.toString());
        });
        NeoForge.EVENT_BUS.register(new GameBar());
        NeoForge.EVENT_BUS.register(new Keybindings());
    }

}
