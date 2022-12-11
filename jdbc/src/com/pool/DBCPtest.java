package com.pool;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPtest {

    /**
     * 直接获取连接
     * @throws SQLException
     */
    @Test
    public void testGetConnection() throws SQLException {
        BasicDataSource source = new BasicDataSource();

        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
        source.setUsername("root");
        source.setPassword("root");
        //初始连接数
        source.setInitialSize(10);
        //最大活跃数
        source.setMaxActive(10);
        Connection conn = source.getConnection();
        System.out.println(conn);
        //jdbc:mysql://localhost:3306/test?serverTimezone=UTC, UserName=root@localhost, MySQL Connector/J
    }

    /**
     * dbcp.properties
     * 使用配置文件获取连接
     * @throws Exception
     */
    public void getConnByProp() throws Exception {
        //载入配置文件
        Properties pros = new Properties();
//        InputStream is = DBCPtest.class.getClassLoader().getResourceAsStream("dbcp.properties");
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        pros.load(is);


        //根据提供的BasicDataSourceFactory创建对应的DataSource对象
        DataSource source = BasicDataSourceFactory.createDataSource(pros);

        Connection connection = source.getConnection();
        System.out.println(connection);

    }

}
