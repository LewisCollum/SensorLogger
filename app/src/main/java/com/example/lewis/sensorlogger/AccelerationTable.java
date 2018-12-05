package com.example.lewis.sensorlogger;

public class AccelerationTable extends SensorTable {
    public static String name = "Acceleration";
    public static SQLColumn[] columns = {
            new SQLColumn("x", "real"),
            new SQLColumn("y", "real"),
            new SQLColumn("z", "real"),
            new SQLColumn("times", "integer")};

    public AccelerationTable() {
        super(name, columns);
    }
}
