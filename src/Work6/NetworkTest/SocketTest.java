package Work6.NetworkTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/*
В этой программе устанавливается сокетное соединение с атомными часами в г. Боулдере,
шт. Колорадо и выводится время, передаваемое из сервера.
 */
public class SocketTest {
    public static void main(String[] args) {
        try {
            Socket s = new Socket();
            String hostName = "time-A.timefreq.bldrdoc.gov";
            s.connect(new InetSocketAddress(hostName, 13), 3000);
            InputStream inStream = s.getInputStream();
            Scanner in = new Scanner(inStream);

            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }

            InetAddress adr = InetAddress.getByName(hostName);
            byte[] adrByte = adr.getAddress();
            System.out.println("IP: " + Arrays.toString(byteToInt(adrByte)));

            adr = InetAddress.getLocalHost();
            adrByte = adr.getAddress();
            System.out.println("My Local IP: " + Arrays.toString(byteToInt(adrByte)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int[] byteToInt(byte[] arr) {
        int[] arrInt = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrInt[i] = arr[i] & 0xFF;
        }
        return arrInt;
    }
}
