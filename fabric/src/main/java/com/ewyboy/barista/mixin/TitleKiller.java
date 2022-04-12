package com.ewyboy.barista.mixin;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public final class TitleKiller {
    @Inject(method = "updateTitle()V", at = @At("HEAD"), cancellable = true)
    private void updateTitle(final CallbackInfo callbackInfo) {
        callbackInfo.cancel();
    }

}