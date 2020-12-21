package Work6.NetworkTest;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    private static final String CLIENT_NAME = "Клиент";
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            String hostName = "127.0.0.1";
            socket.connect(new InetSocketAddress(hostName, 8189), 300000);
            System.out.println("Сокет подключен!");

            var trCl = new ThreadIn(socket, CLIENT_NAME);

            var out = new PrintWriter(socket.getOutputStream(),true); //автоматическая очистка
            while (true) {
                System.out.println("["+CLIENT_NAME+"]");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                if (str.trim().equals("пока")) break;
                out.println("Сообщение от ["+CLIENT_NAME+"]: "+str);
            }
            socket.close();
            System.out.println("Соединение со стороны клиента закрыто!");

        } catch (ConnectException e) {
            System.out.println("Сервер не запущен!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
