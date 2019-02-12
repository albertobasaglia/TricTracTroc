package com.alberto.trictractroc;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.widget.TextView;

import com.alberto.trictractroc.multi.ListenerRunnable;

public class LobbyActivity extends AppCompatActivity {
    private TextView status;
    public static final int ASCOLTO = 0;
    public static final int CONNESSO = 1;
    private Thread runningSocket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        ((TextView)findViewById(R.id.ip)).setText(ip);

        this.status = findViewById(R.id.status);

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what) {
                    case ASCOLTO: {
                        status.setText("Ascolto");
                        break;
                    }
                    case CONNESSO: {
                        status.setText("Connesso");
                        startActivity(new Intent(LobbyActivity.this,Online.class));
                        break;
                    }
                }
            }
        };
        ListenerRunnable run = new ListenerRunnable(handler);
        runningSocket = new Thread(run);
        runningSocket.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        runningSocket.interrupt();

    }
}
