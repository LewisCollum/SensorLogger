package com.example.lewis.sensorlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.util.Log;

public class AccelerationSensorRecorder extends SensorRecorderHelper {
    private AccelerationRecord record;

    AccelerationSensorRecorder(Context context, AccelerationRecord publicRecord) {
        super(context, Sensor.TYPE_LINEAR_ACCELERATION);
        record = publicRecord;
    }

    @Override
    void record(SensorEvent sensorReading) {
        record.x.addElement(sensorReading.values[0]);
        record.y.addElement(sensorReading.values[1]);
        record.z.addElement(sensorReading.values[2]);
        record.times.addElement(System.currentTimeMillis());
    }
}
