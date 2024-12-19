package de.tjorven.servermanagmenttool.beans;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component
public class DatabaseBean {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTables() {
        PreparedStatementCreator preparedStatementCreator = connection -> {
            PreparedStatement createTable = connection.prepareStatement("CREATE TABLE IF NOT EXISTS songs (id INT, name VARCHAR(255), artist INT, path VARCHAR(255), duration INT)");
            return createTable;
        };
        jdbcTemplate.update(preparedStatementCreator);
    }
}
