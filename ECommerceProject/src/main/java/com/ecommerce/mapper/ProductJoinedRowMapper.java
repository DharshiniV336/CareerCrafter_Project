package com.ecommerce.mapper;

import com.ecommerce.enums.Name;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.model.Vendor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ProductJoinedRowMapper implements RowMapper<Product> {
    @Nullable
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product=new Product();
        product.setId(rs.getLong("product_id"));
        product.setName(rs.getString("product_name"));
        product.setPrice(rs.getDouble("price"));
        product.setStockQuantity(rs.getInt("stock_quantity"));

        Category category=new Category();

        category.setId(rs.getInt("category_id"));
        category.setName(Name.valueOf(rs.getString("category_name").toUpperCase().trim()));
        category.setDescription(rs.getString("description"));

        Vendor vendor = new Vendor();

        vendor.setId(rs.getInt("vendor_id"));
        vendor.setName(rs.getString("vendor_name"));
        vendor.setEmail(rs.getString("email"));

        product.setCategory(category);
        product.setVendor(vendor);

        return product;
    }
}
