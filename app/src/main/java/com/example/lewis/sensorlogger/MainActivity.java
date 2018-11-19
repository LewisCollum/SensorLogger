package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.hardware.Sensor;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;

public class MainActivity extends Activity {
    private SensorRecorder accelerometer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accelerometer = new SensorRecorder(this, Sensor.TYPE_LINEAR_ACCELERATION);
        //File root = Environment.getExternalStorageDirectory();
        //if(root.canWrite()){
        //    File dir = new File(root.getAbsolutePath() + "SensorLogger");
        //    dir.mkdirs();
        //}
    }

    @Override
    protected void onResume(){
        super.onResume();
        accelerometer = new SensorRecorder(this, Sensor.TYPE_LINEAR_ACCELERATION);
    }

    @Override
    protected void onPause(){
        super.onPause();
        accelerometer.stopRecording();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        accelerometer.stopRecording();
    }
}
