package com.alberto.trictractroc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alberto.trictractroc.multi.JoinRunnable;

import java.util.HashSet;
import java.util.Set;

public class JoinActivity extends AppCompatActivity {
    public static final int CONNESSO = 1;
    private Thread runningSocket;
    private EditText ip;
    private LinearLayout ips;

    @Override
    protected void onStart() {
        super.onStart();
        Set<String> salvati = getPreferences(Context.MODE_PRIVATE).getStringSet("ips",new HashSet<>());
        ips.removeAllViews();
        for(String s: salvati) {
            View nuovo = getLayoutInflater().inflate(R.layout.ips_history,null);
            ((TextView)nuovo.findViewById(R.id.ipShow)).setText(s);
            nuovo.findViewById(R.id.buttonId).setOnClickListener((View v) -> {
                ip.setText(s);
            });
            ips.addView(nuovo);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        this.ip = findViewById(R.id.inputip);
        this.ips = findViewById(R.id.linearip);

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
        SharedPreferences sp = this.getPreferences(Context.MODE_PRIVATE);

        Set<String> historyIps = sp.getStringSet("ips", new HashSet<>());

        historyIps.add(textIp);

        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet("ips",historyIps);
        editor.commit();



        JoinRunnable run = new JoinRunnable(handler,textIp);
        runningSocket = new Thread(run);
        runningSocket.start();
    }
}
