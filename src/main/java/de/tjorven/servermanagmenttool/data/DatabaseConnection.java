package de.tjorven.servermanagmenttool.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

@Component
public class DatabaseConnection {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseConnection(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setUpDatabase() {
        jdbcTemplate.execute("CREATE DATABASE IF NOT EXISTS Music;");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Artists (\n" +
                "     ArtistID int NOT NULL AUTO_INCREMENT,\n" +
                "     LastName varchar(255) NOT NULL,\n" +
                "     FirstName varchar(255),\n" +
                "     PRIMARY KEY (ArtistID)\n" +
                ");");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS Songs (\n" +
                "     SongID int NOT NULL AUTO_INCREMENT,\n" +
                "     SongName varchar(255) NOT NULL,\n" +
                "     Duration int NOT NULL,\n" +
                "     ArtistID int NOT NULL,\n" +
                "     PRIMARY KEY (SongID),\n" +
                "     FOREIGN KEY (ArtistID) REFERENCES artists(ArtistID)\n" +
                ");");
    }
}
