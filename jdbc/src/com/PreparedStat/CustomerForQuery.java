package com.PreparedStat;

import com.bean.Customer;
import com.utils.JDBCutils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

public class CustomerForQuery {
    /**
     * 通用的查询操作
     */
    @Test
    public void queryTest(){
        Connection connection=null;
        String sql=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        try {
            connection = JDBCutils.getConnection();
            sql="select `id`,`name`,`email`,`birth` from `customers` where id = ?";

            ps = connection.prepareStatement(sql);
            ps.setObject(1,1);
            rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date birth = rs.getDate(4);
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer.toString());
                /*
                Customer{id=1, name='汪峰', email='wf@126.com', date=2010-02-02}
                 */
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCutils.closeResource(connection,ps,rs);
        }


    }

    /**
     *  单个的查询
     * @Description 针对于customers表的通用的查询操作
     * @author shkstart
     * @throws Exception
     * @date 上午10:23:40
     */
    @Test
    public Customer queryForCustomers(String sql,Object...args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCutils.getConnection();

            ps = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            //获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            if(rs.next()){
                Customer cust = new Customer();
                //处理结果集一行数据中的每一个列
                for(int i = 0;i <columnCount;i++){
                    //获取列值
                    Object columValue = rs.getObject(i + 1);

                    //获取每个列的列名
//					String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(cust, columValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JDBCutils.closeResource(conn, ps, rs);

        }

        return null;


    }
}
