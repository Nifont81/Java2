package Work1;

public class TestInterface {
    private interface MouseListener{
        void onMouseClicked();
    }

    private static void method1(MouseListener listener){
        listener.onMouseClicked();
    }

    public static void main(String[] args) {
        class MyClass implements MouseListener {
            @Override
            public void onMouseClicked() {

            }
         }

        // 1. Классический способ:
        MyClass c = new MyClass();
        method1(c);

        // 2. Передача анонимного объекта, реализующего интерфейс:
        method1(new MyClass());

        // 3. Передаем анонимный класс, реализующий интерфейс MouseListener:
        method1(new MouseListener() {
            @Override
            public void onMouseClicked() {

            }
        });

        /*
            addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                mouseEv(e);
            }
        });
         */

        // 4. Лямбда выражение:
        method1(() -> {});
    }
}
