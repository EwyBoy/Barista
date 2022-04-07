package com.ewyboy.barista.mixin;

import com.ewyboy.barista.client.GameBar;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class AccessOverlay {

    @Inject(at = @At("TAIL"), method = "render")
    public void init(PoseStack poseStack, float deltaTime, CallbackInfo info) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.options.renderDebug) {
            return;
        }

        renderOverlay(mc);
    }

    public void renderOverlay(Minecraft mc) {
        if(mc.isPaused()) return;
        mc.getWindow().setTitle(GameBar.buildBar(mc));
    }




}
