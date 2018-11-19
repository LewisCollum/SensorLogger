package com.example.lewis.sensorlogger;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Vector;

public class SensorRecorder implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private Vector<float[]> samples;
    private Vector<Long> times;

    SensorRecorder(Context context, int sensorType) {
        samples = new Vector<>();
        times = new Vector<>();
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        if (hasSensor(sensorType))
            setSensor(sensorType);
        enableSensor();
    }

    private boolean hasSensor(int sensorType) {
        return sensorManager.getDefaultSensor(sensorType) != null;
    }

    private void setSensor(int sensorType) {
        sensor = sensorManager.getDefaultSensor(sensorType);
    }

    private void enableSensor() {
        int samplingPeriodUs = SensorManager.SENSOR_DELAY_NORMAL;
        sensorManager.registerListener(this, sensor, samplingPeriodUs);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorReading) {
        recordSample(sensorReading.values);
        recordTime();
    }

    private void recordSample(float[] sample) {
        samples.addElement(sample.clone());
    }

    private void recordTime() {
        times.addElement(System.currentTimeMillis());
    }

    public void stopRecording() {
        sensorManager.unregisterListener(this);
    }

    public Vector<float[]> getSamples() {
        return samples;
    }

    public Vector<Long> getTimes() {
        return times;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
