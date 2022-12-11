package com.DAO;

import com.bean.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.utils.JDBCutils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    /**
     * 查询多行数据,即多个Order
     * @param sql
     * @param args
     * @return
     */
    public List<Order> queryForOrders(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        List<Order>list=new ArrayList<>();

        try {
            conn=JDBCutils.getConnByDruid();
            ps=conn.prepareStatement(sql);
            //设置占位符
            for(int i=0;i< args.length;i++)
                ps.setObject(i+1,args[i]);
            rs=ps.executeQuery();

            ResultSetMetaData rsmd=rs.getMetaData();

            //获取列数
            int columnCount=rsmd.getColumnCount();

            while(rs.next()){

                //设置order的属性前先建立对象
                Order order=new Order();

                for(int i=0;i<columnCount;i++){
                    //获取列名
                    String columnLabel=rsmd.getColumnLabel(i+1);
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //通过反射设置值
                    Field field=Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order,columnValue);
                }
                //如果上面的执行了就能返回一个
                //如果不执行,则返回null
                list.add(order);
            }
            return list;
            //如果没有查询到,可能返回一个空的List,List.size==0
        } catch (Exception e) {
            throw new RuntimeException(e);
        }  finally {
            JDBCutils.closeResource(conn,ps,rs);
        }
//        return null;
    }


    /**
     * 查询一个Order
     * @param sql
     * @param args
     * @return
     */
    public Order queryForOrder(String sql, Object...args){
        Connection conn = null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            conn=JDBCutils.getConnection();
            ps=conn.prepareStatement(sql);
            //设置占位符
            for(int i=0;i< args.length;i++)
                ps.setObject(i+1,args[i]);
            rs=ps.executeQuery();

            ResultSetMetaData rsmd=rs.getMetaData();
            //设置order的属性前先建立对象
            Order order=new Order();
            //获取列数
            int columnCount=rsmd.getColumnCount();

            if(rs.next()){

                for(int i=0;i<columnCount;i++){
                    //获取列名
                    String columnLabel=rsmd.getColumnLabel(i+1);
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //通过反射设置值
                    Field field=Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(order,columnValue);
                }
                //如果上面的执行了就能返回一个
                //如果不执行,则返回null
                return order;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }  finally {
            JDBCutils.closeResource(conn,ps,rs);
        }
        return null;
    }

}
