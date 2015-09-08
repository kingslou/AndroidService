package com.example.jin.myapplication;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;

/**
 * Created by jin on 2015.06.19.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
        MyBorodCast breceiver = new MyBorodCast(MyApplication.this);
        registerReceiver(breceiver, filter);
        Toast.makeText(MyApplication.this, "开始执行", Toast.LENGTH_SHORT).show();
    }
}
