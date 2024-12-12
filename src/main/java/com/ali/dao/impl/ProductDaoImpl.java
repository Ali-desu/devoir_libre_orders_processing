package com.ali.dao.impl;

import com.ali.dao.ProductDao;
import com.ali.model.Item;
import com.ali.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductDaoImpl implements ProductDao {

    @Override
    public Item getProductById(String productId) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new Item(
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        0,
                        rs.getDouble("price"),
                        0
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}