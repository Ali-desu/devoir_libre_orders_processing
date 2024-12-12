package com.ali.dao;

import com.ali.model.Address;
import com.ali.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    Customer getCustomerById(String customerId);
    List<Customer> getAllCustomers();
    Address mapAddress(ResultSet rs) throws SQLException;
}
