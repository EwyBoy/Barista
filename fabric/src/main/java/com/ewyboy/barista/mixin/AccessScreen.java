package com.ewyboy.barista.mixin;

import com.ewyboy.barista.util.Bartender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

import static com.ewyboy.barista.client.GameBar.buildMainMenuBar;

@Mixin(Screen.class)
public abstract class AccessScreen {

    @Inject(at = @At("TAIL"), method = "render")
    public void init(GuiGraphics poseStack, int mouseX, int mouseY, float partialTicks, CallbackInfo info) {
        Minecraft mc = Minecraft.getInstance();
        Screen screen = mc.screen;

        if (!Objects.requireNonNull(screen).getTitle().getString().isEmpty()) {
            Bartender.serve(mc, () -> buildMainMenuBar(mc, screen.getTitle().getString()));
        }
    }
}
