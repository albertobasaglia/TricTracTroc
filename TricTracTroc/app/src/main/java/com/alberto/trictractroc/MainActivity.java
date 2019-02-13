package com.alberto.trictractroc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int wins;
    private TextView vittorie;
    private static final int WINS = 0;


    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("wins",this.wins);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        this.wins = sharedPref.getInt("wins",0);


        this.vittorie = findViewById(R.id.vittorie);
        setWins();
    }

    public void playCpu(View view) {
        Intent intent = new Intent(this,Cpu.class);
        startActivityForResult(intent,WINS);
    }

    public void playMulti(View view) {
        Intent intent = new Intent(this,MenuMultiActivity.class);
        startActivityForResult(intent,WINS);
    }
    public void setWins() {
        this.vittorie.setText(String.format("Hai vinto %d partite",this.wins));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case WINS: {

                this.wins+=resultCode;
                setWins();

                break;
            }
        }
    }
}
