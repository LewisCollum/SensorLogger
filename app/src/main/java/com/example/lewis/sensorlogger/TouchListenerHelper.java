package com.example.lewis.sensorlogger;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public abstract class TouchListenerHelper extends ListenerHelper implements GestureDetector.OnGestureListener {
    private GestureDetectorCompat gestureDetector;

    public TouchListenerHelper(Context context) {
        super(context);
    }

    @Override
    public void start() {
        gestureDetector = new GestureDetectorCompat(context, this);
    }

    @Override
    public void stop() {
        gestureDetector = null;
    }

    public void onTouchEvent(MotionEvent event) {
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(event);
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }
}
