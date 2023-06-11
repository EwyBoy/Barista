package com.ewyboy.barista.mixin;

import com.ewyboy.barista.client.GameBar;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class AccessOverlay {

    int overlayFrame = 0;

    @Inject(at = @At("TAIL"), method = "render")
    public void init(GuiGraphics poseStack, float deltaTime, CallbackInfo info) {
        Minecraft mc = Minecraft.getInstance();
        renderOverlay(mc);
    }

    public void renderOverlay(Minecraft mc) {
        if(mc.isPaused()) return;
        if(mc.screen != null) return;

        overlayFrame++;

        if (overlayFrame % 10 == 0) {
            mc.getWindow().setTitle(GameBar.buildBar(mc));
            overlayFrame = 0;
        }
    }

}
