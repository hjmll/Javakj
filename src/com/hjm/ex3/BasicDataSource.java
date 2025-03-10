package com.hjm.ex3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: hjm
 * @Date: 2025/03/10/10:37
 * @Description:
 */
public class BasicDataSource extends DataSource{

    private String url;
    private String username;
    private String password;

    public BasicDataSource(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
