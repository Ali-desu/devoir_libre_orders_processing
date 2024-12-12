package com.ali.service;

import com.ali.dao.impl.CustomerDaoImpl;
import com.ali.model.Customer;

public class CustomerService {

    private final CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();

    public boolean validateCustomer(String customerId) {
        Customer customer = customerDaoImpl.getCustomerById(customerId);
        return customer != null;
    }

    public Customer getCustomerDetails(String customerId) {
        return customerDaoImpl.getCustomerById(customerId);
    }
}
