package com.ewyboy.barista.mixin;

import com.ewyboy.barista.module.ModuleFormatter;
import com.mojang.blaze3d.platform.IconSet;
import net.minecraft.server.packs.resources.IoSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;
import java.util.List;

@Mixin(IconSet.class)
public abstract class IconInjector {
    @Inject(at = @At("HEAD"), method = "getStandardIcons", cancellable = true)
    private void customIcons(CallbackInfoReturnable<List<IoSupplier<InputStream>>> callbackInfoReturnable) {
        callbackInfoReturnable.setReturnValue(ModuleFormatter.getAllPngResources());
    }

   /* @Inject(
            method = {"getMacIcon"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private void bringTheClassicCraftingTableIconBack_mac(CallbackInfoReturnable<class_7367<InputStream>> cir) {
        cir.setReturnValue(ClassMinecraftIconStorage.getResource("minecraft.icns"));
    }*/
}
