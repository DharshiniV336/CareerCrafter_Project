package com.ecommerce.repository;

import com.ecommerce.dto.VendorCount;
import com.ecommerce.exception.InvalidProductID;
import com.ecommerce.mapper.ProductJoinedRowMapper;
import com.ecommerce.mapper.VendorProductCount;
import com.ecommerce.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final ProductJoinedRowMapper productJoinedRowMapper;
    private final VendorProductCount vendorProductCount;

    public ProductRepository(JdbcTemplate jdbcTemplate, ProductJoinedRowMapper productJoinedRowMapper, VendorProductCount vendorProductCount) {
        this.jdbcTemplate = jdbcTemplate;
        this.productJoinedRowMapper = productJoinedRowMapper;
        this.vendorProductCount = vendorProductCount;
    }

    public void save(Product product) {
        String sql="insert into product values(?,?,?,?,?,?)";
        Object[] values=new Object[]{product.getId(),product.getName(),product.getPrice(),product.getStockQuantity(),product.getCategory().getId(),product.getVendor().getId()};
        jdbcTemplate.update(sql,values);
    }

    public Product findById(Long id) {
        String sql= """
               
                SELECT p.id AS product_id,
                                  p.name AS product_name,
                                  p.price,
                                  p.stock_quantity AS stock_quantity,
                                  c.id AS category_id,
                                  c.name AS category_name,
                                  c.description AS description,
                                  v.id AS vendor_id,
                                  v.name AS vendor_name,
                                  v.email AS email
                           FROM product p
                           JOIN category c ON c.id = p.category_id
                           JOIN vendor v ON v.id = p.vendor_id
                           WHERE p.id = ?
                """;
        try {
            return jdbcTemplate.queryForObject(sql,productJoinedRowMapper, id);
        }catch (EmptyResultDataAccessException e){
            throw new InvalidProductID("The given Product ID was Invalid please enter the correct Product ID.");
        }
    }

    public void updateStock(Long id, int newQuantity) {
        String sql="update product set stock_quantity=? where id=?";
        Object[] values=new Object[]{newQuantity,id};
        int rowUpdate=jdbcTemplate.update(sql,values);
        if(rowUpdate==0){
            throw new InvalidProductID("The given Product ID was Invalid please enter the correct Product ID.");
        }
    }

    public Map<String, Integer> countProductByVendor() {
        String sql= """
                select v.name as vendor_name,count(p.id) as product_count
                from vendor v
                join product p on p.vendor_id=v.id
                group by v.id,v.name
                """;
        List<VendorCount> list= jdbcTemplate.query(sql,vendorProductCount);
        Map<String,Integer> map=new LinkedHashMap<>();
        for(VendorCount vendorCount:list){
            map.put(vendorCount.vendorName(),vendorCount.productCount());
        }return map;
    }
}
