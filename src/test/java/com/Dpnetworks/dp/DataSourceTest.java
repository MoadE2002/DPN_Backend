package com.Dpnetworks.dp;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataSourceTest {

    @Qualifier("firstDataSource")
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
