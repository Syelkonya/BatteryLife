package su.ternovskiy.batterylife;

public class BatteryInfo {

    private float level;
    private  boolean isCharging;

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void setCharging(boolean charging) {
        isCharging = charging;
    }
}
