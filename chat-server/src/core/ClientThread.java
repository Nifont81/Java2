package core;

import library.Protocol;
import network.SocketThread;
import network.SocketThreadListener;

import java.net.Socket;

public class ClientThread extends SocketThread {

    private String nickname;
    private boolean isAuthorized;

    public ClientThread(SocketThreadListener listener, String name, Socket socket) {
        super(listener, name, socket);
        System.out.println("ClientThread socket=" + socket);
        //isAuthorized = true; // My
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    void authAccept(String nickname) {
        isAuthorized = true;
        this.nickname = nickname;
        sendMessage(Protocol.getAuthAccept(nickname));
    }

    void authFail() {
        isAuthorized = false; // My
        sendMessage(Protocol.getAuthDenied());
        // close(); // My
    }

    void msgFormatError(String msg) {
        sendMessage(Protocol.getMsgFormatError(msg));

        close();
    }
}
