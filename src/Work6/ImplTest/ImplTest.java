package Work6.ImplTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class ImplTest {
    public static void main(String[] args) {
        Test1 t1 = new Test1();
        metod1(t1, "Hello World");
        onMessage t2 = new Test2();
        //metod1(t2, "GUI Hello World");

        metod1(new onMessage() {
            @Override
            public void out(String message) {
                System.out.println(message);
            }
        }, "Привет Мир!");

        metod1((String message) -> {
                    System.out.println(message);
                }, "End."
        );

        var t = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Timer: " + Instant.ofEpochMilli(e.getWhen()));
            }
        });
        t.start();
        JOptionPane.showMessageDialog(null, "Завершить программу?");
    }

    public static void metod1(onMessage message, String str) {
        message.out(str);
    }
}
