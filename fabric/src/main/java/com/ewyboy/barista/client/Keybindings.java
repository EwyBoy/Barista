package com.ewyboy.barista.client;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.json.JsonHandler;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class Keybindings {

    private static KeyMapping reload;

    /**
     * 26.2 replaced the free form category string with a Category keyed by an Identifier,
     * whose label comes from the "key.category.<namespace>.<path>" translation.
     */
    private static final KeyMapping.Category CATEGORY = new KeyMapping.Category(
            Identifier.fromNamespaceAndPath(Barista.MOD_ID, Barista.MOD_ID)
    );

    public static void setup() {
        if (FabricLoader.getInstance().isModLoaded("fabric")) {
            initKeyBinding();
            clickEvent();
        }
    }

    private static void initKeyBinding() {
        reload = new KeyMapping("barista.key.reload", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F12, CATEGORY);
        KeyMappingHelper.registerKeyMapping(reload);
    }

    private static void clickEvent() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (reload.consumeClick()) {
                JsonHandler.reload();
            }
        });
    }
}
