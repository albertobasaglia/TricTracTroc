package com.alberto.trictractroc.multi;

import java.net.Socket;

public class SocketSingleton {
    private static Socket socket;

    public static synchronized Socket getSocket(){
        return socket;
    }

    public static synchronized void setSocket(Socket socket){
        SocketSingleton.socket = socket;
    }
}
