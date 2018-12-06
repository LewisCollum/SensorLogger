package com.example.lewis.sensorlogger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SensorLogManager extends SQLiteOpenHelper {
    ArrayList<String> tableNames;

    public SensorLogManager(Context context){
        super(context, SensorLogInformation.name, null, SensorLogInformation.version);
        tableNames = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    public void createTable(String name, SQLColumn[] columnNames) {
        SQLiteDatabase db = this.getWritableDatabase();
        tableNames.add(name);
        onUpgrade(db, 1, 1);
        String tableCommand = SQLStringTableGenerator.generate(name, columnNames);
        db.execSQL(tableCommand);
    }

    public void insert(SensorSample sample, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQLStringTableInsertGenerator.generate(sample, tableName));
        db.close();
    }

    //TODO figure out better way to do this!!!
    public ArrayList<SensorSample> selectAllFromTable(String tableName, SQLColumn[] columnNames) {
        String sqlQuery = "select * from " + tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        int sensorSampleColumnSize = columnNames.length;
        ArrayList<SensorSample> sensorSamples = new ArrayList<>();

        while (cursor.moveToNext()) {
            SensorSample currentSample = new SensorSample(new String[sensorSampleColumnSize]);
            for (int columnIndex = 0; columnIndex < sensorSampleColumnSize; ++columnIndex) {
                int sqlColumnIndex = cursor.getColumnIndex(columnNames[columnIndex].name);
                currentSample.values[columnIndex] = cursor.getString(sqlColumnIndex);
            }
            sensorSamples.add(currentSample);
        }
        db.close();
        return sensorSamples;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String tableName : tableNames) {
            db.execSQL("drop table if exists " + tableName);
        }
        onCreate(db);
    }
}

