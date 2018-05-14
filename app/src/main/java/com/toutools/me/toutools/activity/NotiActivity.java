package com.toutools.me.toutools.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.authentication.Constants;
import com.google.firebase.messaging.FirebaseMessaging;
import com.toutools.me.toutools.R;

public class NotiActivity extends AppCompatActivity {
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noti);
        spinner = findViewById(R.id.spinnerTopics);

    }
}
