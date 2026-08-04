package com.ewyboy.barista.mixin;

import com.ewyboy.barista.util.Bartender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.ewyboy.barista.client.GameBar.buildMainMenuBar;

@Mixin(LevelLoadingScreen.class)
public abstract class AccessLoading {

    /**
     * 26.2 replaced LevelLoadingScreen#render with #extractRenderState, and the progress now comes
     * from LevelLoadTracker#serverProgress as a 0..1 fraction rather than a whole percentage.
     */
    @Inject(at = @At("TAIL"), method = "extractRenderState")
    public void init(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks, CallbackInfo info) {
        Minecraft mc = Minecraft.getInstance();
        Screen gui = mc.gui.screen();

        if (gui instanceof LevelLoadingScreen levelLoadingScreen) {
            Bartender.serve(mc, () -> buildMainMenuBar(mc, "World Loading: " + Mth.floor(levelLoadingScreen.loadTracker.serverProgress() * 100.0F) + "%"));
        }

    }

}
