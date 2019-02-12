package com.alberto.trictractroc.multi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.alberto.trictractroc.LobbyActivity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ListenerRunnable implements Runnable {
    private Handler handler;

    public ListenerRunnable(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        /*
        Message m = new Message();
        m.what = 0;
        this.handler.sendMessage(m);
        */
        try {
            ServerSocket server = new ServerSocket(1337);
            Message m = new Message();
            m.what = LobbyActivity.ASCOLTO;
            this.handler.sendMessage(m);
            Socket accepted = server.accept();
            m = new Message();
            m.what = LobbyActivity.CONNESSO;
            SocketSingleton.setSocket(accepted);
            this.handler.sendMessage(m);
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
