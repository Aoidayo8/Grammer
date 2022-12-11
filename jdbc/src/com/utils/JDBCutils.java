package com.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.chap1.test1;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCutils {



    public static Connection getConnection() {
        try {
            /*
             * 使用类加载器进行properties文件的读取
             * */
            InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
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
//            System.out.println(connection);
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static ComboPooledDataSource cpds= null;

    public static Connection getConnByC3P0() throws SQLException {
        if(cpds==null){
           cpds=new ComboPooledDataSource("helloc3p0");
        }
        Connection connection = cpds.getConnection();
        return connection;
    }

    private static DataSource source=null;

    public static Connection getConnByDBCP() throws Exception {
        //懒汉模式
        if(source==null){
            Properties pros = new Properties();
            //InputStream is = DBCPtest.class.getClassLoader().getResourceAsStream("dbcp.properties");
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
            pros.load(is);
            //根据提供的BasicDataSourceFactory创建对应的DataSource对象
            source = BasicDataSourceFactory.createDataSource(pros);
        }
        Connection connection = source.getConnection();
        return connection;
    }


    public static Connection getConnByDruid() throws Exception {
        if(source==null){
            Properties pros = new Properties();
            //InputStream is = DBCPtest.class.getClassLoader().getResourceAsStream("dbcp.properties");
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        }
        Connection conn = source.getConnection();
        return conn;
    }

    public static void closeResource(Connection conn, PreparedStatement ps){
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(ps!=null)
                ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs){
        try{
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if(ps!=null)
                ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if(rs!=null)
                rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
