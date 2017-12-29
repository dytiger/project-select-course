package org.forten;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class JDBCDao  {
    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public <T> List<T> query(String sql, RowMapper<T> rm){
        return jdbcTemplate.query(sql,rm);
    }

    public <T> List<T> query(String sql, Map<String ,Object> params, RowMapper<T> rm){
        return jdbcTemplate.query(sql,params,rm);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
