package com.example.lewis.sensorlogger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SensorLogManager extends SQLiteOpenHelper {

    public SensorLogManager(Context context){
        super(context, SensorLogInformation.name, null, SensorLogInformation.version);
        SensorLogTime.setStartMillis(System.currentTimeMillis());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    public void createTable(String name, SQLColumn[] columnNames) {
        addToTotalColumns(columnNames);
        updateMostColumnsInTable(columnNames);
        SQLiteDatabase db = this.getWritableDatabase();
        String tableCommand = SQLStringTableGenerator.generate(name, columnNames);
        db.execSQL(tableCommand);
    }

    //TODO extract class
    private void addToTotalColumns(SQLColumn[] columns) {
        SensorLogInformation.totalColumns += columns.length;
    }

    //TODO extract class
    private void updateMostColumnsInTable(SQLColumn[] columns) {
        if (columns.length > SensorLogInformation.mostColumnsInTable) {
            SensorLogInformation.mostColumnsInTable = columns.length;
        }
    }

    public void insert(SensorSample sample, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQLStringTableInsertGenerator.generate(sample, tableName));
        ++SensorLogInformation.totalRows;
        db.close();
    }

    //TODO figure out better way to do this!!!
    public ArrayList<SensorSample> selectAllFromTable(String tableName, SQLColumn[] columnNames) {
        String sqlQuery = "select * from " + tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        int columnOffsetDueToTimeStamp = 1;
        int columnSize = columnNames.length;
        int columnSizeWithoutTimeStamp = columnSize - columnOffsetDueToTimeStamp;
        ArrayList<SensorSample> sensorSamples = new ArrayList<>();

        while (cursor.moveToNext()) {
            int sqlIDIndex = cursor.getColumnIndex(SensorTable.timeColumnName);
            long timeStamp = Long.parseLong(cursor.getString(sqlIDIndex));
            SensorSample currentSample = new SensorSample(timeStamp, new String[columnSizeWithoutTimeStamp]);

            for (int columnIndex = columnOffsetDueToTimeStamp; columnIndex < columnSize; ++columnIndex) {
                int sqlColumnIndex = cursor.getColumnIndex(columnNames[columnIndex].name);
                currentSample.values[columnIndex - columnOffsetDueToTimeStamp] = cursor.getString(sqlColumnIndex);
            }
            sensorSamples.add(currentSample);
        }
        db.close();
        return sensorSamples;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

