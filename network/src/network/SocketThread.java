package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketThread extends Thread {
    private final SocketThreadListener listener;
    private final Socket socket;
    private DataOutputStream out;

    public SocketThread(SocketThreadListener listener, String name, Socket socket) {
        super(name);
        this.socket = socket;
        this.listener = listener;
        start();
    }

    @Override
    public void run() {
        try {
            listener.onSocketStart(this, socket);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("[SocketThread] : run : out = " + out);
            listener.onSocketReady(this, socket);
            while (!isInterrupted()) {
                String msg = in.readUTF();
                System.out.println("[SocketThread]:run() : msg = " + msg);
                listener.onReceiveString(this, socket, msg);
            }

        } catch (IOException e) {
            listener.onSocketException(this, e);
            close();
        } finally {
            listener.onSocketStop(this);
        }
    }

    public synchronized boolean sendMessage(String msg) {
        //msgformatter, serializer, jsonifier
//        if (out == null) { //My
//            System.out.println("out=null, " + msg + " не доставлено!");
//            return false;
//        }
        try {
            out.writeUTF(msg);
            System.out.println("[SocketThread]:SendMsg() : " + msg);
            out.flush();
            return true;
        } catch (IOException e) {
            listener.onSocketException(this, e);
            close();
            return false;
        }
    }

    public synchronized void close() {
        interrupt();
        try {
            socket.close();
            System.out.println("Сокет закрыт!" + socket);
        } catch (IOException e) {
            listener.onSocketException(this, e);
        }
    }
}
