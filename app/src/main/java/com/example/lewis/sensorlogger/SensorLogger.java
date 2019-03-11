package com.example.lewis.sensorlogger;

public abstract class SensorLogger {
    protected SensorTCPManager sensorTCPManager;

    protected SensorLogger(SensorTCPManager sensorTCPManager) {
        this.sensorTCPManager = sensorTCPManager;
    }

    public abstract void start();
    public abstract void stop();
}
