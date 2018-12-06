package com.example.lewis.sensorlogger;

import android.content.Context;

public abstract class ListenerHelper {
    protected Context context;

    protected ListenerHelper(Context context) {
        this.context = context;
    }

    public abstract void start();
    public abstract void stop();
}
