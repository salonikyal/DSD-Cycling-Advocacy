package com.skuzmic.gpstracker_sampleapp.entities;

import android.location.Location;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.skuzmic.gpstracker_sampleapp.utils.Utils;

import java.util.Date;

public class GnssData {

    @SerializedName("timeTS")
    private Date timestamp;
    @SerializedName("lat")
    private double lat;
    @SerializedName("lon")
    private double lon;
    @SerializedName("ele")
    private double ele;
    @SerializedName("speed")
    private float speed;
    @SerializedName("accuracy")
    private double accuracy;

    public GnssData(Location location) {
        this.timestamp = Utils.toDate(location.getTime());
        this.lat = location.getLatitude();
        this.lon = location.getLongitude();
        this.ele = location.getAltitude();
        this.speed = Utils.convertMpsToKph(location.getSpeed());
        this.accuracy = location.getAccuracy();
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @NonNull
    @Override
    public String toString() {
        return Utils.formatTimestamp(timestamp) + "\n" +
                "Lat: " + lat + "\n" +
                "Lon: " + lon + "\n" +
                "Ele: " + ele + "\n" +
                "Speed(km/h): " + speed + "\n" +
                "Acc: " + accuracy;
    }
}
