package com.example.lewis.sensorlogger;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends Activity {
    private static String TAG = MainActivity.class.getName();
    private SensorLogManager sensorLogManager;
    private AccelerationSensorLogger accelerationSensorLogger;
    private RotationSensorLogger rotationSensorLogger;
    private SwipeTapSensorLogger swipeTapSensorLogger;
    private EditText[] editTexts;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.deleteDatabase(SensorLogInformation.name);
        sensorLogManager = new SensorLogManager(this);
        sensorLogManager.createTable(AccelerationSensorTable.name, AccelerationSensorTable.columns);
        sensorLogManager.createTable(RotationSensorTable.name, RotationSensorTable.columns);
        sensorLogManager.createTable(SwipeSensorTable.name, SwipeSensorTable.columns);
        sensorLogManager.createTable(TapSensorTable.name, TapSensorTable.columns);

        accelerationSensorLogger = new AccelerationSensorLogger(this, sensorLogManager);
        rotationSensorLogger = new RotationSensorLogger(this, sensorLogManager);
        swipeTapSensorLogger = new SwipeTapSensorLogger(this, sensorLogManager);

        accelerationSensorLogger.startUp();
        rotationSensorLogger.startUp();

        swipeTapSensorLogger.setSensorLoggersToActivateOnTouch( new SensorLogger[] {
                accelerationSensorLogger,
                rotationSensorLogger
        });

        swipeTapSensorLogger.start();

        setupMockForm();
    }

    private void setupMockForm() {
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

    private class SubmitButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            accelerationSensorLogger.shutDown();
            rotationSensorLogger.shutDown();

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

    @Override
    protected void onResume(){
        super.onResume();
        accelerationSensorLogger.startUp();
        rotationSensorLogger.startUp();
    }

    @Override
    protected void onPause(){
        super.onPause();
        accelerationSensorLogger.shutDown();
        rotationSensorLogger.shutDown();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        accelerationSensorLogger.shutDown();
        rotationSensorLogger.shutDown();
    }
}
