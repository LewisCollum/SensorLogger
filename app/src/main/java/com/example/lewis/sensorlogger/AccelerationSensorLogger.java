package com.example.lewis.sensorlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

public final class AccelerationSensorLogger extends SensorLogger {
    private static String TAG = AccelerationSensorLogger.class.getName();
    private SensorListenerHelper sensorListenerHelper;
    private boolean allowedToRecord;


    AccelerationSensorLogger(Context context, SensorLogManager sensorLogManager) {
        super(sensorLogManager);
        sensorListenerHelper = new AccelerationListenerHelper(context);
        sensorListenerHelper.start();
    }

    @Override
    public void start() {
        Log.v(TAG, "Started...");
        enableRecording();
    }

    @Override
    public void stop() {
        Log.v(TAG, "Stopped.");
        disableRecording();
    }

    public void startUp() {
        sensorListenerHelper.start();
    }

    public void shutDown() {
        sensorListenerHelper.stop();
    }

    private void enableRecording() {
        allowedToRecord = true;
    }

    private void disableRecording() {
        allowedToRecord = false;
    }

    private class AccelerationListenerHelper extends SensorListenerHelper {

        AccelerationListenerHelper(Context context) {
            super(context, Sensor.TYPE_LINEAR_ACCELERATION);
        }

        @Override
        public void onSensorChanged(SensorEvent sensorReading) {
            if (allowedToRecord) {
                long timeStamp = SensorLogTime.currentMillis(System.currentTimeMillis());
                AccelerationSensorSample sample = new AccelerationSensorSample(timeStamp, sensorReading.values);
                sensorLogManager.insert(sample, AccelerationSensorTable.name);
//                Log.v(TAG, "Inserted");
            }
        }
    }
}
