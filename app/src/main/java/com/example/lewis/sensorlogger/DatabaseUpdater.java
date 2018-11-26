package com.example.lewis.sensorlogger;

import android.os.Handler;
import android.util.Log;

import java.util.Vector;

public class DatabaseUpdater implements Runnable {
    private Handler handler;
    private int updateMillis;
    private Vector<Record> records;

    DatabaseUpdater(int updateMillis) {
        records = new Vector<>();
        handler = new Handler();
        this.updateMillis = updateMillis;
        handler.post(this);
    }

    @Override
    public void run() {
        for (Record record: records) {
            Log.v("Record", "new");
            for (Vector set: record.getCollectedData()) {
                if (!set.isEmpty())
                    Log.v("", String.valueOf(set.elementAt(0)));
            }
            record.clear();
        }
        handler.postDelayed(this, updateMillis);
    }

    void addRecord(Record record) {
        records.addElement(record);
    }
}
