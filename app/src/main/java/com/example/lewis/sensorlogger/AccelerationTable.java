package com.example.lewis.sensorlogger;

public final class AccelerationTable extends SensorTable {
    public static String name = "Acceleration";
    public static SQLColumn[] columns = {
            new SQLColumn("x", "real"),
            new SQLColumn("y", "real"),
            new SQLColumn("z", "real"),
            new SQLColumn("times", "integer")};

    private AccelerationTable() {
        super(name, columns);
    }
}
