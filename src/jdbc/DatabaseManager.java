package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    public static final String URL = "jdbc:mysql://localhost:3306/test";
    public static final String USER = "root";
    public static final String PASSWORD = "dang123";
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối");
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Lỗi đóng kết nối");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = DatabaseManager.getConnection();
        if (conn != null) {
            System.out.println("Kết nối thành công");
            DatabaseManager.closeConnection(conn);
        } else {
            System.out.println("Kết nối thất bại");
        }
    }
}
