package com.example.lewis.sensorlogger;

public class AccelerationSensorSample extends SensorSample {

    AccelerationSensorSample(long timeStamp, float[] values) {
        super(
                timeStamp,
                new String[]{
                        String.valueOf(values[0]),
                        String.valueOf(values[1]),
                        String.valueOf(values[2])
                });
    }
}
