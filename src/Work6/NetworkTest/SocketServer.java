package Work6.NetworkTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {
    private static final String SERVER_NAME = "Сервер";
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8189);
            serverSocket.setSoTimeout(30000);
            Socket socket = serverSocket.accept();
            System.out.println("Соединение установлено!");

            var trS = new ThreadIn(socket, SERVER_NAME);

            var out = new PrintWriter(socket.getOutputStream(),true); //автоматическая очистка
            while (true) {
                System.out.println("["+SERVER_NAME+"]");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                if (str.trim().equals("пока")) break;
                out.println("Сообщение от ["+SERVER_NAME+"]: "+str);
            }
            socket.close();

            System.out.println("Соединение со стороны сервера закрыто!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
