package com.chap1;

import com.service.OrderService;
import org.junit.Test;

public class testOrder {

    /**
     * 测试getOrderById
     */
    @Test
    public void testGetOrderById(){
        System.out.println(OrderService.getOrderById(1));
        //Order{id=1, name='AA', date=2010-03-04}
        //ok
    }

    @Test
    public void testGetOrdersByName(){
        System.out.println(OrderService.getOrdersByName("AA"));
        //[Order{id=1, name='AA', date=2010-03-04}, Order{id=3, name='AA', date=2022-12-17}]
        //[Order{id=1, name='AA', date=2010-03-04}, Order{id=3, name='AA', date=2022-12-17}]
    }

}
