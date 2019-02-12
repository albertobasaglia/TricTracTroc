package com.alberto.trictractroc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alberto.trictractroc.multi.PlayRunnable;

public class Online extends AppCompatActivity {
    private static final int SET_CELL = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what) {
                    case SET_CELL: {
                        int pos = msg.arg1;
                        getButtonById(pos).setBackgroundResource(R.drawable.o);
                        break;
                    }
                }
            }
        };


        PlayRunnable play = new PlayRunnable(handler);
        new Thread(play).start();
    }
    public Button getButtonById(int idRes) {
        int id = 0;
        switch(idRes) {
            case 0: id = R.id.button0;break;
            case 1: id = R.id.button1;break;
            case 2: id = R.id.button2;break;
            case 3: id = R.id.button3;break;
            case 4: id = R.id.button4;break;
            case 5: id = R.id.button5;break;
            case 6: id = R.id.button6;break;
            case 7: id = R.id.button7;break;
            case 8: id = R.id.button8;break;
        }
        return findViewById(id);
    }

    public int getIdByView(View b) {
        int id = 0;
        switch(b.getId()) {
            case R.id.button0: id = 0;break;
            case R.id.button1: id = 1;break;
            case R.id.button2: id = 2;break;
            case R.id.button3: id = 3;break;
            case R.id.button4: id = 4;break;
            case R.id.button5: id = 5;break;
            case R.id.button6: id = 6;break;
            case R.id.button7: id = 7;break;
            case R.id.button8: id = 8;break;
        }
        return id;
    }

    public void cellClicked(View view) {

    }
}
