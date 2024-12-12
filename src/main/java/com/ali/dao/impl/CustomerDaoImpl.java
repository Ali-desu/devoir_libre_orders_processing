package com.ali.dao.impl;

import com.ali.dao.CustomerDao;
import com.ali.model.Address;
import com.ali.model.Customer;
import com.ali.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public Customer getCustomerById(String customerId) {
        String query = "SELECT * FROM customers c JOIN addresses a ON c.address_id = a.address_id WHERE c.customer_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customerId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Address address = mapAddress(rs);
                    return new Customer(
                            rs.getString("customer_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            address
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        String query = "SELECT * FROM customers c JOIN addresses a ON c.address_id = a.address_id";
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                Address address = mapAddress(rs);
                System.out.println(address);
                customers.add(new Customer(
                        rs.getString("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        address
                ));
                System.out.println(customers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Address mapAddress(ResultSet rs) throws SQLException {
        return new Address(
                rs.getString("street"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("postal_code"),
                rs.getString("country")
        );
    }
}
