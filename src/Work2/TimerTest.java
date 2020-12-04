package Work2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;



class TimePrinter implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("Время : "+ Instant.ofEpochMilli(event.getWhen()));
        //Toolkit.getDefaultToolkit().beep();
    }
}
public class TimerTest {
    public static void main(String[] args) {
        TimePrinter listener = new TimePrinter();
        var timer = new Timer(1000, listener);
        timer.start();
        JOptionPane.showMessageDialog(null,"Завершить?");
        System.exit(0);
    }
}
