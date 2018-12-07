package com.example.lewis.sensorlogger;

public class TapSensorTable extends SensorTable {
    public static String name = "Tap";
    public static SQLColumn[] columns = {
            new SQLColumn(timeColumnName, "integer"),
            new SQLColumn("tap", "text")};
}
