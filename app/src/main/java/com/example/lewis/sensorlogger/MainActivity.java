package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private SensorLogManager sensorLogManager;
    private AccelerationSensorLogger accelerationSensorLogger;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorLogManager = new SensorLogManager(this);
        sensorLogManager.createTable(AccelerationTable.name, AccelerationTable.columns);
        accelerationSensorLogger = new AccelerationSensorLogger(this, sensorLogManager);
        accelerationSensorLogger.start();
    }

    @Override
    protected void onResume(){
        super.onResume();
        findViewById(R.id.submitButton).setOnClickListener(new SubmitButton());

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

    private class SubmitButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            accelerationSensorLogger.stop();
            ArrayList<SensorSample> databaseCopy = sensorLogManager.selectAllFromTable(
                    AccelerationTable.name,
                    AccelerationTable.columns);
            Log.v("SIZE", String.valueOf(databaseCopy.size()));
            SensorSample firstSample = databaseCopy.get(0);
            Log.v("FIRST VALUE", firstSample.values[0]);
        }
    }
}

//Intent formCompletedIntent = new Intent(MainActivity.this, FormCompletedActivity.class);
//formCompletedIntent.putExtra("DatabaseManager", sensorLogManager); //Optional parameters
//MainActivity.this.startActivity(formCompletedIntent);
