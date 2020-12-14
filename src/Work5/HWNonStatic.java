package Work5;

public class HWNonStatic {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    private static long t1; // Стартовое время в расчетах производительности;

    static class CalculationRC implements Runnable {

        int id;
        float[] calcArr;
        int deltaI;

        public CalculationRC(float[] calcArr, int deltaI, int id) {
            this.calcArr = calcArr;
            this.deltaI = deltaI;
            this.id = id;
        }

        @Override
        public void run() {
            for (int i = 0; i < h; i++) {
                calcArr[i] = (float) (calcArr[i] * Math.sin(0.2f + (i + deltaI) / 5f)
                        * Math.cos(0.2f + (i + deltaI) / 5f) * Math.cos(0.4f + (i + deltaI) / 2f));
            }
            System.out.println(id + " поток [" + Thread.currentThread().getName() + "] выполнил задачу!");
        }
    }

    static class CalculationRC2 implements Runnable {
    // Не входило в задание! Реализация с общим доступом к одному массиву:
        int i1, i2;

        public CalculationRC2(int i1, int i2) {
            this.i1 = i1;
            this.i2 = i2;
        }

        @Override
        public void run() {
            for (int i = i1; i < i2; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + (i) / 5f)
                        * Math.cos(0.2f + (i) / 5f) * Math.cos(0.4f + (i) / 2f));
            }
            System.out.println("Поток [" + Thread.currentThread().getName() + "] выполнил задачу!");
        }
    }

    HWNonStatic() {
        System.out.println("[Один поток]");
        fillArr();
        startTimer();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }
        printDeltaTime();
        pr(1);
        pr(size - 1);

        System.out.println("\n[Два потока]");
        fillArr();
        startTimer();
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread t1 = new Thread(new CalculationRC(arr1, 0, 1));
        Thread t2 = new Thread(new CalculationRC(arr2, h, 2));
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);
        printDeltaTime();

        pr(1);
        pr(size - 1);

        System.out.println("\n[Два потока с доступом к ощему массиву]");
        fillArr();
        startTimer();

        Thread t3 = new Thread(new CalculationRC2(0, h));
        Thread t4 = new Thread(new CalculationRC2(h, size));
        t3.start();
        t4.start();

        try {
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printDeltaTime();

        pr(1);
        pr(size - 1);

    }

    public static void main(String[] args) {
        new HWNonStatic();
    }

    void fillArr() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
    }

    void startTimer() { // Начало измерения времени;
        t1 = System.currentTimeMillis();
    }

    void printDeltaTime() { //Измерение и вывод времени с события start;
        long t2 = System.currentTimeMillis();
        float dt = (t2 - t1);
        System.out.println("Время выполнения: " + dt + " мс.");
    }

    void pr(int i) {
        // ПРоверка + Print [i] -го элемента массива:
        System.out.println("[Проверка!] arr [" + i + "] = " + arr[i]);
    }
}

/* Результаты:

[Один поток]
Время выполнения: 927.0 мс.
[Проверка!] arr [1] = 0.22295786
[Проверка!] arr [9999999] = 0.10660095

[Два потока]
1 поток [Thread-0] выполнил задачу!
2 поток [Thread-1] выполнил задачу!
Время выполнения: 549.0 мс.
[Проверка!] arr [1] = 0.22295786
[Проверка!] arr [9999999] = 0.10660095

[Два потока с доступом к ощему массиву]
Поток [Thread-2] выполнил задачу!
Поток [Thread-3] выполнил задачу!
Время выполнения: 478.0 мс.
[Проверка!] arr [1] = 0.22295786
[Проверка!] arr [9999999] = 0.10660095

 */