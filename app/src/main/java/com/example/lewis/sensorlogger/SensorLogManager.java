package com.example.lewis.sensorlogger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SensorLogManager extends SQLiteOpenHelper {
    private SensorTable[] sensorTables;

    public SensorLogManager(Context context, SensorTable[] sensorTables){
        super(context, SensorLogInformation.name, null, SensorLogInformation.version);
        this.sensorTables = sensorTables;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
        String[] tableCommands = SQLStringTablesGenerator.generate(sensorTables);
        for (String tableCommand : tableCommands) {
            db.execSQL(tableCommand);
        }
    }

    public void insert(SensorSample sample, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQLStringTableInsertGenerator.generate(sample, tableName));
        db.close();
    }

    //TODO figure out better way to do this!!!
    public ArrayList<SensorSample> selectAllFromTable(SensorTable sensorTable) {
        String sqlQuery = "select * from " + sensorTable.name;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        int sensorSampleColumnSize = sensorTable.columns.length;
        ArrayList<SensorSample> sensorSamples = new ArrayList<>();

        while (cursor.moveToNext()) {
            SensorSample currentSample = new SensorSample(new String[sensorSampleColumnSize]);
            for (int columnIndex = 0; columnIndex < sensorSampleColumnSize; ++columnIndex) {
                currentSample.values[columnIndex] = sensorTable.columns[columnIndex].name;
            }
        }
        return sensorSamples;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

