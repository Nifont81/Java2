package Work6.NetworkTest.DataOutput;
// Общий для сервера и клиента поток чтения данных из сокета.
// С использованием DataInputStream и прерыванием извне,
// на мой взгляд не совсем удачный способ;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ThreadInData extends Thread {
    Socket socket;
    String name;

    public ThreadInData(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
        start();
    }

    @Override
    public void run() {
        try {
            var in = new DataInputStream(socket.getInputStream());

            while (!isInterrupted()) {
                String line = in.readUTF();
                System.out.println(line);
            }
            System.out.println("Поток "+getName()+" закрыт штатно!");
        } catch (IOException e) {
            System.out.println("Поток "+getName()+" закрыт не штатно!");
            //e.printStackTrace();
        }
    }
}
