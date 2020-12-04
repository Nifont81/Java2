package Work2;

public class ArrayIndexException extends Exception{
    public ArrayIndexException(String message, int x, int y) {
        super(message);
        System.out.println("Позиция: ["+x+","+y+"]");
    }
}
