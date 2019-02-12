package com.alberto.trictractroc.multi;

import android.os.Handler;
import android.os.Message;

import com.alberto.trictractroc.JoinActivity;

import java.io.IOException;
import java.net.Socket;

public class JoinRunnable implements Runnable {
    private Handler handler;
    private String ip;

    public JoinRunnable(Handler handler,String ip) {
        this.handler = handler;
        this.ip = ip;
    }
    @Override
    public void run() {
        try {
            Socket socket = new Socket(this.ip,1337);
            SocketSingleton.setSocket(socket);
            SocketSingleton.setHost(false);
            Message message = new Message();
            message.what = JoinActivity.CONNESSO;
            this.handler.sendMessage(message);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
