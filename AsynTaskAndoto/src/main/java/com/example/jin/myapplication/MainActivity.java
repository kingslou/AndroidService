package com.example.jin.myapplication;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.victor.loading.rotate.RotateLoading;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import de.greenrobot.event.EventBus;


public class MainActivity extends AppCompatActivity {
    /** Default {@link Executor} that be used to execute tasks in parallel. **/
    public static final Executor THREAD_POOL_EXECUTOR = Executors
            .newFixedThreadPool(SystemUtils.DEFAULT_THREAD_POOL_SIZE);
    private RotateLoading rotateLoading;
    private final int droidGreen = Color.parseColor("#40E0D0");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rotateLoading = (RotateLoading) findViewById(R.id.rotateloading);
        rotateLoading.setBackgroundColor(droidGreen);
        rotateLoading.start();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            new A().executeOnExecutor(THREAD_POOL_EXECUTOR);
        }else{
            new A().execute();
        }
    }

    class A extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            try{
                Thread.sleep(5000);
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MainActivity.this, "执行结束", Toast.LENGTH_SHORT).show();
            EventBus.getDefault().post(new MessageEvent("cancle"));
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    public void onEvent(MessageEvent event){
        Toast.makeText(MainActivity.this, event.Message, Toast.LENGTH_SHORT).show();
        if(event.Message.equals("cancle")){
            rotateLoading.stop();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
