package com.ewyboy.barista.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.ewyboy.barista.client.GameBar.buildMainMenuBar;

@Mixin(LevelLoadingScreen.class)
public class AccessLoading {
    @Inject(at = @At("TAIL"), method = "render")
    public void init(PoseStack poseStack, int mouseX, int mouseY, float partialTicks, CallbackInfo info) {
        Minecraft mc = Minecraft.getInstance();
        Screen gui = mc.screen;

        if (gui instanceof LevelLoadingScreen levelLoadingScreen) {
            mc.getWindow().setTitle(buildMainMenuBar(mc, "World Loading: " + levelLoadingScreen.progressListener.getProgress() + "%"));
        }

    }

}
