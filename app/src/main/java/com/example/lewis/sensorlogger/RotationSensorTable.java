package com.example.lewis.sensorlogger;

public final class RotationSensorTable extends SensorTable{
    public static String name = "RotationQuaternions";
    public static SQLColumn[] columns = {
            new SQLColumn(timeColumnName, "integer"),
            new SQLColumn("xRot", "real"),
            new SQLColumn("yRot", "real"),
            new SQLColumn("zRot", "real"),
            new SQLColumn("wScalar", "real")};
}
