package models;

public class Point {
    private float latitude;
    private float longitude;
    private boolean used;

    public Point(float latitude, float longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        this.used = false;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed() {
        this.used = true;
    }


}
