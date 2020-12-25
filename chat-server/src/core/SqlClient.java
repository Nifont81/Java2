package core;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlClient {

    private static Connection connection;
    private static Statement statement;

    public static void connect() {
        var db = new DatabaseHandler();
        try {
            connection = db.getConnection();
            statement = connection.createStatement();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String getNickname(String login, String password) {
        System.out.println("Запрос к БД: getNickName");
        // select nickname from clients where login='ivan' and password='123'
        String query = String.format("select nickname from clients where login='%s' and password='%s'",
                login, password);
        try (ResultSet set = statement.executeQuery(query)) {
            if (set.next()) {
                String str = set.getString("nickname");
                System.out.println("[SQL] nickname : " + str);
                return str;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
