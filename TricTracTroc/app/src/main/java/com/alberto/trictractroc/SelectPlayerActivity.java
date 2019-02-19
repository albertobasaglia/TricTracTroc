package com.alberto.trictractroc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SelectPlayerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Button playX = findViewById(R.id.playX);
        Button playO = findViewById(R.id.playO);
        playX.setOnClickListener((View v) -> {
            setResult(1);
            finish();
        });
        playO.setOnClickListener((View v) -> {
            setResult(0);
            finish();
        });
    }
}
