package com.ewyboy.barista.util;

import java.time.Duration;

import static com.ewyboy.barista.module.ModuleFormatter.formatTranslation;
import static com.ewyboy.barista.util.Translation.Bar.*;

public class Clockwork {

    private long startTime;
    private static final String space = " ";

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public float getElapsedTimeSeconds() {
        return (System.currentTimeMillis() - startTime) / 1000f;
    }

    public String duration(int sec) {
        StringBuilder builder = new StringBuilder();
        Duration duration = Duration.ofSeconds(sec);

        int clockSec = sec % 60;
        int clockMin = (int) duration.toMinutes();
        int clockHrs = (int) duration.toHours();

        if (clockHrs != 0) {
            builder.append(duration.toHours()).append(clockHrs > 1
                    ? formatTranslation(SESSION_HOURS)
                    : formatTranslation(SESSION_HOUR)
            );
            builder.append(space);
        }

        if (clockMin != 0) {
            builder.append(duration.toMinutes()).append(clockMin > 1
                    ? formatTranslation(SESSION_MINUTES)
                    : formatTranslation(SESSION_MINUTE)
            );
            builder.append(space);
        }

        if (clockSec != 0) {
            builder.append(clockSec).append(clockSec > 1
                    ? formatTranslation(SESSION_SECONDS)
                    : formatTranslation(SESSION_SECOND)
            );
        }

        return builder.toString();
    }


    public static long formatTimeOfCurrentDay(long time) {
        return time % 24000L;
    }

    public static long formatTotalTime(long time) {
        return time % 2147483647L;
    }

    public static long formatDays(long time) {
        return time / 24000L % 2147483647L;
    }

    public String getSessionLength() {
        return duration((int) getElapsedTimeSeconds());
    }

    private static boolean isTimeBetween(float time, int first, int second) {
        return time >= first && time < second;
    }

    public static String getTimeOfDay(long time) {

        // 0 to 24000
        // Time 6000 Noon
        // Time 18000 Midnight

        time = formatTimeOfCurrentDay(time);

        if (isTimeBetween(time, 500, 3500)) return formatTranslation(TIME_MORNING);
        else if (isTimeBetween(time, 3500, 5500)) return formatTranslation(TIME_LATE_MORNING);
        else if (isTimeBetween(time, 5500, 6500)) return formatTranslation(TIME_NOON);
        else if (isTimeBetween(time, 6500, 10000)) return formatTranslation(TIME_AFTERNOON);
        else if (isTimeBetween(time, 10000, 11000)) return formatTranslation(TIME_LATE_AFTERNOON);
        else if (isTimeBetween(time, 11000, 12500)) return formatTranslation(TIME_EARLY_EVENING);
        else if (isTimeBetween(time, 12500, 13500)) return formatTranslation(TIME_DUSK);
        else if (isTimeBetween(time, 13500, 14500)) return formatTranslation(TIME_EVENING);
        else if (isTimeBetween(time, 14500, 15500)) return formatTranslation(TIME_LATE_EVENING);
        else if (isTimeBetween(time, 15500, 17500)) return formatTranslation(TIME_NIGHT);
        else if (isTimeBetween(time, 17500, 18500)) return formatTranslation(TIME_MIDNIGHT);
        else if (isTimeBetween(time, 18500, 22000)) return formatTranslation(TIME_NIGHTTIME);
        else if (isTimeBetween(time, 20000, 23500)) return formatTranslation(TIME_EARLY_MORNING);
        else return formatTranslation(TIME_DAWN);
    }

}
