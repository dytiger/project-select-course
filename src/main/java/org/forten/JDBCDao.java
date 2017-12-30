package org.forten;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCDao {
    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public <T> List<T> query(String sql, RowMapper<T> rm){
        return jdbcTemplate.query(sql,rm);
    }

    public <T> List<T> query(String sql, Map<String ,Object> params, RowMapper<T> rm){
        return jdbcTemplate.query(sql,params,rm);
    }

    public int executeUpdate(String sql,Map<String ,Object> params){
        return jdbcTemplate.update(sql,params);
    }

    public int executeUpdate(String sql){
        return executeUpdate(sql,new HashMap<>(0));
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
}
