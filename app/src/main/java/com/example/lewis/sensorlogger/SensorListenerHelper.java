package com.example.lewis.sensorlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public abstract class SensorListenerHelper implements SensorEventListener  {
    private SensorManager sensorManager;
    private Sensor sensor;

    SensorListenerHelper(Context context, int sensorType) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (hasSensor(sensorType))
            setSensor(sensorType);
    }

    public void start() {
        int samplingPeriodUs = SensorManager.SENSOR_DELAY_GAME;
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
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
