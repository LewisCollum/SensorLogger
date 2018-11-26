package com.example.lewis.sensorlogger;

import java.util.Vector;

public abstract class Record {
    public String[] dataNames;

    Record(String[] dataNames) {
        this.dataNames = dataNames;
    }

    public abstract Vector<Vector> getCollectedData();
    public abstract void clear();
}
