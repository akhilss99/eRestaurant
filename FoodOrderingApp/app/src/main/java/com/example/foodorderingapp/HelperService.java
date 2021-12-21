package com.example.foodorderingapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class HelperService extends Service {
    public HelperService() {
    }

    Toast t;
    private Handler mHandler = new Handler();
    private Timer mtimer;
    IBinder mBinder = new myBinder();
    public static Context mContext;
    WelcomeActivity mact;
    WelcomeActivity act;


    public class myBinder extends Binder {
        HelperService getService(){
            return HelperService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        super.onUnbind(intent);
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public void schedule(){
        Log.e("click", "scheduled!");
        mtimer = new Timer();

        mtimer.schedule(new TimeDisplayTimerTask(), 5000);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mact = (WelcomeActivity) act.mactivity;
    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.e("logging inside", "Inside run()");
                    AlertDialog.Builder builder = new AlertDialog.Builder(mact);
                    builder.setMessage(R.string.servicemsg);
                    builder.setIcon(R.drawable.helpicon);
                    builder.setTitle(R.string.sericetitle);
                    builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            // TODO Auto-generated method stub
                        }
                    });
                    builder.show();
                }
            });
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        schedule();
        return mBinder;
    }

}