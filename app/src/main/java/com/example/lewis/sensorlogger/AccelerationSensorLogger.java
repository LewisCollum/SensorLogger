package com.example.lewis.sensorlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

public final class AccelerationSensorLogger extends SensorLogger {
    private SensorListenerHelper sensorListenerHelper;

    AccelerationSensorLogger(Context context, SensorLogManager sensorLogManager) {
        super(sensorLogManager);
        sensorListenerHelper = new AccelerationListenerHelper(context);
    }

    @Override
    public void start() {
        sensorListenerHelper.start();
    }

    @Override
    public void stop() {
        sensorListenerHelper.stop();
    }

    private class AccelerationListenerHelper extends SensorListenerHelper {
        AccelerationListenerHelper(Context context) {
            super(context, Sensor.TYPE_LINEAR_ACCELERATION);
        }

        @Override
        public void onSensorChanged(SensorEvent sensorReading) {
            float[] readings = sensorReading.values;
            long time = System.currentTimeMillis();

            AccelerationSample sample = new AccelerationSample(readings, time);
            sensorLogManager.insert(sample, AccelerationTable.name);
        }
    }
}
