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
        return duration.toString();
    }

    public String getSessionLength() {
        return duration((int) getElapsedTimeSeconds());
    }

}
