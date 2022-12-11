package com.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Test {

    /**
     * 获取连续的方式一
     * @throws Exception
     */
    @Test
    public void testPool() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test?serverTimezone=UTC");
        cpds.setUser("root");
        cpds.setPassword("root");

        //设置初始的连接数
        cpds.setInitialPoolSize(25);
//	cpds.setMaxPoolSize(100);
        Connection conn = cpds.getConnection();
        System.out.println(conn);//com.mchange.v2.c3p0.impl.NewProxyConnection@36d64342

        //销毁连接池方法
//        DataSources.destroy(cpds);
    }

    /**
     * 使用配置文件进行数据库的配置
     * c3p0-config
     */
    @Test
    public void getConnByXML() throws SQLException {
        DataSource cpds = new ComboPooledDataSource("helloc3p0");
        System.out.println(cpds.getConnection());
        //com.mchange.v2.c3p0.impl.NewProxyConnection@2812cbfa
        //返回一个连接对象
    }

}
