package com.example.lewis.sensorlogger;

public class SwipeSensorSample extends SensorSample {
    SwipeSensorSample(long timeStamp, String direction) {
        super(timeStamp, new String[]{direction});
    }
}
