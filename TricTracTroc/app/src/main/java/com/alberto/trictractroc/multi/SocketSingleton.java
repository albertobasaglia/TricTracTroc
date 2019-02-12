package com.alberto.trictractroc.multi;

import java.net.Socket;

public class SocketSingleton {
    private static Socket socket;
    private static boolean host;
    public static synchronized Socket getSocket(){
        return socket;
    }

    public static synchronized void setSocket(Socket socket){
        SocketSingleton.socket = socket;
    }

    public static synchronized boolean isHost() { return host; }

    public static synchronized void setHost(boolean what) { SocketSingleton.host = what; }
}
