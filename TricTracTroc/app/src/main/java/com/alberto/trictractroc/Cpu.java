package com.alberto.trictractroc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alberto.trictractroc.tictactoe.CellNotEmptyException;
import com.alberto.trictractroc.tictactoe.CellNotInRangeException;
import com.alberto.trictractroc.tictactoe.Game;
/**
 *  Far partire un dialog per dire chi ha vinto *
 *  ritornare il vincitore e nel caso il player abbia vinto si aumenta il contatore nella main activity.
 * */

public class Cpu extends AppCompatActivity {
    private Game game;
    private Game.State current;
    private boolean ai;
    private int winCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.game = new Game();
        this.current = Game.State.X;
        this.ai = true;
        this.winCounter = 0;
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

    public void resetView() {
        for(int i=0 ; i<9 ; i++) {
            getButtonById(i).setBackgroundResource(R.drawable.empty);
        }
    }

    public void cellClicked(View view) {
        int id = getIdByView(view);
        try {
            this.game.makeMove(id,this.current);
            view.setBackgroundResource((this.current == Game.State.O )?R.drawable.o:R.drawable.x);
            this.current = (this.current == Game.State.O )?Game.State.X:Game.State.O;
            Game.State winner = this.game.checkWinner(id);
            if(winner != null) {
                this.winCounter++;
                endGame();
            }
            else {
                if(!ai) return;

                int pos = this.game.getAiMove(this.current);
                this.game.makeMove(pos,this.current);
                getButtonById(pos).setBackgroundResource((this.current == Game.State.O )?R.drawable.o:R.drawable.x);
                this.current = (this.current == Game.State.O )?Game.State.X:Game.State.O;
                winner = this.game.checkWinner(pos);
                if(winner != null) {
                    endGame();
                }
            }

        } catch (CellNotEmptyException e) {
            Toast.makeText(this,"Cell is not empty",Toast.LENGTH_SHORT).show();
        } catch (CellNotInRangeException e) {
            e.printStackTrace();
        }
    }
    public void endGame() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setPositiveButton("Gioca Ancora", (dialog1, which) -> {
            this.game.reset();
            this.resetView();
        });
        dialog.setNegativeButton("Esci",(dialog1,which) -> {
            setResult(this.winCounter);
            this.current = Game.State.X;
            finish();
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        endGame();
    }
}
