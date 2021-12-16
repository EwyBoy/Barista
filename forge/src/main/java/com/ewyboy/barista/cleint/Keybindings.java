package com.ewyboy.barista.cleint;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class Keybindings {

    private static KeyBinding key;

    public static void setup() {
        initKeyBinding();
        MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, Keybindings :: onKeyInput);
    }

    public static void initKeyBinding() {

    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.KeyInputEvent event) {

    }

}
