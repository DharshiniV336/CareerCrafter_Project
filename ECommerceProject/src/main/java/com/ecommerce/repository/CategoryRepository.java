package com.ecommerce.repository;

import com.ecommerce.enums.Name;
import com.ecommerce.mapper.CategoryMapper;
import com.ecommerce.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;
    private final CategoryMapper categoryMapper;
    public CategoryRepository(JdbcTemplate jdbcTemplate, CategoryMapper categoryMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryMapper = categoryMapper;
    }

    public Category findOrSave(Category category) {
        String sql = "select * from category where name=?";
        List<Category> existing = jdbcTemplate.query(sql, categoryMapper, category.getName().toString());

        if (!existing.isEmpty()) {
            return existing.get(0);
        }

        category.setId((int) (Math.random() * 10000));
        String insertSql = "insert into category values(?,?,?)";
        Object[] values=new Object[]{category.getId(),category.getName().toString(),category.getDescription()};
        jdbcTemplate.update(insertSql,values);
        return category;
    }
}
