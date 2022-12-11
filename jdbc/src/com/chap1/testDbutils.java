package com.chap1;

import com.bean.Order;
import com.utils.JDBCutils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class testDbutils {

    @Test
    public void TestInsert() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCutils.getConnByDruid();
        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        int count = runner.update(conn, sql, "何成飞", "he@qq.com", "1992-09-08");

        System.out.println("添加了" + count + "条记录");

        JDBCutils.closeResource(conn, null);
    }

    @Test
    public void TestDelete() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCutils.getConnByDruid();
        String sql = "delete from customers where `name`=? and `email`=? and `birth` = ?";
        int count = runner.update(conn, sql, "何成飞", "he@qq.com", "1992-09-08");

        System.out.println("删除" + count + "条记录");

        JDBCutils.closeResource(conn, null);
    }

    /**
     * 根据id查询order表中的一行
     * @throws Exception
     */
    @Test
    public void TestQuery() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCutils.getConnByDruid();
        String sql = "SELECT `order_id` as `id`,`order_name` as `name`,`order_date`as`date` FROM `order` where `order_id`=?";
        BeanHandler<Order> bh = new BeanHandler<Order>(Order.class);
        Order ord = runner.query(conn, sql, bh, 1);
        System.out.println(ord);//Order{id=1, name='AA', date=2010-03-04}
        JDBCutils.closeResource(conn, null);
    }

    /**
     * 查询order表中的多行
     * @throws Exception
     */
    @Test
    public void TestQuerys() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCutils.getConnByDruid();
        String sql = "SELECT `order_id` as `id`,`order_name` as `name`,`order_date`as`date` FROM `order` ";
        BeanListHandler<Order> bh = new BeanListHandler<Order>(Order.class);
        List<Order> ords = runner.query(conn, sql, bh);
        System.out.println(ords);
        /*
        [Order{id=1, name='AA', date=2010-03-04},
        Order{id=2, name='BB', date=2000-02-01},
        Order{id=3, name='AA', date=2022-12-17},
        Order{id=4, name='GG', date=1994-06-28}]
         */
        JDBCutils.closeResource(conn, null);
    }

    /**
     * 查询order表中的一个值
     * @throws Exception
     */
    @Test
    public void TestQueryForName() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCutils.getConnByDruid();
        String sql = "SELECT `order_name` as `name`  FROM `order` where `order_id`=?";
        ScalarHandler sh = new ScalarHandler();
        String query = (String)runner.query(conn, sql, sh, 1);
        System.out.println(query);
        //AA
        JDBCutils.closeResource(conn, null);
    }


}
