package com.alberto.trictractroc.multi;
import android.os.Handler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayRunnable implements Runnable{
    private Socket socket;
    private Handler handler;

    public PlayRunnable(Handler handler) {
        this.handler = handler;
        this.socket = SocketSingleton.getSocket();
    }
    @Override
    public void run() {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
