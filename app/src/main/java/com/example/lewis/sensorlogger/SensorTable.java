package com.example.lewis.sensorlogger;

public class SensorTable {
    public String name;
    public SQLColumn[] columns;

    public SensorTable(String name, SQLColumn[] columns){
        this.name = name;
        this.columns = columns;
    }
}
