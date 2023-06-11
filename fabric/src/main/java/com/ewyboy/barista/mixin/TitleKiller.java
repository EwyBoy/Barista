package com.ewyboy.barista.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class TitleKiller {

    @Inject(at = @At("HEAD"), method = "updateTitle()V", cancellable = true)
    private void updateTitle(final CallbackInfo callbackInfo) {
        callbackInfo.cancel();
    }

}