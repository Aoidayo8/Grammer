package com.service;

import com.DAO.CustomerDao;
import com.bean.Customer;
import org.junit.Test;

public class CustomerService {
    @Test
    public static Customer getCustomerById(int id){
        CustomerDao customerDao=new CustomerDao();
        String sql="select `name`,`email`,`birth` as `date` from customers where `id` = ?";
        return  customerDao.queryForCustomers(sql, id);

    }
    public static Customer getCustomerByName(String name){
        CustomerDao customerDao=new CustomerDao();
        String sql="select `name`,`email`,`birth` as `date` from customers where `name` = ?";
        //注意sql字段到java字段的反射;
        return customerDao.queryForCustomers(sql,name);
    }
}
