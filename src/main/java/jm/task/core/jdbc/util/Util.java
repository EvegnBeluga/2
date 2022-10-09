package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class Util {
        private final static String URL = "jdbc:mysql://localhost:3306/mysql";
        private final static String USERNAME = "root";
        private final static String PASSWORD = "root";
        public static Connection connection;
        private volatile static Util instance;

        public static Connection getConnection() {
            try {
                return connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public static Util getInstance() throws SQLException {
            if (instance == null) {
                synchronized (Util.class) {
                    if (instance == null) {
                        instance = new Util();
                    }
                }
            }
            return instance;
        }

    // реализуйте настройку соеденения с БД
}
