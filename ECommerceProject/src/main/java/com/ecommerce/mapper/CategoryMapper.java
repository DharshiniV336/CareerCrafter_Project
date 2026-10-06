package com.ecommerce.mapper;

import com.ecommerce.enums.Name;
import com.ecommerce.model.Category;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class CategoryMapper implements RowMapper<Category> {
    @Nullable
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Category(
                rs.getInt("id"),
                Name.valueOf(rs.getString("name").trim().toUpperCase()),
                rs.getString("description"));
    }
}
