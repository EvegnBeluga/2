package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

    public class Util {
        private final static String URL = "jdbc:mysql://localhost:3306/mysql";
        private final static String USERNAME = "root";
        private final static String PASSWORD = "root";

        static Connection connection = null;
        private volatile static Util instance;

        private static SessionFactory sessionFactory;
        public static SessionFactory getSessionFactory() {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration();
                    Properties settings = new Properties();

                    settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                    settings.put(Environment.URL, URL);
                    settings.put(Environment.USER, USERNAME);
                    settings.put(Environment.PASS, PASSWORD);
                    settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                    settings.put(Environment.SHOW_SQL, "true");
                    settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                    settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                    configuration.setProperties(settings);
                    configuration.addAnnotatedClass(User.class);

                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties()).build();

                    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return sessionFactory;
        }




        public static Connection getConnection() {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return connection;
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

