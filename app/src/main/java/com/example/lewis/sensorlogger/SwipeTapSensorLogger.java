package com.example.lewis.sensorlogger;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

public final class SwipeTapSensorLogger extends SensorLogger {
    private static String TAG = SwipeTapSensorLogger.class.getName();
    private SensorLogger[] sensorsLoggersToActivate;
    private TouchListenerHelper touchListenerHelper;

    SwipeTapSensorLogger(Context context, SensorLogManager sensorLogManager) {
        super(sensorLogManager);
        touchListenerHelper = new SwipeTapListenerHelper(context);
    }

    public void setSensorLoggersToActivateOnTouch (SensorLogger[] sensorLoggersToActivate) {
        this.sensorsLoggersToActivate = sensorLoggersToActivate;
    }

    @Override
    public void start() {
        touchListenerHelper.start();
    }

    @Override
    public void stop() {
        touchListenerHelper.stop();
    }

    public void onSwipeTapEvent(MotionEvent event) {
        touchListenerHelper.onTouchEvent(event);
    }

    private void activateSensorLoggersIfPresent() {
        if (sensorsLoggersToActivate != null) {
            activateSensorLoggers();
        }
    }

    private void activateSensorLoggers() {
        for (SensorLogger sensorLogger : sensorsLoggersToActivate) {
            sensorLogger.start();
        }
    }

    private void deactivateSensorLoggersIfPresent() {
        if (sensorsLoggersToActivate != null) {
            deactivateSensorLoggers();
        }
    }

    private void deactivateSensorLoggers() {
        for (SensorLogger sensorLogger : sensorsLoggersToActivate) {
            sensorLogger.stop();
        }
    }


    private class SwipeTapListenerHelper extends TouchListenerHelper {

        private SwipeTapListenerHelper(Context context) {
            super(context);
        }

        @Override
        public boolean onUp(MotionEvent e) {
            Log.v(TAG, "ON UP ----");
            deactivateSensorLoggersIfPresent();

            long timeStamp = SensorLogTime.currentMillis(System.currentTimeMillis());
            TapSensorSample sample = new TapSensorSample(timeStamp, "\"Finger up\"");
            sensorLogManager.insert(sample, TapSensorTable.name);

            return super.onUp(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.v(TAG, "ON DOWN ----");
            activateSensorLoggersIfPresent();

            long timeStamp = SensorLogTime.currentMillis(System.currentTimeMillis());
            TapSensorSample sample = new TapSensorSample(timeStamp, "\"Finger down\"");
            sensorLogManager.insert(sample, TapSensorTable.name);

            return super.onDown(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            String swipeDirection;
            if (distanceY > 0) {
                Log.v(TAG, "Swiped Up");
                swipeDirection = "\"Swiping UP\"";
            }
            else {
                Log.v(TAG, "Swiped Down");
                swipeDirection = "\"Swiping DOWN\"";
            }

            long timeStamp = SensorLogTime.currentMillis(System.currentTimeMillis());
            SwipeSensorSample sample = new SwipeSensorSample(timeStamp, swipeDirection);
            sensorLogManager.insert(sample, SwipeSensorTable.name);

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
}
