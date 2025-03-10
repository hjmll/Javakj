package com.hjm.ex3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * @Author: hjm
 * @Date: 2025/03/10/10:40
 * @Description:
 */
public class ConnectionHandler implements InvocationHandler {
    private Connection realConnection;
    private DBCPDataSource dataSource;
    public ConnectionHandler(Connection connection, DBCPDataSource dbcpDataSource) {
        this.realConnection = connection;
        this.dataSource = dbcpDataSource;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 拦截close方法 将连接放入连接池
        if ("close".equals(method.getName())){
            dataSource.releaseConnection(realConnection);
            return null;
        }
        return method.invoke(realConnection,args);
    }
}
