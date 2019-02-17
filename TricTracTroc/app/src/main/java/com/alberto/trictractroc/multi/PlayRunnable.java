package com.alberto.trictractroc.multi;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.alberto.trictractroc.Online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class PlayRunnable implements Runnable{
    private Socket socket;
    private Handler handler;
    public static boolean trasmetto;
    private boolean running;
    public PlayRunnable(Handler handler) {
        this.handler = handler;
        this.socket = SocketSingleton.getSocket();
        this.running = true;
    }
    public void stopServer() {
        this.running = false;
    }
    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            trasmetto = SocketSingleton.isHost();
            while(running) {
                int read = dis.readInt();
                Message message = new Message();
                message.what = Online.SET_CELL;
                message.arg1 = read;
                if(read == -1) {
                    this.stopServer();
                }
                this.handler.sendMessage(message);
                trasmetto = true;
            }
            socket.close();
        } catch (SocketException e) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
