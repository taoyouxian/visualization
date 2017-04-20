package edu.ruc.charts.utils.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    private static final ConnectionFactory FACTORY = new ConnectionFactory();
    private Connection conn;

    static {// 静态代码块，初始化类（执行一次），对类的属性进行赋值，用于属性文件的加载
        Properties prop = new Properties();// 用于处理属性文件的键值对
        InputStream in = null;
        try {
            in = ConnectionFactory.class.getClassLoader().getResourceAsStream(
                    "dbconfig.properties");// 属性文件中的内容
            prop.load(in);// 从输入流中读取属性列表
        } catch (Exception e) {
            System.out.println("=======配置文件读取错误=======");
        }
        driver = prop.getProperty("driver");
        url = prop.getProperty("url");
        user = prop.getProperty("user");
        password = prop.getProperty("password");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory() {

    }

    public static ConnectionFactory getInstance() {// 单例模式，只有一个connectionfactory存在
        return FACTORY;
    }

    public Connection makeConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getInstance().makeConnection();
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
