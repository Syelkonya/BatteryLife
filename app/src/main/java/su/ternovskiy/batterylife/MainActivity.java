package su.ternovskiy.batterylife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements IBatteryListener {

    private ImageView mBatteryLevel;
    private BatteryBroadcastReceiver mReceiver;
    private static final int CLIP_DRAWABLE_MAX_LEVEL = 10_000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mBatteryLevel = findViewById(R.id.image_battery);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setupBroadcastReceiver();
    }

    private void setupBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        mReceiver = new BatteryBroadcastReceiver(this);

        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        destroyBroadcastReceiver();
    }

    private void destroyBroadcastReceiver() {
        unregisterReceiver(mReceiver);
        mReceiver = null;
    }

    @Override
    public void onBatteryChanged(float batteryLevel) {

        mBatteryLevel.setImageLevel((int) (batteryLevel * CLIP_DRAWABLE_MAX_LEVEL));
    }
}
