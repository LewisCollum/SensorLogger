package com.example.lewis.sensorlogger;

public final class AccelerationSensorTable extends SensorTable{
    public static String name = "Acceleration";
    public static SQLColumn[] columns = {
            new SQLColumn(timeColumnName, "integer"),
            new SQLColumn("xAcc", "real"),
            new SQLColumn("yAcc", "real"),
            new SQLColumn("zAcc", "real")};
}
