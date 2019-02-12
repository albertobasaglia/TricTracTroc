package com.alberto.trictractroc.multi;
import android.os.Handler;
import android.os.Message;

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
    public PlayRunnable(Handler handler) {
        this.handler = handler;
        this.socket = SocketSingleton.getSocket();
    }
    @Override
    public void run() {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            trasmetto = SocketSingleton.isHost();
            while(true) {
                if(trasmetto) {

                } else {
                    int read = dis.readInt();
                    Message message = new Message();
                    message.what = Online.SET_CELL;
                    message.arg1 = read;
                    this.handler.sendMessage(message);
                    trasmetto = true;
                }
            }
        } catch (SocketException e) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
