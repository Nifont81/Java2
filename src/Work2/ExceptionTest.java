package Work2;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class ExceptionTest {
    private static class IOStream implements Closeable {
        public IOStream() throws FileNotFoundException{
            System.out.println("Stream create");
            //throw new FileNotFoundException("Не могу создать поток в/в!");
        }

        void write() throws SQLException {
            System.out.println("write");
            throw new SQLException("Ошибка записи!");
        }

        public void close() throws IOException {
            System.out.println("closed");
            //throw new IOException("Ошибка при закрытии!");
        }

    }

    public static void main(String[] args) {
        try (IOStream io = new IOStream()) {
            io.write();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
