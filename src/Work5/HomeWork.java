package Work5;

/*
1. Необходимо написать два метода, которые делают следующее:
1) Создают одномерный длинный массив, например:
static final int size = 10000000;
static final int h = size / 2;
float[] arr = new float[size];
2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
Отличие первого метода от второго:
Первый просто бежит по массиву и вычисляет значения.
Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

Пример деления одного массива на два:
System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

Пример обратной склейки:
System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

Примечание:
System.arraycopy() копирует данные из одного массива в другой:
System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
По замерам времени:
Для первого метода надо считать время только на цикл расчета:
for (int i = 0; i < size; i++) {
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}
 */

class CalculationRC implements Runnable {

    int id;
    float[] calcArr;
    int deltaI;

    public CalculationRC(float[] calcArr, int deltaI, int id) {
        this.calcArr = calcArr;
        this.deltaI = deltaI;
        this.id=id;
    }

    @Override
    public void run() {
        for (int i = 0; i < calcArr.length; i++) {
            calcArr[i] = (float) (calcArr[i] * Math.sin(0.2f + (i+deltaI) / 5f)
                    * Math.cos(0.2f + (i+deltaI) / 5f) * Math.cos(0.4f + (i+deltaI) / 2f));
        }
        System.out.println(id+" поток ["+Thread.currentThread().getName()+"] выполнил задачу!");
    }
}

public class HomeWork {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    private static long t1; // Стартовое время в расчетах производительности;

    public static void main(String[] args) {
        System.out.println("[Один поток]");
        fillArr();
        startTimer();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));
        }
        printDeltaTime();
        pr(1); pr(size-1);

        System.out.println("\n[Два потока]");
        fillArr();
        startTimer();
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread t1 = new Thread(new CalculationRC(arr1, 0,1));
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

        pr(1); pr(size-1);
    }

    public static void fillArr() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
    }

    public static void startTimer() { // Начало измерения времени;
        t1 = System.currentTimeMillis();
    }

    public static void printDeltaTime() { //Измерение и вывод времени с события start;
        long t2 = System.currentTimeMillis();
        float dt = (t2 - t1);
        System.out.println("Время выполнения: "+dt + " мс.");
    }

    static void pr(int i) {
        // ПРоверка + Print [i] -го элемента массива:
        System.out.println("[Проверка!] arr ["+i+"] = "+arr[i]);
    }
}
/*

[Один поток]
Время выполнения: 934.0 мс.
[Проверка!] arr [1] = 0.22295786
[Проверка!] arr [9999999] = 0.10660095

[Два потока]
1 поток [Thread-0] выполнил задачу!
2 поток [Thread-1] выполнил задачу!
Время выполнения: 551.0 мс.
[Проверка!] arr [1] = 0.22295786
[Проверка!] arr [9999999] = 0.10660095

 */