package com.example.jin.myapplication;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by jin on 2015.06.19.
 */
public class MyBorodCast extends BroadcastReceiver {
    private Context context;
    public MyBorodCast(Context context){
        this.context = context;
    }

    private boolean isServiceAlive(){
        boolean result = false;
        ActivityManager manager = (ActivityManager)context.getSystemService(context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo info : manager.getRunningServices(Integer.MAX_VALUE)){
            if(info.service.getClassName().equals("com.example.jin.myapplication.PollingService")){
                result = true;
            }
        }
        return result;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_TIME_TICK)){
            if(!isServiceAlive()){
                Toast.makeText(context,"死掉了",Toast.LENGTH_SHORT).show();
                PollingUtils.startPollingService(context,5, PollingService.class,
                        PollingService.ACTION);
            }else{
                Toast.makeText(context,"一直活着",Toast.LENGTH_SHORT).show();
                PollingUtils.startPollingService(context, 5, PollingService.class,
                        PollingService.ACTION);
            }
        }else{
            Toast.makeText(context,"木有找到",Toast.LENGTH_SHORT).show();
        }
    }
}
