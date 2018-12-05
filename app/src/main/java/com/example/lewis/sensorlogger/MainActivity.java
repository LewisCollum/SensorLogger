package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private SensorLogManager sensorLogManager;
    private AccelerationSensorLogger accelerationSensorLogger;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorLogManager = new SensorLogManager(this, SensorTableConfiguration.tables);
        accelerationSensorLogger = new AccelerationSensorLogger(this, sensorLogManager);
        accelerationSensorLogger.start();
    }

    @Override
    protected void onResume(){
        super.onResume();
        accelerationSensorLogger = new AccelerationSensorLogger(this, sensorLogManager);
    }

    @Override
    protected void onPause(){
        super.onPause();
        accelerationSensorLogger.stop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        accelerationSensorLogger.stop();
    }
}
