package com.cycling_advocacy.bumpy.motion;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.cycling_advocacy.bumpy.utils.CsvMotionUtil;
import com.cycling_advocacy.bumpy.utils.GeneralUtil;

import java.io.FileWriter;
import java.io.IOException;

public class MotionManager implements SensorEventListener {

    private VibrationManager accumulatedVibrationsManager;

    private SensorManager sensorManager;
    private Sensor accelerometer, magnetometer, gyroscope;

    // Although we could check whether a sensor is present with <sensor> == null, this is a bit more readable
    private boolean hasAccelerometer = false;
    private boolean hasMagnetometer = false;
    private boolean hasGyroscope = false;

    private float[] accelerometerData = null;
    private float[] magnetometerData = null;
    private float[] gyroscopeData = null;

    private FileWriter fileWriter;

    private VibrationChangedListener listener;

    public MotionManager(Context context, VibrationChangedListener listener) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            hasAccelerometer = true;
        } else {
            Log.d("Motion data", "Device has no accelerometer.");
        }

        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetometer != null) {
            hasMagnetometer = true;
        } else {
            Log.d("Motion data", "Device has no magnetometer.");
        }

        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (gyroscope != null) {
            hasGyroscope = true;
        } else {
            Log.d("Motion data", "Device has no gyroscope.");
        }

        this.listener = listener;
    }

    public void startSensorUpdates(Context context, String tripUUID) {
        accumulatedVibrationsManager = new VibrationManager();

        try {
            fileWriter = CsvMotionUtil.initFileWriter(context, tripUUID);
            // TODO: Is there a good way to link this to motion data writing, to ensure that the order of sensors in the header is the same as the values
            CsvMotionUtil.writeHeader(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (hasAccelerometer) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);  // 50hz
        }
        if (hasMagnetometer) {
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
        }
        if (hasGyroscope) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    public void stopSensorUpdates() {
        accumulatedVibrationsManager = null;

        try {
            CsvMotionUtil.finish(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (hasAccelerometer) {
            sensorManager.unregisterListener(this, accelerometer);
        }
        if (hasMagnetometer) {
            sensorManager.unregisterListener(this, magnetometer);
        }
        if (hasGyroscope) {
            sensorManager.unregisterListener(this, gyroscope);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // sensors are working in background as well
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            accelerometerData = sensorEvent.values.clone();
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetometerData = sensorEvent.values.clone();
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscopeData = sensorEvent.values.clone();
        }

        if ((accelerometerData != null || !hasAccelerometer) && (magnetometerData != null || !hasMagnetometer) && (gyroscopeData != null || !hasGyroscope)) {
            String motionDataString = motionToString();
            try {
                CsvMotionUtil.writeLine(fileWriter, motionDataString);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // If the device has no accelerometer we can't calculate vibrations intensity
            if (hasAccelerometer) {
                accumulatedVibrationsManager.addAccelerometerMeasurement(accelerometerData);
                listener.onVibrationChanged((int) accumulatedVibrationsManager.getBumpPercentage());
            }

            // clean
            accelerometerData = null;
            magnetometerData = null;
            gyroscopeData = null;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private String motionToString() {
        StringBuilder motionDataString = new StringBuilder();
        motionDataString
                .append(GeneralUtil.formatTimestampISO(GeneralUtil.toDate(System.currentTimeMillis())))
                .append(",");

        if (hasAccelerometer) {
            motionDataString
                    .append(accelerometerData[0])
                    .append(",")
                    .append(accelerometerData[1])
                    .append(",")
                    .append(accelerometerData[2])
                    .append(",");
        } else {
            motionDataString.append(",,,");
        }

        if (hasMagnetometer) {
            motionDataString
                    .append(magnetometerData[0])
                    .append(",")
                    .append(magnetometerData[1])
                    .append(",")
                    .append(magnetometerData[2])
                    .append(",");
        } else {
            motionDataString.append(",,,");
        }

        if (hasGyroscope) {
            motionDataString
                    .append(gyroscopeData[0])
                    .append(",")
                    .append(gyroscopeData[1])
                    .append(",")
                    .append(gyroscopeData[2]);
        } else {
            motionDataString.append(",,");
        }

        return motionDataString.toString();
    }
}
