package com.hjm.ex3;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hjm
 * @Date: 2025/03/10/10:37
 * @Description:
 */
public class DBCPDataSource extends DataSource{
    private String url;
    private String username;
    private String password;
    private List<Connection> connectionPool;

    public DBCPDataSource(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.connectionPool = new ArrayList<>();
    }
    @Override
    public Connection getConnection() throws SQLException {
        if (!connectionPool.isEmpty()){
            Connection connection = connectionPool.remove(0);
            return (Connection) Proxy.newProxyInstance(
                    DBCPDataSource.class.getClassLoader(),
                    new Class<?> []{Connection.class},
                    new ConnectionHandler(connection,this));

        }
        Connection realConnection = DriverManager.getConnection(url, username, password);
        return (Connection) Proxy.newProxyInstance(
                DBCPDataSource.class.getClassLoader(),
                new Class<?> []{Connection.class},
                new ConnectionHandler(realConnection,this));
    }

    public void releaseConnection(Connection connection){
        if (connection != null){
            connectionPool.add(connection);
        }
    }
}
