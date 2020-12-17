package Work6.NetworkTest.DataOutput;

import java.io.DataOutputStream;
import java.io.IOException;
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

            var trS = new ThreadInData(socket, SERVER_NAME);

            var out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("["+SERVER_NAME+"]");
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                if (str.trim().equals("пока")) break;
                out.writeUTF("Сообщение от ["+SERVER_NAME+"]: "+str);
                out.flush();
            }
            socket.close(); // если закрыть сокет, то срабатывает исключение на [ThreadInData:26]:
            // String line = in.readUTF(), но по крайней мере поток завершается, хоть и не штатно;

            // trS.interrupt(); если же сделать так, без закрытия сокета, то ничего не происходит, т. к.
            // поток просто не может выйти из [26] String line = in.readUTF(),

            System.out.println("Соединение со стороны сервера закрыто!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
