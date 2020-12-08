package Work2;

public class MainExeption {
    public static void main(String[] args) {
        String str = "10 3 6 2\n2 3 -3 23\n5 6 7 1\n300 3 1 9";

        String[][] arrStr = new String[4][4];

        try {
            arrStr = strToArrStr(str);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Модуль [Main]. Ошибка на этапе создания массива! Выход!");
            System.exit(1);
        }

        printArrStr(arrStr);

        try {
            System.out.println("Сумма элементов массива /2 = " + sumDiv2(arrStr));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Модуль [Main]. Ошибка! Один из элементов - не число! Выход!");
        }
    }

    public static String[][] strToArrStr(String str) throws Exception {
        //преобразует строку в массив;
        String[][] arrStr = new String[4][4];
        int curPosX = 0; // Текущие указатели на индекс массива
        int curPosY = 0;
        String subStr = "";

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (i == str.length() - 1) {
                subStr += ch;
                arrStr[curPosY][curPosX] = subStr;
            }
            if (ch == ' ') {
                arrStr[curPosY][curPosX++] = subStr;
                subStr = "";
                if (curPosX > 3) throw
                        new ArrayIndexException("Выход за границы массива по X !", curPosX, curPosY);
            } else if (ch == '\n') {
                arrStr[curPosY++][curPosX] = subStr;
                if (curPosY > 3) throw
                        new ArrayIndexException("Выход за границы массива по Y !", curPosX, curPosY);
                subStr = "";
                curPosX = 0;
            } else {
                subStr += ch;
            }
        }
        return arrStr;
    }

    public static int sumDiv2(String[][] arrStr) throws Exception {
        int sum = 0;
        for (int i = 0; i < 4; i++) {

            try {
                for (int j = 0; j < 4; j++) {
                    sum += Integer.parseInt(arrStr[i][j]);
                }
            } catch (NumberFormatException e) {
                throw new StrToIntException(e.getMessage());
            }
        }
        return sum / 2;
    }

    public static void printArrStr(String[][] arrStr) {
        System.out.println("\nИсходный массив:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arrStr[i][j] + " ");
            }
            System.out.println();
        }
    }
}

/* Результаты -----------------------------------------------------------
1.
Исходный массив:
10 3 6 2
2 3 3 23
5 6h 7 1
300 3 1 9
Модуль [Main]. Ошибка! Один из элементов - не число! Выход!
Work2.StrToIntException: For input string: "6h"
	at Work2.MainExeption.sumDiv2(MainExeption.java:69)
	at Work2.MainExeption.main(MainExeption.java:20)

2.
Позиция: [4,1]
Модуль [Main]. Ошибка на этапе создания массива! Выход!
Work2.ArrayIndexException: Выход за границы массива по X !
	at Work2.MainExeption.strToArrStr(MainExeption.java:45)
	at Work2.MainExeption.main(MainExeption.java:10)

3.
Исходный массив:
10 3 6 2
2 3 3 23
5 6 7 1
300 3 1 9
Сумма элементов массива /2 = 192
----------------------------------------------------------------------------
 */
