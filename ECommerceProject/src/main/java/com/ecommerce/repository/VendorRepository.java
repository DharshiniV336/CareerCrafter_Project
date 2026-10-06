package com.ecommerce.repository;

import com.ecommerce.mapper.VendorMapper;
import com.ecommerce.model.Vendor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VendorRepository {
    private final JdbcTemplate jdbcTemplate;
    private final VendorMapper vendorMapper;

    public VendorRepository(JdbcTemplate jdbcTemplate, VendorMapper vendorMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.vendorMapper = vendorMapper;
    }

    public Vendor findOrSave(Vendor vendor) {
        String sql = "select * from vendor where email=?";
        List<Vendor> existing = jdbcTemplate.query(sql, vendorMapper , vendor.getEmail().trim());
        if (!existing.isEmpty()) {
            return existing.get(0);
        }

        vendor.setId((int) (Math.random() * 10000));
        String insertSql = "insert into vendor values(?,?,?)";
        Object[] values=new Object[]{vendor.getId(),vendor.getName(),vendor.getEmail()};
        jdbcTemplate.update(insertSql,values);
        return vendor;
    }
}
