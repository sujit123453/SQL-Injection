package com.blueThink.SQLi.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class VulnerableLoginService {
    private final JdbcTemplate jdbcTemplate;
    public VulnerableLoginService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    // Builds an intentionally unsafe SQL query
    public String buildUnsafeQuery(
            String userName,
            String password) {

        return "SELECT * FROM user WHERE user_name = '"
                + userName
                + "' AND password = '"
                + password
                + "'";
    }

    // Executes the query against your local practice database
    public boolean executeUnsafeQuery(String userName, String password){
        String sql = buildUnsafeQuery(userName, password);
        System.out.println("Execute SQL"+ sql);
        return !jdbcTemplate.queryForList(sql).isEmpty();
    }
}
