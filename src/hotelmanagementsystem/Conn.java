package hotelmanagementsystem;

import java.sql.*;

public class Conn {
    private Connection c;

    public Conn() {
        try {
            System.out.println("Loading JDBC driver...");
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to MySQL...");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem","root","topik712");

            System.out.println(" Connection established: " + c);

        } catch (Exception e) {
            System.out.println(" Failed to connect to database.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }
}
