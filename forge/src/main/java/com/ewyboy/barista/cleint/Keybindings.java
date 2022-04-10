package com.ewyboy.barista.cleint;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.json.JsonHandler;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

public class Keybindings {

    private static KeyMapping reload;

    public static void setup() {
        initKeyBinding();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, Keybindings :: onKeyInput);
    }

    public static void initKeyBinding() {
        reload = new KeyMapping("barista.key.reload", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F12, Barista.NAME);
        ClientRegistry.registerKeyBinding(reload);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if(reload.consumeClick()) JsonHandler.reload();
    }

}
