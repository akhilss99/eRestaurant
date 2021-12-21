package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.onesignal.OneSignal;

public class WelcomeActivity extends AppCompatActivity {

    private static final String ONESIGNAL_APP_ID = "5294ea36-a0a3-4bd9-86e8-c24f243ef719";
    @SuppressLint("StaticFieldLeak")
    public static Activity mactivity;
    Boolean isBound = false;
    HelperService helperService;
    Button about;
    boolean isBackButtonPressed = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedID = item.getItemId();
        if(selectedID==R.id.contactdeveloper){
            String url = "https://www.linkedin.com/in/akdataenthusiast/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
        else if(selectedID==R.id.exit){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        mactivity = WelcomeActivity.this;

        ImageButton vegbtn = (ImageButton) findViewById(R.id.veg);
        ImageButton nonvegbtn = (ImageButton) findViewById(R.id.nonveg);
        Button dashboard = (Button) findViewById(R.id.dashboard);

        vegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("akhil", "welcome ui");
                Intent intent = new Intent(getApplicationContext(), FoodSelection.class);
                intent.putExtra("foodtype", "veg");
                startActivity(intent);
            }
        });

        nonvegbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FoodSelection.class);
                intent.putExtra("foodtype", "nonveg");
                startActivity(intent);
            }
        });

        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Form.class);
                intent.putExtra("isDash",true);
                startActivity(intent);
            }
        });

        startService(new Intent(this, HelperService.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(mServiceConnection);
            isBound = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, HelperService.class);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            HelperService.myBinder myBinder = (HelperService.myBinder) iBinder;
            helperService = myBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}