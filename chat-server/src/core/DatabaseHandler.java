package core;

//import com.mysql.cj.jdbc.Driver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class DatabaseHandler {
    private Connection dbConnection;

    public Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        try (var in = Files.newInputStream(Paths.get("database.properties"))) {
            properties.load(in);
        }
        var drivers = properties.getProperty("jdbc.drivers");
        if (drivers != null) System.setProperty("jdbc.drivers", drivers);
        var url = properties.getProperty("jdbc.url");
        var username = properties.getProperty("jdbc.username");
        var password = properties.getProperty("jdbc.password");

        try {
            dbConnection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение установлено.");
        } catch (SQLException throwables) {
            System.err.println("Соединение не установлено!");
            //throwables.printStackTrace();
            throw new SQLException();
        }
        return dbConnection;
    }
}
