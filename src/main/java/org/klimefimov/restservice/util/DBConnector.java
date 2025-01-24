package org.klimefimov.restservice.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;


public class DBConnector {
    private static HikariDataSource dataSource;
    private static final HikariConfig HIKARI_CONFIG = new HikariConfig();
    private static final Properties DB_PROPERTIES = Util.getDbProperties();

    static {
        configureDB();
        dataSource = new HikariDataSource(HIKARI_CONFIG);
        initializeDB();
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    private static void initializeDB() {
        List<String> schema = Util.getInitialSchemaList();
        try(Statement statement = DBConnector.getConnection().createStatement()) {
            for (String query : schema) {
                statement.execute(query);
            }
        } catch (SQLException e) {e.printStackTrace();}
    }

    private static void configureDB() {
        HIKARI_CONFIG.setJdbcUrl(DB_PROPERTIES.getProperty("db.url"));
        HIKARI_CONFIG.setUsername(DB_PROPERTIES.getProperty("db.username"));
        HIKARI_CONFIG.setPassword(DB_PROPERTIES.getProperty("db.password"));
        HIKARI_CONFIG.setDriverClassName(DB_PROPERTIES.getProperty("db.driver"));
    }

}
