package com.hjm.ex3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: hjm
 * @Date: 2025/03/10/10:35
 * @Description:
 */
public abstract class DataSource {

    public abstract Connection getConnection() throws SQLException;

}
