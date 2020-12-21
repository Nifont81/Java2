package Work6.NetworkTest;
// Общий для сервера и клиента поток чтения данных из сокета;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ThreadIn extends Thread {
    Socket socket;
    String name;

    public ThreadIn(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
        start();
    }

    @Override
    public void run() {
        try {
            var in = new Scanner(socket.getInputStream());

            while (in.hasNextLine()) { // Ожидаем поступления данных, если сокет закрыт - штатно выходим;
                String line = in.nextLine();
                System.out.println(line);
            }
            System.out.println("Поток ["+name+"] закрыт штатно!");
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Поток ["+name+"] закрыт!");
        }
    }
}
