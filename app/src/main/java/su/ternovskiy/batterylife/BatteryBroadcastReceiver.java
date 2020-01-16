package su.ternovskiy.batterylife;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

import java.lang.ref.WeakReference;

public class BatteryBroadcastReceiver extends BroadcastReceiver {

    private WeakReference<IBatteryListener> mListener;

    public BatteryBroadcastReceiver(IBatteryListener listener) {
        mListener = new WeakReference<>(listener);
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("TEST", "Level: ");

        IBatteryListener listener = mListener.get();
        if (listener != null) {

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryLevel = level / (float) scale;

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
            Log.d("TEST", "Level: " + batteryLevel);
            listener.onBatteryChanged(batteryLevel);
        }
    }


}
