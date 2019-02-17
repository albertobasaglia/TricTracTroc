package com.alberto.trictractroc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alberto.trictractroc.multi.PlayRunnable;
import com.alberto.trictractroc.multi.SocketSingleton;
import com.alberto.trictractroc.tictactoe.CellNotEmptyException;
import com.alberto.trictractroc.tictactoe.CellNotInRangeException;
import com.alberto.trictractroc.tictactoe.Game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Online extends AppCompatActivity {
    public static final int SET_CELL = 0;
    private Game.State mioStato;
    private int miaRisorsa;
    private Game.State suoStato;
    private int suaRisorsa;
    private Thread runningGame;
    private Game game;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        this.game = new Game();

        mioStato = (SocketSingleton.isHost())? Game.State.X : Game.State.O;
        miaRisorsa = (SocketSingleton.isHost())? R.drawable.x : R.drawable.o;

        suoStato = (!SocketSingleton.isHost())? Game.State.X : Game.State.O;
        suaRisorsa = (!SocketSingleton.isHost())? R.drawable.x : R.drawable.o;
        AppCompatActivity activity = this;
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch(msg.what) {
                    case SET_CELL: {

                        int pos = msg.arg1;

                        if(pos == -1) {
                            activity.finish();
                        } else {

                            getButtonById(pos).setBackgroundResource(suaRisorsa);
                            try {
                                game.makeMove(pos,suoStato);
                                Game.State winner = game.checkWinner(pos);
                                if(winner != null){
                                    finish();
                                    SocketSingleton.getSocket().close();
                                }
                            } catch (CellNotEmptyException e) {
                                e.printStackTrace();
                            } catch (CellNotInRangeException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        break;
                    }
                }
            }
        };


        PlayRunnable play = new PlayRunnable(handler);
        this.runningGame = new Thread(play);
        this.runningGame.start();
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

    public void cellClicked(View view) throws CellNotEmptyException, CellNotInRangeException, IOException {
        if(PlayRunnable.trasmetto) {
            new Thread(() -> {
                try {
                    DataOutputStream dos = new DataOutputStream(SocketSingleton.getSocket().getOutputStream());
                    dos.writeInt(getIdByView(view));
                    PlayRunnable.trasmetto = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            getButtonById(getIdByView(view)).setBackgroundResource(miaRisorsa);
            this.game.makeMove(getIdByView(view),mioStato);
            Game.State winner = this.game.checkWinner(getIdByView(view));
            if(winner != null){
                finish();
                SocketSingleton.getSocket().close();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        new Thread(() -> {
            try {
                DataOutputStream dis = new DataOutputStream(SocketSingleton.getSocket().getOutputStream());
                dis.writeInt(-1);
                SocketSingleton.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
