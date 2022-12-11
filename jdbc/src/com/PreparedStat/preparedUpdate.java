package com.PreparedStat;

import com.chap1.test1;
import com.utils.JDBCutils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class preparedUpdate {
    public void TestInsert() throws IOException, ClassNotFoundException, SQLException {
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
//        System.out.println(connection);

        String sql="insert into customers(name,email,birth) values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1,"nezha");
        ps.setString(2,"nezha@123.com");
        ps.setDate(3,new Date(1234556L));

        ps.execute();

        ps.close();
        connection.close();


    }

    /**
     * 使用JDBCUtils封装的操作进行数据库的修改
     */
    @Test
    public void TestSet(){
        Connection connection=null;
        PreparedStatement ps=null;
        try {
            connection = JDBCutils.getConnection();
            String sql="update customers set name = ? where id =?";
            ps = connection.prepareStatement(sql);
            ps.setObject(1,"mzt");
            ps.setObject(2,Integer.getInteger("18"));
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("修改成功");
            JDBCutils.closeResource(connection,ps);
        }

    }

    @Test
    public void TestDelete(){
        Connection connection=null;
        PreparedStatement ps=null;
        try {
            connection = JDBCutils.getConnection();
            String sql="delete from customers  where id =?";
            ps = connection.prepareStatement(sql);
//            ps.setObject(1,"mzt");
            ps.setObject(1,Integer.getInteger("18"));
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("删除成功");
            JDBCutils.closeResource(connection,ps);
        }

    }

    @Test
    public void TestAdd(){
        Connection connection=null;
        PreparedStatement ps=null;
        try {
            connection = JDBCutils.getConnection();
            String sql="insert into customers(name,email,birth) values(?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setString(1,"nezha");
            ps.setString(2,"nezha@123.com");
            ps.setDate(3,new Date(1234556L));
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("修改成功");
            JDBCutils.closeResource(connection,ps);
        }

    }

    /**
     * 通用的增删改操作
     * @param sql
     * @param args
     */
    @Test
    public void GenUpdate(String sql,Object...args){
        Connection connection=null;
        PreparedStatement ps=null;
        try {
            connection = JDBCutils.getConnection();
            ps = connection.prepareStatement(sql);
           for(int i=0;i<args.length;i++){
               ps.setObject(i+1,args[i]);
           }
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("更新成功");
            JDBCutils.closeResource(connection,ps);
        }
    }
}
