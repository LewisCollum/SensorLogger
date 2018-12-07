package com.example.lewis.sensorlogger;

public class TapSensorSample extends SensorSample {
    TapSensorSample(long timeStamp, String action) {
        super(timeStamp, new String[]{action});
    }
}
