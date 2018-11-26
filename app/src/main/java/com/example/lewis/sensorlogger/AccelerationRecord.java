package com.example.lewis.sensorlogger;

import java.util.Vector;

public class AccelerationRecord extends Record {
    public static String[] dataNames = {"x", "y", "z", "times"};
    public Vector<Float> x;
    public Vector<Float> y;
    public Vector<Float> z;
    public Vector<Long> times;

    AccelerationRecord() {
        super(dataNames);
        x = new Vector<>();
        y = new Vector<>();
        z = new Vector<>();
        times = new Vector<>();
    }

    @Override
    public Vector<Vector> getCollectedData() {
        Vector<Vector> collectedData = new Vector<>();
        collectedData.addElement(x);
        collectedData.addElement(y);
        collectedData.addElement(z);
        collectedData.addElement(times);
        return collectedData;
    }

    public void clear() {
        x.clear();
        y.clear();
        z.clear();
        times.clear();
    }
}
