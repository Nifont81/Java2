package Work6.ImplTest;

import javax.swing.*;

public class Test2 implements onMessage, SwingConstants{
    @Override
    public void out(String message) {
        JOptionPane.showMessageDialog(null, message,"Сообщение", JOptionPane.INFORMATION_MESSAGE);

    }
}
