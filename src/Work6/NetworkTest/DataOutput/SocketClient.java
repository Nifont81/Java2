package Work6.NetworkTest.DataOutput;

import java.io.DataOutputStream;
import java.io.IOException;
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

            var trCl = new ThreadInData(socket, "Клиент");

            var out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("["+CLIENT_NAME+"]");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                if (str.trim().equals("пока")) break;
                out.writeUTF("Сообщение от ["+CLIENT_NAME+"]: "+str);
                out.flush();
            }
            socket.close(); // если закрыть сокет, то срабатывает исключение на [ThreadInData:26]:
            // String line = in.readUTF(), но по крайней мере поток завершается, хоть и не штатно;

            // trS.interrupt(); если же сделать так, без закрытия сокета, то ничего не происходит, т. к.
            // поток просто не может выйти из [26] String line = in.readUTF(),

            System.out.println("Соединение со стороны клиента закрыто!");

        } catch (ConnectException e) {
            System.out.println("Сервер не запущен!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
