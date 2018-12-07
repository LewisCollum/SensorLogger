package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private static String TAG = MainActivity.class.getName();
    private SensorLogManager sensorLogManager;
    private AccelerationSensorLogger accelerationSensorLogger;
    private SwipeTapSensorLogger swipeTapSensorLogger;
    private EditText[] editTexts;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.deleteDatabase(SensorLogInformation.name);
        sensorLogManager = new SensorLogManager(this);
        sensorLogManager.createTable(AccelerationSensorTable.name, AccelerationSensorTable.columns);
        sensorLogManager.createTable(SwipeSensorTable.name, SwipeSensorTable.columns);
        sensorLogManager.createTable(TapSensorTable.name, TapSensorTable.columns);

        accelerationSensorLogger = new AccelerationSensorLogger(this, sensorLogManager);
        swipeTapSensorLogger = new SwipeTapSensorLogger(this, sensorLogManager);

        accelerationSensorLogger.startUp();
        swipeTapSensorLogger.setSensorLoggersToActivateOnTouch( new SensorLogger[] {
                accelerationSensorLogger
        });

        swipeTapSensorLogger.start();

        editTexts = new EditText[] {
                findViewById(R.id.firstNameEdit),
                findViewById(R.id.lastNameEdit),
                findViewById(R.id.emailEdit),
                findViewById(R.id.passwordEdit),
                findViewById(R.id.birthdayEdit)
        };

        findViewById(R.id.submitButton).setOnClickListener(new SubmitButton());
        findViewById(R.id.restartButton).setOnClickListener(new RestartButton());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipeTapSensorLogger.onSwipeTapEvent(event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onResume(){
        super.onResume();
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

    private class

    private class SubmitButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            accelerationSensorLogger.shutDown();
            Intent formCompletedIntent = new Intent(MainActivity.this, FormCompletedActivity.class);
            MainActivity.this.startActivity(formCompletedIntent);
        }
    }

    private class RestartButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            for (EditText editText : editTexts) {
                editText.getText().clear();
            }
        }
    }
}
