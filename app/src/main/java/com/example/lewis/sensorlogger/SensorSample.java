package com.example.lewis.sensorlogger;

public class SensorSample {
    public String[] values;
    public long timeStamp;

    SensorSample(long timeStamp, String[] values) {
        this.values = values;
        this.timeStamp = timeStamp;
    }

}
