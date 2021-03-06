package network;

import java.net.ServerSocket;
import java.net.Socket;

public interface ServerSocketThreadListener {
    void onServerStart(ServerSocketThread thread);
    void onServerStop(ServerSocketThread thread);
    void onServerSocketCreated(ServerSocketThread thread, ServerSocket server);
    void onServerTimeout(ServerSocketThread thread, ServerSocket server);
    void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket);
    void onServerException(ServerSocketThread thread, Throwable exception);
}

/*
1) Не совсем понял «историю» с сокетом. Для чего он нужен?
2) Класс ClientGUI, строки начиная с 166 по 189, данные методы описывают поведение сокета?
3) Тот же самый вопрос, но уже в классе ChatServer. Методы с 41 строки отвечают за сокет, но уже с серверной стороны?
4) Модуль nerwork. Интерфейсы ServerSocket и SocketThread. В чем их разница? Немного запутался за что они отвечают?

Почему мы вызываем метод @overide run() при запуске клиентского приложения?
Просьба еще раз пройтись по uncoughtexception и всему, что с ним связано.
Почему вызывается SocetTimeoutException при выходе из метода accept() на таймаут. Какую информацию мы можем здесь получить?
Как останавливается поток серверного сокета в случае исключения - отрабатывает try с ресурсами?
что делает метод?
SwingUtilities.invokeLater(new Runnable() {
@Override
public void run() {
new ClientGUI();
}
});
понятно, что создается объект клиента, но почему так?

что происходит в цикле
while (!isInterrupted()) {
String msg = in.readUTF();
listener.onReceiveString(this, socket, msg);
}
метода run() класса SocketThread ?

Расскажите пожалуйста про конструкцию SwingUtilities.invokeLater и использование ее в методе putLog.

Как понять, какие модули должны зависеть, или не должны зависеть друг от друга? Можно ли считать те модули, от которых зависят другие модули, как родительский класс, или их воспринимать, условно, как абстрактный класс(для простоты понимания)?

Вот не понял, как в потоках сокет Сервера и сокет клиента становятся доступны методы интерфейсов SocketThreadListener & ServerSocketThreadListener.

1) В методе connect класса [clientGUI] возникает исключение, если не запущен сервер.
2) Класс [ServerSocketThread]:
try (ServerSocket server = new ServerSocket(port)) {
server.setSoTimeout(timeout); …
} catch …
Почему подчеркнутое выражение в круглых скобках и до фигурных?
3) С остановкой потока ServerSocketThread все понятно, он завершается через isInterrupted.
А вот с потоком SocketThread возникли вопросы в связке с методом close() того же класса.
Хотел узнать, чем DataInputStream отличается от Scaner? Хотя последний тоже работает с кодировкой UTF, не глумясь над русскими буквами при передаче, провел эксперимент в [Work6].
Так вот в методе run() сего потока в цикле while сокет ждет поступления сообщения {String msg = in.readUTF();}. Если мы хотим закрыть соединение, то сначала срабатывает interrupt(), который ни как не может прервать вышеописанную операцию readUTF(), там даже в методичке написано, что эта операция блокирующая. Далее в методе close() мы выполняем socket.close(), после чего в потоке на той же строке {String msg = in.readUTF();} возникает исключение, которое вызывает тот же метод close(), ни до какой проверки isInterrupted мы никогда не дойдем, на мой взгляд. Можно конечно, просто убрать close() из обработчика исключений, и все будет хорошо, но решил дойти до истины:
Мною были проведены полевые испытания в [Work6.NetworkTest], по типу домашки из методички.

var in = new Scanner(socket.getInputStream());
while (in.hasNextLine()) { // Ожидаем поступления данных, если сокет закрыт – штатно выходим;
String line = in.nextLine();
System.out.println(line);
}
System.out.println("Поток ["+name+"] закрыт штатно!");
} catch (IOException e) {
//e.printStackTrace();
System.out.println("Поток ["+name+"] закрыт!");
}
Тут прием данных разбит на 2 части, а) ожидание приема, из которого можно спокойно выйти, если извне закрыть сокет, даже не вызывая исключений, и б) сам прием данных, 3) повторюсь UTF работает. Как я понял и BufferedReader из примера в методичке организован по такому же двухступенчатому принципу, сначала ожидая приема ready(), а потом принимая данные readLine(). Кто-то говорил, что он потокобезопасный, а что Scaner опасный какой-то?
4) Опять вопрос к [SocketThread]. При создании нового потока, создается новый объект, новый socket, новый, отдельный экземпляр out. В каждом потоке мы пишем в свой out. Так нужна ли здесь синхронизация объектов, которая делается при доступе к общим объектам, например статичным, как мы делали на уроке? Может быть у них общий порт, тогда по другому принципу как-то синхронизировать нужно?
5) Еще вопрос какой конечный итог нашего проекта, что он будет из себя представлять?

Пока только один вопрос: "Должен ли интерфейс ClientGui функционировать (отправлять сообщения и получать их от Echo) при остановке потока Server socket?

Как называть переменные?
Как называть модули?
Как называть пакеты?
Ограничения в названиях пакетов, использование "-" в названии, почему нельзя?
Почему папка совпадает с названием пакета
Как подключать внешние файлы/пакеты
что такое *.iml? какое назначение этого файла?
До какой глубины закладывать решение? как определить достаточность решения/реализации?
Как себя остановить от "вылизывания кода"?
Какие папки обязательны в модуле/проекте?
Как настраивать jar
Как настраивать компиляцию?

1)Я не понял откуда чат берет иконку, прошелся по всем папкам, не нашел.
2) При подключении к чату пользователя. У нас сообщение приходят не от авторизированного пользователя, а от Echo. Я определил где это происходит. Но пока не понял как изменить что бы сообщение от конкретного пользователя приходило.
3) И еще такой вопрос с помощью Java можно сделать статус бар(в нижней панели) с текущим состоянием. (Подключен, отключен, идет подключение)

* */