package com.example.lewis.sensorlogger;

public class AccelerationSample extends SensorSample {
    AccelerationSample(float[] values, long time) {
        super(new String[]{
                String.valueOf(values[0]),
                String.valueOf(values[1]),
                String.valueOf(values[2]),
                String.valueOf(time)
        });
    }
}
