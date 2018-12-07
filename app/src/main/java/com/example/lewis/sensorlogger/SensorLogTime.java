package com.example.lewis.sensorlogger;

public final class SensorLogTime {

    public static void setStartMillis(long startMillis) {
        SensorLogInformation.startTime = startMillis;
    }

    public static long currentMillis(long systemMillis) {
        return systemMillis - SensorLogInformation.startTime;
    }
}
