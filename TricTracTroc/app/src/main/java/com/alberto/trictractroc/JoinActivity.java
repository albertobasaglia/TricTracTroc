package com.alberto.trictractroc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.alberto.trictractroc.multi.JoinRunnable;

public class JoinActivity extends AppCompatActivity {
    public static final int CONNESSO = 1;
    private Thread runningSocket;
    private EditText ip;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        this.ip = findViewById(R.id.inputip);
    }

    public void connect(View view) {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what) {
                    case CONNESSO: {
                        startActivity(new Intent(JoinActivity.this,Online.class));
                        break;
                    }
                }
            }
        };

        String textIp = ip.getText().toString();

        JoinRunnable run = new JoinRunnable(handler,textIp);
        runningSocket = new Thread(run);
        runningSocket.start();
    }
}
