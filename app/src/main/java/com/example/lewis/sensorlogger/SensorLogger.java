package com.example.lewis.sensorlogger;

public abstract class SensorLogger {
    protected SensorLogManager sensorLogManager;

    protected SensorLogger(SensorLogManager sensorLogManager) {
        this.sensorLogManager = sensorLogManager;
    }

    public abstract void start();
    public abstract void stop();
}
