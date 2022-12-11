package com.chap1;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class test1 {
    @Test
    public void testConnection() throws SQLException {

        try {
        /*
        四个要素:
        driver
        url
        password and username
        conn
         */
            Driver driver=new com.mysql.cj.jdbc.Driver();
            String url="jdbc:mysql://localhost:3306/atm?serverTimezone=UTC";
            /*
            数据库连接的时区问题
             */
            Properties pro=new Properties();
            pro.setProperty("user","root");
            pro.setProperty("password","root");

            Connection conn=driver.connect(url,pro);
        /*
             有输出就是连接成功
         */
            //com.mysql.cj.jdbc.ConnectionImpl@c540f5a
            System.out.println(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void testConnection5() throws IOException, ClassNotFoundException, SQLException {
        /*
        * 使用类加载器进行properties文件的读取
        * */
        InputStream resourceAsStream = test1.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);

        /*
         * 四要素
         */
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverclass = properties.getProperty("driverclass");

        Class.forName(driverclass);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }


}
