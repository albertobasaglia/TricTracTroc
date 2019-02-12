package com.alberto.trictractroc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuMultiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_multi);
    }
    public void join(View view) {
        Intent i = new Intent(this,JoinActivity.class);
        startActivity(i);

    }

    public void create(View view) {
        Intent i = new Intent(this,LobbyActivity.class);
        startActivity(i);
    }
}
