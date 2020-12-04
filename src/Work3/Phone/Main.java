package Work3.Phone;

/*
2. Написать простой класс PhoneBook(внутри использовать HashMap):
    - В качестве ключа использовать фамилию
    - В каждой записи всего два поля: phone, e-mail
    - Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов),
     и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть
      несколько записей. Итого должно получиться 3 класса Main, PhoneBook, Person.
 */

public class Main {

    static PhoneBook phoneBook=new PhoneBook();

    public static void main(String[] args) {

        phoneBook.add("Иванов","+7800131212","ivanov@ya.ru");
        phoneBook.add("Петров","+7952131213","petrov@ya.ru");
        phoneBook.add("Иванов","+7800131217","ivanov2@ya.ru");
        phoneBook.add("Иванов","+7800131218","ivanov3@ya.ru");
        phoneBook.add("Калашников","+79611891800","kalash@bk.ru");
        phoneBook.add("Петров","+17001002000","petrov2@bk.ua");

        printTr();
        phoneBook.print();
        printTr();

        printPhone("Калашников");
        printPhone("Петров");
        printPhone("Иванов");
        printPhone("Сидоров");
        printTr();

        printMail("Калашников");
        printMail("Петров");
        printMail("Иванов");
        printMail("Сидоров");
        printTr();
    }

    static void printPhone(String fio){
        System.out.println("\n["+fio+"] Список телефонов:");
        System.out.println(phoneBook.findPhone(fio));
    }

    static void printMail(String fio){
        System.out.println("\n["+fio+"] Список e-mail:");
        System.out.println(phoneBook.findMail(fio));
    }

    static void printTr(){
        System.out.println("----------------------------------------------------------" +
                "--------------------------------------------------------------");
    }
}
/* Результаты работы программы -----------------------------------------------------------------------------------------
[Телефонная книга]
Иванов - [тел.:+7800131212, e-mail:ivanov@ya.ru, тел.:+7800131217, e-mail:ivanov2@ya.ru, тел.:+7800131218, e-mail:ivanov3@ya.ru]
Калашников - [тел.:+79611891800, e-mail:kalash@bk.ru]
Петров - [тел.:+7952131213, e-mail:petrov@ya.ru, тел.:+17001002000, e-mail:petrov2@bk.ua]
------------------------------------------------------------------------------------------------------------------------

[Калашников] Список телефонов:
[+79611891800]

[Петров] Список телефонов:
[+7952131213, +17001002000]

[Иванов] Список телефонов:
[+7800131212, +7800131217, +7800131218]

[Сидоров] Список телефонов:
null
------------------------------------------------------------------------------------------------------------------------

[Калашников] Список e-mail:
[kalash@bk.ru]

[Петров] Список e-mail:
[petrov@ya.ru, petrov2@bk.ua]

[Иванов] Список e-mail:
[ivanov@ya.ru, ivanov2@ya.ru, ivanov3@ya.ru]

[Сидоров] Список e-mail:
null
------------------------------------------------------------------------------------------------------------------------
 */