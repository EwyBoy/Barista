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
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

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
        JsonHandler.setup();
        InfoHandler.setup();
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    private void startClock() {
        clockwork = new Clockwork();
        clockwork.start();
    }

    // Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
    private void ignoreServerOnly() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(
                () -> FMLNetworkConstants.IGNORESERVERONLY,
                (YouCanWriteWhatEverTheFuckYouWantHere, ICreatedSlimeBlocks2YearsBeforeMojangDid) -> true)
        );
    }

    @SubscribeEvent
    public void clientRegister(FMLClientSetupEvent event) {
        Minecraft mc = event.getMinecraftSupplier().get();
        StringBuilder builder = new StringBuilder();
        event.enqueueWork(() -> JsonHandler.barConfig.getModuleList().forEach(module -> {
            if (Objects.equals(module.getName(), "icon")) {
                if (module.isDisplay()) {
                    ModuleHandler.getIcon(Minecraft.getInstance(), module.getContext());
                }
            }
            if (Objects.equals(module.getName(), "text")) {
                if (module.isDisplay()) {
                    ModuleHandler.getText(builder, module.getContext());
                    ModLogger.info("Text: " + module.getContext());
                }
            }
        }
        )).thenRun(() -> {
            builder.append("Starting up..");
            mc.getWindow().setTitle(builder.toString());
        });
        Keybindings.setup();
        MinecraftForge.EVENT_BUS.register(new GameBar());
    }

}
