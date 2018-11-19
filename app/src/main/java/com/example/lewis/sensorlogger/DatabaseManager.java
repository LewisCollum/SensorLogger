package com.example.lewis.sensorlogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String databaseName = "SensorLogDatabase";
    private static final int databaseVersion = 1;
    private static final String tableAcceleration = "acceleration";
    private static final String time = "time";
    private static final String acceleration = "acceleration";

    public DatabaseManager(Context context){
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        String sqlCreate = "";
        database.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onCreate(database);
    }
}
