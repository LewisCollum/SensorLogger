package com.example.lewis.sensorlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class AccelerationSensorLogger extends SensorLoggerHelper {
    private SensorLogManager sensorLogManager;

    AccelerationSensorLogger(Context context, SensorLogManager sensorLogManager) {
        super(context, Sensor.TYPE_LINEAR_ACCELERATION);
        this.sensorLogManager = sensorLogManager;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorReading) {
        float[] readings = sensorReading.values;
        long time = System.currentTimeMillis();

        AccelerationSample sample = new AccelerationSample(readings, time);
        sensorLogManager.insert(sample, AccelerationTable.name);
    }
}
