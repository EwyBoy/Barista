package com.ewyboy.barista.util;

public class Translation {

    public static final class Display {
        public static final String FPS = "fps.display.fps";
        public static final String PING = "fps.display.ping";
        public static final String MEMORY = "fps.display.memory";
    }

    public static final class Key {
        public static final String ALL = "fps.key.toggle_all";
    }

    public static final class Settings {

        public static final class Display {
            public static final String GAME_WINDOW_INFO = "fps.settings.display.game_window_info";
        }

        public static final class Components {

            public static final class Fps {
                public static final String TOGGLE_FPS = "fps.settings.components.fps.toggle_fps";
                public static final String COLOR = "fps.settings.components.fps.color";
            }
            public static final class Ping {
                public static final String TOGGLE_PING = "fps.settings.components.ping.toggle_ping";
                public static final String COLOR = "fps.settings.components.ping.color";
            }
            public static final class Memory {
                public static final String TOGGLE_MEMORY = "fps.settings.components.memory.toggle_memory";
                public static final String COLOR = "fps.settings.components.memory.color";
            }
        }
    }

}
