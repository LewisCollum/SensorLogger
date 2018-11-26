package com.example.lewis.sensorlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public abstract class SensorRecorderHelper implements SensorEventListener  {
    private SensorManager sensorManager;
    private Sensor sensor;

    SensorRecorderHelper(Context context, int sensorType) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (hasSensor(sensorType))
            setSensor(sensorType);
    }

    public void start() {
        int samplingPeriodUs = SensorManager.SENSOR_DELAY_NORMAL;
        sensorManager.registerListener(this, sensor, samplingPeriodUs);
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    private boolean hasSensor(int sensorType) {
        return sensorManager.getDefaultSensor(sensorType) != null;
    }

    private void setSensor(int sensorType) {
        sensor = sensorManager.getDefaultSensor(sensorType);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorReading) {
        record(sensorReading);
    }

    abstract void record(SensorEvent sensorReading);

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
