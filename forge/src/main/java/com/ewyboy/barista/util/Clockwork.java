package com.ewyboy.barista.util;

import java.time.Duration;

public class Clockwork {

    private long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public float getElapsedTimeSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000f;
    }

    public String duration(int sec) {
        Duration duration = Duration.ofSeconds(sec);
        StringBuilder builder = new StringBuilder();

        if (duration.toHours() != 0) {
            builder.append(duration.toHours()).append("hours ");
        }
        if (duration.toMinutes() != 0) {
            builder.append(duration.toMinutes()).append("5min ");
        }

        int clockSec = sec % 60;
        builder.append(clockSec).append("sec");

        return builder.toString();
    }

    public String getSessionLength() {
        return duration((int) getElapsedTimeSeconds());
    }

}
