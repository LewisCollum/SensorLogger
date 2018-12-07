package com.example.lewis.sensorlogger;

public final class SwipeSensorTable extends SensorTable{
    public static String name = "Swipe";
    public static SQLColumn[] columns = {
            new SQLColumn(timeColumnName, "integer"),
            new SQLColumn("direction", "text")};
}
