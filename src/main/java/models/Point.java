package models;

public class Point {
    private float x;
    private float y;
    private boolean used;

    public Point(float x, float y){
        this.x = x;
        this.y = y;
        this.used = false;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed() {
        this.used = true;
    }


}
