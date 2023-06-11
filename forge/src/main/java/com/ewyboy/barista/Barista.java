package com.ewyboy.barista;

import com.ewyboy.barista.cleint.GameBar;
import com.ewyboy.barista.cleint.Keybindings;
import com.ewyboy.barista.json.InfoHandler;
import com.ewyboy.barista.json.JsonHandler;
import com.ewyboy.barista.module.ModuleHandler;
import com.ewyboy.barista.util.Clockwork;
import com.ewyboy.barista.util.ModLogger;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Objects;

import static com.ewyboy.barista.Barista.MOD_ID;

@Mod(MOD_ID)
public class Barista {

    public static final String NAME = "Barista";
    public static final String MOD_ID = "barista";

    public static Clockwork clockwork;

    public Barista() {
        startClock();
        ignoreServerOnly();
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Keybindings::onRegisterKeyBinds);
    }

    private void startClock() {
        clockwork = new Clockwork();
        clockwork.start();
    }

    private void ignoreServerOnly() {
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () ->
                new IExtensionPoint.DisplayTest(() -> "You Can Write Whatever The Fuck You Want Here", (YouCanWriteWhatEverTheFuckYouWantHere, ICreatedSlimeBlocks2YearsBeforeMojangDid) -> ICreatedSlimeBlocks2YearsBeforeMojangDid)
        );
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
        MinecraftForge.EVENT_BUS.register(new GameBar());
        MinecraftForge.EVENT_BUS.register(new Keybindings());
    }

}
