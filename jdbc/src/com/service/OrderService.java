package com.service;

import com.DAO.OrderDao;
import com.bean.Order;

import java.util.List;

public class OrderService {

    public static Order getOrderById(int id){
        String sql="select `order_id`as `id`,`order_name` as `name`,`order_date` as `date` from `order` where `order_id`=?";
        OrderDao orderDao=new OrderDao();
        return orderDao.queryForOrder(sql,id);

    }

    /**
     * 若有则返回对象
     * 若没有则返回null
     * @param name
     * @return
     */
    public static List<Order> getOrdersByName(String name){
        String sql="select `order_id`as `id`,`order_name` as `name`,`order_date` as `date` from `order` where `order_name`=?";
        OrderDao orderDao=new OrderDao();
        List<Order> orders = orderDao.queryForOrders(sql, name);
        return (orders.size()==0)?null:orders;
    }

}
