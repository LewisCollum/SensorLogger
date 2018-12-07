package com.example.lewis.sensorlogger;

public class SensorSample {
    public String[] values;
    public long timeStamp;

    SensorSample(long timeStamp, String[] values) {
        this.values = values;
        this.timeStamp = timeStamp;
    }

    public String[] getAll() {
        int newStringArrayLength = values.length + 1;
        String[] all = new String[newStringArrayLength];
        all[0] = String.valueOf(timeStamp);
        System.arraycopy(values, 0, all, 1, values.length);
        return all;
    }
}
