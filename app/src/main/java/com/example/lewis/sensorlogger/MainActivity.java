package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private static String TAG = MainActivity.class.getName();
    private SensorLogManager sensorLogManager;
    private AccelerationSensorLogger accelerationSensorLogger;
    private SwipeTapSensorLogger swipeTapSensorLogger;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.deleteDatabase(SensorLogInformation.name);
        sensorLogManager = new SensorLogManager(this);
        sensorLogManager.createTable(AccelerationSensorTable.name, AccelerationSensorTable.columns);
        sensorLogManager.createTable(SwipeSensorTable.name, SwipeSensorTable.columns);

        accelerationSensorLogger = new AccelerationSensorLogger(this, sensorLogManager);
        swipeTapSensorLogger = new SwipeTapSensorLogger(this, sensorLogManager);

        accelerationSensorLogger.startUp();
        swipeTapSensorLogger.setSensorLoggersToActivateOnTouch( new SensorLogger[] {
                accelerationSensorLogger
        });

        swipeTapSensorLogger.start();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipeTapSensorLogger.onSwipeTapEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onResume(){
        super.onResume();
        findViewById(R.id.submitButton).setOnClickListener(new SubmitButton());
    }

    @Override
    protected void onPause(){
        super.onPause();
        accelerationSensorLogger.shutDown();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        accelerationSensorLogger.shutDown();
    }

    private class SubmitButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            accelerationSensorLogger.shutDown();
            ArrayList<SensorSample> acceleration = sensorLogManager.selectAllFromTable(
                    AccelerationSensorTable.name,
                    AccelerationSensorTable.columns);
            Log.v(TAG, String.valueOf(acceleration.size()));

            ArrayList<SensorSample> swipe = sensorLogManager.selectAllFromTable(
                    SwipeSensorTable.name,
                    SwipeSensorTable.columns);
            Log.v(TAG, String.valueOf(swipe.size()));

            for (int i = 0; i < acceleration.size(); ++i) {
                SensorSample sample = acceleration.get(i);
                Log.v(TAG, sample.timeStamp + ": " + sample.values[0] + ", " + sample.values[1] + ", " + sample.values[2]);
            }

            for (int i = 0; i < swipe.size(); ++i) {
                SensorSample sample = swipe.get(i);
                Log.v(TAG, sample.timeStamp + ": " + sample.values[0]);
            }
        }
    }
}

//Intent formCompletedIntent = new Intent(MainActivity.this, FormCompletedActivity.class);
//formCompletedIntent.putExtra("DatabaseManager", sensorLogManager); //Optional parameters
//MainActivity.this.startActivity(formCompletedIntent);
