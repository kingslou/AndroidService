package com.example.jin.myapplication;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PollingService extends Service {

	public static final String ACTION = "com.ieasy360.pkboard.PollingService";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		new PollingThread().start();
	 
	}

	class PollingThread extends Thread {
		@Override
		public void run() {
			System.out.println("Polling...");
			Intent i=new Intent();
            i.setAction("update");
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PollingService.this.sendBroadcast(i);
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("Service:onDestroy");
	}

}
