package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private AccelerationSensorRecorder accelerometer;
    private AccelerationRecord accelerationBufferForDatabase;
    private DatabaseUpdater databaseUpdater;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerationBufferForDatabase = new AccelerationRecord();
        databaseUpdater = new DatabaseUpdater(1000);
        databaseUpdater.addRecord(accelerationBufferForDatabase);
        accelerometer = new AccelerationSensorRecorder(this, accelerationBufferForDatabase);
        accelerometer.start();
    }

    @Override
    protected void onResume(){
        super.onResume();
        accelerometer = new AccelerationSensorRecorder(this, accelerationBufferForDatabase);
    }

    @Override
    protected void onPause(){
        super.onPause();
        accelerometer.stop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        accelerometer.stop();
    }
}
