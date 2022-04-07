package com.ewyboy.barista.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.ewyboy.barista.client.Bar.buildMainMenuBar;

@Mixin(Screen.class)
public class AccessScreen {

    @Inject(at = @At("TAIL"), method = "render")
    public void init(PoseStack poseStack, int mouseX, int mouseY, float partialTicks, CallbackInfo info) {

        Minecraft mc = Minecraft.getInstance();
        Screen gui = mc.screen;

        if (!gui.getTitle().getString().isEmpty()) {
            mc.getWindow().setTitle(buildMainMenuBar(mc, gui.getTitle().getString()));
        }
    }

}
