package com.ewyboy.barista.mixin;

import com.ewyboy.barista.client.GameBar;
import com.ewyboy.barista.util.Bartender;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class AccessOverlay {

    /**
     * 26.2 replaced Gui#render with Gui#extractRenderState, which is still called once per frame.
     */
    @Inject(at = @At("TAIL"), method = "extractRenderState")
    public void init(DeltaTracker deltaTracker, boolean shouldRenderLevel, boolean resourcesLoaded, CallbackInfo ci) {
        Minecraft mc = Minecraft.getInstance();
        renderOverlay(mc);
    }

    public void renderOverlay(Minecraft mc) {
        if(mc.isPaused()) return;
        if(mc.gui.screen() != null) return;

        Bartender.serve(mc, () -> GameBar.buildBar(mc));
    }

}
