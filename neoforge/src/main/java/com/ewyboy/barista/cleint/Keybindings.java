package com.ewyboy.barista.cleint;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.json.JsonHandler;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class Keybindings {

    private static KeyMapping reload;

    /**
     * 26.2 replaced the free form category string with a registered Category keyed by an Identifier,
     * whose label comes from the "key.category.<namespace>.<path>" translation.
     */
    private static final KeyMapping.Category CATEGORY = new KeyMapping.Category(
            Identifier.fromNamespaceAndPath(Barista.MOD_ID, Barista.MOD_ID)
    );

    public static void onRegisterKeyBinds(RegisterKeyMappingsEvent event) {
        event.registerCategory(CATEGORY);
        reload = new KeyMapping("barista.key.reload", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F12, CATEGORY);
        event.register(reload);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        if(reload.consumeClick()) JsonHandler.reload();
    }

}
