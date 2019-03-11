package com.example.lewis.sensorlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

public final class RotationSensorLogger extends SensorLogger {
    private static String TAG = RotationSensorLogger.class.getName();
    private SensorListenerHelper sensorListenerHelper;
    private boolean allowedToRecord;


    RotationSensorLogger(Context context, SensorTCPManager sensorTCPManager) {
        super(sensorTCPManager);
        sensorListenerHelper = new RotationSensorLogger.RotationListenerHelper(context);
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

    private class RotationListenerHelper extends SensorListenerHelper {

        RotationListenerHelper(Context context) {
            super(context, Sensor.TYPE_ROTATION_VECTOR);
        }

        @Override
        public void onSensorChanged(SensorEvent sensorReading) {
            if (allowedToRecord) {
                long timeStamp = SensorLogTime.currentMillis(System.currentTimeMillis());
                RotationSensorSample sample = new RotationSensorSample(timeStamp, sensorReading.values);
                sensorTCPManager.insert(sample, "rot");
            }
        }
    }
}
