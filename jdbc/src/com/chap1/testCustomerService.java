package com.chap1;

import com.service.CustomerService;
import org.junit.Test;

public class testCustomerService {

    @Test
    public void testGetByID(){
        System.out.println(CustomerService.getCustomerById(1));
        //Customer{id=0, name='汪峰', email='wf@126.com', date=2010-02-02}
    }
}
