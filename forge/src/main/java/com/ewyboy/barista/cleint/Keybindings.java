package com.ewyboy.barista.cleint;

import com.ewyboy.barista.Barista;
import com.ewyboy.barista.json.JsonHandler;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.util.Locals;

public class Keybindings {

    private static KeyBinding reload;

    public static void setup() {
        initKeyBinding();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, Keybindings :: onKeyInput);
    }

    public static void initKeyBinding() {
        reload = new KeyBinding("barista.key.reload", KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_F12, Barista.NAME);
        ClientRegistry.registerKeyBinding(reload);
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if(reload.consumeClick()) JsonHandler.reload();
    }

}
