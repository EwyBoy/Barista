package com.ewyboy.barista.config;

import com.electronwill.nightconfig.core.Config;
import com.ewyboy.barista.util.Translation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class Settings {

    public static final ForgeConfigSpec SETTING_SPEC;
    public static final ClientSettings CLIENT_SETTINGS;

    static {
        Pair<ClientSettings, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(ClientSettings :: new);
        SETTING_SPEC = specPair.getRight();
        CLIENT_SETTINGS = specPair.getLeft();
    }

    public static class ClientSettings {

        private final ForgeConfigSpec.ConfigValue<Boolean> gameWindowInfo;

        public Boolean getGameWindowInfo() {
            return gameWindowInfo.get();
        }

        public static void toggle(ForgeConfigSpec.ConfigValue<Boolean> booleanConfigValue) {
            booleanConfigValue.set(booleanConfigValue.get() ? Boolean.FALSE : Boolean.TRUE);
        }

        ClientSettings(ForgeConfigSpec.Builder builder) {
            builder.comment("FPS Monitor - Settings File");

            builder.push("display");
                this.gameWindowInfo = builder
                    .comment("Displays Information on the Game-Window Bar")
                    .translation(Translation.Settings.Display.GAME_WINDOW_INFO)
                    .define("game_window_info", true
                );
            builder.pop();
        }
    }

    public static void setup() {
        Config.setInsertionOrderPreserved(true);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Settings.SETTING_SPEC);
    }

}
