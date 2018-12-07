package com.example.lewis.sensorlogger;

public final class AccelerationSensorTable extends SensorTable{
    public static String name = "Acceleration";
    public static SQLColumn[] columns = {
            new SQLColumn(timeColumnName, "integer"),
            new SQLColumn("x", "real"),
            new SQLColumn("y", "real"),
            new SQLColumn("z", "real")};
}
