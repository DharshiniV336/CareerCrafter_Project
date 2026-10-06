package com.ecommerce.mapper;

import com.ecommerce.dto.VendorCount;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class VendorProductCount implements RowMapper<VendorCount> {
    @Nullable
    @Override
    public VendorCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new VendorCount(
                rs.getString("vendor_name"),
                rs.getInt("product_count")
        );
    }
}
