package com.ewyboy.barista.cleint;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.json.JsonHandler;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class Keybindings {

    private static KeyMapping reload;

    public static void onRegisterKeyBinds(RegisterKeyMappingsEvent event) {
        reload = new KeyMapping("barista.key.reload", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F12, Barista.NAME);
        event.register(reload);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        if(reload.consumeClick()) JsonHandler.reload();
    }

}
