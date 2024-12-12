package com.ali.dao;

import com.ali.model.Item;

public interface ProductDao {
    public Item getProductById(String productId);
}
