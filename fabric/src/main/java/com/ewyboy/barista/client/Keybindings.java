package com.ewyboy.barista.client;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.json.JsonHandler;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class Keybindings {

    private static KeyMapping reload;

    public static void setup() {
        if (FabricLoader.getInstance().isModLoaded("fabric")) {
            initKeyBinding();
            clickEvent();
        }
    }

    private static void initKeyBinding() {
        reload = new KeyMapping("barista.key.reload", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F12, Barista.MOD_NAME);
        KeyBindingHelper.registerKeyBinding(reload);
    }

    private static void clickEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (reload.consumeClick()) {
                JsonHandler.reload();
            }
        });
    }
}
