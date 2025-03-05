package com.hjm.ex2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hjm
 * @Date: 2025/03/05/10:27
 * @Description: JDBCTemplate
 */
public class JDBCTemplate {
    public <T> T querForObject(String sql, Object[] params, Class<T> clazz){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DataBaseUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            if (params != null){
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<String> columnNames = new ArrayList<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            if (rs.next()) {
                T obj = clazz.getDeclaredConstructor().newInstance();
                for (String columnName : columnNames) {
                    Field field = null;
                    try {
                        field = clazz.getDeclaredField(columnName);
                    } catch (NoSuchFieldException e) {
                        continue;
                    }
                    String setMethodName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method setMethod = clazz.getMethod(setMethodName, field.getType());
                    Object value = rs.getObject(columnName);
                    setMethod.invoke(obj, value);
                }
                return obj;
            }

        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseUtil.closeConnection(rs, pstmt, conn);
        }
        return null;
    }
}
