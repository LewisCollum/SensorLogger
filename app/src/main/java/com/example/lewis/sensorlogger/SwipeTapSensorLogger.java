package com.example.lewis.sensorlogger;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

public final class SwipeTapSensorLogger extends SensorLogger {
    private SensorLogger[] sensorsLoggersToActivate;
    private TouchListenerHelper touchListenerHelper;
    private static String TAG = "TouchSensorLogger";

    SwipeTapSensorLogger(Context context, SensorLogManager sensorLogManager) {
        super(sensorLogManager);
        touchListenerHelper = new SwipeTapListenerHelper(context);
    }

    public void setSensorLoggersToActivateOnEvent (SensorLogger[] sensorLoggersToActivate) {
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

    public void onTouchEvent(MotionEvent event){
        if (sensorsLoggersToActivate != null) {
            for (SensorLogger sensorLogger : sensorsLoggersToActivate) {
                sensorLogger.start();
            }
        }
        touchListenerHelper.onTouchEvent(event);
    }

    private class SwipeTapListenerHelper extends TouchListenerHelper {

        private SwipeTapListenerHelper(Context context) {
            super(context);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.v(TAG, String.valueOf(e2.getPressure()));
            if (distanceY > 0) {
                Log.v(TAG, "Swiped Up");
            }
            else {
                Log.v(TAG, "Swiped Down");
            }
            return false;
        }
    }
}
