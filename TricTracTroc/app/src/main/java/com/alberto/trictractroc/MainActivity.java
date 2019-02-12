package com.alberto.trictractroc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playCpu(View view) {
        Intent intent = new Intent(this,Cpu.class);
        startActivity(intent);
    }

    public void playMulti(View view) {
        Intent intent = new Intent(this,MenuMultiActivity.class);
        startActivity(intent);
    }
}
